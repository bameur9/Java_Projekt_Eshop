package eshop.local.domain.exception;

import eshop.local.valueObjects.Artikel;

/**
 * Exception zur Signalisierung, dass ein Artikel bereits existiert.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class ArtikelExistiertBereitsException extends Exception {

    /**
     *Konstruktor
     *
     * @param artikel der bereits existierende Artikel
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public ArtikelExistiertBereitsException(Artikel artikel, String zusatzMsg){
        super("Der Artikel mit der Nr. "+ artikel.getArtikelnummer() + " oder mit dem Bezeichner "+ artikel.getBezeichnung()+
                " existiert bereit! "+ zusatzMsg);
    }

    /**
     * Konstruktor
     *
     * @param artikelNummer der  bereits existierende Nummer
     * @param bezeichner  der  bereits existierende Bezeichner
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public ArtikelExistiertBereitsException(int artikelNummer, String bezeichner, String zusatzMsg){
        super("Der Artikel mit der Nr. "+ artikelNummer + " oder mit dem Bezeichner "+ bezeichner+
                " existiert bereit! "+ zusatzMsg);
    }

    /**
     * Konstruktor
     *
     * @param zusatzMsg Infos
     */
    public ArtikelExistiertBereitsException(String zusatzMsg){
        super(zusatzMsg);
    }
}
