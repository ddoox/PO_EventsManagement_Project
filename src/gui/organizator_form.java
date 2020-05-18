package gui;


import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class organizator_form {
    public Organizator organizator;
    public Wydarzenie wydarzenie;
    public Sala sala;
    public Zespół zespół;

    private String log;
    private String pass;
    private ListaOrganizatorów lista;
    private JFrame parent;

    public JPanel panel1;
    private JTextField cenapodstawowa_textField2;
    private JTextField VIP_textField6;
    private JTextField Regular_textField7;
    private JTextField Econowmy_textField8;
    private JTextField typZespol_textField11;
    private JTextField nazwaWydarzenie_textField1;
    private JComboBox typSali_comboBox2;
    private JTextField pow_textField3;
    private JPanel Ramka;
    private JTextField nazwaSali_textField4;
    private JButton zapiszButton;
    private JTextField nazwaZespol_textField5;
    private JTextField cenaZespol_textField9;
    private JTextField lokalizacja_textField10;
    private JTextField data_textField1;
    private JTextField godzina_textField1;
    private JCheckBox takNieCheckBox;


    public organizator_form(String _log, String _pass, ListaOrganizatorów lista, JFrame parent) {
        log=_log;
        pass=_pass;


        this.organizator=lista.login(log,pass);


        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  sala = new Sala(nazwaSali_textField4.getText(), lokalizacja_textField10.getText(), (String) typSali_comboBox2.getSelectedItem(), Integer.parseInt(pow_textField3.getText()),
                          Integer.parseInt(Econowmy_textField8.getText()), Integer.parseInt(Regular_textField7.getText()), Integer.parseInt(VIP_textField6.getText()));

                  zespół = new Zespół(nazwaZespol_textField5.getText(), typZespol_textField11.getText(), Integer.parseInt(cenaZespol_textField9.getText()));


                  organizator.dodajWydarzenie(sala, zespół, nazwaWydarzenie_textField1.getText(), data_textField1.getText(),
                          godzina_textField1.getText(), takNieCheckBox.isSelected(), Integer.parseInt(cenapodstawowa_textField2.getText()));

                  JOptionPane.showMessageDialog(panel1, "Zapisano wydarzenie");

                  JFrame events_frame = new JFrame("Organizator Events");
                  events_frame.setContentPane(new organizator_events(log, pass, lista, events_frame).panel1);
                  events_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  events_frame.pack();
                  events_frame.setVisible(true);
                  events_frame.setLocationRelativeTo(null);
                  events_frame.setResizable(false);

                  parent.dispose();


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

 /*   public static void main(String[] args) {
        JFrame frame = new JFrame("Organizator");
        frame.setContentPane(new organizator_form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
 */
}
