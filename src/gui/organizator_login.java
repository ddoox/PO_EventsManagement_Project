package gui;
import model.ListaOrganizatorów;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class organizator_login {

    private ListaOrganizatorów lista;

    public JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    public JButton loginButton;
    private JButton registerButton;




    public organizator_login(ListaOrganizatorów _lista) {



        ListaOrganizatorów lista=_lista;


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log = textField1.getText();
                String pass = String.valueOf(passwordField1.getPassword());

                if (log.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(panel1, "Wpisz login i hasło");
                }
                else if (lista.login(log,pass) != null) {

                    JFrame events_frame = new JFrame("Organizator: Wydarzenia");
                    events_frame.setContentPane(new organizator_events(log,pass,lista,events_frame).panel1);
                    events_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    events_frame.pack();
                    events_frame.setVisible(true);
                    events_frame.setLocationRelativeTo(null);
                    events_frame.setResizable(false);

                }
                else {
                    JOptionPane.showMessageDialog(panel1, "Błędny login lub hasło");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log = textField1.getText();
                String pass = String.valueOf(passwordField1.getPassword());

                if (log.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(panel1, "Wpisz login i hasło");
                }
                else {
                    lista.rejestracja(log,pass);
                    JOptionPane.showMessageDialog(panel1, "Teraz możesz się zalogować");
                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    /*public static void main(String[] args) {
        JFrame org_login = new JFrame("Login");
        org_login.setContentPane(new organizator_login().panel1);
        org_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        org_login.pack();
        org_login.setVisible(true);
        org_login.setLocationRelativeTo(null);

    }*/
}
