package model;

public class Zespół {


    private String nazwa;
    private String typ_muzyki;
    private int cena;


    public Zespół(String _nazwa, String _muzyka, int _cena)
    {
        nazwa = _nazwa;
        typ_muzyki = _muzyka;
        cena = _cena;
    }


    public String getNazwa()  {return nazwa;}

    public String getMuzyka()  {return typ_muzyki;}

    public int getCena()  {return cena;}




}
