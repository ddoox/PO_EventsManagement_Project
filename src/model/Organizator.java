package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Organizator implements Runnable,Obserwowany{
    private String login;
    private String hasło;
    private ArrayList<Wydarzenie> listaWydarzeń = new ArrayList<Wydarzenie>();
    private Obserwator obserwator;


    public Organizator(String _login, String _haslo)
    {
        hasło = _haslo;
        login = _login;
    }

    public String getLogin(){ return login;}


    public String getHasło(){ return hasło;}


    public Organizator getThis() { return this;}


    public int dodajWydarzenie(Sala _sala, Zespół _zespół, String _nazwa, String _data, String _godzina, boolean _dlaDorosłych, int _cenaPodstawowa)
    {
        for(int i = 0; i < listaWydarzeń.size(); i++)
        {
            if (listaWydarzeń.get(i).getNazwa().equals(_nazwa) )
            {
                return 404;
            }
        }
        listaWydarzeń.add(new Wydarzenie(_sala,_zespół,_nazwa,_data,_godzina,_dlaDorosłych,_cenaPodstawowa));
        return 0;
    }


    public Wydarzenie getWydarzenie(String nazwaWydarzenia)
    {
        for(int i = 0; i < this.listaWydarzeń.size(); i++){
            if (listaWydarzeń.get(i).getNazwa() == nazwaWydarzenia)
                return listaWydarzeń.get(i);
        }
        return null;
    }
    public boolean czyPusta()
    {
        return listaWydarzeń.isEmpty();
    }


    public ArrayList<Wydarzenie> getListaWydarzeń()
    {
        if(listaWydarzeń.isEmpty()){ return null;}
        else{ return this.listaWydarzeń; }
    }


    public double getŁącznyZarobek()
    {
        double zarobek = 0;

        for(int i = 0; i < this.listaWydarzeń.size(); i++)
        {
             zarobek += listaWydarzeń.get(i).getZarobek();
        }
        return zarobek;
    }


    public void usuńWydarzenie(String nazwa)
    {
        Iterator<Wydarzenie> it = listaWydarzeń.iterator();

        while (it.hasNext())
        {
            if (it.next().getNazwa().equals(nazwa))
            {
                it.remove();
                break;
            }
        }
    }


    public int getWolneMiejsca()
    {
        int wolneMiejsca = 0;

        for(int i = 0; i < this.listaWydarzeń.size(); i++){
            wolneMiejsca += listaWydarzeń.get(i).wolneMiejscaTyp(1);
            wolneMiejsca += listaWydarzeń.get(i).wolneMiejscaTyp(2);
            wolneMiejsca += listaWydarzeń.get(i).wolneMiejscaTyp(3);
        }
        return wolneMiejsca;
    }


    public void run()
    {
        int wolneMiejsca = this.getWolneMiejsca();

        while(true)
        {
            if(wolneMiejsca != this.getWolneMiejsca())
            {
                wolneMiejsca = this.getWolneMiejsca();
                try { Thread.sleep(1000);}
                catch (InterruptedException e){ e.printStackTrace();}
                if(obserwator != null)
                    obserwator.inform();

            }
        }
    }


    public void subskrybuj(Obserwator o){ obserwator = o;}


    public void odsubskrybuj(Obserwator o)
    {
        if (obserwator == o)
        {
            obserwator = null;
        }
    }


    public void inform() { obserwator.inform();}

}
