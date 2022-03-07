package eshop.local.ui.cui;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.LoginFehlgeschlagenException;
import eshop.local.domain.exception.RegistrationFehlgeschlagenException;
import eshop.local.domain.exception.WarenkorbException;
import eshop.local.valueObjects.Artikel;
import eshop.local.valueObjects.PositionImWarenkorb;


/**
 * Klasse zur Repraesentation der Klasse Kundenportal (Untermenü des Kunden).
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Kundenportal {
    //Attribute der Klasse Kundenportal
    private final Eshop eshop;
    private  String wahl="";

    /**
     * Konstruktor
     *
     * @param es Eshop-Objekt
     */
    public Kundenportal(Eshop es){
       eshop = es;
    }

    /**
     * Methode des erstes Menüs nach als Kunde
     */
    public void alsKundeWeiter() {
        IO.println("\n------------Kundenportal---------------");
        IO.println("Einloggen: ------------- 'a'");
        IO.println("KundenRegistrieren: ---------- 'b'");
        IO.println("Beenden: --------------- 'z'");

        wahl = IO.readString("\nIhre Wahl : ");
        while (true){
            switch (wahl) {
                case "a", "A" -> optionKundeEinloggen();
                case "b", "B" -> optionKundeRegistrieren();
                case "z", "Z" -> System.exit(0);
                default -> IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!");
            }
            wahl = IO.readString("\nIhre Wahl : ");
        }
    }

    /**
     * Methode zur Registrierung des Kunden
     */
    private void optionKundeRegistrieren()  {
        IO.println("\n---------------Registrierung:--------------");
        String name = IO.readString("Name: ");
        String adresse = IO.readString("Adresse: ");
        String benutzername = IO.readString("Benutzername: ");
        String passwort = IO.readString("Passwort: ");
        try {
            eshop.kundeRegistrieren( name, adresse, benutzername, passwort);
        } catch (RegistrationFehlgeschlagenException r) {
            IO.printErr(r.getMessage());
        }
        alsKundeWeiter();
    }

    /**
     * Methode zum Einloggen des Kunden
     */
    private void optionKundeEinloggen()  {
        IO.println("\n----------------Einloggen:--------------------");
        String benutzername = IO.readString("Benutzername: ");
        String passwort = IO.readString("Passwort: ");
        try{
            eshop.kundeEinloggen(benutzername, passwort);
            IO.println("\nHallo "+benutzername+" Du bist Online!");
            kundeIsOnline();
        }catch(LoginFehlgeschlagenException e){
            IO.printErr(e.getMessage());
            alsKundeWeiter();
        }
    }

    /**
     * Methode zur Ausgabe der Rechnung
     */
    private void rechnung(){
        IO.println("\n------------------Rechnung:----------------------");
        try {
           IO.println(eshop.rechnungErzeugen());
            eshop.kundeKauftArtikel();
            eshop.artikelSpeichern();
        } catch (WarenkorbException e) {
            IO.println(e.getMessage());
        }
    }

    /**
     * Methode zur Anzeige alle Artikel im Warenkorb
     */
    public void artikelImWKAnzeigen(){
        IO.println("\n--------------Artikel im Warenkorb---------------");
        try {
            for (PositionImWarenkorb p : eshop.kundegibtArtikelImWKAus().getPositionen()) {
                IO.println(p);
            }
        }catch (NullPointerException e){ }
        optionWarenkorb();
    }

    /**
     * Methode des zweites Menüs, Wenn der Kunde erfolgreich eingeloggt wurde
     */
    private void kundeIsOnline() {
        IO.println("\n------------------Artikel im Shop----------------");
        //Hier wird die Liste von Artkiel im Lager eingezeigt mit Hilfe einer Foreach -Schleife

        eshop.artikelImShopAusgeben().forEach(e -> {IO.println(e.artikelKundePortal());});

        IO.println("\n---------------------Ihre Optionen----------------");
        IO.println("Artikel im Warenkorb einlegen: --- 'a'");
        IO.println("Warenkorb anzeigen: -------------- 'b'");
        IO.println("Ausloggen: ----------------------- 'x'");
        IO.println("Beenden: ------------------------- 'z'");
        wahl = IO.readString("\nIhre Wahl : ");

        while(true) {
            switch (wahl) {
                case "a", "A" -> optionLegeArtikelEin();
                case "b", "B" -> artikelImWKAnzeigen();
                case "x", "X" -> alsKundeWeiter();
                case "z", "Z" -> {
                    eshop.ereignisSpeichern();
                    eshop.artikelSpeichern();
                    System.exit(0);
                }
                default -> IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!");
            }
            wahl = IO.readString("\nIhre Wahl : ");
        }
    }

    /**
     * Methode zum Einlegen des Artikels im Warenkorb
     */
    private void optionLegeArtikelEin() {
        IO.println("\n---------Lege Artikel im Warenkorb ein---------");
        try {
            int artikelNummer = IO.readInt("Artikelnummer: ");
            int menge = IO.readInt("Menge: ");
            eshop.kundeLegtArtikelImWkEin(artikelNummer,menge);
        } catch (ArtikelExistiertNichtException | NumberFormatException ar) {
            IO.printErr(ar.getMessage());
        }
        kundeIsOnline();
    }

    /**
     * Methode zum Entfernen eines Artikel aus dem Warenkorb
     */
    private void artikelImKorbEntfernen(){
        IO.println("Geben Sie die Nummer der zu loeschende Artikel :");
        try {
            int artikelNummer = IO.readInt("Artikelnummer :");
            eshop.kundeEntferntArtikelVomWk(artikelNummer );
        } catch (ArtikelExistiertNichtException | NumberFormatException ex) {
            IO.printErr(ex.getMessage());
        }
    }

    /**
     * Methode des drittes Untermenüs, wenn der Kunde sein Warenkorb sehen möchte
     */
    private void optionWarenkorb() {
        while(true) {
        IO.println("\n-------------------Dein Warenkorb----------------");
        IO.println("Artikel im Warenkorb kaufen: ---- 'a'");
        IO.println("Artikel im Warenkorb loeschen: ---'b'");
        IO.println("Warenkorb entleeren: ------------ 'c'");
        IO.println("Neuer Artikel einlegen:-----------'d'");
        IO.println("Ausloggen: ---------------------- 'x'");
        IO.println("Beenden: ------------------------ 'z'");
        wahl = IO.readString("\nIhre Wahl : ");

            switch (wahl) {
                case "a", "A" -> {
                    rechnung();
                    kundeIsOnline();
                }
                case "b", "B" -> {
                    artikelImKorbEntfernen();
                    artikelImWKAnzeigen();
                    optionWarenkorb();
                }
                case "c", "C" -> {
                    eshop.kundeEntleertWK();
                    kundeIsOnline();
                }
                case "d", "D" -> kundeIsOnline();
                case "x", "X" -> alsKundeWeiter();
                case "z", "Z" -> {
                    eshop.ereignisSpeichern();
                    eshop.artikelSpeichern();
                    System.exit(0);
                }
                default -> IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!");
            }
            wahl = IO.readString("\nIhre Wahl : ");
        }
    }
}
