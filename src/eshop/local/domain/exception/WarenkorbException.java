package eshop.local.domain.exception;

import eshop.local.valueObjects.Warenkorb;

/**
 * Exception zur Signalisierung, dass ein Warenkorb leer ist.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class WarenkorbException extends Exception{

    /**
     * Konstruktor
     *
     * @param zusatzInfo zusaetzliche Information
     */
    public WarenkorbException(String zusatzInfo){
        super("Dein Warenkorb ist leer! "+ zusatzInfo);
    }

    /**
     * zweite Konstruktor
     */
    public WarenkorbException(){
        super("Nicht genung im Lager!");
    }
}
