package eshop.local.ui.gui.alsKunde;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.LoginFehlgeschlagenException;
import eshop.local.ui.gui.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasse zur Repraesentation der Klasse KundenPortalGUI
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class KundenPortalGUI extends JFrame {
    /**
     * Instanzvariable der Klasse JLabel
     */
    private JLabel title, text, benutzername, passwort, registrieren;
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
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public KundenPortalGUI(String title, Eshop eshop) {
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

    /**
     * Panel fuer den Titel
     *
     * @return panel
     */
    public JPanel titlePanel(){
        JPanel panel = new JPanel();
        // panel.setLayout(new GridLayout(2,0, 10, 10));
        panel.setBackground(Color.yellow);

        title = new JLabel("KUNDENPORTAL");
        title.setFont( new Font("Bodoni MT", Font.PLAIN,30));

        panel.add(title, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Panel fuer text
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
     * Panel zum Einloggen
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

        registrieren = new JLabel("Noch kein Konto?, hier registrieren");
        registrieren.setFont( new Font("Bell MT", Font.ITALIC,15));
        registrieren.setForeground(Color.blue);
        registrieren.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)   {
                    setVisible(false);
                    new KundenRegistrieren("KundenRegistrieren",eshop).setVisible(true);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                registrieren.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registrieren.setForeground(Color.blue);
            }
        });

        confirm = new JButton("Einloggen!");
        confirm.addActionListener(e -> {
            String input1 =  textfield.getText();
            String input2 =  new String(passfield.getPassword());
            try {
                eshop.kundeEinloggen(input1, input2);
                setVisible(false);
                new KundenVerwaltungGUI("Kundenverwaltung", this.eshop).setVisible(true);
            } catch (LoginFehlgeschlagenException|NumberFormatException l) {
                JOptionPane.showMessageDialog(this, l.getMessage(), "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        zurueck = new JButton("Zurueck");
        zurueck.addActionListener(e -> {
            setVisible(false);
            new Home("EshopGUI", this.eshop ).setVisible(true);

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
        panel.add(registrieren);
        panel.add(new JPanel());
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(zurueck);
        panel.add(confirm);

        panel.setMaximumSize( new Dimension(  100, 100) );
        panel.setAlignmentX( Component.CENTER_ALIGNMENT);

        return panel;
    }
}
