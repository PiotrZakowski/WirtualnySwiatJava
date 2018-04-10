package wirtualny_swiat;

//import java.util.Scanner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Wirtualny_swiat extends JFrame implements ActionListener, KeyListener
{
    protected final int ZADANA_ROZMIAR_PION = 25;
    protected final int ZADANA_ROZMIAR_POZIOM = 20;
    protected final int ZADANA_ILOSC_ZWIERZAT = 50;
    protected static final int MARGINES = 10;
    protected static final int WIELKOSC_KOMORKI = 23;
    
    JButton przycisk_nowej_tury;
    JButton przycisk_restartu;
    JButton przycisk_kierunek_gora;
    JButton przycisk_kierunek_dol;
    JButton przycisk_kierunek_prawo;
    JButton przycisk_kierunek_lewo;
    JButton przycisk_kierunek_zostan;
    JButton przycisk_umiejetnosci;
    /*
    JButton przycisk_zapisu;
    JButton przycisk_odczytu;
    */
    JButton[][] plansza=new JButton[ZADANA_ROZMIAR_PION][ZADANA_ROZMIAR_POZIOM];
    JButton[] legenda = new JButton[10];
    
    swiat rezerwat;
    swiat save_rezerwatu;
    String klawisz_dla_czlowieka_kierunek_ruchu = "ZOSTAN";
    boolean klawisz_dla_czlowieka_umiejetnosc = false;
    
    private void stworz_plansze()
    {
        for(int i=0;i<this.rezerwat.rozmiar_pola_pion; i++)
        {
            for (int j=0; j<this.rezerwat.rozmiar_pola_poziom; j++)
            {
                plansza[i][j]=new JButton();
            }
        }
    }
    
    private void stworz_legende()
    {
        Color kolor;
        //Czlowiek
        this.legenda[0]=new JButton("Człowiek");
        kolor = new Color(255,229,180); //Cielisty #FFE5B4 
        this.legenda[0].setBackground(kolor);
        //Wilk
        this.legenda[1]=new JButton("Wilk");
        kolor = new Color(154,154,154); //Szary #9A9A9A 
        this.legenda[1].setBackground(kolor);
        //Owca
        this.legenda[2]=new JButton("Owca");
        kolor = new Color(206,206,206); //Jasnoszary #CECECE
        this.legenda[2].setBackground(kolor);
        //Lis
        this.legenda[3]=new JButton("Lis");
        kolor = new Color(205,87,0); //Pomarańczowy (Rudy) #CD5700
        this.legenda[3].setBackground(kolor);
        //Antylopa
        this.legenda[4]=new JButton("Antylopa");
        kolor = new Color(255,192,0); //Pomarańczowy (marchewkowy) #FFC000
        this.legenda[4].setBackground(kolor);
        //Zolw
        this.legenda[5]=new JButton("Żółw");
        kolor = new Color(110,111,47); //Zielony (zgniła zieleń) #6E6F2F 
        this.legenda[5].setBackground(kolor);
        //Trawa
        this.legenda[6]=new JButton("Trawa");
        kolor = new Color(124,252,0); //Zielony (trawiasty) #7CFC00 
        this.legenda[6].setBackground(kolor);
        //Mlecz
        this.legenda[7]=new JButton("Mlecz");
        kolor = new Color(254,254,51); //Żółty (bananowy) #FEFE33
        this.legenda[7].setBackground(kolor);
        //Guarana
        this.legenda[8]=new JButton("Guarana");
        kolor = new Color(227,66,52); //Czerwony (cynober) #E34234 
        this.legenda[8].setBackground(kolor);
        //Wilcze jagody
        this.legenda[9]=new JButton("Wilcze jagody");
        kolor = new Color(102,0,102); //Fiolet (jagodowy) #660066
        this.legenda[9].setBackground(kolor);  
    }
    
    private void generuj_zwierzeta(int liczba_zwierzat)
    {
        Random rand = new Random();
        int losowa, wsp_x=0, wsp_y=0, pomocnicza;
        for(int i=0; i< liczba_zwierzat; i++)
        {
           pomocnicza=0;
           losowa = rand.nextInt(9);
           while(pomocnicza==0)
           {
                pomocnicza=1;
                wsp_x = rand.nextInt(this.ZADANA_ROZMIAR_POZIOM);
                wsp_y = rand.nextInt(this.ZADANA_ROZMIAR_PION);
                for(int j=0; j<i; j++)
                {
                    if(this.rezerwat.organizmy[j].polozenie_x==wsp_x && this.rezerwat.organizmy[j].polozenie_y==wsp_y) pomocnicza=0;
                }
           }
           if(losowa == 0) new wilk(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 1) new owca(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 2) new lis(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 3) new antylopa(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 4) new zolw(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 5) new trawa(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 6) new mlecz(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 7) new guarana(this.rezerwat,wsp_x,wsp_y);
           if(losowa == 8) new wilcze_jagody(this.rezerwat,wsp_x,wsp_y);
        }
    }
    
    public Wirtualny_swiat()
    {
        setSize(768,640);
        setTitle("Piotr Żakowski 160320");
        setLayout(null);
        rezerwat = new swiat(this.ZADANA_ROZMIAR_PION, this.ZADANA_ROZMIAR_POZIOM);
        this.rezerwat.ustaw_swiat(this);
        addKeyListener(this);
        setFocusable(true);
        
        czlowiek Bubek = new czlowiek(rezerwat, this.ZADANA_ROZMIAR_POZIOM/2, this.ZADANA_ROZMIAR_PION/2);
        this.generuj_zwierzeta(this.ZADANA_ILOSC_ZWIERZAT);
        
        przycisk_nowej_tury = new JButton("Nowa tura");
        przycisk_nowej_tury.setBounds(500, 20, 100, 20);
        add(przycisk_nowej_tury);
        
        przycisk_restartu = new JButton("Restart");
        przycisk_restartu.setBounds(620, 20, 100, 20);
        add(przycisk_restartu);
        
        przycisk_kierunek_gora = new JButton("↑");
        przycisk_kierunek_gora.setBounds(585, 60, 50, 50);
        add(przycisk_kierunek_gora);
        
        przycisk_kierunek_dol = new JButton("↓");
        przycisk_kierunek_dol.setBounds(585, 160, 50, 50);
        add(przycisk_kierunek_dol);
        
        przycisk_kierunek_prawo = new JButton("→");
        przycisk_kierunek_prawo.setBounds(635, 110, 50, 50);
        add(przycisk_kierunek_prawo);
        
        przycisk_kierunek_lewo = new JButton("←");
        przycisk_kierunek_lewo.setBounds(535, 110, 50, 50);
        add(przycisk_kierunek_lewo);
        
        przycisk_kierunek_zostan = new JButton("•");
        przycisk_kierunek_zostan.setBounds(585, 110, 50, 50);
        add(przycisk_kierunek_zostan);
        
        przycisk_umiejetnosci = new JButton("IMMORTALITY");
        przycisk_umiejetnosci.setBounds(520, 230, 180, 20);
        add(przycisk_umiejetnosci);
        /*
        przycisk_zapisu = new JButton("Zapisz");
        przycisk_zapisu.setBounds(520, 280, 180, 20);
        add(przycisk_zapisu);
        
        przycisk_odczytu = new JButton("Wczytaj");
        przycisk_odczytu.setBounds(520, 310, 180, 20);
        add(przycisk_odczytu);
        */
        przycisk_nowej_tury.addActionListener(this);
        przycisk_restartu.addActionListener(this);
        przycisk_kierunek_gora.addActionListener(this);
        przycisk_kierunek_dol.addActionListener(this);
        przycisk_kierunek_prawo.addActionListener(this);
        przycisk_kierunek_lewo.addActionListener(this);
        przycisk_kierunek_zostan.addActionListener(this);
        przycisk_umiejetnosci.addActionListener(this);
        /*
        przycisk_zapisu.addActionListener(this);
        przycisk_odczytu.addActionListener(this);
        */
        
        this.stworz_plansze();
        for(int i=0;i<this.ZADANA_ROZMIAR_PION; i++)
        {
            for (int j=0; j<this.ZADANA_ROZMIAR_POZIOM; j++)
            {
                plansza[i][j].setBounds(this.MARGINES+this.WIELKOSC_KOMORKI*j,this.MARGINES+this.WIELKOSC_KOMORKI*i,this.WIELKOSC_KOMORKI,this.WIELKOSC_KOMORKI);
                add(plansza[i][j]);
            }
        }
        
        this.stworz_legende();
        for(int j=0; j<10; j++)
        {
            legenda[j].setBounds(535, 350+j*23, 150, 20);
            add(legenda[j]);
        }
            
        
        this.rezerwat.rysujSwiat();  
    }
    
    public static void main(String[] args) 
    {
        Wirtualny_swiat okienko = new Wirtualny_swiat();
        okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okienko.setVisible(true);
    }
    
    @Override public void actionPerformed(ActionEvent event)
    {
        if("↑".equals(event.getActionCommand())){
            this.klawisz_dla_czlowieka_kierunek_ruchu="GORA";
            rezerwat.wykonajTure();
        }
        if("↓".equals(event.getActionCommand())){
            this.klawisz_dla_czlowieka_kierunek_ruchu="DOL";
            rezerwat.wykonajTure();
        }
        if("→".equals(event.getActionCommand())){
            this.klawisz_dla_czlowieka_kierunek_ruchu="PRAWO";
            rezerwat.wykonajTure();
        }
        if("←".equals(event.getActionCommand())){
            this.klawisz_dla_czlowieka_kierunek_ruchu="LEWO";
            rezerwat.wykonajTure();
        }
        if("•".equals(event.getActionCommand())){
            this.klawisz_dla_czlowieka_kierunek_ruchu="ZOSTAN";
            rezerwat.wykonajTure();
        }
        if("Nowa tura".equals(event.getActionCommand())) rezerwat.wykonajTure();
        if("Restart".equals(event.getActionCommand()))
        {
            for(int i=0; i<this.rezerwat.liczba_organizmow; i++)
            {
                this.rezerwat.organizmy[i].ustaw_czy_zyje(false);
            }
            this.rezerwat.zredukuj_tablice_organizmow();
            new czlowiek(this.rezerwat,this.ZADANA_ROZMIAR_POZIOM/2,this.ZADANA_ROZMIAR_PION/2);
            this.generuj_zwierzeta(ZADANA_ILOSC_ZWIERZAT);
            this.rezerwat.rysujSwiat();
        }
        if("IMMORTALITY".equals(event.getActionCommand()))
        {
            this.klawisz_dla_czlowieka_umiejetnosc=true;
        }
        /*
        if("Zapisz".equals(event.getActionCommand()))
        {
            this.save_rezerwatu=new swiat(this.rezerwat);
        }
        if("Wczytaj".equals(event.getActionCommand()))
        {
            this.rezerwat=new swiat(this.save_rezerwatu);
            this.rezerwat.rysujSwiat();
        }
        */
    }

    @Override
    public void keyTyped(KeyEvent ke) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        int kod_unicode_klawisza = ke.getKeyCode();
        if(kod_unicode_klawisza == KeyEvent.VK_T)
        {
            rezerwat.wykonajTure();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int kod_unicode_klawisza = ke.getKeyCode();
        if(kod_unicode_klawisza == KeyEvent.VK_UP || kod_unicode_klawisza == KeyEvent.VK_DOWN || kod_unicode_klawisza == KeyEvent.VK_RIGHT || kod_unicode_klawisza == KeyEvent.VK_LEFT || kod_unicode_klawisza == KeyEvent.VK_SHIFT)
        {
            if(kod_unicode_klawisza == KeyEvent.VK_UP) this.klawisz_dla_czlowieka_kierunek_ruchu="GORA";
            if(kod_unicode_klawisza == KeyEvent.VK_DOWN) this.klawisz_dla_czlowieka_kierunek_ruchu="DOL";
            if(kod_unicode_klawisza == KeyEvent.VK_RIGHT) this.klawisz_dla_czlowieka_kierunek_ruchu="PRAWO";
            if(kod_unicode_klawisza == KeyEvent.VK_LEFT) this.klawisz_dla_czlowieka_kierunek_ruchu="LEWO";
            if(kod_unicode_klawisza == KeyEvent.VK_SHIFT) this.klawisz_dla_czlowieka_kierunek_ruchu="ZOSTAN";
        }
        else
        {
            if(kod_unicode_klawisza == KeyEvent.VK_SPACE)
            {
                this.klawisz_dla_czlowieka_umiejetnosc=true;
            }
        }
    }
    
}
