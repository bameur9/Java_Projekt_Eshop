package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation eines Artikels
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Artikel {
    // Attribute zur Beschreibung eines Artikels
    private final int artikelnummer;
    private final String bezeichnung;
    private int menge;
    private final float preis;

    /**
     * Konstruktor fuer Objekte der Klasse Artikel
     *
     * @param artikelnummer die Artikelnummer des Artikels
     * @param bezeichner der Name des Artikel
     * @param menge der Bestand des Artikel
     * @param preis der Preis des Artikels
     */
    public Artikel(int artikelnummer, String bezeichner, int menge, float preis) {
        //Initialisierung von Attributen
        this.artikelnummer = artikelnummer;
        this.bezeichnung = bezeichner;
        this.menge = menge;
        this.preis = preis;
    }

    /**
     * Standard-Methode von Object java.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Artikel-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ("Nr. " + this.artikelnummer + " : " + this.bezeichnung + " x "+ this.menge+" stk "+ " ------------- " + this.preis+" Euro / Artikel"
        );
    }

    /**
     * Die Methode zur Ausgabe des Artikel im Kundenportal
     * Dort wird die Menge der Artikel im Shop nicht angezeigt
     *
     * @return ein Artikel im Shop.
     */
    public String artikelKundePortal(){
        return ("Nr. "+ this.artikelnummer+ " : "+ this.bezeichnung + " ------------- "+ this.preis+" Euro ");
    }

    /**
     * Methode gibt die Bezeichnung zurueck
     *
     * @return die Bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Methode gibt die Menge zurueck
     *
     * @return die Menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Methode initializiert die Menge
     *
     * @param menge die neue Menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Methode gibt den Preis zurueck
     *
     * @return der preis
     */
    public float getPreis() {
        return preis;
    }

    /**
     * Methode gibt die Artikelnummer aus!
     *
     * @return die Artikelnummer
     */
    public int getArtikelnummer(){
        return artikelnummer;
    }

}
