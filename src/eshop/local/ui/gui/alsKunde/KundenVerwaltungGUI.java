package eshop.local.ui.gui.alsKunde;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.WarenkorbException;
import eshop.local.valueObjects.Artikel;
import eshop.local.valueObjects.PositionImWarenkorb;

import javax.swing.*;
import java.awt.*;

import java.util.List;

/**
 * Klasse zur Repraesentation der Klasse KundenVerwaltungGUI
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class KundenVerwaltungGUI extends JFrame {
    /**
     * Instanzvarible der Klasse JLabel
     */
    private JLabel title;
    /**
     * Instanzvarible der Klasse Eshop
     */
     private Eshop eshop;
    /**
     * Erzeugung eines Panels
     */
   private  JPanel pane = new JPanel();
    /**
     * Erzeugung eines Panels
     */
   private JPanel pane1 = new JPanel();

    /**
     * Panel fuer Titel
     * @return panel
     */
    public JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.yellow);

        title = new JLabel("KUNDENVERWALTUNG");
        title.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        panel.add(title, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Panel fuer die Option von Warenkorb
     * @return panel
     */
    public JPanel optionArtikelAnzeigen() {
        JPanel panel = new JPanel();

        List<Artikel> artikelList = eshop.artikelImShopAusgeben();
        DefaultListModel<String> l2 = new DefaultListModel<>();

        for (Artikel a : artikelList) {
            l2.addElement(a.artikelKundePortal());
           // l2.addElement(a.toString());
        }

        JList<String> list = new JList<>(l2);

        list.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
        list.setForeground(Color.BLUE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        list.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Panel fuer den Warenkorb ausgabe
     * @return panel
     */
    public JPanel warenkorbPanel() {
        JPanel panel = new JPanel();
        List<PositionImWarenkorb> positionen = eshop.kundegibtArtikelImWKAus().getPositionen();
        DefaultListModel<PositionImWarenkorb> l2 = new DefaultListModel<>();

        for (PositionImWarenkorb position : positionen) {
            l2.addElement(position);
        }

        JList<PositionImWarenkorb> list = new JList<>(l2);

        list.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
        list.setForeground(Color.BLUE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        list.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * @return panel
     */
    public JPanel warenkorbOption() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 0, 10, 10));

        JButton kaufen = new JButton("Artikel kaufen!");
        kaufen.addActionListener(e->{
            try {
                JOptionPane.showMessageDialog(this,"RECHNUNG: \n"+ eshop.rechnungErzeugen(), "Rechnung",
                        JOptionPane.INFORMATION_MESSAGE);
                eshop.kundeKauftArtikel();
                eshop.artikelSpeichern();
                pane.removeAll();
                pane.add(warenkorbPanel());
                pane.revalidate();
                pane.repaint();
            } catch (WarenkorbException w) {
                JOptionPane.showMessageDialog(this, w.getMessage(), "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }

        });

        JLabel label = new JLabel("ID_Nummer:");
        label.setFont(new Font("Bodoni MT", Font.PLAIN, 15));

        JTextField field = new JTextField();
        field.setFont(new Font("Bodoni MT", Font.PLAIN, 20));

        JButton artikelloeschen = new JButton("Artikel loeschen!");
        artikelloeschen.addActionListener(e -> {
            try {
                int input = Integer.parseInt(field.getText());
                eshop.kundeEntferntArtikelVomWk(input);
                pane.removeAll();
                pane.add(warenkorbPanel());
                pane.revalidate();
                pane.repaint();

            } catch (ArtikelExistiertNichtException| NumberFormatException a) {
                JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton warenkorbEntleeren = new JButton("Warenkorb entleeren!");
        warenkorbEntleeren.addActionListener(e ->{
            eshop.kundeEntleertWK();

            pane.removeAll();
            pane.add(optionArtikelAnzeigen());
            pane.revalidate();
            pane.repaint();

            pane1.removeAll();
            pane1.add(jPaneloptionPanel());
            pane1.revalidate();
            pane1.repaint();
        });

        JButton neuerArtikelEinlegen = new JButton("Neuer Artikel einlegen!");
        neuerArtikelEinlegen.addActionListener(e ->{
            pane.removeAll();
            pane1.removeAll();

            pane.add(optionArtikelAnzeigen());
            pane1.add(jPaneloptionPanel());

            pane.revalidate();
            pane.repaint();

            pane1.revalidate();
            pane1.repaint();
        });

        panel.add(new JPanel());
        panel.add(kaufen);
        panel.add(new JPanel());
        panel.add(label);
        panel.add(field);
        panel.add(artikelloeschen);
        panel.add(new JPanel());
        panel.add(warenkorbEntleeren);
        panel.add(new JPanel());
        panel.add(neuerArtikelEinlegen);

        return panel;
    }

    /**
     *
     * @return panel
     */
    public JPanel jPaneloptionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 2, 10, 10));

        JLabel text = new JLabel("ID_Nummer:");
        text.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
        JTextField idField = new JTextField();
        idField.setFont(new Font("Bodoni MT", Font.PLAIN, 20));

        JLabel text1 = new JLabel("Menge:");
        text1.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
        JTextField mengeField = new JTextField();
        mengeField.setFont(new Font("Bodoni MT", Font.PLAIN, 20));

        JButton einleggen = new JButton("Artikel im Warenkorb einlegen!");
        einleggen.addActionListener(e -> {
                    int input1, input2;
                    try {
                        input1 = Integer.parseInt(idField.getText());
                        input2 = Integer.parseInt(mengeField.getText());
                        eshop.kundeLegtArtikelImWkEin(input1, input2);
                        JOptionPane.showMessageDialog(this, "Artikel Nr." + input1
                                        + " wurde im Warenkorb einlegt. ", "Warenkorb Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        idField.setText("");
                        mengeField.setText("");
                    } catch (ArtikelExistiertNichtException | NumberFormatException a) {
                        JOptionPane.showMessageDialog(this, a.getMessage(), "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
        panel.add(text);
        panel.add(idField);
        panel.add(text1);
        panel.add(mengeField);

        panel.add(einleggen);
        panel.add(new JPanel());

        JButton wk = new JButton("Warenkorb anzeigen!");
        wk.addActionListener(e -> {
            pane.removeAll();

            pane.add(warenkorbPanel());
            pane1.removeAll();
            pane1.add(warenkorbOption());

            pane.revalidate();
            pane.repaint();
            pane1.revalidate();
            pane1.repaint();
            //eshop.artikelSpeichern();
        });
        JButton ausloggen = new JButton("Ausloggen!");
        ausloggen.addActionListener(e ->{
            int ausloggen1= JOptionPane.showConfirmDialog(null,
                    "Wollen Sie sich wirklich ausloggen?",
                    "Ausloggen",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if(ausloggen1 == 0) {
                eshop.ereignisSpeichern();
                eshop.artikelSpeichern();
                setVisible(false);
                new KundenPortalGUI("Kundenportal", eshop).setVisible(true);
            }
        });
        JButton beenden = new JButton("Beenden!");
        beenden.addActionListener(e -> {
            int beenden1= JOptionPane.showConfirmDialog(null,
                    "Wollen Sie wirklich beenden?",
                    "Beenden",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if(beenden1 == 0) {
                eshop.ereignisSpeichern();
                eshop.artikelSpeichern();
                System.exit(0);
            }
        });

        panel.add(wk);
        panel.add(new JPanel());
        panel.add(ausloggen);
        panel.add(beenden);
        return panel;
    }

    /**
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public KundenVerwaltungGUI(String title, Eshop eshop) {
        super(title);
        this.eshop = eshop;
        pane.setForeground(Color.BLACK);
        pane1.setForeground(Color.yellow);

        JMenuBar menue = new JMenuBar();
        JMenu dateiMenue = new JMenu("Menue");

        JMenuItem speichern = new JMenuItem();
        speichern.setText("speichern");

        JMenuItem exit = new JMenuItem();
        exit.addActionListener(e -> {
            eshop.ereignisSpeichern();
            eshop.artikelSpeichern();
            System.exit(0);
        });

        exit.setText("beenden");
        dateiMenue.add(speichern);
        dateiMenue.add(exit);
        menue.add(dateiMenue);

        setJMenuBar(menue);
        pane.add(optionArtikelAnzeigen());
        pane1.add(jPaneloptionPanel());

        add(titlePanel(), BorderLayout.PAGE_START);
        add(pane1, BorderLayout.LINE_START);
        add(pane, BorderLayout.CENTER);

        setSize(new Dimension(910, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
