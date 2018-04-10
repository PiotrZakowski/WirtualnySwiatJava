package wirtualny_swiat;

import java.awt.Color;

public class lis extends zwierze
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Abstract
    @Override void akcja()
    {
	int x_lisa, y_lisa;
	x_lisa = this.zwroc_polozenie_x();
	y_lisa = this.zwroc_polozenie_y();
	this.ustaw_pop_polozenie_x(x_lisa);
	this.ustaw_pop_polozenie_y(y_lisa);
	int[] opcje_ruchu = { 1, 1, 1, 1 }; //1 - mozna przejsc, 0 - nie mozna przejsc
	this.zwiad(opcje_ruchu, x_lisa, y_lisa, 1);
	if (opcje_ruchu[0] == 0 && opcje_ruchu[1] == 0 && opcje_ruchu[2] == 0 && opcje_ruchu[3] == 0){/*nic nie rob*/}
	else
	{
            //this.ref_swiat.swiat.plansza[y_lisa][x_lisa].setBackground(this.ref_swiat.pusty_kwadrat); //??? Rysowac?
            this.ustaw_kolizje_specjalna(true);
            this.ucieczka(opcje_ruchu, x_lisa, y_lisa);
            this.ustaw_kolizje_specjalna(false);
	}
    }
    //Public
    public lis(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('L');
	this.ustaw_sile(3);
	this.ustaw_inicjatywe(7);
	this.ustaw_kolizje_specjalna(false);
        this.kolor = new Color(205,87,0); //Pomarańczowy (Rudy) #CD5700
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
