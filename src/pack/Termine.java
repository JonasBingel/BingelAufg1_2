/**
 * @author Jonas Bingel
 * @version 2.0 , 29.10.2018 Die Termin Klasse erfuellt die Anforderungen der 2.
 *          Aufgabe 'Datumsberechnung' vom Modul Prog1 AWIS18 WS18/19 an der HS
 *          Mainz. Eine Ausgangsdatum wird angegeben, dessen Gueltigkeit und
 *          Wochentag geprueft wird. Zudem wird ein Bearbeitungszeitraum in
 *          Wochen angegeben. Dann wird ein Vorwaerts- und Rueckwaertstermin
 *          berechnet mittels Addition/Subtraktion des Bearbeitungszeitraums vom
 *          Ausgangsdatum.
 */
package pack;

public class Termine {

	/**
	 * pruefeDatum() prueft, ob das Datum gueltig ist d.h. ein gueltiges Jahr, Monat
	 * sowie Tag angegeben wurde.
	 * 
	 * @param tag: Tag des Ausgangsdatums.
	 * @param monat: Monat des Ausgangsdatums.
	 * @param jahr: Jahr des Ausgangsdatums.
	 * @return ein boolschen Wert. True, wenn das Datum gueltig ist, sonst false.
	 */
	public static boolean pruefeDatum(int tag, int monat, int jahr) {

		/**
		 * If-Statement prueft, ob das Jahr zwischen 1582 und 3000 liegt, da nur dann
		 * der Gregorianische Kalender gilt. Das Innere If-Statement prueft, ob der
		 * angegebene Tag gueltig ist, d.h. ungleich 0 ist und kleiner als die
		 * Tagesanzahl im angegebenen Monat, sodass falsche Daten wie 31.02 oder 42.12
		 * gefiltert werden.
		 */
		if (1582 <= jahr && jahr <= 3000) {
			if (0 < tag && tag <= berechneAnzahlTageInMonat(monat, jahr)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * pruefeSchaltjahr() prueft anhand der Definition eines Schaltjahrs (Restlos
	 * teilbar durch 4; 400 nicht 100), ob das angegebene Jahr ein Schaltjahr ist.
	 * 
	 * @param jahr: Jahr, das uberprueft werden soll.
	 * @return ein boolschen Wert. True, wenn das Jahr ein Schaltjahr ist, sonst
	 *         false.
	 */
	public static boolean pruefeSchaltjahr(int jahr) {
		if (jahr % 400 == 0 || jahr % 4 == 0 && jahr % 100 != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * berechneWochentag() berechnet den Wochentag mit der gausschen Kalenderformel
	 * siehe: http://www.straub.as/java/basic/Uwochentag.html (Quelle:
	 * Aufgabenvorgaben)
	 * 
	 * @param tag: Tag des angegebenen Datums.
	 * @param monat: Monat des angegebenen Datums.
	 * @param jahr: Jahr des angegebenen Datums.
	 * @return den Wochentag als String.
	 */
	public static String berechneWochentag(int tag, int monat, int jahr) {

		int h;
		int k;
		int wochentagNummer;
		if (monat <= 2) {
			h = monat + 12;
			k = jahr - 1;
		} else {
			h = monat;
			k = jahr;
		}
		wochentagNummer = (tag + 2 * h + (3 * h + 3) / 5 + k + k / 4 - k / 100 + k / 400 + 1) % 7;

		/**
		 * Die Formel kann nur Werte zwischen 0 und 6 liefern, daher gibt es nur 6
		 * Cases, mit 0 als Default
		 */
		switch (wochentagNummer) {
		case 1:
			return "Montag";
		case 2:
			return "Dienstag";
		case 3:
			return "Mittwoch";
		case 4:
			return "Donnerstag";
		case 5:
			return "Freitag";
		case 6:
			return "Samstag";
		default:
			return "Sonntag";
		}
	}

	/**
	 * berechneAnzahlTageInMonat() gibt die Anzahl der Tage in einem angegebenen
	 * zurueck.
	 * 
	 * @param monat: Monat fuer den die Anzahl der Tage bestimmt werden sollen.
	 * @param jahr: Jahr des Datums.
	 * @return die Anzahl der Tage im Monat als Integer. Default = 0, um falsche
	 *         Monatsangaben zu filtern.
	 */
	public static int berechneAnzahlTageInMonat(int monat, int jahr) {
		switch (monat) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (pruefeSchaltjahr(jahr) == true) {
				return 29;
			} else {
				return 28;
			}
		default:
			return 0;
		}
	}

	/**
	 * berechneVorwaertsDatum() rechnet anzahlTage auf Ausgangsdatum.
	 * 
	 * @param tag: Tag des Ausgangsdatums.
	 * @param monat: Monat des Ausgangsdatums.
	 * @param jahr: Jahr des Ausgangsdatums.
	 * @param anzahlTage: Bearbeitungszeitraum in Tagen.
	 * @return das Datum als formatierten String.
	 */
	public static String berechneVorwaertsDatum(int tag, int monat, int jahr, int anzahlTage) {
		int anzahlTageMonat;

		/**
		 * Das Datum wird auf den 1. Tag des Monats gesetzt. Dadurch erhoeht sich die
		 * anzahlTage, um den Wert von Tag.
		 */
		anzahlTage = anzahlTage + tag - 1;
		tag = 1;

		/**
		 * While-Schleife laueft solange bis anzahlTage < 0, d.h. keine Tage mehr auf
		 * das Ausgansdatums addiert werden koennen.
		 */
		while (anzahlTage > 0) {

			/**
			 * If prueft nach Monat, da bei Dezember zusätzlich das Jahr verändert werden
			 * muss.
			 */

			if (monat != 12) {

				/**
				 * Wenn AnzahlTage >= TageInMonat ist, liegt das neue Datum nicht in diesem
				 * Monat. Daher erhoeht sich der Monat um 1 und die TageInMonat wird von
				 * AnzahlTage abgezogen. Sonst liegt das neue Datum im Monat und der Tag wird
				 * berechnet, durch Addition der AnzahlTage.
				 */
				anzahlTageMonat = berechneAnzahlTageInMonat(monat, jahr);
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = monat + 1;
				} else {
					tag = tag + anzahlTage;
					anzahlTage = 0;
					// break;
				}
			} else {

				/**
				 * Analog zum vorigen if-Teil, jedoch wird das Jahr erhoeht, wenn AnzahlTage >=
				 * TageInMonat.
				 */
				anzahlTageMonat = berechneAnzahlTageInMonat(monat, jahr);
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = 1;
					jahr = jahr + 1;
				} else {
					tag = tag + anzahlTage;
					anzahlTage = 0;
				}
			}

		}
		return formatiereDatum(tag, monat, jahr);
	}

	/**
	 * berechneRueckwaertsDatum() subtrahiert anzahlTage vom Ausgangsdatum.
	 * 
	 * @param tag: Tag des Ausgangsdatums.
	 * @param monat: Monat des Ausgangsdatums.
	 * @param jahr: Jahr des Ausgangsdatums.
	 * @param anzahlTage: Bearbeitungszeitraum in Tagen.
	 * @return das Datum als formatierten String.
	 */
	public static String berechneRueckwaertsDatum(int tag, int monat, int jahr, int anzahlTage) {
		anzahlTage = anzahlTage - tag;
		int anzahlTageMonat;

		while (anzahlTage > 0) {

			/**
			 * If prueft nach aktuellem Monat, da bei Januar zusätzlich das Jahr verändert
			 * werden muss.
			 */
			if (monat != 1) {

				/**
				 * Wenn AnzahlTage >= TageInMonat des Vormonats ist, liegt das neue Datum nicht
				 * in diesem Monat. Daher verringert sich der Monat um 1 und die TageInMonat
				 * wird von AnzahlTage abgezogen. Sonst liegt das neue Datum im vorigen Monat
				 * und der Tag wird berechnet, durch Subtraktion der anzahlTage von TageInMonat,
				 * da der Tag standardmaessig der letzte eines Monats ist.
				 */
				anzahlTageMonat = berechneAnzahlTageInMonat((monat - 1), jahr);
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = monat - 1;
				} else {
					tag = anzahlTageMonat - anzahlTage;
					monat = monat - 1;
					anzahlTage = 0;
				}
			} else {

				/**
				 * Analog zum vorigen if-Teil. anzahlTageMonat ist hardcodiert, da
				 * anzahlTageMonat von Dezember benoetigt werden
				 */
				anzahlTageMonat = 31;
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = 12;
					jahr = jahr - 1;
				} else {
					tag = anzahlTageMonat - anzahlTage;
					monat = 12;
					jahr = jahr - 1;
					anzahlTage = 0;
				}
			}
		}
		return formatiereDatum(tag, monat, jahr);
	}

	/**
	 * formatiereDatum() formatiert das Datum aus den berechneDatum Funktionen fuer
	 * die Ausgabe. Aus didaktischen Gruenden wurde dieses Mal auf die
	 * String.format("%02d")-Methode verzichtet.
	 * 
	 * @param tag: Tag des zu formatierenden Datums.
	 * @param monat: Monat des zu formatierenden Datums.
	 * @param jahr: Jahr des zu formatierenden Datums.
	 * @return formatiertes Datum als String.
	 */
	public static String formatiereDatum(int tag, int monat, int jahr) {

		String tagFormatiert;
		String monatFormatiert;
		if (tag < 10) {
			tagFormatiert = "0" + tag;
		} else {
			tagFormatiert = Integer.toString(tag);
		}
		if (monat < 10) {
			monatFormatiert = "0" + monat;
		} else {
			monatFormatiert = Integer.toString(monat);
		}
		return (tagFormatiert + "." + monatFormatiert + "." + jahr);

	}

	/**
	 * ausgebenAusgangsdatum() gibt Ausgangsdatum auf der Konsole aus.
	 * 
	 * @param wochentag: Wochentag des Ausgansdatums.
	 * @param datum: Ausgansdatum.
	 * @param bearbeitungszeit: Bearbeitungszeit der Arbeit in Wochen.
	 */
	public static void ausgebenAusgangsdatum(String wochentag, String datum, int bearbeitungszeit) {
		System.out.println("Ausgangsdatum: " + wochentag + ", " + datum);
		System.out.println(bearbeitungszeit + " Wochen Abschlussarbeit");
	}

	/**
	 * ausgebenTerminierung() gibt den Vorwaerts- und Rueckwaertstermin aus.
	 * 
	 * @param wochentag: Wochentag des Datums.
	 * @param datum: Datum des berechneten Termins,
	 * @param terminwahl: Wahl zwischen vorwaerts und rueckwarts, zur richtigen
	 *        Ausgabe.
	 */
	public static void ausgebenTerminierung(String wochentag, String datum, String terminwahl) {
		if (terminwahl == "vorwaerts") {
			System.out.println("Vorwaertsterminierung ergibt als Abgabedatum: " + wochentag + ", " + datum);
		} else {
			System.out.println("Rueckwaertsterminierung ergibt als Anfangsdatum: " + wochentag + ", " + datum);
		}
	}

	/**
	 * Main-Methode der Termine-Klasse, in der das Ausgansdatum festgelegt wird und
	 * alle notwendigen Methoden aufgerufen werden. Zudem wird eine
	 * Fallunterscheidung durchgefuehrt, ob das Datum gueltig und der Wochentag
	 * ungleich Samstag/Sonntag ist.
	 * 
	 * @param args: Keine weitere Implementierung
	 */
	public static void main(String[] args) {
		final int TAG = 22;
		final int MONAT = 4;
		final int JAHR = 2019;
		final int BEARBEITUNGSZEIT_WOCHEN = 8;
		final int BEARBEITUNGSZEIT_TAGE = BEARBEITUNGSZEIT_WOCHEN * 7;

		String wochentag = berechneWochentag(TAG, MONAT, JAHR);
		String datumAusgang = formatiereDatum(TAG, MONAT, JAHR);
		ausgebenAusgangsdatum(wochentag, datumAusgang, BEARBEITUNGSZEIT_WOCHEN);

		if (pruefeDatum(TAG, MONAT, JAHR)) {
			if (!wochentag.equals("Samstag") && !wochentag.equals("Sonntag")) {

				String datumv = berechneVorwaertsDatum(TAG, MONAT, JAHR, BEARBEITUNGSZEIT_TAGE);
				String datumr = berechneRueckwaertsDatum(TAG, MONAT, JAHR, BEARBEITUNGSZEIT_TAGE);

				ausgebenTerminierung(wochentag, datumv, "vorwaerts");
				ausgebenTerminierung(wochentag, datumr, "rueckwaerts");
			} else {
				System.out.println("Ausgangsdatum ist kein Wochentag");
			}
		} else {
			System.out.println("Geben Sie ein gueltiges Datum an");
		}
	}
}