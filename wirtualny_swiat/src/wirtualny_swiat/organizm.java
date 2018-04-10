package wirtualny_swiat; 

import java.awt.Color;
import java.util.Random;

public abstract class organizm {

//Pola
    //Public
    //Protected
    protected boolean czy_zyje;
    protected int inicjatywa;
    protected int sila;
    protected char znak;
    protected int wiek;
    protected int polozenie_x;
    protected int polozenie_y;
    protected swiat ref_swiat;
    protected boolean kolizja_specjalna;
    protected Color kolor;
    //Private
    
//Metody
    //Abstract
    abstract void kolizja();
    abstract void akcja();
    abstract void rysowanie();
    
    //Public
    public organizm(swiat swiat, int wsp_x, int wsp_y)
    {
        this.czy_zyje = true;
	this.wiek = 1;
	this.polozenie_x = wsp_x;
	this.polozenie_y = wsp_y;
        this.ref_swiat=swiat;
    }
    
    public organizm(organizm oryginal)
    {
        this.czy_zyje = oryginal.sprawdz_czy_zyje();
	this.wiek = oryginal.zwroc_wiek();
	this.polozenie_x = oryginal.zwroc_polozenie_x();
	this.polozenie_y = oryginal.zwroc_polozenie_y();
        this.ref_swiat= oryginal.ref_swiat;
    }
    
    //Protected
    protected int zwroc_inicjatywe()
    {
	return this.inicjatywa;
    }

    protected void ustaw_inicjatywe(int wartosc)
    {
	this.inicjatywa = wartosc;
    }

    protected int zwroc_sile()
    {
	return this.sila;
    }

    protected void ustaw_sile(int wartosc)
    {
	this.sila = wartosc;
    }

    protected char zwroc_znak()
    {
	return this.znak;
    }

    protected void ustaw_znak(char wartosc)
    {
	this.znak = wartosc;
    }

    protected int zwroc_wiek()
    {
	return this.wiek;
    }

    protected void ustaw_wiek(int wartosc)
    {
	this.wiek = wartosc;
    }

    protected int zwroc_polozenie_x()
    {
	return this.polozenie_x;
    }

    protected void ustaw_polozenie_x(int wartosc)
    {
	this.polozenie_x = wartosc;
    }

    protected int zwroc_polozenie_y()
    {
	return this.polozenie_y;
    }

    protected void ustaw_polozenie_y(int wartosc)
    {
	this.polozenie_y = wartosc;
    }

    protected void ustaw_czy_zyje(boolean wartosc)
    {
	this.czy_zyje = wartosc;
    }

    protected boolean sprawdz_czy_zyje()
    {
	return this.czy_zyje;
    }

    protected void ustaw_kolizje_specjalna(boolean wartosc)
    {
	this.kolizja_specjalna = wartosc;
    }

    protected boolean sprawdz_kolizja_specjalna()
    {
	return this.kolizja_specjalna;
    }
    
    protected Color zwroc_kolor()
    {
        return this.kolor;
    }
    
    protected boolean czy_zajete(int wsp_x, int wsp_y)
    {
	int wsp_organizmy_x, wsp_organizmy_y, liczba_organizmow = this.ref_swiat.zwroc_liczbe_organizmow();
	for (int i = 0; i < liczba_organizmow; i++)
	{
		wsp_organizmy_x = this.ref_swiat.organizmy[i].zwroc_polozenie_x();
		wsp_organizmy_y = this.ref_swiat.organizmy[i].zwroc_polozenie_y();
		if (wsp_x == wsp_organizmy_x && wsp_y == wsp_organizmy_y) return true;
	}
	return false;
    }
    
    protected void rozmnazanie(swiat swiat, int x_rodzicow, int y_rodzicow)
    {
	int[] opcje_rozmnazania = { 1, 1, 1, 1 };
	this.znajdz_miejsce_dla_potomnego(opcje_rozmnazania, x_rodzicow, y_rodzicow);
	if (opcje_rozmnazania[0] == 1 || opcje_rozmnazania[1] == 1 || opcje_rozmnazania[2] == 1 || opcje_rozmnazania[3] == 1) //Jesli istnieje choc jedno miejsce dla potomka
	{	
            //this->ref_swiat.powiekszenie_swiata();
            int x_potomka, y_potomka, losowa, spr = 0;
            int x_planszy = this.ref_swiat.zwroc_szerokosc_planszy();
            int y_planszy = this.ref_swiat.zwroc_wysokosc_planszy();
            Random rand = new Random();
            while (spr == 0)
            {
                losowa = rand.nextInt(4);
                if (opcje_rozmnazania[losowa] != 0)
                {
                    spr = 1;
                    x_potomka = x_rodzicow;
                    y_potomka = y_rodzicow;
                    switch (losowa)
                    {
			case 0: //Ruch w gore 
				if (y_rodzicow > 0){ y_potomka -= 1; }; break;
			case 1: //Ruch w prawo
				if (x_rodzicow < x_planszy - 1){ x_potomka += 1; }; break;
			case 2: //Ruch w dol
				if (y_rodzicow < y_planszy - 1){ y_potomka += 1; }; break;
			case 3: //Ruch w lewo
				if (x_rodzicow > 0){ x_potomka -= 1; }; break;
                    }
                    this.ref_swiat.dodaj_potomka(this.getClass().getSimpleName(), swiat, x_potomka, y_potomka);
                    System.out.println("("+this.polozenie_x+","+this.polozenie_y+") "+this.getClass().getSimpleName()+" ma dziecko ("+x_potomka+","+y_potomka+").");
                }
            }
	}
    }
    
    protected void walka(int indeks_defensora, int x_oponentow, int y_oponentow)
    {
	int sila_agresora, sila_defensora;
	sila_agresora = this.zwroc_sile();
	sila_defensora = this.ref_swiat.organizmy[indeks_defensora].zwroc_sile();
        //this.ref_swiat.swiat.plansza[y_oponentow][x_oponentow].setBackground(this.ref_swiat.pusty_kwadrat); //??? Rysowac?
	if (sila_agresora == sila_defensora)
	{
		this.ref_swiat.organizmy[indeks_defensora].ustaw_czy_zyje(false);
		this.ref_swiat.organizmy[indeks_defensora].ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ref_swiat.organizmy[indeks_defensora].ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                System.out.println("("+x_oponentow+","+y_oponentow+") "+this.getClass().getSimpleName()+" pokonuje "+this.ref_swiat.organizmy[indeks_defensora].getClass().getSimpleName()+".");
		//this.rysowanie(); //??? Rysowac?
	}
	else
	{
            if (sila_agresora > sila_defensora)
            {
		this.ref_swiat.organizmy[indeks_defensora].ustaw_czy_zyje(false);
		this.ref_swiat.organizmy[indeks_defensora].ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ref_swiat.organizmy[indeks_defensora].ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                System.out.println("("+x_oponentow+","+y_oponentow+") "+this.getClass().getSimpleName()+" pokonuje "+this.ref_swiat.organizmy[indeks_defensora].getClass().getSimpleName()+".");
		//this.rysowanie(); //??? Rysowac?
            }
            else
            {
		this.ustaw_czy_zyje(false);
		this.ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ustaw_polozenie_y(this.ref_swiat.zaswiaty);
		//this.ref_swiat.organizmy[indeks_defensora].rysowanie(); //??? Rysowac?
                System.out.println("("+x_oponentow+","+y_oponentow+") "+this.ref_swiat.organizmy[indeks_defensora].getClass().getSimpleName()+" pokonuje "+this.ref_swiat.organizmy[indeks_defensora].getClass().getSimpleName()+".");
            }
	}
    }
    //Private
    private void znajdz_miejsce_dla_potomnego(int[] notatka, int x_zwiadowcy, int y_zwiadowcy)
    {
	int liczba_organizmow = this.ref_swiat.zwroc_liczbe_organizmow();
	int sila_zwiadowcy = this.zwroc_sile();
        int x_planszy = this.ref_swiat.zwroc_szerokosc_planszy();
        int y_planszy = this.ref_swiat.zwroc_wysokosc_planszy();
	//GORA
	if (this.czy_zajete(x_zwiadowcy, y_zwiadowcy - 1) || y_zwiadowcy == 0)
	{
            notatka[0] = 0;
	}
	//PRAWO
	if (this.czy_zajete(x_zwiadowcy + 1, y_zwiadowcy) || x_zwiadowcy == x_planszy - 1) 
	{
            notatka[1] = 0;
	}
	//DOL
	if (this.czy_zajete(x_zwiadowcy, y_zwiadowcy + 1) || y_zwiadowcy == y_planszy - 1) 
	{
            notatka[2] = 0;
	}
	//LEWO
	if (this.czy_zajete(x_zwiadowcy - 1, y_zwiadowcy) || x_zwiadowcy == 0)
	{
            notatka[3] = 0;
	}
    }
}