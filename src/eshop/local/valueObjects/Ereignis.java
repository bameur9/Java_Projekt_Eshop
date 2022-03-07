package eshop.local.valueObjects;

import java.text.DateFormat;
import java.util.Date;
/**
 * Klasse zur Repraesentation des Ereignisses
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Ereignis {

    // Attribute zur Beschreibung eines Ereignisses
    private final String datum;
    private final int anzahl;
    private final int personID;
    private final String betroffenerArtikel;
    private final String  betroffenerPerson;
    private final String aktion;
    private final int idArtikel;

    /**
     *  @param betroffenerArtikel der Name des Artikels
     * @param anzahl der Bestand des aus/-eingelagerten oder verkauften Artikels
     * @param personID die ID-nummer der betroffenen Person
     * @param betroffenerPerson der Typ der Person , ob es um einen Mitarbeiter oder einen Kunde handelt
     * @param aktion die Aktion des Ereignisses : Eingelagern(Artikel anlegen oder bestand erhoehen, ), Auslagern(Artikel loeschen),
     *                                           Verkaufen(Kinder käuft einen Artikel).
     * @param idArtikel die ID-nummer des betroffenen Artikel.
     */
    public Ereignis(String betroffenerArtikel, int anzahl, int personID, String betroffenerPerson, String aktion, int idArtikel){
        //Carmen
        this.idArtikel = idArtikel;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date currentTime = new Date();
        this.datum = df.format(currentTime);
        this.anzahl = anzahl;
        this.personID = personID;
        this.betroffenerArtikel = betroffenerArtikel;
        this.betroffenerPerson = betroffenerPerson;
        this.aktion = aktion;
    }

    /**
     * Standard-Methode von Object java.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Artikel-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "- Am " + datum + " : " + anzahl +" Stueck von << Nr."+ idArtikel+" : "+betroffenerArtikel  +" >> wurde vom "+betroffenerPerson+" mit der ID : "+
                personID+" "+aktion+"!\n";
    }
}



