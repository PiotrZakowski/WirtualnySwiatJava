package wirtualny_swiat;

import java.awt.Color;

public class guarana extends roslina
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Abstract
    @Override void kolizja()
    {
	int liczba_organizmow, x_guarany, y_guarany, x_kolidujacego, y_kolidujacego;
	liczba_organizmow = ref_swiat.zwroc_liczbe_organizmow();
	x_guarany = this.zwroc_polozenie_x();
	y_guarany = this.zwroc_polozenie_y();
	for (int i = 0; i < liczba_organizmow; i++)
	{
            x_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_x();
            y_kolidujacego = ref_swiat.organizmy[i].zwroc_polozenie_y();
            if (x_guarany == x_kolidujacego && y_guarany == y_kolidujacego && this != this.ref_swiat.organizmy[i])
            {
		int sila_kolidujacego = this.ref_swiat.organizmy[i].zwroc_sile();
		this.ref_swiat.organizmy[i].ustaw_sile(sila_kolidujacego + 3);
                this.ustaw_czy_zyje(false);
		this.ustaw_polozenie_x(this.ref_swiat.zaswiaty);
		this.ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                System.out.println("("+x_guarany+","+y_guarany+") "+this.ref_swiat.organizmy[i].getClass().getSimpleName()+" zjada guarane.");
            }
	}
    }
    //Public
    public guarana(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('g');
	this.ustaw_sile(0);
	this.ustaw_inicjatywe(0);
	this.ustaw_kolizje_specjalna(true);
        this.kolor = new Color(227,66,52); //Czerwony (cynober) #E34234
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
