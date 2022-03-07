package eshop.local.valueObjects;

import eshop.local.domain.exception.ArtikelExistiertNichtException;

/**
 * Klasse zur Repraesentation eines Kundes.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Kunde extends Person {
    // Attribute zur Beschreibung eines Kunden
    private final String adresse;
    //Instanzevariable der Klasse Warenkorb
    private Warenkorb warenkorb;
    //Statische Variable fuer die ID-Nummer.
    /**
     * Statische Variable fuer die ID-Nummer des Kunden
     */
    public static int idNummer;

    /**
     * Konstruktor fuer Objekt der Klasse Kunde
     *
     * @param name Name des Kunden
     * @param adresse Adresse des Kunden
     * @param benutzername Username des Kunden
     * @param passwort Passwort des Kunden
     */
    public Kunde(String name, String adresse, String benutzername, String passwort) {
        super(name, benutzername, passwort);
        this.adresse = adresse;
        //Hier wird einen Warenkorb erstellt, jedes mal, wenn ein Kunde-Objekt erzeugt wird
        this.warenkorb= new Warenkorb();
    }


    /**
     * Methode fuege einen Artikel im Warenkorb hinzu.
     *
     * @param artikel der hinzuzufuegende Artikel
     * @param menge  die hinzuzufuegende Menge
     */
    public void legeArtikelInWarenkorbEin(Artikel artikel, int menge){
        warenkorb.fuegePositionHinzu(artikel, menge);
    }

    /**
     * Methode zum Loeschen die gekauften artikel im Warenkorb nach dem Kauf
     * Nur die Artikel mit der passenden Menge, werden geloescht
     */
    public void warenkorbStandNachKauf(){warenkorb.gekaufteArtikelImWKLoeschen();}

    /**
     * Methode zur Entleerung des Warenkorbs
     */
    public void warenkorbEntleeren(){
        warenkorb.warenkorbEntleeren();
    }

    /**
     * Methode loescht einen Artikel
     *
     * @param artikelNummer der zuloeschende Artikel
     * @throws ArtikelExistiertNichtException Fehlermeldung wenn ein Artikel nicht verhanden ist.
     */
    public void einArtikelLoeschen(int artikelNummer) throws ArtikelExistiertNichtException {warenkorb.einePositionLoeschen(artikelNummer);}

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Mitarbeiter-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Kunde{" +
                "\n\t\t ID-Nummer : "+idNummer+
                "\n\t\t Name : "+name+
                "\n\t\t Adresse :  " + adresse+
                "\n\t\t Benutzername : "+ benutzername+
                "\n\t\t Passwort: "+ passwort+
                "\n\t\t Warenkorb : " + warenkorb+
                "\n\t\t}";
    }

    /**
     * Methode gibt einen Warenkorb zurueck
     *
     * @return die Variable Warenkorb
     */
    public Warenkorb getWarenkorb() {return warenkorb;}

    /**
     * Methode initialisiert den Warenkorb
     *
     * @param warenkorb der Warenkorb eines Kunden
     */
    public void setWarenkorb(Warenkorb warenkorb){this.warenkorb = warenkorb;}

    /**
     * Methode gibt die Adresse zurueck
     *
     * @return die Variable Adresse
     */
    public String getAdresse() {return adresse;}
}
