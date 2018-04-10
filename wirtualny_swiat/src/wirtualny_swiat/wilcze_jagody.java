package wirtualny_swiat;

import java.awt.Color;

public class wilcze_jagody extends roslina
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Abstract
    @Override void kolizja()
    {
	int liczba_organizmow, x_wilczej_jagody, y_wilczej_jagody, x_kolidujacego, y_kolidujacego;
	liczba_organizmow = ref_swiat.zwroc_liczbe_organizmow();
	x_wilczej_jagody = this.zwroc_polozenie_x();
	y_wilczej_jagody = this.zwroc_polozenie_y();
	for (int i = 0; i < liczba_organizmow; i++)
	{
            x_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_x();
            y_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_y();
            if (x_wilczej_jagody == x_kolidujacego && y_wilczej_jagody == y_kolidujacego && this != this.ref_swiat.organizmy[i])
            {
		this.ref_swiat.organizmy[i].ustaw_czy_zyje(false);
		this.ref_swiat.organizmy[i].ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ref_swiat.organizmy[i].ustaw_polozenie_y(this.ref_swiat.zaswiaty);
		this.ustaw_czy_zyje(false); //Wilcza jagoda tez ginie
		this.ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                //this.ref_swiat.swiat.plansza[y_wilczej_jagody][x_wilczej_jagody].setBackground(this.ref_swiat.pusty_kwadrat); //??? Rysowac?
		System.out.println("("+x_wilczej_jagody+","+y_wilczej_jagody+") "+this.ref_swiat.organizmy[i].getClass().getSimpleName()+" zjada wilcze jagody. Ginie wilcza jagoda i "+this.ref_swiat.organizmy[i].getClass().getSimpleName()+".");
                break;
            }
	}
    }
    //Public
    public wilcze_jagody(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('j');
	this.ustaw_sile(99);
	this.ustaw_inicjatywe(0);
	this.ustaw_kolizje_specjalna(true);
        this.kolor = new Color(102,0,102); //Fiolet (jagodowy) #660066
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
