package eshop.local.ui.gui;
import eshop.local.domain.Eshop;
import eshop.local.ui.gui.alsKunde.KundenPortalGUI;
import eshop.local.ui.gui.alsMitarbeiter.MitarbeiterPortalGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Klasse zur Repraesentation des Homes
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Home extends JFrame {
    /**
     * Instanzvairable der Klasse JRadioButton
     */
    private JRadioButton alsKunde, alsMitarbeiter;
    /**
     * Instanzvariavele der Klasse Eshop
     */
    private final Eshop eshop;

    /**
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public Home(String title, Eshop eshop) {
        super(title);
        this.eshop = eshop;
        setLayout(new GridLayout(7,0, 20, 20));

        add(titlePanel());
        add(textPanel());
        add(new JPanel());
        add(radioButtonPanel());
        add( buttonPanel());

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
       // panel.setLayout(new GridLayout(2,0, 10, 10));
        panel.setBackground(Color.yellow);

        JLabel labelTitle = new JLabel("WILLKOMMEN IN UNSEREM ESHOP");
        labelTitle.setFont( new Font("Bodoni MT Black", Font.PLAIN,40));

        panel.add(labelTitle, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Panel fuer den Text
     *
     * @return panel
     */
    public JPanel textPanel(){
        JPanel panel = new JPanel();
        JLabel text = new JLabel("Weiter Als :");
        text.setFont( new Font("Bell MT", Font.PLAIN,20));

        panel.add(text, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Panel fuer die RadioButton
     *
     * @return panel
     */
    public JPanel radioButtonPanel(){
        JPanel panel = new JPanel();

        alsKunde = new JRadioButton("Kunde");
        alsKunde.setFont( new Font("Bell MT", Font.BOLD,20));

        alsMitarbeiter = new JRadioButton("Mitarbeiter");
        alsMitarbeiter.setFont( new Font("Bell MT", Font.BOLD,20));

        ButtonGroup group = new ButtonGroup();
        group.add(alsKunde);
        group.add(alsMitarbeiter);

        //die Addition ist ausgewählt
        alsKunde.setSelected(true);
        panel.add(alsKunde, CENTER_ALIGNMENT);
        panel.add(alsMitarbeiter, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Panel fuer die Schaltflaeche
     * @return panel
     */
    public JPanel buttonPanel(){
        JPanel panel = new JPanel();
        JButton confirmWahl = new JButton("Confirm!");

        confirmWahl.addActionListener(e -> {
                if(alsKunde.isSelected()){
                    setVisible(false);
                    new KundenPortalGUI("Kundenportal", this.eshop);
                }
                if(alsMitarbeiter.isSelected()){
                   setVisible(false);
                   new MitarbeiterPortalGUI("Mitarbeiterportal", this.eshop);
                }
        });

        panel.add(confirmWahl, CENTER_ALIGNMENT);
        return panel;
    }
}
