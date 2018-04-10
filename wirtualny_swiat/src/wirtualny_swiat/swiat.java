package wirtualny_swiat;

import java.awt.Color;

public class swiat {
//Pola
    //Public
    //Protected
    protected organizm[] organizmy;
    protected Wirtualny_swiat swiat;
    protected int zaswiaty=-1;
    protected int rozmiar_pola_poziom;
    protected int rozmiar_pola_pion;
    protected int liczba_organizmow;
    //Private
    private int liczba_tur;
    private final Color pusty_kwadrat = new Color(255,255,255);

//Metody
    //Public
    public swiat(int szerokosc_planszy, int wysokosc_planszy)
    {
        this.liczba_organizmow = 0;
	this.organizmy = new organizm[this.liczba_organizmow];
        this.rozmiar_pola_pion=szerokosc_planszy;
        this.rozmiar_pola_poziom=wysokosc_planszy;
    }
    
    public swiat(swiat oryginal)
    {
        this.liczba_organizmow = oryginal.liczba_organizmow;
        System.arraycopy(oryginal.organizmy, 0, this.organizmy, 0, this.liczba_organizmow);
        this.rozmiar_pola_pion=oryginal.rozmiar_pola_pion;
        this.rozmiar_pola_poziom=oryginal.rozmiar_pola_poziom;
    }
    //protected
    protected int zwroc_liczbe_organizmow()
    {
	return this.liczba_organizmow;
    }
    
    protected void ustaw_liczbe_organizmow(int wartosc)
    {
	this.liczba_organizmow = wartosc;
    }
    
    protected int zwroc_szerokosc_planszy()
    {
        return this.rozmiar_pola_poziom;
    }
    
    protected int zwroc_wysokosc_planszy()
    {
        return this.rozmiar_pola_pion;
    }
    
    protected void ustaw_swiat(Wirtualny_swiat swiat_gry)
    {
        this.swiat = swiat_gry;
    }
    
    protected void wykonajTure()
    {
	this.zwieksz_wiek();
        this.liczba_tur++;
        System.out.println("----------- Tura nr "+this.liczba_tur+" -----------");
        this.sortowanie_organizmow();
	int liczba_petli = this.liczba_organizmow;
	for (int i = 0; i < liczba_petli; i++)
	{
		if(this.organizmy[i].sprawdz_czy_zyje()) this.organizmy[i].akcja();
	}
	this.zredukuj_tablice_organizmow();
        this.rysujSwiat();
        this.swiat.klawisz_dla_czlowieka_umiejetnosc=false;
    }
    
    protected void powiekszenie_swiata()
    {
	organizm[] organizmy_nowa = new organizm[this.liczba_organizmow + 1];
        System.arraycopy(this.organizmy, 0, organizmy_nowa, 0, this.liczba_organizmow);
        /*
        for (int i = 0; i < this.liczba_organizmow; i++)
	{
		organizmy_nowa[i] = this.organizmy[i];
	}
        */
	this.liczba_organizmow += 1;
	//delete[] this->organizmy; //??? Sprawdzic czy garbage collector da rade
	this.organizmy = organizmy_nowa;
    }
    
    protected void dodaj_potomka( String nazwa_klasy, swiat swiat, int x_potomka, int y_potomka)
    {
	if ("wilk".equals(nazwa_klasy))
	{
		wilk potomek = new wilk(swiat,x_potomka,y_potomka);
		return;
	}
	if ("owca".equals(nazwa_klasy))
	{
		owca potomek = new owca(swiat, x_potomka, y_potomka);
		return;
	}
	if ("lis".equals(nazwa_klasy))
	{
		lis potomek = new lis(swiat, x_potomka, y_potomka);
		return;
	}
	if ("zolw".equals(nazwa_klasy))
	{
		zolw potomek = new zolw(swiat, x_potomka, y_potomka);
		return;
	}
	if ("antylopa".equals(nazwa_klasy))
	{
		antylopa potomek = new antylopa(swiat, x_potomka, y_potomka);
		return;
	}
	if ("trawa".equals(nazwa_klasy))
	{
		trawa potomek = new trawa(swiat, x_potomka, y_potomka);
		return;
	}
	if ("mlecz".equals(nazwa_klasy))
	{
		mlecz potomek = new mlecz(swiat, x_potomka, y_potomka);
		return;
	}
	if ("guarana".equals(nazwa_klasy))
	{
		guarana potomek = new guarana(swiat, x_potomka, y_potomka);
		return;
	}
	if ("wilcze_jagody".equals(nazwa_klasy))
	{
		wilcze_jagody potomek = new wilcze_jagody(swiat, x_potomka, y_potomka);
	}
    }
    
    protected void rysujSwiat()
    {
        int wsp_y, wsp_x;
        Color kolor_organizmu;
        
        for(int a=0;a<this.swiat.ZADANA_ROZMIAR_PION; a++)
        {
            for (int b=0; b<this.swiat.ZADANA_ROZMIAR_POZIOM; b++)
            {
                this.swiat.plansza[a][b].setBackground(this.pusty_kwadrat);
            }
        }
        
        for(int i=0; i<this.liczba_organizmow; i++)
        {
            wsp_y = this.organizmy[i].zwroc_polozenie_y();
            wsp_x = this.organizmy[i].zwroc_polozenie_x();
            kolor_organizmu = this.organizmy[i].zwroc_kolor();
            this.swiat.plansza[wsp_y][wsp_x].setBackground(kolor_organizmu);
        }
    };
    
    protected void zredukuj_tablice_organizmow()
    {
	int liczba_zywych = 0;
	int liczba_wszystkich_organizmow = this.liczba_organizmow;
	for (int i = 0; i < liczba_wszystkich_organizmow; i++)
	{
		if (this.organizmy[i].sprawdz_czy_zyje()) liczba_zywych++;
	}
	organizm[] nowa_tablica_organizmow = new organizm[liczba_zywych];
	int j = 0;
	for (int k = 0; k < liczba_wszystkich_organizmow; k++)
	{
		if (this.organizmy[k].sprawdz_czy_zyje())
		{
			nowa_tablica_organizmow[j] = this.organizmy[k];
			j++;
		}
	}
	//delete[] this->organizmy; ??? Sprawdzic garbage collector
	this.organizmy = nowa_tablica_organizmow;
	this.liczba_organizmow = liczba_zywych;
    }
    
    //private
    private void sortowanie_organizmow()
    {
        organizm pomocnicza;
        boolean czy_zaszla_zmiana;

        for (int i=0; i<this.liczba_organizmow-1; ++i) 
        {
            czy_zaszla_zmiana=false;
            for (int j=0; j<this.liczba_organizmow-1-i; j++)
            { 
                if ((this.organizmy[j+1].inicjatywa < this.organizmy[j].inicjatywa) || (this.organizmy[j+1].inicjatywa == this.organizmy[j].inicjatywa && this.organizmy[j+1].wiek < this.organizmy[j].wiek ))   //porównanie sąsiądów
                {  
                    pomocnicza = this.organizmy[j];      
                    this.organizmy[j] = this.organizmy[j+1];
                    this.organizmy[j+1] = pomocnicza;
                    czy_zaszla_zmiana=true;
                }
            }
            if(czy_zaszla_zmiana == false) break;
        }
    }
    
    private void zwieksz_wiek()
    {
	int aktualny_wiek;
	for (int i = 0; i < this.liczba_organizmow; i++)
	{
		aktualny_wiek=this.organizmy[i].zwroc_wiek();
		this.organizmy[i].ustaw_wiek(aktualny_wiek + 1);
	}
    }
}
