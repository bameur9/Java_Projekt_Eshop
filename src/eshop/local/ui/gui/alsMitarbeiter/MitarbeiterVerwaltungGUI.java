package eshop.local.ui.gui.alsMitarbeiter;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.ArtikelExistiertBereitsException;
import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.RegistrationFehlgeschlagenException;
import eshop.local.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Klasse zur Repraesentation der Klasse MitarbeiterVerwaltungGUI
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class MitarbeiterVerwaltungGUI extends JFrame {
    /**
     * Instanzvariable der Klasse Eshop
     */
    private final Eshop eshop;
    /**
     * Array von Typ JButton
     */
    JButton[] button;
    /**
     * Erzeugung des Contenaere
     */
    JPanel contenaire = new JPanel();

    /**
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public MitarbeiterVerwaltungGUI(String title, Eshop eshop) {
        super(title);
        this.eshop = eshop;
        contenaire.setBackground(Color.BLACK);

        JMenuBar menue = new JMenuBar();
        JMenu dateiMenue = new JMenu("Menue");

        JMenuItem speichern = new JMenuItem();
        speichern.setText("speichern");

        JMenuItem exit = new JMenuItem();
        exit.addActionListener(e ->{
            eshop.artikelSpeichern();
            System.exit(0);
        });
        exit.setText("beenden");

        dateiMenue.add(speichern);
        dateiMenue.add(exit);

        menue.add(dateiMenue);

        setJMenuBar(menue);

        add(titlePanel(), BorderLayout.PAGE_START);
        add(optionPanel(), BorderLayout.LINE_START);
        add(contenaire, BorderLayout.CENTER);

        // add(registrieren(false));
        setSize(new Dimension(910, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Panel fuer den Titel
     * @return panel
     */
    public JPanel titlePanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.yellow);

        JLabel title = new JLabel("MITARBEITERVERWALTUNG");
        title.setFont( new Font("Bodoni MT", Font.PLAIN,30));
        panel.add(title, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * @return panel
     */
    public JPanel optionPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,0, 10, 10));

         button = new JButton[9];
        for(int i = 0; i < button.length; i++){
            button[i] = new JButton();
            switch (i) {
                case 0 -> {
                    button[0].setText("Mitarbeiter registrieren!");
                    button[0].addActionListener(e -> {
                        contenaire.removeAll();
                        contenaire.add(optionRegistration());
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(false);

                        button[1].setEnabled(true);
                        button[2].setEnabled(true);
                        button[3].setEnabled(true);
                        button[4].setEnabled(true);
                        button[5].setEnabled(true);
                        button[6].setEnabled(true);
                    });
                }

                case 1 -> {
                    button[1].setText("Artikel einlegen!");
                    button[1].addActionListener(e -> {
                        contenaire.removeAll();
                        contenaire.add(optionArtikelEinlegen());
                        contenaire.revalidate();
                        contenaire.repaint();

                        button[0].setEnabled(true);
                        button[1].setEnabled(false);
                        button[2].setEnabled(true);
                        button[3].setEnabled(true);
                        button[4].setEnabled(true);
                        button[5].setEnabled(true);
                        button[6].setEnabled(true);

                    });
                }

                case 2 -> {
                    button[2].setText("Artikel erhoehen!");
                    button[2].addActionListener(e -> {
                        contenaire.removeAll();
                        contenaire.add(optionBestandErhoehen());
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(true);
                        button[1].setEnabled(true);
                        button[2].setEnabled(false);
                        button[3].setEnabled(true);
                        button[4].setEnabled(true);
                        button[5].setEnabled(true);
                        button[6].setEnabled(true);
                    });
                }

                case 3 -> {
                    button[3].setText("Artikel suchen!");
                    button[3].addActionListener(e -> {
                        contenaire.removeAll();
                        contenaire.add(optionArtikelSuchen());
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(true);
                        button[1].setEnabled(true);
                        button[2].setEnabled(true);
                        button[3].setEnabled(false);
                        button[4].setEnabled(true);
                        button[5].setEnabled(true);
                        button[6].setEnabled(true);
                    });
                }

                case 4 -> {
                    button[4].setText("Artikel Anzeigen!");
                    button[4].addActionListener(e ->{
                        contenaire.removeAll();
                        contenaire.add(optionArtikelAnzeigen());
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(true);
                        button[1].setEnabled(true);
                        button[2].setEnabled(true);
                        button[3].setEnabled(true);
                        button[4].setEnabled(false);
                        button[5].setEnabled(true);
                        button[6].setEnabled(true);
                    });
                }
                case 5 -> {
                    button[5].setText("Artikel loeschen!");
                    button[5].addActionListener(e -> {
                        contenaire.removeAll();
                        contenaire.add(optionArtikelLoeschen());
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(true);
                        button[1].setEnabled(true);
                        button[2].setEnabled(true);
                        button[3].setEnabled(true);
                        button[4].setEnabled(true);
                        button[5].setEnabled(false);
                        button[6].setEnabled(true);
                    });
                }
                case 6->{
                    button[6].setText("Ereignisse");
                    button[6].addActionListener(e->{
                        contenaire.removeAll();
                        contenaire.add( optionArtikelEreignis(), BorderLayout.CENTER);
                        contenaire.revalidate();
                        contenaire.repaint();
                        button[0].setEnabled(true);
                        button[1].setEnabled(true);
                        button[2].setEnabled(true);
                        button[3].setEnabled(true);
                        button[4].setEnabled(true);
                        button[5].setEnabled(true);
                        button[6].setEnabled(false);
                    });
                }

                case 7 -> {
                    button[7].setText("Ausloggen!");
                    button[7].addActionListener(e -> {
                        int ausloggen = JOptionPane.showConfirmDialog(null,
                                "Wollen Sie sich wirklich ausloggen?",
                                "Ausloggen",
                                JOptionPane.YES_NO_CANCEL_OPTION);

                        if(ausloggen == 0) {
                            eshop.ereignisSpeichern();
                            eshop.artikelSpeichern();
                            setVisible(false);
                            new MitarbeiterPortalGUI("Mitarbeiterportal", this.eshop);
                        }
                    });
                }

                case 8 ->{
                    button[8].setText("Beenden!");
                    button[8].addActionListener(e->{
                       int beenden= JOptionPane.showConfirmDialog(null,
                                "Wollen Sie wirklich beenden?",
                                "Beenden",
                                JOptionPane.YES_NO_CANCEL_OPTION);

                        if(beenden == 0) {
                            eshop.ereignisSpeichern();
                            eshop.artikelSpeichern();
                            System.exit(0);
                        }
                    });
                }
            }
            button[i].setEnabled(true);
            panel.add(button[i]);
        }
        return panel;
    }

    /**
     * @return panel
     */
    public JPanel optionRegistration(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2, 10, 10));

        JTextField[] textFields = new JTextField[3];
        JLabel[] label = new JLabel[3];

        for(int i = 0; i < label.length; i++){
            label[i] = new JLabel();
            textFields[i] = new JTextField();

            label[i].setFont( new Font("Bell MT", Font.PLAIN,20));
            textFields[i].setFont( new Font("Bell MT", Font.PLAIN,20));

            switch (i) {
                case 0 -> {
                    panel.add(new JPanel());
                    label[0].setText("Name: ");
                    panel.add(label[0]);
                    panel.add(textFields[0]);
                    panel.add(new JPanel());
                }
                case 1 -> {
                    panel.add(new JPanel());
                    label[1].setText("Benutzername: ");
                    panel.add(label[1]);
                    panel.add(textFields[1]);
                    panel.add(new JPanel());
                }
                case 2 -> {
                    panel.add(new JPanel());
                    label[2].setText("Passwort: ");
                    panel.add(label[2]);
                    panel.add(textFields[2]);
                    panel.add(new JPanel());
                }
            }
        }
        JButton confirm = new JButton("Registrieren!");

        confirm.addActionListener(e -> {
            String input1, input2, input3;
            input1 = textFields[0].getText();
            input2 = textFields[1].getText();
            input3 = textFields[2].getText();
            try {
                eshop.mitarbeiterRegistrieren(input1, input2,input3);
                JOptionPane.showMessageDialog(this, "Ein neuer Mitarbeiter wurde registriert", "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (RegistrationFehlgeschlagenException r) {
                JOptionPane.showMessageDialog(this, r.getMessage(), "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(new JPanel());
        panel.add(confirm);
        panel.add(new JPanel());

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,0, 10, 10));

        panel1.add(panel);
        panel1.add(new JPanel());

        return panel1;
    }

    /**
     * @return panel
     */
    public JPanel optionArtikelEinlegen(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2, 10, 10));

        JTextField[] textFields = new JTextField[4];
        JLabel[] label = new JLabel[4];

        for(int i = 0; i < label.length; i++){
            label[i] = new JLabel();
            textFields[i] = new JTextField();

            label[i].setFont( new Font("Bell MT", Font.PLAIN,20));
            textFields[i].setFont( new Font("Bell MT", Font.PLAIN,20));

            switch (i) {
                case 0 -> {
                    panel.add(new JPanel());
                    label[0].setText("Artikelnummer: ");
                    panel.add(label[0]);
                    panel.add(textFields[0]);
                    panel.add(new JPanel());
                }
                case 1 -> {
                    panel.add(new JPanel());
                    label[1].setText("Bezeichnung: ");
                    panel.add(label[1]);
                    panel.add(textFields[1]);
                    panel.add(new JPanel());
                }
                case 2 -> {
                    panel.add(new JPanel());
                    label[2].setText("menge: ");
                    panel.add(label[2]);
                    panel.add(textFields[2]);
                    panel.add(new JPanel());
                }
                case 3 -> {
                    panel.add(new JPanel());
                    label[3].setText("Preis: ");
                    panel.add(label[3]);
                    panel.add(textFields[3]);
                    panel.add(new JPanel());
                }
            }
        }
        JButton confirm = new JButton("Einlegen!");
        confirm.addActionListener(e -> {
            int input1;
            String input2;
            int input3;
            float input4;
                try {
                    input1 = Integer.parseInt(textFields[0].getText());
                    input2 = textFields[1].getText();
                    input3 = Integer.parseInt(textFields[2].getText());
                    input4 = Float.parseFloat(textFields[3].getText());

                    eshop.mitarbeiterLegtArtikelAn(input1, input2, input3, input4);
                    JOptionPane.showMessageDialog(this, "Ein neuer Artikel wurde eingelegt!", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (ArtikelExistiertBereitsException|NumberFormatException a) {
                    JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
        });
        panel.add(new JPanel());
        panel.add(confirm);
        panel.add(new JPanel());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,0, 10, 10));

        panel1.add(panel);
        panel1.add(new JPanel());

        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel1;
    }

    /**
     * @return panel
     */
    public JPanel optionBestandErhoehen(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2, 10, 10));

        JTextField[] textFields = new JTextField[2];
        JLabel[] label = new JLabel[2];

        for(int i = 0; i < label.length; i++){
            label[i] = new JLabel();
            textFields[i] = new JTextField();

            label[i].setFont( new Font("Bell MT", Font.PLAIN,20));
            textFields[i].setFont( new Font("Bell MT", Font.PLAIN,20));

            switch (i) {
                case 0 -> {
                    panel.add(new JPanel());
                    label[0].setText("Artikelnummer: ");
                    panel.add(label[0]);
                    panel.add(textFields[0]);
                    panel.add(new JPanel());
                }
                case 1 -> {
                    panel.add(new JPanel());
                    label[1].setText("Menge: ");
                    panel.add(label[1]);
                    panel.add(textFields[1]);
                    panel.add(new JPanel());
                }
            }
        }
        JButton confirm = new JButton("Erhoehen!");
        confirm.addActionListener(e -> {
            int input1, input2;
                try {
                    input1 = Integer.parseInt(textFields[0].getText());
                    input2 = Integer.parseInt(textFields[1].getText());

                    eshop.mitarbeiterErhoehtArtikel(input1, input2);
                    JOptionPane.showMessageDialog(this, "Bestand des Artikels mit Nr. "+input1+" wurde erhoehrt", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (ArtikelExistiertNichtException |NumberFormatException a) {
                    JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
        });
        panel.add(new JPanel());
        panel.add(confirm);
        panel.add(new JPanel());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,0, 40, 40));

        panel1.add(panel);
        panel1.add(new JPanel());
        panel1.add(new JPanel());

        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel1;
    }

    /**
     * @return panel
     */
    public JPanel optionArtikelSuchen(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,5, 0, 70));

        JTextField  textFields = new JTextField();
        JLabel  input = new JLabel("Artikelnummer: ");
        JButton confirm = new JButton("Suchen!");

        JLabel output = new JLabel("Output: ");
        output.setFont( new Font("Bell MT", Font.PLAIN,20));

        confirm.addActionListener(e -> {
                int input1;
                try {
                    input1 = Integer.parseInt(textFields.getText());
                    output.setText( eshop.mitarbeiterSuchtArtikel(input1).toString());
                    output.setForeground(Color.RED);
                }catch (ArtikelExistiertNichtException|NumberFormatException a){
                    output.setText("Artikel nicht gefunden!");
                    output.setForeground(Color.blue);
                    JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
        });

        input.setFont( new Font("Bell MT", Font.PLAIN,20));
        textFields.setFont( new Font("Bell MT", Font.PLAIN,20));

        panel.add(new JPanel());
        panel.add(input);
        panel.add(textFields);
        panel.add(confirm);
        panel.add(new JPanel());


        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(output);
        panel.add(new JPanel());
        panel.add(new JPanel());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,0, 50, 50));

        panel1.add(panel);
        panel1.add(output);
        panel1.add(new JPanel());

        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel1;
    }

    /**
     * @return panel
     */
    public JPanel optionArtikelAnzeigen(){
        JPanel panel = new JPanel();
        List<Artikel> artikelList = eshop.artikelImShopAusgeben();
        DefaultListModel<Artikel> l2 = new DefaultListModel<>();

        for(Artikel a : artikelList){
            l2.addElement(a);
        }

        JList<Artikel> list = new JList<>(l2);
        list.setFont( new Font("Bodoni MT", Font.PLAIN,20));
        list.setForeground(Color.BLUE);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(700,400));
        list.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * @return panel
     */
    public JPanel optionArtikelEreignis(){
        JPanel panel = new JPanel();
        DefaultListModel<String> l2 = new DefaultListModel<>();

        for(String str : eshop.ereignisLesen()){
            l2.addElement(str);
        }

        JList<String> list = new JList<>(l2);
        list.setFont( new Font("Bodoni MT", Font.PLAIN,20));
        list.setForeground(Color.BLUE);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(700,430));
        list.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * @return panel
     */
    public JPanel optionArtikelLoeschen(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,5, 0, 70));

        JTextField  textFields = new JTextField();
        JLabel  input = new JLabel("Artikelnummer: ");

        JButton confirm = new JButton("Loeschen!");
        confirm.addActionListener(e -> {
                int input1;
                try {
                    input1 = Integer.parseInt(textFields.getText());
                    eshop.mitarbeiterEntferntArtikel(input1);
                    JOptionPane.showMessageDialog(this, "Artikel mit Nr. "+input1+" wurde geloescht", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                }catch (ArtikelExistiertNichtException|NumberFormatException a){
                    JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
        });

        input.setFont( new Font("Bell MT", Font.PLAIN,20));
        textFields.setFont( new Font("Bell MT", Font.PLAIN,20));

        panel.add(new JPanel());
        panel.add(input);
        panel.add(textFields);
        panel.add(confirm);
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,0, 50, 50));

        panel1.add(panel);
        panel1.add(new JPanel());

        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel1;
    }
}
