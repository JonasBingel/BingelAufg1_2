package pack;

public class Geldscheine {

	public static void main(String[] args) {
		final int EINGABE_BETRAG = 365;
		int ausgabeBetrag = (EINGABE_BETRAG - (EINGABE_BETRAG % 5));
		System.out.println("Eingabebetrag: " + EINGABE_BETRAG + " EUR");
		System.out.println("Auszahlungsbetrag: " + ausgabeBetrag);
		System.out.println("Ausgabe:");

		berechneMinimaleScheine(ausgabeBetrag);
		System.out.println();
		System.out.println("Varianten von Ausgaben:");
		berechneVarianten(ausgabeBetrag);

	}

	public static boolean pruefenHoehe(int anzahl) {

		if (anzahl <= 100) {
			return true;
		} else {
			return false;
		}
	}

	public static void berechneVarianten(int ausgabeBetrag) {
		int anzahlVarianten = 0;
		int anzahl5 = 0;

		for (int j = 0; j < (ausgabeBetrag / 10) + 1; j++) {
			anzahl5 = (ausgabeBetrag - j * 10) / 5;

			if (pruefenHoehe(berechneAnzahl(anzahl5, j, 0, 0))) {
				ausgabeVarianten(ausgabeBetrag, j, anzahl5);
				anzahlVarianten = anzahlVarianten + 1;
			}

		}
		ausgabeVariantenanzahl(anzahlVarianten, ausgabeBetrag);
	}

	public static void berechneMinimaleScheine(int ausgabeBetrag) {
		int restBetrag = ausgabeBetrag;
		int anzahl = 0;
		int anzahl5 = 0;
		int anzahl10 = 0;
		int anzahl20 = 0;
		int anzahl50 = 0;
		int geldscheine[] = { 50, 20, 10, 5 };
		for (int element : geldscheine) {
			anzahl = restBetrag / element;
			restBetrag = restBetrag % element;

			if (element == 50) {
				anzahl50 = anzahl;
			} else if (element == 20) {
				anzahl20 = anzahl;
			} else if (element == 10) {
				anzahl10 = anzahl;
			} else {
				anzahl5 = anzahl;
			}
		}
		anzahl = berechneAnzahl(anzahl5, anzahl10, anzahl20, anzahl50);
		if (pruefenHoehe(anzahl)) {

			ausgabeHoehe(anzahl);
			ausgabeMinimaleSchein(anzahl5, anzahl10, anzahl20, anzahl50);

		} else {
			System.out.println(
					"Die Anzahl der Scheine ist groeßer 100 und kann daher nicht ausgegben werden. Wählen Sie einen kleineren Betrag.");
		}

	}

	public static void ausgabeVarianten(int ausgabeBetrag, int anzahl10, int anzahl5) {
		System.out.println(ausgabeBetrag + " koennen aus:");
		System.out.println("  " + anzahl10 + " mal 10-EUR und");
		System.out.println("  " + anzahl5 + " mal 5-EUR zusammengesetzt werden");

	}

	public static void ausgabeMinimaleSchein(int anzahl5, int anzahl10, int anzahl20, int anzahl50) {
		System.out.println("  Anzahl 50-EUR-Scheine:" + anzahl50);
		System.out.println("  Anzahl 20-EUR-Scheine:" + anzahl20);
		System.out.println("  Anzahl 10-EUR-Scheine:" + anzahl10);
		System.out.println("  Anzahl 5-EUR-Scheine:" + anzahl5);
	}

	public static void ausgabeVariantenanzahl(int anzahl, int ausgabeBetrag) {
		if (anzahl == 0) {
			System.out.println("Fuer " + ausgabeBetrag + " EUR keine Kombination aus 10-\r\n"
					+ "EUR-Scheinen bzw. 5-EUR-Scheinen\r\n" + "moeglich");
		}
		System.out.println(anzahl + " Varianten");
	}

	public static void ausgabeHoehe(int anzahl) {
		// format only 2 decimals
		// Formatierung um auf genau 2 Dezimalstellen zu runden; alternativ * 100
		// typecasten zu int und zu double und dann / 100
		// Lösung auf Stackoverflow von "Arun"
		// https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
		System.out.println("Hoehe: " + String.format("%.2f", 0.01 * anzahl) + " cm");
	}

	public static int berechneAnzahl(int anzahl5, int anzahl10, int anzahl20, int anzahl50) {
		int anzahl = anzahl5 + anzahl10 + anzahl20 + anzahl50;
		return anzahl;

	}

}
