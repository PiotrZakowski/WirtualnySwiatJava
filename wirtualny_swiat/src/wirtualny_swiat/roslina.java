package wirtualny_swiat;

import java.util.Random;

public class roslina extends organizm
{
//Pola
    //Public
    //Protected
    //Private
    private int modulo_randomu = 20;
    
//Metody
    //Abstract
    @Override void akcja()
    {
        Random rand = new Random();
	int losowa = rand.nextInt(this.modulo_randomu);
	if (losowa == 0)
	{
		int x_rosliny, y_rosliny;
		x_rosliny = this.zwroc_polozenie_x();
		y_rosliny = this.zwroc_polozenie_y();
		this.rozmnazanie(this.ref_swiat, x_rosliny, y_rosliny);
	}
    }
    
    @Override void kolizja()
    {
    }
    
    @Override void rysowanie()
    {
	//this.ref_swiat.swiat.plansza[this.polozenie_y][this.pop_polozenie_x].setBackground(this.kolor); //??? rysowac?
    }
    //Public
    public roslina(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
    }
    
    public roslina(roslina oryginal)
    {
        super(oryginal);
        this.modulo_randomu = oryginal.modulo_randomu;
    }
    //Protected
    //Private
}
