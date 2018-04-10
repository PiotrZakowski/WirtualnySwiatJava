package wirtualny_swiat;

import java.awt.Color;
import java.util.Random;

public class antylopa extends zwierze
{
//Pola
    //Public
    //Protected
    //Private

//Metody
    //Abstract
    @Override void akcja()
    {
	int x_antylopy, y_antylopy;
	x_antylopy = this.zwroc_polozenie_x();
	y_antylopy = this.zwroc_polozenie_y();
        int szerokosc_planszy=this.ref_swiat.zwroc_szerokosc_planszy();
        int wysokosc_planszy=this.ref_swiat.zwroc_wysokosc_planszy();
	this.ustaw_pop_polozenie_x(x_antylopy);
	this.ustaw_pop_polozenie_y(y_antylopy);
        //this.ref_swiat.swiat.plansza[y_antylopy][x_antylopy].setBackground(this.ref_swiat.pusty_kwadrat); //??? Rysowac?
	Random rand = new Random();
        int losowa = rand.nextInt(4);  // !!! Zdecydowac czy niedozwolony ruch poza plansze to ruch stracony czy tez zrobic petle...
	switch (losowa)
	{
	case 0: //Ruch w gore 
		if (y_antylopy > 1){ y_antylopy -= 2; this.ustaw_polozenie_y(y_antylopy); }; break;
	case 1: //Ruch w prawo
		if (x_antylopy < szerokosc_planszy - 2){ x_antylopy += 2; this.ustaw_polozenie_x(x_antylopy); }; break;
	case 2: //Ruch w dol
		if (y_antylopy < wysokosc_planszy - 2){ y_antylopy += 2; this.ustaw_polozenie_y(y_antylopy); }; break;
	case 3: //Ruch w lewo
		if (x_antylopy > 1){ x_antylopy -= 2; this.ustaw_polozenie_x(x_antylopy); }; break;
	}
	this.rysowanie();
	this.ustaw_kolizje_specjalna(false);
	this.kolizja();
	this.ustaw_kolizje_specjalna(true);
    }
    
    @Override void kolizja()
    {
	int liczba_organizmow, x_antylopy, y_antylopy, x_kolidujacego, y_kolidujacego;
	liczba_organizmow = ref_swiat.zwroc_liczbe_organizmow();
	x_antylopy = this.zwroc_polozenie_x();
	y_antylopy = this.zwroc_polozenie_y();
	for (int i = 0; i < liczba_organizmow; i++)
	{
            x_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_x();
            y_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_y();
            if (x_antylopy == x_kolidujacego && y_antylopy == y_kolidujacego && this != this.ref_swiat.organizmy[i])
            {   
		if(this.getClass()==ref_swiat.organizmy[i].getClass())
                {
                    this.rozmnazanie(this.ref_swiat, x_kolidujacego, y_kolidujacego);
		}
		else
		{
                    Random rand = new Random();
                    int losowa = rand.nextInt(2);
                    int[] opcje_ruchu = { 1, 1, 1, 1 }; //1 - mozna przejsc, 0 - nie mozna przejsc
                    if (losowa == 0)
                    {
                        this.zwiad(opcje_ruchu, x_antylopy, y_antylopy, 1);
                        if (opcje_ruchu[0] == 0 && opcje_ruchu[1] == 0 && opcje_ruchu[2] == 0 && opcje_ruchu[3] == 0) losowa = 1; //Nie ma mozliwosci do ucieczki
                    }
                    if (losowa == 0)
                    {
			this.ucieczka(opcje_ruchu, x_antylopy, y_antylopy);
			this.ref_swiat.organizmy[i].rysowanie();
                    }
                    else
                    {
                        this.ustaw_kolizje_specjalna(false);
                        super.kolizja();
                        this.ustaw_kolizje_specjalna(true);
                    }
                }
            }
        }
    }
    //Public
    public antylopa(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('A');
	this.ustaw_sile(4);
	this.ustaw_inicjatywe(4);
	this.ustaw_kolizje_specjalna(true);
        this.kolor = new Color(255,192,0); //Pomarańczowy (marchewkowy) #FFC000
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
