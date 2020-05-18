package test;

import model.Organizator;
import model.Sala;
import model.Wydarzenie;
import model.Zespół;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrganizatorTest {
    Sala sala = new Sala("sala","Kraków","Kawiarnia",100,5,20,10);
    Zespół zespół = new Zespół("zespół","rock",1000);


    @Test
    public void dodajWydarzenie() {

        Organizator organizator = new Organizator("login","hasło");

        organizator.dodajWydarzenie(sala,zespół,"wyda","11-12-2019","20:00",true,75);
        organizator.getWydarzenie("wyda").sprzedajBilet("1","2",2,17);
        assertEquals(20, organizator.getWydarzenie("wyda").wolneMiejscaTyp(2));


        assertEquals(404,organizator.dodajWydarzenie(sala,zespół,"wyda","11-12-2019","20:00",true,75));


        assertEquals(0,organizator.dodajWydarzenie(sala,zespół,"wyda_uni","11-12-2019","20:00",true,75));



        // -    -   -   - Działania na wydarzeniu za pomocą organizatora -  -   -   - //

        assertEquals(2137,organizator.getWydarzenie("wyda").sprzedajBilet("1","2",2,17));


        for (int i = 0; i < 30; i++)
        {
            organizator.getWydarzenie("wyda").sprzedajBilet(Integer.toString(i),Integer.toString(i),2,22);
        }
        assertEquals(0, organizator.getWydarzenie("wyda").wolneMiejscaTyp(2));


        assertEquals(1489,organizator.getWydarzenie("wyda").sprzedajBilet("d","d",2,22));


        double cenaTypIII = 75*1.5;
        assertEquals(Double.toString(organizator.getWydarzenie("wyda").getCenaTyp(3)),Double.toString(cenaTypIII));


        double a = 20*75;
        assertEquals(Double.toString(a - zespół.getCena()),Double.toString(organizator.getWydarzenie("wyda").getZarobek()));





    }

}