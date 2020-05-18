package test;

import model.ListaOrganizatorów;
import model.Sala;
import model.Zespół;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListaOrganizatorówTest {
    Sala sala = new Sala("sala","Kraków","Kawiarnia",100,5,20,10);
    Zespół zespół = new Zespół("zespół","rock",1000);


    @Test
    public void dodajWydarzenie() {

        ListaOrganizatorów lista = new ListaOrganizatorów(1);

        assertEquals(0,lista.rejestracja("login","hasło"));
        assertEquals(404,lista.rejestracja("login","d"));


        // -    -   -   - Działania na organizatorze z listy -  -   -   -//


        lista.login("login","hasło").dodajWydarzenie(sala,zespół,"wyda","11-12-2019","20:00",true,75);
        lista.login("login","hasło").getWydarzenie("wyda").sprzedajBilet("1","2",2,17);
        assertEquals(20, lista.login("login","hasło").getWydarzenie("wyda").wolneMiejscaTyp(2));


        assertEquals(404,lista.login("login","hasło").dodajWydarzenie(sala,zespół,"wyda","11-12-2019","20:00",true,75));


        assertEquals(0,lista.login("login","hasło").dodajWydarzenie(sala,zespół,"wyda_uni","11-12-2019","20:00",true,75));



        // -    -   -   - Działania na wydarzeniu za pomocą organizatora z listy-  -   -   - //

        assertEquals(2137,lista.login("login","hasło").getWydarzenie("wyda").sprzedajBilet("1","2",2,17));


        for (int i = 0; i < 30; i++)
        {
            lista.login("login","hasło").getWydarzenie("wyda").sprzedajBilet(Integer.toString(i),Integer.toString(i),2,22);
        }
        assertEquals(0, lista.login("login","hasło").getWydarzenie("wyda").wolneMiejscaTyp(2));


        assertEquals(1489,lista.login("login","hasło").getWydarzenie("wyda").sprzedajBilet("d","d",2,22));


        double cenaTypIII = 75*1.5;
        assertEquals(Double.toString(lista.login("login","hasło").getWydarzenie("wyda").getCenaTyp(3)),Double.toString(cenaTypIII));


        double a = 20*75;
        assertEquals(Double.toString(a - zespół.getCena()),Double.toString(lista.login("login","hasło").getWydarzenie("wyda").getZarobek()));





    }

}