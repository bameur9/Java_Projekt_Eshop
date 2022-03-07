package eshop.local.ui.gui;


import eshop.local.domain.Eshop;
/**
 * Klasse zur Repraesentation des EshopGUI
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class EshopGUI {
    /**
     *
     * @param args Parameter
     */
    public static void main(String[] args) {
        new Home("EshopGUI", new Eshop());
    }
}
