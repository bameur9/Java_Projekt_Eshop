package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation der Position eines Artikels
 * im Warenkorb.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class PositionImWarenkorb {
    //Attribute zur Beschreibung einer Position im Warenkorb
    private int menge;
    //Instanzvariable der Klasse Artikel
    private final Artikel artikel;

    /**
     * Konstruktor der Klasse PositionImWarenkorb
     *
     * @param artikel Objekt-Artikel
     * @param menge Bestand vom Artikel
     */
    public PositionImWarenkorb(Artikel artikel, int menge) {
        //Initialisierung der Attribute
        this.artikel = artikel;
        this.menge = menge;
    }

    /**
     * Methode gibt den Gesamtbetrag je nach Menge zurueck.
     *
     * @return das Produkt des ursprünglichen Preises und der gewuenschten Menge
     */
    public float gesamtPreis(){
        return artikel.getPreis()*menge;
    }

    /**
     * Methode gibt eine Meldung, wenn die Menge eines Artikels im Lager kleiner als
     * die gewuenschte Menge ist.
     * Wenn aber die Menge des Artikels im Lager groeßer gleich als die gewuenschte
     * Menge ist, gibt nichts zurueck,
     *
     * @return String
     */
    public String mengeMeldung(){
        //Hier wird geprueft, ob die gegebene Menge grosser als die Menge des betroffenen Artikels im Shop.
        //Wenn dies Fall ist, erhalten wir einen Hinweis. sonst nichts
        if(this.menge > artikel.getMenge()){
            return "\nHinweis : ArtikelNr. "+artikel.getArtikelnummer()+" nur noch "+artikel.getMenge() + " im Lager."+
                    " Der Artikel wird daher nicht verkauft !";
        }else
            return  "";
    }


    /**
     * Methode zur Erhoehung der Menge, wenn ein Artikel sich schon in einer
     * Position befindet.
     *
     * @param zuseatzlicheMenge die zugefuegte Menge
     */
    public void fuegeHinzu(int zuseatzlicheMenge){
        //Die neue Menge wird mit der alten Menge addiert,
        //Wenn der Artikel sich bereits im WK befindet.
        int updateMenge = menge +  zuseatzlicheMenge;
        //Die Menge der Artikel wird Update
        setMenge(updateMenge);
    }

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Mitarbeiter-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return  "Nr "+artikel.getArtikelnummer()+". " + artikel.getBezeichnung() +
                " | preis : "+ artikel.getPreis()+" Euro"+
                " | menge : " + menge+
                " | Gesamtpreis : "+ gesamtPreis() +" Euro "+ mengeMeldung()+"\n\t\t\t ";
    }

    /**
     * Methode gibt einen Artikel zurueck
     *
     * @return artikel
     */
    public Artikel getArtikel() {
        return artikel;
    }

    /**
     * Methode gibt eine Menge zurueck
     *
     * @return menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Methode initialisiert die Menge
     *
     * @param menge menge eines Artikels in einer Position
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }
}

