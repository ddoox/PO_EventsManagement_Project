package model;
import gui.witamyGUI;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Nazwy klas piszemy Dużymi Literami                   Dodałem, bo cały czas zapominam
        // Nazwy pakietów, zmiennych i metod małymi literami.

        JFrame frame = new JFrame("Witamy!");
        frame.setContentPane(new witamyGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


/*        // -  -   -   - Do usunięcia  -  -   -   - //

        Sala test_sala = new Sala("naz","pow","typ",100,5,5,5);
        Zespół test_zespół = new Zespół("nazwa","muz",100);
        Organizator test_organizator = new Organizator("nah","rip");

        test_organizator.dodajWydarzenie(test_sala,test_zespół,"wydarzenie testowe","Typ wydarzenia","05-04-2004","11:23",true,1);

/*        test_organizator.getWydarzenie("wydarzenie testowe").sprzedajBilet("d","e",2,22);
        test_organizator.getWydarzenie("wydarzenie testowe").sprzedajBilet("d","e",2,22);
        test_organizator.getWydarzenie("wydarzenie testowe").sprzedajBilet("d","e",2,22);
        test_organizator.getWydarzenie("wydarzenie testowe").sprzedajBilet("d","e",2,22);
  *//*
        test_organizator.dodajWydarzenie(test_sala,test_zespół,"wydarzenie dwa","Typ wydarzenia","05-04-2004","11:23",true,1);
        System.out.println(test_organizator.getWydarzenie("wydarzenie dwa").getZarobek());
/*
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",3,22);
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",3,22);
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",3,22);
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",3,22);
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",3,22);
        test_organizator.getWydarzenie("wydarzenie dwa").sprzedajBilet("d","e",2,22);
*//*
        System.out.println(test_organizator.getWolneMiejsca());



        // -    -   -   - Wypisanie wydarzeń danego organizatora -  -   -   -//
        for(int i = 0; i < test_organizator.getListaWydarzeń().size(); i++){
            System.out.println(test_organizator.getListaWydarzeń().get(i).getNazwa());
        }


        test_organizator.usuńWydarzenie("wydarzenie dwa");


        // -    -   -   - Wypisanie wydarzeń danego organizatora -  -   -   -//
        for(int i = 0; i < test_organizator.getListaWydarzeń().size(); i++){
            System.out.println(test_organizator.getListaWydarzeń().get(i).getNazwa());

            System.out.println(test_organizator.getWolneMiejsca());
        }

    }

    */


    }
}
