package wirtualny_swiat;

import java.awt.Color;

public class mlecz extends roslina
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Abstract
    @Override void akcja()
    {
	for (int i = 0; i < 3; i++) //!!! Trzy razy rozprzestrzenianie czy trzy razy podejscie do rozprzestrzeniania?
	{
		super.akcja();
	}
    }
    //Public
    public mlecz(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('m');
	this.ustaw_sile(0);
	this.ustaw_inicjatywe(0);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(254,254,51); //Żółty (bananowy) #FEFE33
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
