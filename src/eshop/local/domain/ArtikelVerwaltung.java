package eshop.local.domain;

import eshop.local.domain.exception.ArtikelExistiertBereitsException;
import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.valueObjects.Artikel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



/**
 * Klasse zur Repraesentation einer ArtikelVerwaltung.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class ArtikelVerwaltung {
    EreignisseVerwaltung ev;
    //List fuer die Artikel
    final List<Artikel> artikelRepository =  Collections.synchronizedList(new ArrayList<>());

    /**
     * Konstruktor
     *
     * @param ev Instanzvariable der Klasse EreignisseVerwaltung
     */
    public ArtikelVerwaltung(EreignisseVerwaltung ev){
        this.ev = ev;
        //Hier werden die gespeicherten Artikel in die Liste wieder zurückgegeben
        FilePersistenceManager.artikelLesen( artikelRepository,"artikelDatei");
    }

    /**
     *  Methode legt einen neuen Artikel im Shop an.
     *
     * @param neuerArtikel des einzulegenden Artikels
     * @param artikelnummer Artikelnummer des Artikels
     * @throws ArtikelExistiertBereitsException Fehlermeldung, wenn Artikel schon existiert!
     */
    public void artikelAnlegen(Artikel neuerArtikel, int artikelnummer) throws ArtikelExistiertBereitsException  {
        if(!artikelExistiertNicht(neuerArtikel.getArtikelnummer(), neuerArtikel.getBezeichnung())){
            artikelRepository.add(neuerArtikel);
            //Ereignis Anlagerung erstellen
            ev.ereignisseHinzufuegen(neuerArtikel.getBezeichnung(), neuerArtikel.getMenge(),
                    artikelnummer, "Mitarbeiter", "angelegt", neuerArtikel.getArtikelnummer());
        }
    }

    /**
     * Methode prueft, ob eine Bezeichnung oder ein Artikelnummer
     * bereits existiert.
     *
     * @param artikelnummer des anzulegenden Artikels
     * @param bezeichnung  des anzulegenden Bezeichners
     * @return true  wenn ein Artikel bereits existiert oder false wenn nich!
     */
    private boolean artikelExistiertNicht(int artikelnummer, String bezeichnung) throws ArtikelExistiertBereitsException {
        for (Artikel a : artikelRepository) {
            if (a.getBezeichnung().equalsIgnoreCase(bezeichnung) || a.getArtikelnummer() == artikelnummer)
                throw new ArtikelExistiertBereitsException(artikelnummer, bezeichnung,"");
        }
        return false;
    }

    /**
     * Die Methode sucht einen Artikel mit Hilfe einer Artikelnummer
     *
     * @param artikelnummer eines Artikels
     * @return ein Artikel
     * @throws ArtikelExistiertNichtException Fehler wenn ein Artikel nicht existiert
     */
    public Artikel artikelSuchen(int artikelnummer) throws ArtikelExistiertNichtException {
        return artikelRepository.stream()
                .filter((a) -> a.getArtikelnummer() == artikelnummer)
                .findFirst()
                .orElseThrow(() -> new ArtikelExistiertNichtException(""));
    }

    /**
     * Methode zur Entfernung eines Artikels
     *
     * @param artikelnummer der zuloeschenden Artikelnummer
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert!
     */
    public void artikelLoeschen(int artikelnummer) throws ArtikelExistiertNichtException {
        boolean exist = false;
        synchronized(artikelRepository) {
            for(Iterator<Artikel> it = artikelRepository.iterator(); it.hasNext();){
                Artikel a = it.next();
                if (a.getArtikelnummer() == artikelnummer) {
                    exist = true;
                    it.remove();
                    ev.ereignisseHinzufuegen(a.getBezeichnung(), a.getMenge(),
                            artikelnummer, "Mitarbeiter", "ausgelegt", a.getArtikelnummer());
                    break;
                }
            }
        }
        if(!exist){
            throw new ArtikelExistiertNichtException("");
        }
    }

    /**
     * Die Methode gibt die Liste des Artikels aus.
     * @return eine Liste
     */
    public List<Artikel> getArtikelRepository() {
        return artikelRepository;
    }


    /**
     * Methode zur Erhoehung der Menge eines Artikels
     *
     * @param artikelnummer des zu erhoehenden Artikels
     * @param neuerBestand Zusätzliche Menge
     * @param mitarbeiternummer Nummer eines Mitarbeiter
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn der Artikel nicht existiert!
     */
    public void artikelBestandErhoehen(int artikelnummer, int neuerBestand, int mitarbeiternummer) throws  ArtikelExistiertNichtException {
        artikelSuchen(artikelnummer).setMenge(neuerBestand +  artikelSuchen(artikelnummer).getMenge());
        ev.ereignisseHinzufuegen(artikelSuchen(artikelnummer).getBezeichnung(), neuerBestand,
                        mitarbeiternummer, "Mitarbeiter", "zusätliche Artikel eingelegt",  artikelSuchen(artikelnummer).getArtikelnummer());
    }

    /**
     * Methode speichert die erstellten Artikel in einer Datei,
     * wenn der Mitarbeiter das System verlässt.
     */
    public void artikelSpeichern(){
        FilePersistenceManager.artikelSpeichern(artikelRepository, "artikelDatei");
    }
}
