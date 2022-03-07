package eshop.local.domain.exception;

import eshop.local.valueObjects.Artikel;

/**
 *  Exception zur Signalisierung, dass ein Artikel nicht existiert.
 *
 *  @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class ArtikelExistiertNichtException extends Exception {

    /**
     *Konstruktor
     *
     * @param artikel der nicht existierende Artikel
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public ArtikelExistiertNichtException(Artikel artikel, String zusatzMsg){
        super("Der Artikel mit der Nr "+ artikel.getArtikelnummer() + " existiert nicht! "+ zusatzMsg);
    }

    /**
     * Konstruktor
     *
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public ArtikelExistiertNichtException(String zusatzMsg){
        super("Artikelnummer ist nicht vorhanden! "+ zusatzMsg);
    }
}
