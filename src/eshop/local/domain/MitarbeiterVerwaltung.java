package eshop.local.domain;

import eshop.local.domain.exception.ArtikelExistiertBereitsException;
import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.LoginFehlgeschlagenException;
import eshop.local.domain.exception.RegistrationFehlgeschlagenException;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.valueObjects.Artikel;
import eshop.local.valueObjects.Mitarbeiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasse zur Repraesentation einer MitarbeiterVerwaltung.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class MitarbeiterVerwaltung {
    // Attribute zur Beschreibung einer Mitarbeiterverwaltung
    private Map<Integer, Mitarbeiter> mitarbeiterRepository = new HashMap<>();
    private Mitarbeiter admin;
    private ArtikelVerwaltung av;


    /**
     * Konstruktor
     *
     * @param av Instanzvariable von Type ArtikelVerwaltung
     */
    public MitarbeiterVerwaltung(ArtikelVerwaltung av){
        this.av = av;
        //Der Aufruf der statischen Methode speichert einen Mitarbeiter in der Datei jedes mal , wenn
        //ein Mitarbeiter registriert wird
        FilePersistenceManager.mitarbeiterLesen( mitarbeiterRepository, "mitarbeiterDatei");

        String nameAdmin = "Stephane";
        String benutzernameAdmin = "prog";
        String passwortAdmin = "123";

        admin = new Mitarbeiter(nameAdmin, benutzernameAdmin, passwortAdmin);
        mitarbeiterRepository.put( 98 , admin);
    }

    /**
     * Methode zur Registrierung des neuen Mitarbeiters
     * Nur der eingeloggte Admin kann einen neuen Mitarbeiter registrieren
     *
     *@param neuerMitarbeiter der zu registrierende Mitarbeiter
     * @throws RegistrationFehlgeschlagenException Fehlermeldung, wenn benutzername schon existiert!
     */
    public void mitarbeiterRegistrieren(Mitarbeiter neuerMitarbeiter) throws RegistrationFehlgeschlagenException {
        benutzernameGibtBereits(neuerMitarbeiter.getBenutzername());
        Mitarbeiter.idNummer++;
        mitarbeiterRepository.put(Mitarbeiter.idNummer, neuerMitarbeiter);

        //Ereignis Einlagerungen speichern
        if (!neuerMitarbeiter.getBenutzername().equalsIgnoreCase("bameur")) {
            FilePersistenceManager.mitarbeiterSpeichern(neuerMitarbeiter, "mitarbeiterDatei");
        }

    }

    /**
     * Methode prueft, ob ein Mitarbeiter schon den gleichen benutzername besitzt
     *
     * @param benutzername des neuen Mitabeiter
     */
    private void benutzernameGibtBereits(String benutzername) throws RegistrationFehlgeschlagenException {
        for(Map.Entry<Integer, Mitarbeiter> mitarbeiterEntry : mitarbeiterRepository.entrySet()){
            if(mitarbeiterEntry.getValue().getBenutzername().equalsIgnoreCase(benutzername))
                throw new RegistrationFehlgeschlagenException("Benutzername existiert bereits!");
        }
    }

    /**
     *
     * Methode zum Einloggen des Mitarbeiters
     *
     * @param benutzername der Mitarbeiter, die sich einloggen soll
     * @param passwort     der Mitarbeiter, die sich einloggen soll
     * @throws LoginFehlgeschlagenException Fehlermeldung, wenn ein Passwort oder einen benutzername falsch ist.
     */
    public void mitarbeiterEinloggen(String benutzername, String passwort) throws LoginFehlgeschlagenException {
        for(Map.Entry<Integer,Mitarbeiter> entry :mitarbeiterRepository.entrySet()) {
            if (entry.getValue().getBenutzername().equalsIgnoreCase(benutzername)
                    && entry.getValue().getPasswort().equalsIgnoreCase(passwort)) {
                return;
            }
        }
        throw new LoginFehlgeschlagenException("");
    }

    /**
     *Methode legt einen neuen Artikelhinzu
     *
     * @param a der einlegende Artikel
      @throws ArtikelExistiertBereitsException Fehler meldung wenn der Artikel bereits existiert
     */
    public void artikelEinlegen(Artikel a) throws ArtikelExistiertBereitsException {
        av.artikelAnlegen(a, holeMitarbeiterID());
    }

    /**
     * Methode gibt einen Mitarbeiter in der Liste aus!
     *
     * @return  ein Mitarbeiter oder null.
     */
    private int holeMitarbeiterID() {
        for (Map.Entry<Integer, Mitarbeiter> mitarbeiter : mitarbeiterRepository.entrySet()) {
            return mitarbeiter.getKey();
        }
        return 0;
    }

    /**
     * Methode zur Entfernung eines Artikels
     *
     * @param artikelNummer der zuloeschenden Artikelnummer
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert!
     * @return ein Artikel
     */
    public Artikel artikelSuchen(int artikelNummer ) throws ArtikelExistiertNichtException {
        return av.artikelSuchen(artikelNummer);
    }

    /**
     * Methode zur Entfernung eines Artikels
     *
     * @param artikelNummer der zuloeschenden Artikelnummer
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert!
     */
    public void artikelLoeschen(int artikelNummer) throws ArtikelExistiertNichtException {
        av.artikelLoeschen(artikelNummer);
    }

    /**
     * Methode zur Erhoehung der Menge eines Artikels
     *
     * @param artikelNummer des zu erhoehenden Artikels
     * @param neuerBestand Zusätzliche Menge
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert!
     */
    public void artikelBestandErhoehen(int  artikelNummer, int neuerBestand) throws ArtikelExistiertNichtException {
        av.artikelBestandErhoehen(artikelNummer,neuerBestand, Mitarbeiter.idNummer);
    }

    /**
     * Methode zur Ausgabe der ArtikelList
     *
     * @return List von Artikel
     */
    public List<Artikel> artikelList(){
        return av.getArtikelRepository();
    }
}
