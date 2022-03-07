package eshop.local.ui.cui;

import eshop.local.domain.Eshop;
import eshop.local.domain.exception.*;
import eshop.local.valueObjects.Artikel;

import static java.lang.Thread.sleep;

/**
 * Klasse zur Repraesentation der Klasse Mitarbeiterportal (Untermenü des Kunden).
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Mitarbeiterportal {
    //Attribut der Klasse Mitarbeiterportal
    private final Eshop eshop;
    private  String wahl="";

    /**
     * Konstruktor
     *
     * @param es Eshop-objekt
     */
    public Mitarbeiterportal(Eshop es){
        eshop = es;
    }

    /**
     * Methode des erstes Menüs nach als Mitarbeiter
     */
    public void alsMitarbeiterWeiter() {
            IO.println("\n------------Mitarbeiternportal---------------");
            IO.println("Einloggen: ------------- 'a'");
            IO.println("Beenden: --------------- 'z'");
            wahl = IO.readString("Ihre Wahl : ");

        while(true){
            switch (wahl) {
                case "a", "A" -> optionMitarbeiterEinloggen();
                case "z", "Z" -> {
                    eshop.artikelSpeichern();
                    eshop.ereignisSpeichern();
                    System.exit(0);
                }
                default -> IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!");
            }
            wahl = IO.readString("Ihre Wahl : ");
        }
    }

    /**
     * Methode zum Einloggen des Mitarbeiters
     */
    private void optionMitarbeiterEinloggen() {
        IO.println("\n----------------Einloggen:--------------------");
        String benutzername = IO.readString("Benutzername: ");
        String passwort = IO.readString("Passwort: ");
        try {
            eshop.mitarbeiterEinloggen(benutzername, passwort);
            IO.println("\nHallo "+benutzername+" Du bist Online!");
            mitarbeiterIsOnline();
        } catch (LoginFehlgeschlagenException l) {
            IO.printErr(l.getMessage());
        alsMitarbeiterWeiter();
        }
    }

    /**
     * Methode des zweites Menüs, Wenn der Mitarbeiter erfolgreich eingeloggt wurde
     */
    private void mitarbeiterIsOnline() {
            IO.println("\n------------------ Artikel Im Shop----------------");
            for(Artikel a : eshop.artikelImShopAusgeben() ){
                IO.println(a);
            }

            IO.println("\n---------------------Ihre Optionen----------------");
            IO.println("Neuen Mitarbeiter KundenRegistrieren:-- 'a'");
            IO.println("Artikel einlegen: --------------- 'b'");
            IO.println("Artikelbestand erhoehen: ---------'c'");
            IO.println("Artikel suchen: ----------------- 'd'");
            IO.println("Artikel loeschen: ----------------'e'");
            IO.println("Ereignisse einzeigen: ------------'f'");
            IO.println("Ausloggen: -----------------------'x'");
            IO.println("Beenden: -------------------------'z'");

            wahl = IO.readString("\nIhre Wahl : ");
        while(true){
            switch (wahl) {
                case "a","A" -> optionMitarbeiterRegistrieren();
                case "b","B" -> optionArtikelEinlegen();
                case "c","C" -> {
                    optionArtikelBestandErhoehen();
                    mitarbeiterIsOnline();
                }
                case "d","D" -> {
                    optionArtikelSuchen();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                       IO.println(e.getMessage());
                    }
                    mitarbeiterIsOnline();
                }
                case "e","E" -> {
                    optionArtikelLoeschen();
                    mitarbeiterIsOnline();
                }
                case "f", "F" -> optionEreignisseAnzeigen();
                case "x","X" ->{
                    alsMitarbeiterWeiter();
                    eshop.artikelSpeichern();
                }
                case "z","Z" -> {
                    eshop.artikelSpeichern();
                    eshop.ereignisSpeichern();
                    System.exit(0);
                }
                default ->  IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!");
            }
            wahl = IO.readString("\nIhre Wahl : ");
        }
    }

    /**
     * Methode zur Registration eines Mitarbeiter
     */
    private void optionMitarbeiterRegistrieren() {
        IO.println("\n---------------Registrierung:--------------");
        String name = IO.readString("Name: ");
        String benutzername = IO.readString("Benutzername: ");
        String passwort = IO.readString("Passwort: ");

        try {
            eshop.mitarbeiterRegistrieren(name, benutzername, passwort);
        } catch (RegistrationFehlgeschlagenException r) {
            IO.printErr(r.getMessage());
        }
        mitarbeiterIsOnline();
    }

    /**
     * Methode zur Einlagerung eines Artikel
     */
    private void optionArtikelEinlegen() {
        IO.println("\n---------------Neuer Artikel:--------------");
        try {
            int artikelNummer = IO.readInt("Artikelnummer: ");
            String bezeichner = IO.readString("Bezeichner: ");
            int menge = IO.readInt("Menge: ");
            float preis = IO.readFloat("Preis: ");

            eshop.mitarbeiterLegtArtikelAn(artikelNummer, bezeichner, menge, preis);
            IO.println("Artikelbestand von Artikel Nr. " + artikelNummer + " wurde erhöht !");
        } catch (ArtikelExistiertBereitsException | NumberFormatException a) {
            IO.printErr(a.getMessage());
        }
        mitarbeiterIsOnline();
    }

    /**
     * Methode zur Erhoehung eines Artikels
     */
    private void optionArtikelBestandErhoehen() {
        IO.println("\n---------Bestand eines Artikels:--------------");
        try {
            int artikelNummer = IO.readInt("Artikelnummer: ");
            int menge = IO.readInt("Menge: ");

            eshop.mitarbeiterErhoehtArtikel(artikelNummer, menge);
        } catch (ArtikelExistiertNichtException| NumberFormatException a) {
            IO.printErr(a.getMessage());
        }
    }

    /**
     * Methode zur Suchung eines Artikels
     */
    private void optionArtikelSuchen()  {
        IO.println("\n-----------------Gesuchte Artikel:---------------");
        try {
            int artikelNummer = IO.readInt("Artikelnummer: ");
           IO.println(eshop.mitarbeiterSuchtArtikel(artikelNummer));
        } catch (ArtikelExistiertNichtException | NumberFormatException a) {
            IO.printErr(a.getMessage());
        }
    }

    /**
     * Methode zur Entfernung (Auslagerung) eines Artikels
     */
    private void optionArtikelLoeschen() {
        IO.println("\n---------------Artikel löschen:----------------");
        try {
            int artikelNummer = IO.readInt("Artikelnummer: ");
            eshop.mitarbeiterEntferntArtikel(artikelNummer);
        } catch (ArtikelExistiertNichtException| NumberFormatException a) {
            IO.printErr(a.getMessage());
        }
    }

    /**
     * Methode zur Anzeige des Ereignisses
     */
    private void optionEreignisseAnzeigen()  {
        IO.println("\n---------------Ereignis anzeigen:----------------");
        for(String str : eshop.ereignisLesen()) {
                IO.println(str);
        }
        mitarbeiterIsOnline();
    }
}
