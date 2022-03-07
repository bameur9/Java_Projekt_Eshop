package eshop.local.valueObjects;

import eshop.local.domain.exception.ArtikelExistiertNichtException;
import eshop.local.domain.exception.WarenkorbException;
import eshop.local.ui.cui.IO;

import java.util.*;

/**
 * Klasse zur Repraesentation eines Warenkorb.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Warenkorb {
    //Instanzvariable
    private final List<PositionImWarenkorb > positionen = Collections.synchronizedList(new ArrayList<>());

    /**
     * Methode legt einen neuen Artikel im Warenkorb an,
     * wenn der Artikel bereits im Warenkorb vorhanden ist,
     * erhoeht sich die Menge.
     *
     * @param artikel der hinzugefuegte Artikel
     * @param menge  die gewuenschte Menge
     */
    public void fuegePositionHinzu(Artikel artikel, int menge ){
        //Die Positionen werden von der Methode positionMitArtikel durchgelaufen
        //Wenn eine Position gefunden wird , wird die in der Variable position gespeichert
        Optional<PositionImWarenkorb> position = positionMitArtikel(artikel);

        //Hier wird ob ein Artikel schon in einer Position vorhanden ist
        //Wenn nicht dann ist die Position leer!
        //die Menge der Artikel muss groeßer null sein.
        if(menge > 0) {
            if (position.isEmpty()) {
                //Wird ein einen neuen Artikel angelegt.
                positionen.add(new PositionImWarenkorb(artikel, menge));
            } else {
                //Sonst erhoeht sich nur die Menge der Artikel
                position.get().fuegeHinzu(menge);
            }
        }
    }


    /**
     * Methode zur Pruefung, wenn eine Artikel in einer Position bereits vorhanden ist.
     * API-Dokumentation
     * Diese Methode unterstützt die Nachbearbeitung für optionale Werte,
     * ohne dass explizit nach einem Rückgabestatus gesucht werden muss.
     *
     * @param artiklels der hinzugefuegte Artikel
     * @return ein Position mit artikel
     */
    private Optional<PositionImWarenkorb> positionMitArtikel(Artikel artiklels ) {
        synchronized(positionen) {
            return positionen.stream()
                    .filter(p -> p.getArtikel().getArtikelnummer() == artiklels.getArtikelnummer())
                    .findFirst();
        }
    }

    /**
     * Methode zur Entleerung des Warenkorbes
     */
    public void warenkorbEntleeren(){positionen.clear();}

    /**
     * Methode entfernt nur Artikel im Warenkorb, deren Menge nach dem Kauf im Store verfügbar ist.
     */
    public void gekaufteArtikelImWKLoeschen(){
        //Die Artikel, deren Menge kleiner ist als die Menge im Shop, werden gelöscht
        synchronized(positionen) {
            positionen.removeIf(position -> position.getArtikel().getMenge() >= position.getMenge());
        }
    }

    /**
     * Methode loescht einen Artikel in einer Position.
     *
     * @param artikelNummer Artikelnummer des zu loeschenden Artikels
     * @throws ArtikelExistiertNichtException Fehlermelung, wenn der zuloeschende Artikel nicht existiert!
     */
    public void einePositionLoeschen(int artikelNummer) throws ArtikelExistiertNichtException {
        boolean exist = false;
        synchronized(positionen) {
            Iterator<PositionImWarenkorb> itr = positionen.iterator();
            //Die Liste wird durchgelaufen und es wird durchgesucht, wenn die Nummer des zu löschenden Artikels vorhanden ist,
            // wird dieser Artikel gelöscht und die Variable exist wird auf true gesetzt.
            // Wenn hingegen kein Artikel vorhanden ist, erhalten wir eine Fehlermeldung
            while (itr.hasNext()) {
                PositionImWarenkorb position = itr.next();
                if (position.getArtikel().getArtikelnummer() == artikelNummer) {
                    itr.remove();
                    exist = true;
                }
            }
        }
        if(!exist){throw new ArtikelExistiertNichtException("");}
    }

    /**
     * Methode prueft, ob die Menge eines Artikel im Warenkorb,
     * kleiner ist, als die im Shop.
     *
     * @param position position im Warenkorb
     * @return true or else
     */
    private boolean isRichtigeMengeImKorb(PositionImWarenkorb position ){
        return position.getMenge() <= position.getArtikel().getMenge();
    }

    /**
     *Methode gibt den Gesamtbetrag von allen Artikeln im Wartike
     *
     * @return der Gesamtbetrag
     */
    public float gesamtPreis(){
        float gesamtPreis = 0;
        //Mit einer Each-Schleife wird die Liste durchgelaufen
        //Wenn der Artikel vorhanden ist und eine richtige Menge hat,
        //Alle gesamtpreise im WK wird additiert.
        for(PositionImWarenkorb position: positionen){
            if ( isRichtigeMengeImKorb(position)) {
                gesamtPreis += position.gesamtPreis();
            }
        }
        return gesamtPreis;
    }

    /**
     * Methode zur Anpassung der Menge eines Artikels im Lager,
     * nach dem Kauf.
     */
    public void mengeImShopNachKaufAnpassen(){
        //Mit einer Each-Schleife wird die Liste durchgelaufen
        //Wenn der Artikel vorhanden ist und eine richtige Menge hat,
        //Die Menge des Artikels wird dann nach dem Kauf im Shop angepasst.
        for(PositionImWarenkorb position: positionen){
            if(isRichtigeMengeImKorb(position)) {
                position.getArtikel().setMenge(position.getArtikel().getMenge() - position.getMenge());
            }
        }
    }

    /**
     * Methode bestimmt, wenn ein Artikel gekauft werden kann!
     *
     * @return true wenn der Artikel gekauft werden kann, sonst false
     * @throws WarenkorbException Fehlermeldung wenn Artikel nicht genug im Lager
     */
    public boolean rechnungKannErzeugt() throws WarenkorbException {
        for(PositionImWarenkorb position: positionen){
            if(isRichtigeMengeImKorb(position)) {
                return true;
            }
        }
        throw new WarenkorbException();
    }

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Mitarbeiter-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "\n\t\t" + positionen +
                "\n\n\tZu zahlende Betrag : " + gesamtPreis() + " Euros";
    }

    /**
     * Methode gibt einen Position zurueck.
     *
     * @return positionen
     */
    public List<PositionImWarenkorb> getPositionen() {
        return positionen;
    }
}
