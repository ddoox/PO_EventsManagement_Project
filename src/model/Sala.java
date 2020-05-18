package model;

public class Sala {

    private String nazwa;
    private int powierzchnia;
    private String lokalizacja;
    private String typ;
    private int[] wolneMiejsca = new int[3];  // każdy element to ilość miejsc danego typu


    public Sala(String _nazwa, String _lokalizacja, String _typ, int _powierzchnia, int miejscaTypuI, int miejscaTypuII, int miejscaTypuIII)
    {
        nazwa = _nazwa;
        powierzchnia = _powierzchnia;
        lokalizacja = _lokalizacja;
        typ = _typ;
        wolneMiejsca[0] = miejscaTypuI;
        wolneMiejsca[1] = miejscaTypuII;
        wolneMiejsca[2] = miejscaTypuIII;

    }

    public String getNazwa() {
        return nazwa;
    }

    public int getPowierzchnia() {
        return powierzchnia;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public String getTyp() {
        return typ;
    }

    public int getWolneMiejscaTyp(int a)
    {
        if (a >= 1 && a <= 3)
        {
            return wolneMiejsca[a - 1];  // typ I = 1, II = 2, III = 3
        }
        return 0;
    }


}
