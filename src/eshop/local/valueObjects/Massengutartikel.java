package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation eines  Massengutartikels
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Massengutartikel extends Artikel {

    private int packungsgroesse;

    /**
     * Konstruktor für Objekte der Klasse Artikel
     *
     * @param artikelnummer die Artikelnummer des Artikels
     * @param bezeichner    der Name des Artikel
     * @param menge         der Bestand des Artikel
     * @param preis         der Preis des Artikels
     * @param packungsgroesse die Packungsgroesse der Artikels
     */
    public Massengutartikel(int artikelnummer, String bezeichner, int menge, float preis, int packungsgroesse) {
        super(artikelnummer, bezeichner, menge, preis);
        this.packungsgroesse = packungsgroesse;
    }

    /**
     * Methode gibt die Packungsgroesse zurueck
     *
     * @return packungsgroesse eines Artikels
     */
    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    /**
     * Methode initialisiert die Packungsgroesse
     *
     * @param packungsgroesse neue Packungsgroesse
     */
    public void setPackungsgroesse(int packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }
}
