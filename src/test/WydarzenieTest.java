package test;

import model.Sala;
import model.Wydarzenie;
import model.Zespół;
import org.junit.Test;

import static org.junit.Assert.*;

public class WydarzenieTest {

    @Test
    public void sprzedajBilet() {

        Sala sala = new Sala("ICE","Kraków","Centrum Kongresowe",100,5,20,10);
        Zespół zespół = new Zespół("Within Temptation","Symphonic metal",1000);

        Wydarzenie wydarzenie = new Wydarzenie(sala,zespół,"Waddinxveen attack","11-12-2019","20:00",true,75);
        wydarzenie.sprzedajBilet("Jan","Kowalski",3,17);
        assertEquals(20, wydarzenie.wolneMiejscaTyp(2));

        Wydarzenie wydarzenie2 = new Wydarzenie(sala,zespół,"Waddinxveen attack","11-12-2019","20:00",false,75);
        assertEquals(0,wydarzenie2.sprzedajBilet("Jan","Kowalski",3,17));



        assertEquals(2137,wydarzenie.sprzedajBilet("1","2",2,17));


        for (int i = 0; i < 30; i++)
        {
            wydarzenie.sprzedajBilet("Jan","Kowalski",2,22);
        }
        assertEquals(0, wydarzenie.wolneMiejscaTyp(2));


        assertEquals(1489,wydarzenie.sprzedajBilet("d","d",2,22));


        double cenaTypIII = 75*1.5;
        assertEquals(Double.toString(wydarzenie.getCenaTyp(3)),Double.toString(cenaTypIII));


        double a = 20*75;
        assertEquals(Double.toString(a - zespół.getCena()),Double.toString(wydarzenie.getZarobek()));


        assertEquals(0,wydarzenie.sprzedajBilet("Jan","Kowalski",3,22));




    }

}