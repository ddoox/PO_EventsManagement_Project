package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Wydarzenie {
    private String nazwa;
    private String data;
    private String godzina;
    private int cenaPodstwowa;
    private ArrayList<Integer> idSprzedanychBiletow = new ArrayList<Integer>(); //lista id do szybkiego wyszukiwania po id
    private ArrayList<Bilet> listaSprzedanychBiletow = new ArrayList<Bilet>();
    private boolean dlaDorosłych;
    private int[] wolneMiejsca = new int[3];  // Na początku kopia tablicy z Sali, później będą odejmowane miejsca
    private int[] kupioneMiejsca = new int[3];
    private Sala sala;
    private double zarobek;
    private Zespół zespół;
    Random losuj = new Random();
    private String komunikat;

    public Wydarzenie(Sala _sala, Zespół _zespół, String _nazwa, String _data, String _godzina, boolean _dlaDorosłych, int _cenaPodstawowa){
        nazwa = _nazwa;
        zespół = _zespół;
        data = _data;
        cenaPodstwowa = _cenaPodstawowa;
        godzina = _godzina;
        dlaDorosłych = _dlaDorosłych;
        sala = _sala;
        zarobek = 0 - zespół.getCena();


        wolneMiejsca[0] = sala.getWolneMiejscaTyp(1); //kopia maksymalnej ilości wolnych miejsc
        wolneMiejsca[1] = sala.getWolneMiejscaTyp(2); //dla poszczególnych typów
        wolneMiejsca[2] = sala.getWolneMiejscaTyp(3); //brana z klasy sala



    }


     public String getData(){ return data; }

    public String getNazwa() { return nazwa; }

    public String getGodzina() { return godzina; }

    public Wydarzenie getThis() {return this;}

    public int wolneMiejscaTyp(int i) { return this.wolneMiejsca[i - 1];}

    public double getCenaTyp(int typMiejsca)
    {
        if (typMiejsca == 1)
            return (this.cenaPodstwowa * 0.85);
        else if (typMiejsca == 2)
            return this.cenaPodstwowa;
        else
            return this.cenaPodstwowa * 1.5;
    }

    public double getZysk(){
        return this.cenaPodstwowa*0.85*this.kupioneMiejsca[0]+this.cenaPodstwowa*this.kupioneMiejsca[1]+this.cenaPodstwowa*1.5*this.kupioneMiejsca[2];
    }

    public boolean getDlaDorosłcych() { return dlaDorosłych; }

    public double getZarobek() {
        return zarobek;
    }

    public int getKupioneMiejsca(int i){
        return kupioneMiejsca[i-1];
    }

    public int getMiejscaNaSali(int i){
        return sala.getWolneMiejscaTyp(i);
    }

    public String getKomunikat(){return komunikat;}

    public int sprzedajBilet(String _imie, String _nazwisko, int _typMiejsca, int _wiek)
    {
        try
        {

            // -   -   -   - Sprawdzanie wieku -   -   -   -//
            if(_wiek < 18 && getDlaDorosłcych())
            {
                 komunikat="To wydarzenie dla dorosłych!";
                 BladWiek wiek = new BladWiek();
                 throw wiek;
            }

            // -   -   -   - Sprawdzanie czy jest wolne miejsce -  -   -   -//
                if(this.wolneMiejsca[_typMiejsca - 1] <= 0)
            {                komunikat="Bilety tego typu wyprzedano";
                BladMiejsce miejsce = new BladMiejsce();
                 throw miejsce;
            }

            // -   -   -   - Sprawdzanie unikalnosci ID biletu-  -   -   -//
            boolean idBiletuIstnieje = false;
            int biletTymczasoweId;

            do
            {
                biletTymczasoweId = losuj.nextInt(999999999) + 1;

                if(this.idSprzedanychBiletow.isEmpty()) {}
                else
                {
                    for(int i = 0; i < this.idSprzedanychBiletow.size(); i++)
                    {
                        if(this.idSprzedanychBiletow.get(i) == biletTymczasoweId)
                        {
                            idBiletuIstnieje = true;
                            break;
                        }
                    }
                }
            } while (idBiletuIstnieje);

            komunikat="Kupiono bilet";

            // -    -   -   - Wszystkie warunki sprawdzone -    -   -   -//

            this.idSprzedanychBiletow.add(biletTymczasoweId);
            this.listaSprzedanychBiletow.add(new Bilet(_imie, _nazwisko, _typMiejsca, _wiek, getNazwa(),biletTymczasoweId,getCenaTyp(_typMiejsca)));
            this.wolneMiejsca[_typMiejsca - 1] -= 1;
            this.kupioneMiejsca[_typMiejsca - 1] += 1;
            this.zarobek += getCenaTyp(_typMiejsca);


            // -    -   -   -   Generowanie obrazka   -     -   -   -//

            generujObrazek(listaSprzedanychBiletow.get(listaSprzedanychBiletow.size() - 1).getThis(), this.getData(), this.getGodzina(), this.sala, this.zespół);


            return 0;
        }

        catch (BladWiek wiek)
        {
            return 2137;
        }
        catch (BladMiejsce miejsce)
        {
            return 1489;
        }
        catch (IOException e)
        {
            return 7777; //kiedy wszystko zadziała oprócz generowania obrazka
        }

    }


    public void generujObrazek(Bilet bilet, String data, String godzina, Sala sala, Zespół zespół) throws IOException {
        File source = new File("src//model//blank.png");
        File destination = new File("src//model//blank_swap.png");

        BufferedImage image = ImageIO.read(source);

        int imageType = BufferedImage.TYPE_INT_ARGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
        w.setComposite(alphaChannel);
        w.setColor(Color.BLACK);


        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        FontMetrics fontMetrics = w.getFontMetrics();
        String nazwaWydarzenia = "Wydarzenie: ";
        StringBuilder nazwaWydarzeniaBuilder = new StringBuilder(nazwaWydarzenia).append(bilet.getNazwaWydarzenia());
        nazwaWydarzenia = nazwaWydarzeniaBuilder.toString();

        Rectangle2D rect = fontMetrics.getStringBounds(nazwaWydarzenia, w);
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        String imię = "Imię: ";
        String nazwisko = "Nazwisko: ";
        String typMiejsca;
        String typMuzyki = "Gatunek: ";
        String id = "ID: ";
        String cena = "Cena: ";
        String zł = " zł";


        if(bilet.getTyp() == 1)
            typMiejsca = "Strefa: Economy";
        else if (bilet.getTyp() == 2)
            typMiejsca = "Strefa: Regular";
        else
            typMiejsca = "Strefa: VIP";

        StringBuilder imięBuilder = new StringBuilder(imię).append(bilet.getImię());
        imię = imięBuilder.toString();

        StringBuilder nazwiskoBuilder = new StringBuilder(nazwisko).append(bilet.getNazwisko());
        nazwisko = nazwiskoBuilder.toString();

        StringBuilder typMuzykiBuilder = new StringBuilder(typMuzyki).append(zespół.getMuzyka());
        typMuzyki = typMuzykiBuilder.toString();

        StringBuilder idBuilder = new StringBuilder(id).append(Integer.toString(bilet.getId()));
        id = idBuilder.toString();

        StringBuilder cenaBuilder = new StringBuilder(cena).append(Double.toString(bilet.getCena())).append(zł);
        cena = cenaBuilder.toString();

        w.drawString(nazwaWydarzenia, centerX, centerY - 10);


        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        w.drawString(typMiejsca, image.getWidth() * 65 / 100, image.getHeight() - 10);


        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        w.drawString(imię, image.getWidth() / 10, centerY / 4);
        w.drawString(nazwisko, image.getWidth() / 10, centerY / 2);

        w.drawString(data, image.getWidth() / 10, image.getHeight() - 10);
        w.drawString(godzina, image.getWidth() / 3, image.getHeight() - 10);

        w.drawString(zespół.getNazwa(), image.getWidth() / 6, centerY + 30);
        w.drawString(typMuzyki, image.getWidth() / 6, centerY + 60);

        w.drawString(sala.getTyp(), image.getWidth() * 2/3, centerY + 30);
        w.drawString(sala.getNazwa(), image.getWidth() * 2/3, centerY + 60);
        w.drawString(sala.getLokalizacja(), image.getWidth() * 2/3, centerY + 90);

        w.drawString(cena, image.getWidth()* 65/100, image.getHeight() - 50);

        rect = fontMetrics.getStringBounds(id, w);
        int centerId = (image.getWidth() - (int) rect.getWidth()) / 2;
        w.drawString(id, centerId, centerY + 180);

        ImageIO.write(watermarked, "png", destination);
        w.dispose();
    }



}
