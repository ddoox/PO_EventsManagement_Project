package gui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class organizator_events implements Obserwator{
    private Organizator organizator;
    private ListaOrganizatorów lista;
    private String log;
    private String pass;
    private JFrame parent;

    public Zespół zespol;
    public Sala sala;
    private String tymczasowyWybor;

    public JPanel panel1;
    private JProgressBar Economy_progressBar1;
    private JProgressBar Regular_progressBar2;
    private JProgressBar VIP_progressBar3;
    private JTextField Zysk_textField1;
    private JComboBox events_comboBox1;
    private JButton odświeżButton;
    private JButton utwórzWydarzenieButton;
    private JLabel witaj;
    private JTextField calkowityZysk_textField1;
    private JButton usuńWydarzenieButton;

    private Obserwator obserwator;

    public organizator_events(String _log, String _pass, ListaOrganizatorów lista, JFrame parent) {
        log=_log;
        pass=_pass;

        this.organizator=lista.login(log,pass);
        events_comboBox1.removeAllItems();
        witaj.setText("Witaj " + log);
        Thread t1 = new Thread(this.organizator);
        t1.start(); // to ma działać
        //t1.stop();  // ma się wykonać po naciśnięciu stop
        refresh();  // jeśli to jest dodanie nie trzeba za pierwszym razem klikać refresh, lista jest załadowana, zmieniłbym nazwę przycisku z refresh na select


        this.organizator.subskrybuj(this);

        utwórzWydarzenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame formularz_frame = new JFrame("Organizator: Dodaj Wydarzenie");
                formularz_frame.setContentPane(new organizator_form(log,pass,lista,formularz_frame).panel1);
                formularz_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formularz_frame.pack();
                formularz_frame.setVisible(true);
                formularz_frame.setLocationRelativeTo(null);
                formularz_frame.setResizable(false);
                t1.stop();
                parent.dispose();


            }
        });
        odświeżButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();  //to tylko do testu chy refresh działa
            }
        });
        usuńWydarzenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void refresh(){

        if (!organizator.czyPusta()) {
            if(events_comboBox1!=null){
                tymczasowyWybor=(String)events_comboBox1.getSelectedItem();
            }

            events_comboBox1.removeAllItems();
            Economy_progressBar1.setValue(0);
            Regular_progressBar2.setValue(0);
            VIP_progressBar3.setValue(0);


            for (int i = 0; i < organizator.getListaWydarzeń().size(); i++) {
                events_comboBox1.addItem(organizator.getListaWydarzeń().get(i).getNazwa());

            }
            events_comboBox1.setSelectedItem(tymczasowyWybor);
        }
        else {
                JOptionPane.showMessageDialog(panel1,"Brak wydarzeń");
            }

         if (events_comboBox1.getSelectedItem()!=null){
             for (int i = 0; i < organizator.getListaWydarzeń().size(); i++) {
                 if (organizator.getListaWydarzeń().get(i).getNazwa()==(String)events_comboBox1.getSelectedItem()){

                     Economy_progressBar1.setMaximum(organizator.getListaWydarzeń().get(i).getMiejscaNaSali(1));
                     Regular_progressBar2.setMaximum(organizator.getListaWydarzeń().get(i).getMiejscaNaSali(2));
                     VIP_progressBar3.setMaximum(organizator.getListaWydarzeń().get(i).getMiejscaNaSali(3));

                     Economy_progressBar1.setValue(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(1));
                     Regular_progressBar2.setValue(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(2));
                     VIP_progressBar3.setValue(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(3));

                     Economy_progressBar1.setString(String.valueOf(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(1)));
                     Regular_progressBar2.setString(String.valueOf(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(2)));
                     VIP_progressBar3.setString(String.valueOf(organizator.getListaWydarzeń().get(i).getKupioneMiejsca(3)));

                     Zysk_textField1.setText(String.valueOf(organizator.getListaWydarzeń().get(i).getZysk()));
                     calkowityZysk_textField1.setText(String.valueOf(organizator.getListaWydarzeń().get(i).getZarobek()));

                 }
             }

         }

    }

    private void delete(){
        if (events_comboBox1.getSelectedItem()!=null){
            for (int i = 0; i < organizator.getListaWydarzeń().size(); i++) {
                if (organizator.getListaWydarzeń().get(i).getNazwa()==(String)events_comboBox1.getSelectedItem()){

                    organizator.usuńWydarzenie((String)events_comboBox1.getSelectedItem());

                    Economy_progressBar1.setMaximum(100);
                    Regular_progressBar2.setMaximum(100);
                    VIP_progressBar3.setMaximum(100);

                    Economy_progressBar1.setValue(0);
                    Regular_progressBar2.setValue(0);
                    VIP_progressBar3.setValue(0);

                    Economy_progressBar1.setString("");
                    Regular_progressBar2.setString("");
                    VIP_progressBar3.setString("");

                    Zysk_textField1.setText("");
                    calkowityZysk_textField1.setText("");

                    events_comboBox1.removeAllItems();
                    refresh();

                }
            }

        }
    }

    @Override
    public void inform() {
        refresh();
    }







 /* public static void main(String[] args) {
        JFrame frame = new JFrame("Events");
        frame.setContentPane(new organizator_events().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }*/

}
