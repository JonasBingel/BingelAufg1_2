package pack;

public class Termine {
	// TODO Kommentare hinzufuegen && Conventions pruefen

	public static boolean pruefeDatum(int tag, int monat, int jahr) {

		if (1582 <= jahr && jahr <= 3000) {
			if (0 < tag && tag <= berechneAnzahlTageinMonat(monat, jahr)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static int berechneAnzahlTageinMonat(int monat, int jahr) {
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
		// case 2 is default
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
	
	public static String berechneWochentag(int tag, int monat, int jahr) {

		int h;
		int k;
		int wochentagNummer;
		String wochentag;
		if (monat <= 2) {
			h = monat + 12;
			k = jahr - 1;
		} else {
			h = monat;
			k = jahr;
		}

		wochentagNummer = (tag + 2 * h + (3 * h + 3) / 5 + k + k / 4 - k / 100 + k / 400 + 1) % 7;

		switch (wochentagNummer) {
		case 1:
			wochentag = "Montag";
			break;
		case 2:
			wochentag = "Dienstag";
			break;
		case 3:
			wochentag = "Mittwoch";
			break;
		case 4:
			wochentag = "Donnerstag";
			break;
		case 5:
			wochentag = "Freitag";
			break;
		case 6:
			wochentag = "Samstag";
			break;
		default:
			wochentag = "Sonntag";

		}
		return wochentag;
	}

	public static boolean pruefeSchaltjahr(int jahr) {
		if (jahr % 400 == 0 || jahr % 4 == 0 && jahr % 100 != 0) {
			return true;
		} else {
			return false;
		}
	}
	
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

	public static String berechneVorwaertsDatum(int tag, int monat, int jahr, int anzahlTage) {
		anzahlTage = anzahlTage + tag - 1;
		tag = 1;

		while (anzahlTage > 0) {
			int anzahlTageMonat;

			if (monat != 12) {
				anzahlTageMonat = berechneAnzahlTageinMonat(monat, jahr);
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = monat + 1;
				} else {
					tag = tag + anzahlTage;
					anzahlTage = 0;
					break;
				}
			} else {
				anzahlTageMonat = berechneAnzahlTageinMonat(monat, jahr);
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

	public static String berechnerueckwaertsDatum(int tag, int monat, int jahr, int anzahlTage) {
		anzahlTage = anzahlTage - tag;

		while (anzahlTage > 0) {
			int anzahlTageMonat;

			
			if (monat != 1) {
				// es müssen die Monate des Vormonats genommen werden
				anzahlTageMonat = berechneAnzahlTageinMonat((monat - 1), jahr);
				if (anzahlTage >= anzahlTageMonat) {
					anzahlTage = anzahlTage - anzahlTageMonat;
					monat = monat - 1;
				} else {
					tag = anzahlTageMonat - anzahlTage;
					monat = monat - 1;
					anzahlTage = 0;
				}
			} else {
				// muss hardcodiert sein, da monat - 1 != 12, was benötigt wird
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

	public static void ausgebenAusgangsdatum(String wochentag, String datum, int bearbeitungszeit) {
		System.out.println("Ausgangsdatum: " + wochentag + ", " + datum);
		System.out.println(bearbeitungszeit + " Wochen Abschlussarbeit");
	}

	public static void ausgebenTerminierung(String wochentag, String datum, String terminwahl) {
		if (terminwahl == "vorwaerts") {
			System.out.println("Vorwaertsterminierung ergibt als Abgabedatum: " + wochentag + ", " + datum);
		} else {
			System.out.println("Rueckwaertsterminierung ergibt als Anfangsdatum: " + wochentag + ", " + datum);
		}
	}
	
	public static void main(String[] args) {
		final int TAG = 22;
		final int MONAT = 4;
		final int JAHR = 2019;
		final int BEARBEITUNGSZEIT = 8;
		final int BEARBEITUNGSZEIT_TAGE = BEARBEITUNGSZEIT * 7;

		String wochentag = berechneWochentag(TAG, MONAT, JAHR);
		String datumAusgang = formatiereDatum(TAG, MONAT, JAHR);
		ausgebenAusgangsdatum(wochentag, datumAusgang, BEARBEITUNGSZEIT);

		if (pruefeDatum(TAG, MONAT, JAHR)) {
			if (!wochentag.equals("Samstag") && !wochentag.equals("Sonntag")) {

				String datumv = berechneVorwaertsDatum(TAG, MONAT, JAHR, BEARBEITUNGSZEIT_TAGE);
				String datumr = berechnerueckwaertsDatum(TAG, MONAT, JAHR, BEARBEITUNGSZEIT_TAGE);

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