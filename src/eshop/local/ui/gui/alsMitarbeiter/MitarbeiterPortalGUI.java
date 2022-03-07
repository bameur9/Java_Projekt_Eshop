package eshop.local.ui.gui.alsMitarbeiter;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.LoginFehlgeschlagenException;
import eshop.local.ui.gui.Home;

import javax.swing.*;
import java.awt.*;

/**
 * Klasse zur Repraesentation der Klasse MitarbeiterPortalGUI
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class MitarbeiterPortalGUI extends JFrame {
    /**
     * Instanzvariable der Klasse JLabel
     */
    private JLabel title, text, benutzername, passwort;
    /**
     * Instanzvariable der Klasse JTextField
     */
    private JTextField textfield;
    /**
     * Instanzvariable der Klasse JPasswordField
     */
    private JPasswordField passfield;
    /**
     * Instanzvariable der Klasse JButton
     */
    private JButton confirm , zurueck;
    /**
     * Instanzvariable der Klasse Eshop
     */
    private Eshop eshop;

    /**
     *
     * @return panel
     */
    public JPanel titlePanel(){
        JPanel panel = new JPanel();
        // panel.setLayout(new GridLayout(2,0, 10, 10));
        panel.setBackground(Color.yellow);

        title = new JLabel("MITARBEITERPORTAL");
        title.setFont( new Font("Bodoni MT", Font.PLAIN,30));

        panel.add(title, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     *
     * @return panel
     */
    public JPanel textPanel(){
        JPanel panel = new JPanel();
        // panel.setBackground(Color.green);
        text = new JLabel("Einloggen");
        text.setFont( new Font("Bell MT", Font.PLAIN,35));

        panel.add(text, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     *
     * @return panel
     */
    public JPanel einloggen(){
        JPanel panel = new JPanel();
        // panel.setBackground(Color.red);
        panel.setLayout(new GridLayout(4, 8, 70, 10));
        panel.setMaximumSize(new Dimension(300, 300));

        benutzername = new JLabel("Benutzername");
        benutzername.setFont( new Font("Bell MT", Font.PLAIN,20));
        passwort = new JLabel("Passwort");
        passwort.setFont( new Font("Bell MT", Font.PLAIN,20));

        textfield = new JTextField();
        textfield.setFont( new Font("Bell MT", Font.PLAIN,20));
        passfield = new JPasswordField();
        passfield.setFont( new Font("Bell MT", Font.PLAIN,20));

        confirm = new JButton("Einloggen!");
        confirm.addActionListener(e -> {
            String input1 =  textfield.getText();
           String  input2 =  new String(passfield.getPassword());
            try {
                eshop.mitarbeiterEinloggen(input1, input2);
                setVisible(false);
                new MitarbeiterVerwaltungGUI("Mitarbeiterverwaltung",this.eshop).setVisible(true);
            } catch (LoginFehlgeschlagenException l) {
                JOptionPane.showMessageDialog(this, l.getMessage(), "Fehler",
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        zurueck = new JButton("Zurueck");
        zurueck.addActionListener(e -> {
            setVisible(false);
            new Home("EshopGUI", this.eshop).setVisible(true);
        });

        panel.add(new JPanel());
        panel.add(benutzername);
        panel.add(textfield);
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(passwort);
        panel.add(passfield);
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(zurueck);
        panel.add(confirm);

        panel.setMaximumSize( new Dimension(  100, 100) );
        panel.setAlignmentX( Component.CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public MitarbeiterPortalGUI(String title, Eshop eshop) {
        super(title);
        this.eshop = eshop;
        setLayout(new GridLayout(4,0, 40, 20));

        add(titlePanel());
        // add(new JPanel());
        add(textPanel());

        //add(new JPanel());
        add(einloggen());

        setSize(new Dimension(910, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
