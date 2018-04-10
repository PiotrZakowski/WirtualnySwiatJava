package wirtualny_swiat;

import java.awt.Color;
import java.util.Random;

public class zolw extends zwierze
{
//Pola
    //Public
    //Protected
    //Private
    
//Metody
    //Abstract
    @Override void akcja()
    {
	//this.ustaw_kolizje_specjalna(false); //??? Zrozumiec czemu
	Random rand = new Random();
        int losowa = rand.nextInt(4);
	if (losowa == 0)
	{
            this.ustaw_kolizje_specjalna(false); //zolw kiedy atakuje traci umiejetnosc specjalna
            super.akcja();
            this.ustaw_kolizje_specjalna(true); //zolw po wykonanym ruchu znow odzyskuje umiejetnosc specjalna
	}
    }
    
    @Override void kolizja()
    {
	if (this.sprawdz_kolizja_specjalna())
	{
            int liczba_organizmow, x_zolwia, y_zolwia, x_kolidujacego, y_kolidujacego;
            liczba_organizmow = ref_swiat.zwroc_liczbe_organizmow();
            x_zolwia = this.zwroc_polozenie_x();
            y_zolwia = this.zwroc_polozenie_y();
            for (int i = 0; i < liczba_organizmow; i++)
            {
                x_kolidujacego = this.ref_swiat.organizmy[i].zwroc_polozenie_x();
		y_kolidujacego = this.ref_swiat.organizmy[i].zwroc_polozenie_y();
                if (x_zolwia == x_kolidujacego && y_zolwia == y_kolidujacego && this != this.ref_swiat.organizmy[i])
                {
                    if(this.getClass()==ref_swiat.organizmy[i].getClass())
                    {
                        this.rozmnazanie(this.ref_swiat, x_kolidujacego, y_kolidujacego);
                    }
                    else
                    {
                        int sila_zolwia = this.zwroc_sile();
                        int sila_kolidujacego = this.ref_swiat.organizmy[i].zwroc_sile();
                        if (sila_kolidujacego <= sila_zolwia) //zolw zabija
                        {
                            this.ref_swiat.organizmy[i].ustaw_czy_zyje(false);
                            this.ref_swiat.organizmy[i].ustaw_polozenie_x(this.ref_swiat.zaswiaty);
                            this.ref_swiat.organizmy[i].ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                            System.out.println("("+x_zolwia+","+y_zolwia+") zolw pokonuje "+this.ref_swiat.organizmy[i].getClass().getSimpleName());
                            //this.rysowanie();
                        }
                        else
                        {
                            if (sila_kolidujacego < 5) //zolw odgania
                            {
                                this.ref_swiat.organizmy[i].ustaw_polozenie_x(this.zwroc_pop_polozenie_x());
                                this.ref_swiat.organizmy[i].ustaw_polozenie_y(this.zwroc_pop_polozenie_y());
                                System.out.println("("+x_zolwia+","+y_zolwia+") zolw odgania "+this.ref_swiat.organizmy[i].getClass().getSimpleName());
                            }
                            else //zolw ginie
                            {
                                this.ustaw_czy_zyje(false);
                                this.ustaw_polozenie_x(this.ref_swiat.zaswiaty);
                                this.ustaw_polozenie_y(this.ref_swiat.zaswiaty);
                                this.ref_swiat.organizmy[i].rysowanie();
                                System.out.println("("+x_zolwia+","+y_zolwia+") "+this.ref_swiat.organizmy[i].getClass().getSimpleName()+" pokonuje zolw.");
                            }
                        }    
                    }
                }
            }
        }
        else
        {
            super.kolizja();
        }
    }
    //Public
    public zolw(swiat swiat, int wsp_x, int wsp_y)
    {
        super(swiat, wsp_x, wsp_y);
	this.ustaw_znak('Z');
	this.ustaw_sile(2);
	this.ustaw_inicjatywe(1);
	this.ustaw_kolizje_specjalna(true);
        this.kolor = new Color(110,111,47); //Zielony (zgniła zieleń) #6E6F2F
	this.ref_swiat.powiekszenie_swiata();
	this.ref_swiat.organizmy[this.ref_swiat.zwroc_liczbe_organizmow() - 1] = this;
	//this.ref_swiat.organizmy[this.ref_swiat.zwroc_odpowiedni_indeks(this.inicjatywa)] = this;
    }
    //Protected
    //Private
}
