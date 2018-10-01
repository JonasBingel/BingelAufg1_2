package pack;

public class Termine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// wahl der bearbeitungszeit
		final int TAG = 5;
		final int MONAT = 5;
		final int JAHR = 2015;

		// TODO Formatierung bearbeiten d.h. Tag = 1 wird zu Tag 01
		// vermutlich hardcode d.h. sysout("0"+tag+"."+"monat") aber überprüben ob tage
		// > 9!

		// if (pruefeDatum && berechneWochentag() != "Samstag" || "Sonntag") {programm ausfuehren} else {"ungueltige eingabe} evt. unterscheidung zwischen
		
		
	}

	public static boolean pruefeDatum() {
		// angabe von tag, monat, jahr
		// wenn alles gültig return true;
		// pruefe bei februar auf schaltjahr um zwischen 29/28 zu unterscheiden
		return true;
	}

	public static String berechneWochentag(int tag, int monat, int jahr) {
		// angabe von tag, monat, jahr
		// wenn datum != arbeitstag dann neues datum wählen
		return "Hello";
	}

	public static String berechneWochenTagAusString() {
		// angabe ist string (Datum), tag.monat.jahr parsen und damit wochentag
		// berechnen
		// TODO wird wohl nicht einmal benötigt; wochentag ändert sich nicht einmal bei schaltjahr in februar
		return "Hello";

	}

	public static boolean pruefeSchaltjahr() {
		// angabe von jahr
		// pruefen mit formel
		return true;
	}

	public static String berechneVorwaertsDatum() {
		// angabe von tag, monat, jahr, anzahlTage
		// anzahlTage + tag -1;
		// tag = 1;
		// while (anzahltage > 0) vielleicht lieber while true und am ende auf false
		// oder anzahltage = 0 wenn fertig
		// switch statement mit monaten; --> break benutzen!
		// wenn anzahltage >= anzahlTageMonat dann monat +1 und anzahltage -
		// anzahlTageMonat
		// sonst tag += anzahltage return convertSTring(Tag"."+"monat"+"jahr")
		// bei dezember jahr +1
		return "Hello";
	}

	public static String berechnerueckwaertsDatum() {
		// angabe von tag, monat, jahr, anzahltage
		// anzahlTage = anzahlTage - tag
		// tag = 1; an sich kann man das auch weglassen wird erst später zugewiesen
		// while (anzahltage > 0) vielleicht lieber while true und am ende auf false
		// oder anzahltage = 0 wenn fertig
		// switch statement mit monaten --> break nutzen!
		// wenn anzahlTage >=
		return "olleh";
	}

	public static void ausgebenAusgangsdatum() {
		// erste Zeile ausgeben
	}

	public static void ausgebenTerminierung() {
		// ausgeben zweite Zeile
		// parameter:datum als string, vorwärts oder rückwärts
		// if termin = vorwärts dann ausgabevorwärts else ausgabe rückwärts
		// berechnung Wochentag mit neuem Datum in sysout statement?
		// Vorwaertsterminierung ergibt als Abgabedatum: Dienstag, 26.03.2019
		// Rueckwaertsterminierung ergibt als Anfangsdatum: Dienstag, 09.10.2018
	}

	public static void ungueltigerTag() {
		System.out.println("Das angegebene Datum ist an keinem Wochentag. Bitte neues Datum wählen!");
	}

}
