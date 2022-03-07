package eshop.local.domain;

import eshop.local.persistence.FilePersistenceManager;
import eshop.local.valueObjects.Ereignis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasse zur Repraesentation einer EreignisseVerwaltung.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class EreignisseVerwaltung {
    //Attribute der Klasse Ereignisse
    List<Ereignis> ereignisList = Collections.synchronizedList(new ArrayList<>());

    /**
     * Konstruktor
     * Die Methode fuegt ein neues Ereignis in der List
     *
     * @param betroffenerArtikel der Name des Artikels
     * @param anzahl der Bestand des aus/-angelagerten oder verkauften Artikels
     * @param personID die ID-nummer der betroffenen Person
     * @param betroffenerPerson der Typ der Person , ob es um einen Mitarbeiter oder einen Kunde handelt
     * @param aktion die Aktion des Ereignisses : Eingelagern(Artikel einlegen oder bestand erhoehen, ), Auslagern(Artikel loeschen),
     *                                           Verkaufen(Kinder käuft einen Artikel)
     * @param idArtikel die Artikelnummer des Artikels
     */
    public void ereignisseHinzufuegen(String betroffenerArtikel, int anzahl, int personID, String betroffenerPerson, String aktion, int idArtikel){
        Ereignis er = new Ereignis(betroffenerArtikel,anzahl, personID,betroffenerPerson, aktion, idArtikel);
        ereignisList.add(er);
    }

    /**
     * Die Methode speichert Ereignisse : (An/-Auslagerung, Verkaufen, ...)
     */
    public void ereignisSpeichern(){
        FilePersistenceManager.ereignisSpeichern(ereignisList, "ereignisseDatei");
    }

    /**
     * Die Methode liesst Ereignisse:
     * @return die Liste des Ereignisses.
     */
    public List<String> ereignisLesen(){
        return FilePersistenceManager.ereignisLesen("ereignisseDatei");
    }

}
