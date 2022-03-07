package eshop.local.domain;

import eshop.local.domain.exception.*;

import eshop.local.valueObjects.*;

import java.util.List;

/**
 * Klasse zur Repraesentation eines Eshop.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Eshop {
    // Attribute zur Beschreibung eines Eshop
    private EreignisseVerwaltung ev;
    private ArtikelVerwaltung av;
    private MitarbeiterVerwaltung mv;
    private KundenVerwaltung kv;

    /**
     * Konstruktor der Klasse Eshop
     */
    public Eshop(){
        ev = new EreignisseVerwaltung();
        av = new ArtikelVerwaltung (ev);
        mv = new MitarbeiterVerwaltung(av);
        kv = new KundenVerwaltung(ev);
    }

    /**
     * Methode zur Registration eines Mitarbeiters
     *
     * @param name  Name des Mitarbeiters
     * @param benutzername Benutzername des Mitarbeiters
     * @param password Passwort des Mitarbeiters
     * @throws RegistrationFehlgeschlagenException Fehlermeldung, wenn der Mitarbeiter nicht registrieren kann
     */
    public void  mitarbeiterRegistrieren(String name, String benutzername, String password) throws RegistrationFehlgeschlagenException {
        if(name.trim().equals("")||
                benutzername.trim().equals("")||
                password.trim().equals("")) {
            throw new RegistrationFehlgeschlagenException("Feld ist leer!");
        }
        else{ mv.mitarbeiterRegistrieren(new Mitarbeiter(name, benutzername, password));
        }
    }

    /**
     * Methode zum Einloggen des Mitarbeiters
     *
     * @param benutzername der Mitarbeiter, die sich einloggen soll
     * @param passwort     der Mitarbeiter, die sich einloggen soll
     * @throws LoginFehlgeschlagenException Fehlermelung beim Einloggen
     */
    public void mitarbeiterEinloggen(String benutzername, String passwort) throws LoginFehlgeschlagenException {
        if(benutzername.trim().equals("")||
                passwort.trim().equals("")) {
            throw new LoginFehlgeschlagenException("Feld ist leer!");
        }else
        mv.mitarbeiterEinloggen(benutzername,passwort);
    }

    /**
     * Methode Zur Einlaggerung des Artikels
     *
     * @param id Artikelnummer des Artikels
     * @param bezeichnung Bezeichnung des Artikels
     * @param menge Menge des Artikels
     * @param preis Preis des Artikels
     * @throws ArtikelExistiertBereitsException Fehlermeldung, wenn Artikel bereits existiert.
     */
    public void mitarbeiterLegtArtikelAn(int id, String bezeichnung, int menge, float preis) throws ArtikelExistiertBereitsException {
        if(String.valueOf(id).trim().equals("")||
                bezeichnung.trim().equals("") ||String.valueOf(menge).trim().equals("")||String.valueOf(preis).trim().equals("")) {
            throw new ArtikelExistiertBereitsException("Feld ist leer!");
        }else
        mv.artikelEinlegen(new Artikel(id, bezeichnung, menge, preis));
    }

    /**
     * Methode zur Suche eines Artikels
     *
     * @param artikelNummer Nummer des gesuchten Artikels
     * @return gesuchte Artikel
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert.
     */
    public Artikel mitarbeiterSuchtArtikel(int artikelNummer) throws ArtikelExistiertNichtException {
        if(String.valueOf(artikelNummer).trim().equals(""))
            throw  new ArtikelExistiertNichtException("Feld ist leer!");
       return  mv.artikelSuchen(artikelNummer);
    }

    /**
     * Methode zur Entfernung eines Artikels
     *
     * @param artikelNummer der zuloeschenden Artikelnummer
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert.
     */
    public void mitarbeiterEntferntArtikel(int artikelNummer) throws ArtikelExistiertNichtException {
        if(String.valueOf(artikelNummer).trim().equals(""))
            throw  new ArtikelExistiertNichtException("Feld ist leer!");
        mv.artikelLoeschen(artikelNummer);

    }

    /**
     * Methode zur Erhoehung der Menge eines Artikels
     *
     * @param artikelNummer des zu erhoehenden Artikels
     * @param menge Zusätzliche Menge
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert.
     */
    public void mitarbeiterErhoehtArtikel(int artikelNummer, int menge) throws ArtikelExistiertNichtException {
        if(String.valueOf(artikelNummer).trim().equals("")|| String.valueOf(menge).trim().equals("")) {
            throw new ArtikelExistiertNichtException("Feld ist leer!");
        }else
        mv.artikelBestandErhoehen(artikelNummer, menge);
    }

    /**
     * Methode speichert die Artikel
     */
    public void artikelSpeichern(){
        av.artikelSpeichern();
    }

    /**
     * Methode gibt alle Artikel im Lager
     *
     * @return Artikel im Lager
     */
    public List<Artikel> artikelImShopAusgeben(){
        return mv.artikelList();
    }

    /**
     *
     * @param name Name des Kunden
     * @param adresse Adresse des Kunden
     * @param benutzername Benutzername des Kunden
     * @param passwort Passwort des Kunden
     * @throws RegistrationFehlgeschlagenException Fehlermeldung wenn Kunde nicht registriert werden kann
     */
    public void kundeRegistrieren(String name, String adresse, String benutzername, String passwort) throws RegistrationFehlgeschlagenException {
        if(name.trim().equals("")|| benutzername.trim().equals("")||
                passwort.trim().equals("") || adresse.trim().equals("")) {
            throw new RegistrationFehlgeschlagenException("Feld ist leer!");
        }else
        kv.kundeRegistrieren(new Kunde(name, adresse, benutzername, passwort));
    }

    /**
     * Methode zum Einloggen eines Kunden
     *
     * @param benutzername des Kunden
     * @param passwort  des Kunden
     * @throws LoginFehlgeschlagenException Fehlermeldung, wenn Passwort oder Benutzername falsch ist.
     */
    public void kundeEinloggen(String benutzername, String passwort) throws LoginFehlgeschlagenException {
        if(benutzername.trim().equals("")||passwort.trim().equals("")) {
            throw new LoginFehlgeschlagenException("Feld ist leer!");
        }else
        kv.kundeEinloggen(benutzername,passwort);
    }

    /**
     * Methode zur Erstellung eines Warenkorb,
     * falls der Warenkorb noch null ist und
     * fuege einen neuen Artikel und eine Menge hinzu.
     *
     * @param id     die hinzuzufuegende Nummer
     * @param menge  die hinzuzufuegende Menge
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn ein Artikelnummer nicht vorhanden ist!
     */
    public void kundeLegtArtikelImWkEin(int id, int menge) throws ArtikelExistiertNichtException {
        if(String.valueOf(id).trim().equals("")|| String.valueOf(menge).trim().equals("")) {
            throw new ArtikelExistiertNichtException("Feld ist leer!");
        }else
        kv.artikelImWarenKorbEinlegen(av.artikelSuchen(id) , menge);
    }

    /**
     * Methode entfernt einen Artikel aus dem Warenkorb
     *
     * @param artikelNummer der zu entfernende Artikel
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn ein Artikelnummer nicht vorhanden ist!
     */
    public void kundeEntferntArtikelVomWk(int artikelNummer) throws ArtikelExistiertNichtException {
        if(String.valueOf(artikelNummer).trim().equals(""))
            throw  new ArtikelExistiertNichtException("Feld ist leer!");
        kv.einArtikelLoeschen(artikelNummer);
    }

    /**
     *Methode entleert den Warenkorb
     */
    public void kundeEntleertWK(){
        kv.warenkorbEntlleren();
    }

    /**
     * Methode zur Erstellung einer Rechnung
     * @throws WarenkorbException Fehlermeldung, wenn Warenkorb leer ist oder Artikel nicht kaufbar ist.
     */
    public void kundeKauftArtikel() throws WarenkorbException {
       kv.artikelKaufen();
    }

    /**
     * Die Methode gibt eine Rechnung zurueck
     *
     * @return eine Rechnung
     * @throws WarenkorbException Exception, wenn die Warenkorb leer ist oder Wenn ein Artikel nicht Kaufbar ist.
     */
    public Rechnung rechnungErzeugen() throws WarenkorbException {
        return kv.rechnungErstellen();
    }

    /**
     * Methode zur Ausgabe der Warenkorb.
     *
     * @return der Warenkorb wird zurueck gegeben
     */
    public Warenkorb kundegibtArtikelImWKAus(){
        return kv.artikelImWkAusgeben();
    }

    /**
     * Methode zur Speicherung der Ereignisse
     */
    public void ereignisSpeichern(){
        ev.ereignisSpeichern();
    }


    /**
     * Die List liest Ereignisse ein.
     * @return die Liste des Ereignisses
     */
    public List<String> ereignisLesen(){return ev. ereignisLesen();}

}
