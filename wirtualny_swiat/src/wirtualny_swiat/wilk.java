package wirtualny_swiat;

import java.awt.Color;

public class wilk extends zwierze
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Public
    public wilk(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('W');
	this.ustaw_sile(9);
	this.ustaw_inicjatywe(5);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(154,154,154); //Szary #9A9A9A
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
