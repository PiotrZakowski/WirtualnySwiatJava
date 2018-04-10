package wirtualny_swiat;

import java.awt.Color;

public class trawa  extends roslina
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Public
    //Protected
    trawa(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('t');
	this.ustaw_sile(0);
	this.ustaw_inicjatywe(0);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(124,252,0); //Zielony (trawiasty) #7CFC00
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Public
}
