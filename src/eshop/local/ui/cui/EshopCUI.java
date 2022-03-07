package eshop.local.ui.cui;

import eshop.local.domain.Eshop;

/**
 * Klasse zur Repraesentation der EshopCUI.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class EshopCUI {
    private final Eshop eshop = new Eshop();

    /**
     * Die Methode des Hauptmenüs
     * Enthaelt Untermenü 1. Fuer Kunde und 2. Fuer Mitarbeiter
     */
    public void run(){
        String wahl;
        IO.println("-----------Willkommen Im Unserem Shop--------------");
        IO.println("Ich moechte mich einloggen als : ");
        IO.println("Kunde: ---------------- 'k'");
        IO.println("Mitarbeiter: ---------- 'm'");

        while (true) {
            wahl = IO.readString("Ihre Wahl : ");
            switch (wahl) {
                case "k","K" -> new Kundenportal(eshop).alsKundeWeiter();

                case "m","M" ->new Mitarbeiterportal(eshop).alsMitarbeiterWeiter();

                default ->IO.println("\nFalsche Eingabe!\nBitte Wiederholen Sie noch einmal!\n");
            }
        }
    }

    /**
     * Hauptmenue fuer das EshopCUI
     * @param args Parameter
     */
    public static void main(String[] args){
       new EshopCUI().run();
    }
}
