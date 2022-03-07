package eshop.local.ui.gui.alsKunde;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.RegistrationFehlgeschlagenException;

import javax.swing.*;
import java.awt.*;


/**
 * Klasse zur Repraesentation der Klasse KundenRegistrieren
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class KundenRegistrieren extends JFrame {
    /**
     * Array von typ JLabel
     */
   private JLabel[] label;
    /**
     * Instanzvariable der Klasse JLabel
     */
   private JLabel title, text;
    /**
     * Array von typ JTextField
     */
   private JTextField[] textFields;
    /**
     * Instanzvariable vom Typ JButton
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

        title = new JLabel("KUNDENPORTAL");
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
        text = new JLabel("Kundenregistration");
        text.setFont( new Font("Bell MT", Font.PLAIN,35));

        panel.add(text, CENTER_ALIGNMENT);

        return panel;
    }

    /**
     *
     * @return panel
     */
    public JPanel registrationPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2, 10, 10));

        textFields = new JTextField[4];
        label = new JLabel[4];

        for(int i = 0; i < label.length; i++){
            label[i] = new JLabel();
            textFields[i] = new JTextField();

            label[i].setFont( new Font("Bell MT", Font.PLAIN,20));
            textFields[i].setFont( new Font("Bell MT", Font.PLAIN,20));

            switch (i){
                case 0:
                    panel.add(new JPanel());
                    label[0].setText("Name: ");
                    panel.add(label[0]);
                    panel.add(textFields[0]);
                    panel.add(new JPanel());
                    break;
                case 1:
                    panel.add(new JPanel());
                    label[1].setText("Adresse: ");
                    panel.add(label[1]);
                    panel.add(textFields[1]);
                    panel.add(new JPanel());
                    break;
                case 2:
                    panel.add(new JPanel());
                    label[2].setText("Benutzername: ");
                    panel.add(label[2]);
                    panel.add(textFields[2]);
                    panel.add(new JPanel());
                    break;
                case 3:
                    panel.add(new JPanel());
                    label[3].setText("Passwort: ");
                    panel.add(label[3]);
                    panel.add(textFields[3]);
                    panel.add(new JPanel());
            }
        }
        panel.setAlignmentX( Component.CENTER_ALIGNMENT);
        return panel;
    }

    /**
     *
     * @return panel
     */
    public JPanel buttonPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,5, 50, 70));

        confirm = new JButton("Registrieren!");
        confirm.addActionListener(e -> {
            String input1, input2, input3, input4;
            input1 = textFields[0].getText();
            input2 = textFields[1].getText();
            input3 = textFields[2].getText();
            input4 = textFields[3].getText();

            try {
                eshop.kundeRegistrieren(input1, input2, input3, input4);
                setVisible(false);
                new KundenPortalGUI("Kundenportal", this.eshop).setVisible(true);
            } catch (RegistrationFehlgeschlagenException r) {
                JOptionPane.showMessageDialog(this, r.getMessage(), "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        zurueck = new JButton("Zurueck");
        zurueck.addActionListener(e -> {
            setVisible(false);
            new KundenPortalGUI("Kundenportal", this.eshop).setVisible(true);
        });
        panel.add(new JPanel());
        panel.add(zurueck);
        panel.add(confirm);
        panel.add(new JPanel());

        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());

        panel.setAlignmentX( Component.CENTER_ALIGNMENT);

        return panel;
    }

    /**
     * Konstruktor
     *
     * @param title Titel von GUI
     * @param eshop Instanzvariable der Klasse Eshop
     */
    public KundenRegistrieren(String title, Eshop eshop) {
        super(title);
        this.eshop = eshop;
        setLayout(new GridLayout(4,0, 40, 20));
        add(titlePanel());
        add(textPanel());

        add(registrationPanel());
        add(add(buttonPanel()));

        setSize(new Dimension(910, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
