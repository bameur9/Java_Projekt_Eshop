package eshop.local.ui.cui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Hilfsklasse mit Ein-/Ausgabeanweisungen
/**
 * Klasse zur Repraesentation der Klasse IO.
 *
 * @author Techke
 */
public class IO {

  // Ausgeben eines Strings

  /**
   * Die Methode gibt einen Text aus
   * @param str auszugegebene Text
   */
  public static void print(String str) {
    System.out.print(str);
  }

  /**
   * Die Methode gibt einen Fehlermeldung aus (in rot)
   *
   * @param str  auszugegebene Text
   */
  public static void printErr(String str) {
	  System.err.println(str);
  }


 /**
  * Ausgeben eines Strings inklusive Zeilenvorschub
   *
   * @param str Parameter String
   */
  public static void println(String str) {
    System.out.println(str);
  }

  /**
   *  Ausgeben eines long
   *
   * @param number Parameter long
   */
  public static void print(long number) {
    System.out.print(number);
  }

  /**
   * Ausgeben eines long inklusive Zeilenvorschub
   *
   * @param number Parameter long
   */
  public static void println(long number) {
    System.out.println(number);
  }

  /**
   * Ausgeben eines double
   *
   * @param number Parameter double
   */
  public static void print(double number) {
    System.out.print(number);
  }

  /**
   * Ausgeben eines double inklusive Zeilenvorschub
   *
   * @param number Parameter double
   */
  public static void println(double number) {
    System.out.println(number);
  }

  /**
   * Ausgeben eines char
   *
   * @param zeichen Parameter char
   */
  public static void print(char zeichen) {
    System.out.print(zeichen);
  }

  /**
   *  Ausgeben eines char inklusive Zeilenvorschub
   *
   * @param zeichen Parameter char
   */
  public static void println(char zeichen) {
    System.out.println(zeichen);
  }

  /**
   * Ausgeben eines Objektes
   * @param obj Parameter Object
   */
  public static void print(Object obj) {
    System.out.print(obj);
  }

  /**
   *  Ausgeben eines char inklusive Zeilenvorschub
   *
   * @param obj Parameter Objekt
   */
  public static void println(Object obj) {
    System.out.println(obj);
  }

  /**
   * Ausgeben eines Zeilenvorschub
   */
  public static void println() {
    System.out.println();
  }

  /**
   *  Einlesen eines char
   *
   * @return charakter
   */
  public static char readChar() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      return eingabe.charAt(0);
    }
    catch(Exception e) {
      return '\0';
    }
  }

  /**
   * Einlesen eines short
   *
   * @return Short
   */
  public static short readShort() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Short shortValue = Short.parseShort(eingabe);
      return shortValue;
    }
    catch (Exception e) {
      return 0;
    }
  }

  /**
   * Einlesen eines int
   * @return integer
   */
  public static int readInt() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Integer integerValue = Integer.parseInt(eingabe);
      return integerValue;
    }
    catch (Exception e) {
      return 0;
    }
  }

  /**
   *  Einlesen eines long
   * @return long
   */
  public static long readLong() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Long longValue = Long.parseLong(eingabe);
      return longValue;
    }
    catch (Exception e) {
      return 0L;
    }
  }

  /**
   * Einlesen eines float
   *
   * @return float
   */
  public static float readFloat() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Float floatValue = Float.parseFloat(eingabe);
      return floatValue;
    }
    catch (Exception e) {
      return 0.0F;
    }
  }

  /**
   * Einlesen eines double
   *
   * @return double
   */
  public static double readDouble() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Double doubleValue = Double.parseDouble(eingabe);
      return doubleValue;
    }
    catch (Exception e) {
      return 0.0;
    }
  }

  /**
   * Einlesen eines Strings
   *
   * @return String
   */
  public static String readString() {
    try {
      BufferedReader input =
              new BufferedReader(new InputStreamReader(System.in));
      return input.readLine();
    }
    catch (Exception e) {
      return "";
    }
  }

  /**
   * Ausgeben eines Strings und Einlesen eines char
   * @param str Parameter
   * @return char
   */
  public static char readChar(String str) {
    System.out.print(str);
    return readChar();
  }

  /**
   *  Ausgeben eines Strings und Einlesen eines short
   *
   * @param str Parameter
   * @return short
   */
  public static short readShort(String str) {
    System.out.print(str);
    return readShort();
  }

  /**
   * Ausgeben eines Strings und Einlesen eines int
   *
   * @param str Parameter
   * @return integer
   */
  public static int readInt(String str) {
    System.out.print(str);
    return readInt();
  }

  /**
   * Ausgeben eines Strings und Einlesen eines long
   *
   * @param str Parameter
   * @return long
   */
  public static long readLong(String str) {
    System.out.print(str);
    return readLong();
  }

  /**
   * Ausgeben eines Strings und Einlesen eines float
   *
   * @param str Parameter
   * @return float
   */
  public static float readFloat(String str) {
    System.out.print(str);
    return readFloat();
  }

  /**
   * Ausgeben eines Strings und Einlesen eines double
   *
   * @param str Parameter
   * @return double
   */
  public static double readDouble(String str) {
    System.out.print(str);
    return readDouble();
  }

  /**
   * Ausgeben eines Strings und Einlesen eines string
   *
   * @param str Parameter
   * @return String
   */
  public static String readString(String str) {
    System.out.print(str);
    return readString();
  }
}

