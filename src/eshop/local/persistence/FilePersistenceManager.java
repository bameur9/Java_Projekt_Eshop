package eshop.local.persistence;

import eshop.local.ui.cui.IO;
import eshop.local.valueObjects.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Klasse zur Repraesentation des FilePersistenceManager.
 *
 * @author Stephane Dongmo und Carmen Stephanie Ngosso
 */
public class FilePersistenceManager {

    /**
     * Methode zur Speicherung der ArtikelList in einer Datei
     * List -> Datei
     *
     * @param artikelRepository Artikellist
     * @param dateiName Name der Datei
     */
   public static void artikelSpeichern(List<Artikel> artikelRepository, String dateiName){
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            datei.setLength(0);
            for (Artikel artikel : artikelRepository ) {
                datei.writeInt(artikel.getArtikelnummer());
                datei.writeUTF(artikel.getBezeichnung());
                datei.writeInt(artikel.getMenge());
                datei.writeFloat(artikel.getPreis());
            }
        }catch (IOException e){IO.printErr("Fehler beim schreiben aufgetreten!");}
    }

    /**
     * Methode liest die Artikel in der Datei und legt die zurueck in der List ein.
     * Datei -> List
     *
     * @param artikelRepository Artikellist
     * @param dateiName Name der Datei
     */
    public static void artikelLesen(List<Artikel>  artikelRepository,String dateiName){
        boolean endOfFile= false;
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            while(!endOfFile){
                try {
                    int idNummer = datei.readInt();
                    String bezeichner = datei.readUTF();
                    int menge = datei.readInt();
                    float preis = datei.readFloat();
                    artikelRepository.add(new Artikel(idNummer,bezeichner,menge,preis));
                }catch(EOFException e){
                    endOfFile = true;
                }
            }
        } catch (IOException e){IO.printErr("Fehler beim Lesen aufgetreten!");}
    }

    /**
     * Methode zur Speicherung eines Mitarbeiers in einer Datei
     * jedes Mal, wenn ein Mitarbeiter registriert wird.
     * ein Mitarbeiter -> Datei
     *
     * @param mitarbeiter Der registrierte Mitarbeiter
     * @param dateiName Name der Datei
     */
    public static void mitarbeiterSpeichern(Mitarbeiter mitarbeiter, String dateiName){
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            datei.seek(datei.length());

            datei.writeUTF(mitarbeiter.getName());
            datei.writeUTF(mitarbeiter.getBenutzername());
            datei.writeUTF(mitarbeiter.getPasswort());

        }catch (IOException e){IO.printErr("Fehler beim schreiben aufgetreten!");}
    }

    /**
     * Methode liest alle gespeichte Mitarbeiter in der Datei und legt die zurueck in der Mitarbeiterlist ein.
     * Alle Mitarbeiter in der Datei -> Map
     *
     * @param mitarbeiterRepository Mitarbeiterlist
     * @param dateiName Name der Datei
     */
    public static void mitarbeiterLesen(Map<Integer, Mitarbeiter> mitarbeiterRepository,String dateiName){
        boolean endOfile= false;
        int id =99;
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            while(!endOfile){
                try {
                    id++;
                    String name= datei.readUTF();
                    String benutzername = datei.readUTF();
                    String passwort = datei.readUTF();

                    Mitarbeiter.idNummer = id;
                    mitarbeiterRepository.put(Mitarbeiter.idNummer,new Mitarbeiter( name,benutzername,passwort));
                }catch(EOFException e){
                    endOfile = true;
                }
            }
        } catch (IOException e){IO.printErr("Fehler beim Lesen aufgetreten!");}
    }

    /**
     * Methode zur Speicherung des Kunden in einer Datei
     * jedes Mal, wenn ein Kunde registriert wird.
     * ein Kunde -> Datei
     *
     * @param kunde ein Kunde
     * @param dateiName Name der Datei
     */
    public static void kundenSpeichern(Kunde kunde, String dateiName){
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            datei.seek(datei.length());

            datei.writeUTF(kunde.getName());
            datei.writeUTF(kunde.getAdresse());
            datei.writeUTF(kunde.getBenutzername());
            datei.writeUTF(kunde.getPasswort());

        }catch (IOException e){IO.printErr("Fehler beim schreiben aufgetreten!");}
    }

    /**
     * Methode liest alle gespeichte Kunden in der Datei und legt die zurueck in der Mitarbeiterlist ein.
     * Alle Kunde in der Datei -> Map
     *
     * @param kundenRepository Kundenlist
     * @param dateiName Name der Datei
     */
    public static void kundenLesen(Map<Integer, Kunde> kundenRepository,String dateiName){
        boolean endOfile= false;
        int id =99;

        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
            while(!endOfile){
                try {
                    id++;
                    String name= datei.readUTF();
                    String adresse = datei.readUTF();
                    String benutzername = datei.readUTF();
                    String passwort = datei.readUTF();

                    Kunde.idNummer = id;
                    kundenRepository.put(Kunde.idNummer, new Kunde(name,adresse,benutzername,passwort));
                }catch(EOFException e){
                    endOfile = true;
                }
            }
        } catch (IOException e){IO.printErr("Fehler beim Lesen aufgetreten!");}
    }

    /**
     * Die Methode zur Speicherung alle Ereignisse vom dem Eshop
     *
     * @param ereignisList list der Ereignisse
     * @param dateiName Name der Datei
     */
    public static void ereignisSpeichern(List<Ereignis> ereignisList , String dateiName) {
        try (RandomAccessFile datei = new RandomAccessFile(dateiName + ".text", "rw")) {
            datei.seek(datei.length());
            for (Ereignis ereignis: ereignisList  ) {
                datei.writeUTF(ereignis.toString());
            }
        } catch (IOException e) {
            IO.printErr("Fehler beim schreiben aufgetreten!");
        }
    }

    /**
     * Methode liest die geschreibenen Ereignisse aus der Datei!
     * Die gespeicherten Ereignissen werden nicht mehr in der Liste zurück geschickt.
     *
     * @param dateiName der DateiName
     * @return gibt die Liste des Ereignisses als String zurueck
     */
    public static List<String> ereignisLesen(String dateiName){
        List<String> ereignisList = new ArrayList<>();
        boolean endOfile= false;
        try(RandomAccessFile datei = new RandomAccessFile(dateiName+".text","rw")){
           while(!endOfile){
                try {
                    String ereignis = datei.readUTF();
                    ereignisList.add(ereignis);

                }catch(EOFException e){
                    endOfile = true;
                }
           }
        } catch (IOException e){IO.printErr("Fehler beim Lesen aufgetreten!");}
        return ereignisList;
    }
}


