package eshop.local.domain.exception;

/**
 * Exception zur Signalisierung, dass ein Konto
 * (Kunde oder Mietarbeiter) nicht existiert.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class LoginFehlgeschlagenException extends Exception{

    /**
     * Konstruktor
     *
     * @param zusatzMsg zusaetzlicher Text fuer die Fehlermeldung
     */
    public LoginFehlgeschlagenException(String  zusatzMsg){
        super("Konto existiert nicht! "+zusatzMsg);
    }

}
