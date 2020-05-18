package gui;

import model.ListaOrganizatorów;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class witamyGUI {
    public JPanel panel1;
    private JButton wejdźButton;
    private JRadioButton klientRadioButton;
    private JRadioButton organizatorRadioButton;
    private ListaOrganizatorów lista;

    public witamyGUI() {

        ListaOrganizatorów lista=new ListaOrganizatorów(1);


        klientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(klientRadioButton.isSelected()){
                    organizatorRadioButton.setSelected(false);
                }
            }
        });
        organizatorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(klientRadioButton.isSelected()){
                    klientRadioButton.setSelected(false);
                }
            }
        });
        wejdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(klientRadioButton.isSelected()){
                    JFrame frame = new JFrame("Sprzedaż Biletów");
                    frame.setContentPane(new bilet(lista).bilet_form);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                }
                else  if (organizatorRadioButton.isSelected()){
                    JFrame org_login = new JFrame("Organizator: Login");
                    org_login.setContentPane(new organizator_login(lista).panel1);
                    org_login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    org_login.pack();
                    org_login.setVisible(true);
                    org_login.setLocationRelativeTo(null);
                    org_login.setResizable(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Witamy!");
        frame.setContentPane(new witamyGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);



    }
}
