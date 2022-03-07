package eshop.local.valueObjects;

/**
 * Klasse zur Repraesentation einer Person.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public abstract class Person {
    // Attribute zur Beschreibung einer Person
    /**
     * Attribute Name einer Person
     */
    protected String name;
    /**
     * Attribute Passwort einer Person
     */
    protected String passwort;
    /**
     * Attribute Benutzername einer Person
     */
    protected String benutzername;

    /**
     * Konstruktor für Objekte der Klasse Person
     * @param name Name der Person
     * @param benutzername Username der Person
     * @param passwort Passwort der Person
     */
    public Person(String name, String benutzername, String passwort){
        // Attribute initialisieren
        this.name = name;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    /**
     * Standard-Methode von Object Ueberschrieben.
     * Methode wird immer automatisch aufgerufen,
     * wenn ein Person-Objekt als String benutzt wird.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Person { " +
                ", name='" + name + '\'' +
                ", passwort='" + passwort + '\'' +
                ", benutzername='" + benutzername + '\'' +
                " }";
    }

    /**
     * Methode gibt den Namen zurueck
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Methode gibt den Passwort zurueck
     *
     * @return passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Methode gibt den Benutzername zurueck
     *
     * @return benutzername
     */
    public String getBenutzername() {
        return benutzername;
    }

}

