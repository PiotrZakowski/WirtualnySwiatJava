package wirtualny_swiat;

import java.util.Random;

public class zwierze extends organizm
{
//Pola
    //Public
    //Protected
    protected int pop_polozenie_x;
    protected int pop_polozenie_y; 
    //Private

//Metody
    //Abstact
    @Override void rysowanie()//??? Rysowac?
    {
        //this.ref_swiat.swiat.plansza[this.polozenie_y][this.pop_polozenie_x].setBackground(this.kolor); 
    }
    
    @Override void akcja()
    {
	int wsp_x, wsp_y;
	wsp_x = this.zwroc_polozenie_x();
	wsp_y = this.zwroc_polozenie_y();
        int szerokosc_planszy=this.ref_swiat.zwroc_szerokosc_planszy();
        int wysokosc_planszy=this.ref_swiat.zwroc_wysokosc_planszy();
	this.ustaw_pop_polozenie_x(wsp_x);
	this.ustaw_pop_polozenie_y(wsp_y);
        //this.ref_swiat.swiat.plansza[polozenie_y][polozenie_x].setBackground(this.ref_swiat.pusty_kwadrat); //??? Rysowac?
        Random rand = new Random();
        int losowa = rand.nextInt(4); 
	switch (losowa)
	{
            case 0: //Ruch w gore 
		if (wsp_y > 0){ wsp_y -= 1; this.ustaw_polozenie_y(wsp_y); }; break;
            case 1: //Ruch w prawo
		if (wsp_x < szerokosc_planszy - 1){ wsp_x += 1; this.ustaw_polozenie_x(wsp_x); }; break;
            case 2: //Ruch w dol
		if (wsp_y < wysokosc_planszy - 1){ wsp_y += 1; this.ustaw_polozenie_y(wsp_y); }; break;
            case 3: //Ruch w lewo
		if (wsp_x > 0){ wsp_x -= 1; this.ustaw_polozenie_x(wsp_x); }; break;
	}
	this.rysowanie();
	this.kolizja();
    }
            
    @Override void kolizja()
    {
	int liczba_organizmow, x_rozpatrywany, y_rozpatrywany, x_kolidujacego, y_kolidujacego;
	liczba_organizmow = this.ref_swiat.zwroc_liczbe_organizmow();
	x_rozpatrywany = this.zwroc_polozenie_x();
	y_rozpatrywany = this.zwroc_polozenie_y();
	for (int i = 0; i < liczba_organizmow; i++)
	{
            x_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_x();
            y_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_y();
            if (x_rozpatrywany == x_kolidujacego && y_rozpatrywany == y_kolidujacego && this!=this.ref_swiat.organizmy[i])
            {
                //!!! Odpowiednik typeid w Javie
		if(this.getClass()==ref_swiat.organizmy[i].getClass())
                {
			this.rozmnazanie(this.ref_swiat, x_kolidujacego, y_kolidujacego);
                }
                else
                {
                    if (this.ref_swiat.organizmy[i].sprawdz_kolizja_specjalna() == true && this.sprawdz_kolizja_specjalna() == false) this.ref_swiat.organizmy[i].kolizja();
                    else this.walka(i, x_kolidujacego, y_kolidujacego);
		}
		break;
            }
	}
    }
            
    //Public
    public zwierze(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
    }
    
    public zwierze(zwierze oryginal)
    {
        super(oryginal);
        this.ustaw_pop_polozenie_x(oryginal.zwroc_pop_polozenie_x());
        this.ustaw_pop_polozenie_y(oryginal.zwroc_pop_polozenie_y());
    }
    //Protected
    protected int zwroc_pop_polozenie_x()
    {
        return this.pop_polozenie_x;
    }
    
    protected void ustaw_pop_polozenie_x(int wartosc)
    {
        this.pop_polozenie_x = wartosc;
    }
    
    protected int zwroc_pop_polozenie_y()
    {
        return this.pop_polozenie_y;
    }
    
    protected void ustaw_pop_polozenie_y(int wartosc)
    {
        this.pop_polozenie_y = wartosc;
    }
    
    protected void ucieczka(int[] opcje_ucieczki, int x_uciekajacego, int y_uciekajacego)
    {
        int losowa, ucieczka = 0;
        int szerokosc_planszy=this.ref_swiat.zwroc_szerokosc_planszy();
        int wysokosc_planszy=this.ref_swiat.zwroc_wysokosc_planszy();
        Random rand = new Random();
	while (ucieczka == 0) // dopoki nie wybierze wolnego miejsca do ucieczki (zawsze takie istnieje - poprzednia pozycja napastnika)
	{    
            losowa = rand.nextInt(4);
            if (opcje_ucieczki[losowa] == 0) continue;
            else
            {
                ucieczka = 1;
                switch (losowa)
                {
                    case 0: //Ruch w gore 
                        if (polozenie_y > 0){ y_uciekajacego -= 1; this.ustaw_polozenie_y(y_uciekajacego); }; break;
                    case 1: //Ruch w prawo
			if (polozenie_x < szerokosc_planszy - 1){ x_uciekajacego += 1; this.ustaw_polozenie_x(x_uciekajacego); }; break;
                    case 2: //Ruch w dol
			if (polozenie_y < wysokosc_planszy - 1){ y_uciekajacego += 1; this.ustaw_polozenie_y(y_uciekajacego); }; break;
                    case 3: //Ruch w lewo
			if (polozenie_x > 0){ x_uciekajacego -= 1; this.ustaw_polozenie_x(x_uciekajacego); }; break;
                }
            }
            this.rysowanie();
            int x_kolidujacego, y_kolidujacego;
            for (int i = 0; i < this.ref_swiat.zwroc_liczbe_organizmow(); i++)
            {
		x_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_x();
		y_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_y();
		if (x_uciekajacego == x_kolidujacego && y_uciekajacego == y_kolidujacego && this != this.ref_swiat.organizmy[i])
		{
                    if(this.getClass()==ref_swiat.organizmy[i].getClass())
                    {
			this.rozmnazanie(this.ref_swiat, x_kolidujacego, y_kolidujacego);
                    }
                    else
                    {
                        this.kolizja();
                    }
		}
            }
	}
    }
    
    protected void zwiad(int[] notatka, int x_zwiadowcy, int y_zwiadowcy, int promien)
    {
	//w notatce jesli notatka[i] == 0 to nie mozna tam pojsc, jesli ==1 to mozna sie tam udac
	int liczba_organizmow = this.ref_swiat.zwroc_liczbe_organizmow();
	int sila_zwiadowcy = this.zwroc_sile();
        int szerokosc_planszy=this.ref_swiat.zwroc_szerokosc_planszy();
        int wysokosc_planszy=this.ref_swiat.zwroc_wysokosc_planszy();
	//GORA
	if (this.czy_zajete(x_zwiadowcy, y_zwiadowcy - promien))
	{
            for (int i = 0; i < liczba_organizmow; i++)
            {
		if (this.ref_swiat.organizmy[i].zwroc_polozenie_x() == x_zwiadowcy && this.ref_swiat.organizmy[i].zwroc_polozenie_y() == y_zwiadowcy - promien)
		{
                    if (this.ref_swiat.organizmy[i].zwroc_sile() > sila_zwiadowcy) notatka[0] = 0;
                    break;
		}
            }
	}
	else
	{
            if (y_zwiadowcy == 0)notatka[0] = 0;
	}
	//PRAWO
	if (this.czy_zajete(x_zwiadowcy + promien, y_zwiadowcy))
	{
            for (int i = 0; i < liczba_organizmow; i++)
            {
		if (this.ref_swiat.organizmy[i].zwroc_polozenie_x() == x_zwiadowcy + promien && this.ref_swiat.organizmy[i].zwroc_polozenie_y() == y_zwiadowcy)
		{
                    if (this.ref_swiat.organizmy[i].zwroc_sile() > sila_zwiadowcy) notatka[1] = 0;
                    break;
		}
            }
	}
	else
	{
            if (x_zwiadowcy == szerokosc_planszy - promien) notatka[1] = 0;
	}
	//DOL
	if (this.czy_zajete(x_zwiadowcy, y_zwiadowcy + promien))
	{
            for (int i = 0; i < liczba_organizmow; i++)
            {
		if (this.ref_swiat.organizmy[i].zwroc_polozenie_x() == x_zwiadowcy && this.ref_swiat.organizmy[i].zwroc_polozenie_y() == y_zwiadowcy + promien)
		{
                    if (this.ref_swiat.organizmy[i].zwroc_sile() > sila_zwiadowcy) notatka[2] = 0;
                    break;
		}
            }
	}
	else
	{
            if (y_zwiadowcy == wysokosc_planszy - promien) notatka[2] = 0;
	}
	//LEWO
	if (this.czy_zajete(x_zwiadowcy - promien, y_zwiadowcy))
	{
            for (int i = 0; i < liczba_organizmow; i++)
            {
		if (this.ref_swiat.organizmy[i].zwroc_polozenie_x() == x_zwiadowcy - promien && this.ref_swiat.organizmy[i].zwroc_polozenie_y() == y_zwiadowcy)
		{
                    if (this.ref_swiat.organizmy[i].zwroc_sile() > sila_zwiadowcy) notatka[3] = 0;
                    break;
		}
            }
	}
	else
	{
            if (x_zwiadowcy == 0) notatka[3] = 0;
	}
    }
    //Private
}
