package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation eines Mitarbeiters.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class Mitarbeiter extends Person {
    /**
     * Statische Variable fuer die ID-Nummer des Mitarbeiters
     */
     public static int idNummer;

    /**
     * Konstruktor für Objekte der Klasse Mitarbeiter
     *
     * @param name Name des Mitarbeiters
     * @param benutzername Username des Mitarbeiters
     * @param passwort Passwort des Mitarbeiters
     */
    public Mitarbeiter(String name, String benutzername, String passwort) {
        super( name, benutzername, passwort);
    }

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Mitarbeiter-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Mitarbeiter {" +
                "\n\t\t ID-Nummer : " +idNummer+
                "\n\t\t Name : "+name+
                "\n\t\t Benutzername : "+ benutzername+
                "\n\t\t Passwort : "+ passwort+
                "\n\t\t}";
    }
}
