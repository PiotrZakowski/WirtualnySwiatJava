package wirtualny_swiat;

import java.awt.Color;

public class czlowiek extends zwierze 
{
//Pola
    //Public
    //Protected
    //Private
    private int tury_umiejetnosc;
            //wyjasnienie tury_umiejetnosc
            //0 -> umiejetnosc gotowa do uzycia
            //0+1 => 1 -> umiejetnosc wlaczona
            //1,2,3,4,5 -> umiejetnosc w uzyciu
            //6,7,8,9,10 -> cooldown
            //10+1 => 0 -> znow gotowy do uzycia
    
//Metody
    //Abstract
    @Override void akcja()
    {
	int ilosc_tur_umiejetnosci = this.zwroc_ilosc_tur();
	if (ilosc_tur_umiejetnosci > 0)
	{
            ilosc_tur_umiejetnosci+=1;
            if(ilosc_tur_umiejetnosci==6) this.ref_swiat.swiat.przycisk_umiejetnosci.setBackground(Color.RED);
            if (ilosc_tur_umiejetnosci == 11)
            {
		this.ustaw_ilosc_tur(0);
		this.ustaw_kolizje_specjalna(false);
		ilosc_tur_umiejetnosci = 0;
                this.ref_swiat.swiat.przycisk_umiejetnosci.setBackground(Color.WHITE);
            }
            else this.ustaw_ilosc_tur(ilosc_tur_umiejetnosci);
	}
	int polozenie_x=this.zwroc_polozenie_x();
	int polozenie_y=this.zwroc_polozenie_y();
	this.ustaw_pop_polozenie_x(polozenie_x);
	this.ustaw_pop_polozenie_y(polozenie_y);
        String klawisz_ruchu = this.ref_swiat.swiat.klawisz_dla_czlowieka_kierunek_ruchu;
        boolean klawisz_umiejetnosci = this.ref_swiat.swiat.klawisz_dla_czlowieka_umiejetnosc;
	if (klawisz_umiejetnosci == true)
	{
		if (ilosc_tur_umiejetnosci == 0)
		{
			this.ustaw_kolizje_specjalna(true);
			this.ustaw_ilosc_tur(1);
                        this.ref_swiat.swiat.przycisk_umiejetnosci.setBackground(Color.BLUE);
		} 
	}
	switch (klawisz_ruchu) // !!! Zdecydowac czy niedozwolony ruch poza plansze to ruch stracony czy tez zrobic petle...
	{
	case "GORA":
		if (polozenie_y > 0)
		{
			this.ustaw_polozenie_y(polozenie_y - 1);
			polozenie_y -= 1;
		}; break;
	case "DOL":
		if (polozenie_y < this.ref_swiat.rozmiar_pola_pion - 1)
		{
			this.ustaw_polozenie_y(polozenie_y + 1);
			polozenie_y += 1;
		}; break;
	case "LEWO":
		if (polozenie_x > 0)
		{
			this.ustaw_polozenie_x(polozenie_x - 1);
			polozenie_x -= 1;
		}; break;
	case "PRAWO":
		if (polozenie_x < this.ref_swiat.rozmiar_pola_poziom - 1)
		{
			this.ustaw_polozenie_x(polozenie_x + 1);
			polozenie_x += 1;
		}; break;
	}
	//this.rysowanie();
	this.kolizja();
    }
    
    @Override void kolizja() //specjalna kolizja czlowieka
    {
	if (this.sprawdz_kolizja_specjalna()) // umiejetnosc wlaczona -> kolizja specjalna
	{
            int liczba_organizmow, x_czlowieka, y_czlowieka, x_kolidujacego, y_kolidujacego;
            liczba_organizmow = this.ref_swiat.zwroc_liczbe_organizmow();
            x_czlowieka = this.zwroc_polozenie_x();
            y_czlowieka = this.zwroc_polozenie_y();
            for (int i = 0; i < liczba_organizmow; i++)
            {
		x_kolidujacego = this.ref_swiat.organizmy[i].zwroc_polozenie_x();
		y_kolidujacego = this.ref_swiat.organizmy[i].zwroc_polozenie_y();
		if (x_czlowieka == x_kolidujacego && y_czlowieka == y_kolidujacego && this != this.ref_swiat.organizmy[i])
		{
                    int sila_czlowieka = this.zwroc_sile();
                    int sila_kolidujacego = this.ref_swiat.organizmy[i].zwroc_sile();
                    if(sila_kolidujacego > sila_czlowieka) // organizm silniejszy -> ucieczka czlowieka
                    {
			int[] opcje_ruchu = { 1, 1, 1, 1 }; //1 - mozna przejsc, 0 - nie mozna przejsc
			this.zwiad(opcje_ruchu, x_czlowieka, y_czlowieka, 1);
			this.ucieczka(opcje_ruchu, x_czlowieka, y_czlowieka);
                    }
                    else // organizm slabszy -> kolizja zwyczajna
                    {
                        super.kolizja();
                    }
		}
            }
	}
        else // umiejetnosc wylaczona -> kolizja zwyczajna
        {
            super.kolizja();
        }
    }
    
    //Public
    public czlowiek(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('C');
	this.ustaw_sile(5);
	this.ustaw_inicjatywe(4);
	this.ustaw_ilosc_tur(0);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(255,229,180); //Cielisty #FFE5B4 
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow()-1] = this;
	//this->ref_swiat.organizmy[this->ref_swiat.zwroc_odpowiedni_indeks(this->zwroc_inicjatywe())] = this;
    }
    
    public czlowiek(czlowiek oryginal)
    {
        super(oryginal);
        this.ustaw_ilosc_tur(oryginal.zwroc_ilosc_tur());
    }
    //Protected
    //Private
    private int zwroc_ilosc_tur()
    {
	return this.tury_umiejetnosc;
    }
    
    private void ustaw_ilosc_tur(int wartosc)
    {
	this.tury_umiejetnosc = wartosc;
    }
}
