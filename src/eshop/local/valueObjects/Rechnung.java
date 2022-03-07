package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation einer Rechnung.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Rechnung {
    //Attribute
    Kunde kunde;
    Warenkorb warenkorb;
    private final String datum;

    /**
     * Konstruktor
     *
     * @param kunde Objekt der Klasse Kunde
     * @param warenkorb Objekt der Klasser Warenkorb
     * @param datum Datum
     */
    public Rechnung(Kunde kunde, Warenkorb warenkorb, String datum){
        this.kunde = kunde;
        this.warenkorb = warenkorb;
        this.datum = datum;
    }

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Mitarbeiter-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Datum:    "+ datum+
                "\nName:    "+ kunde.getName()+
                "\nAdresse: "+ kunde.getAdresse()+
                "\nArtikel: "+
                "\n"+ warenkorb;
    }
}
