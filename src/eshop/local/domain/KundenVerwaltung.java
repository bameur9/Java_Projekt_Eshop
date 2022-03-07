package eshop.local.domain;

import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.LoginFehlgeschlagenException;
import eshop.local.domain.exception.RegistrationFehlgeschlagenException;
import eshop.local.domain.exception.WarenkorbException;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.valueObjects.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasse zur Repraesentation einer Kundenverwaltung.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class KundenVerwaltung {
    EreignisseVerwaltung ev;
    // Attribute zur Beschreibung einer Kundenverwaltung
    Map<Integer, Kunde> kundenRepository = new HashMap<>();
    private int id=  Kunde.idNummer;

    /**
     * Konstruktor
     * @param ev Instanzvariable der Klasse EreignisseVerwaltung
     */
    public KundenVerwaltung(EreignisseVerwaltung ev){
        this.ev = ev;
        //Hier werden die gespeicherten Mietarbeiter in die Liste wieder zurückgegeben
        FilePersistenceManager.kundenLesen(kundenRepository,"kundenDatei");
    }

    /**
     * Methode zur Registrierung eines Kunden
     *
     * @param neuerKunde der zu registrierende Kunde
     * @throws RegistrationFehlgeschlagenException Fehlermeldung, wenn Benutzername bereitsexistiert!
     */
    public void kundeRegistrieren(Kunde neuerKunde) throws RegistrationFehlgeschlagenException {
        benutzernameGibtBereits(neuerKunde.getBenutzername());
        id++;
        kundenRepository.put(Kunde.idNummer, neuerKunde);

        //Der Aufruf der statischen Methode speichert einen Kunde in der Datei jedes mal , wenn
        // ein Kunde registriert wird
        FilePersistenceManager.kundenSpeichern(neuerKunde, "kundenDatei");
    }

    /**
     * Methode prueft, ob ein Kunde schon den gleichen benutzername besitzt
     *
     * @param benutzername des neuen Kunden
     */
    private void benutzernameGibtBereits(String benutzername) throws RegistrationFehlgeschlagenException {
        for(Map.Entry<Integer,Kunde> entry : kundenRepository.entrySet()){
            if(entry.getValue().getBenutzername().equalsIgnoreCase(benutzername))
                throw new RegistrationFehlgeschlagenException("Benutzername existiert bereits!");
        }
    }

    /**
     * Methode gibt einen Kunde in der Liste aus!
     *
     * @return  ein Kunde oder null.
     */
    public Kunde holeKunde() {
        for (Map.Entry<Integer, Kunde> kunde: kundenRepository.entrySet()) {
            return kunde.getValue();
        }
        return null;
    }

    /**
     * Methode zum Einloggen eines Kunden
     *
     * @param benutzername des Kunden
     * @param passwort  des Kunden
     * @throws LoginFehlgeschlagenException Fehlermeldung, wenn das Passwort oder ein benutzername falsch ist.
     */
    public void kundeEinloggen(String benutzername, String passwort) throws LoginFehlgeschlagenException {
        for(Map.Entry<Integer, Kunde> entry : kundenRepository.entrySet()) {
            if (entry.getValue().getBenutzername().equalsIgnoreCase(benutzername)
                    && entry.getValue().getPasswort().equalsIgnoreCase(passwort)) {
                return;
            }
        }
        throw new LoginFehlgeschlagenException("");
    }

    /**
     * Methode zur Erstellung eines Warenkorb,
     * falls der Warenkorb noch null ist und
     * fuege einen neuen Artikel und eine Menge hinzu.
     *
     * @param artikel der hinzuzufuegende Artikel
     * @param menge  die hinzuzufuegende Menge
     */
    public void artikelImWarenKorbEinlegen(Artikel artikel, int menge)  {holeKunde().legeArtikelInWarenkorbEin(artikel , menge);}

    /**
     * Methode loescht die gekauften Artikel im Warenkorb nach dem Kauf
     *
     */
    private void artikelNachKaufLoeschen() {
        holeKunde().warenkorbStandNachKauf();
    }

    /**
     * Methode entleert den Warenkorb
     *
     */
    public void warenkorbEntlleren(){
        holeKunde().warenkorbEntleeren();
    }

    /**
     * Methode gibt alle Artikel im Warenkorb
     * @return der Warenkorb
     */
    public Warenkorb artikelImWkAusgeben() {return holeKunde().getWarenkorb();}

    /**
     * Methode entfernt einen Artikel aus dem Warenkorb
     *
     * @param artikelNummer der zu entfernende Artikel
     * @throws ArtikelExistiertNichtException Fehlermeldung, wenn Artikel nicht existiert
     */
    public void einArtikelLoeschen(int artikelNummer) throws ArtikelExistiertNichtException {
        holeKunde().einArtikelLoeschen(artikelNummer);
    }

    /**
     * Methode erstellt eine Rechnung.
     *
     * @return  eine Rechnung
     * @throws WarenkorbException Fehlermeldung, wenn Warenkorb leer ist oder Artikel nicht kaufbar ist
     */
    public Rechnung rechnungErstellen() throws WarenkorbException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ");
        String dateString = format.format( new Date());
        //Warenkorben wird geloescht
        //Menge wird sich verändern im Lager
        if(!holeKunde().getWarenkorb().getPositionen().isEmpty()) {
            if (holeKunde().getWarenkorb().rechnungKannErzeugt()) {
                return new Rechnung(holeKunde(), holeKunde().getWarenkorb(), dateString);
            } else
                throw new WarenkorbException();
        }else throw new WarenkorbException("Bitte Legen Sie zuerst einen Artikel an!");
    }

    /**
     * Methode wird aufgerufen jedes mal, wenn ein Kunde einen Artikel kauft.
     * eine Rechnung wird erzeugt
     * Die Menge des gekauften Artikels wird sich im Shop anpassen
     */
    public void artikelKaufen() throws WarenkorbException {
        if (holeKunde().getWarenkorb().rechnungKannErzeugt()) {
            holeKunde().getWarenkorb().mengeImShopNachKaufAnpassen();
            List<PositionImWarenkorb> positionen = holeKunde().getWarenkorb().getPositionen();
            for (PositionImWarenkorb p : positionen) {
                ev.ereignisseHinzufuegen(p.getArtikel().getBezeichnung(),
                        p.getMenge(), Kunde.idNummer, "Kunden", "gekauft", p.getArtikel().getArtikelnummer());
            }
            artikelNachKaufLoeschen();
        }
    }
}

