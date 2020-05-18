package model;

public class Bilet {


    private String imię;
    private String nazwisko;
    private int wiek;
    private int id;
    private int typ;
    private double cena;
    private String nazwaWydarzenia;



    public Bilet (String imię, String _nazwisko, int _typ, int _wiek, String _nazwaWydarzenia, int _id, double _cena)
    {
        this.imię = imię;
        nazwisko = _nazwisko;
        wiek = _wiek;
        typ = _typ;
        nazwaWydarzenia = _nazwaWydarzenia;
        id = _id;
        cena = _cena;

    }


    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public double getCena() {
        return cena;
    }

    public int getId() {
        return id;
    }

    public int getTyp() {
        return typ;
    }

    public Bilet getThis() {
        return this;
    }

    public String getNazwaWydarzenia() {
        return nazwaWydarzenia;
    }
}
