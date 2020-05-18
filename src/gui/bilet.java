package gui;

import model.ListaOrganizatorów;
import model.Organizator;
import model.Wydarzenie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bilet {
    private Wydarzenie wydarzenie;
    private ListaOrganizatorów listaOrganizatorów;
    private Organizator organizator;
    private String tymczasowe;

    public JPanel bilet_form;
    private JTextField imie_textField1;
    private JTextField nazwisko_textField2;
    private JComboBox wydarzenie_comboBox1;
    private JComboBox typbiletu_comboBox2;
    private JButton kupBiletButton;
    private JTextField wiek_textField1;
    public JTextField komunikat_textField1;


    public bilet(ListaOrganizatorów _lista) {

        listaOrganizatorów=_lista;
        wydarzenie_comboBox1.removeAllItems();

        refreshBilet();


        kupBiletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save();

                if(wydarzenie_comboBox1.getSelectedItem()!=null && !imie_textField1.getText().isEmpty() && !nazwisko_textField2.getText().isEmpty()&& !wiek_textField1.getText().isEmpty()) {

                    if (!listaOrganizatorów.czyPusta()) {
                        for (int i = 0; i < listaOrganizatorów.getListaOrganizatorów().size(); i++) {
                            organizator=listaOrganizatorów.login(listaOrganizatorów.getListaOrganizatorów().get(i).getLogin(),listaOrganizatorów.getListaOrganizatorów().get(i).getHasło());
                            if (!organizator.czyPusta()) {
                                for (int j = 0; j < organizator.getListaWydarzeń().size(); j++) {
                                    //wydarzenie_comboBox1.addItem(organizator.getListaWydarzeń().get(j).getNazwa());
                                    if ((String)wydarzenie_comboBox1.getSelectedItem()==organizator.getListaWydarzeń().get(j).getNazwa()){
                                        organizator.getListaWydarzeń().get(j).sprzedajBilet(imie_textField1.getText(),nazwisko_textField2.getText(),
                                                typbiletu_comboBox2.getSelectedIndex()+1, Integer.parseInt(wiek_textField1.getText()));

                                        wydarzenie_comboBox1.removeAllItems();
                                        //komunikat_textField1.setText("");
                                        tymczasowe=organizator.getListaWydarzeń().get(j).getNazwa();


                                        if (komunikat_textField1.getText().equals("Kupiono bilet") && organizator.getListaWydarzeń().get(j).getKomunikat().equals("Kupiono bilet")){
                                            komunikat_textField1.setText("Kupiono kolejny bilet!");
                                        }
                                        else if (komunikat_textField1.getText().equals("Kupiono kolejny bilet!") && organizator.getListaWydarzeń().get(j).getKomunikat().equals("Kupiono bilet")){
                                            komunikat_textField1.setText("Bilet został kupiony!");
                                        }
                                        else{
                                            komunikat_textField1.setText(organizator.getListaWydarzeń().get(j).getKomunikat());
                                        }

                                        refreshBilet();
                                        wydarzenie_comboBox1.setSelectedItem(tymczasowe);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(bilet_form,"Brak Organizatorów");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(bilet_form, "Uzupełnij wszystkie dane!");
                }
            }
        });
    }

    private void refreshBilet(){


        if (!listaOrganizatorów.czyPusta()) {
            for (int i = 0; i < listaOrganizatorów.getListaOrganizatorów().size(); i++) {
                //wydarzenie_comboBox1.addItem(listaOrganizatorów.getListaOrganizatorów().get(i).getLogin());
                organizator=listaOrganizatorów.login(listaOrganizatorów.getListaOrganizatorów().get(i).getLogin(),listaOrganizatorów.getListaOrganizatorów().get(i).getHasło());

                if (!organizator.czyPusta()) {
                    for (int j = 0; j < organizator.getListaWydarzeń().size(); j++) {
                        wydarzenie_comboBox1.addItem(organizator.getListaWydarzeń().get(j).getNazwa());
                    }

                }
            }
        }
        else {
            JOptionPane.showMessageDialog(bilet_form,"Brak Organizatorów");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void save(){
        //wydarzenie.sprzedajBilet(imie_textField1.getText(), nazwisko_textField2.getText(), typbiletu_comboBox2.getSelectedIndex()+1, Integer.parseInt(wiek_textField1.getText()));
        //System.out.println(typbiletu_comboBox2.getSelectedItem());
        //(Integer)typbiletu_comboBox2.getSelectedItem()
        //System.out.println(typbiletu_comboBox2.getSelectedIndex()+1);
    }
/*
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bilet");
        frame.setContentPane(new bilet().bilet_form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    */




}
