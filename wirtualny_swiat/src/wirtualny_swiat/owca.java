package wirtualny_swiat;

import java.awt.Color;

public class owca extends zwierze
{
//Pola
    //Public
    //Protected
    //Private

//Metody
    //Public
    public owca(swiat swiat, int wsp_x, int wsp_y) 
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('O');
	this.ustaw_sile(4);
	this.ustaw_inicjatywe(4);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(206,206,206);//Jasnoszary #CECECE
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
