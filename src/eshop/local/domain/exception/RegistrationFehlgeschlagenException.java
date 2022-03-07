package eshop.local.domain.exception;


/**
 * Exception zur Signalisierung, dass eine Person
 * (Kunde oder Mietarbeiter) sich nicht registrieren kann.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class RegistrationFehlgeschlagenException extends Exception {
    /**
     *Konstruktor
     *
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public RegistrationFehlgeschlagenException (String  zusatzMsg){
        super("Registration fehlgeschlagen! "+ zusatzMsg);
    }
}
