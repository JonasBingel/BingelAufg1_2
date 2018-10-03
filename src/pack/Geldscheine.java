package pack;

public class Geldscheine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int EINGABE_BETRAG = 47;
		int anzahl = 0;
		// int ausgabeBetrag = (EINGABE_BETRAG / 5) * 5;
		int ausgabeBetrag = (EINGABE_BETRAG - (EINGABE_BETRAG % 5));
		double scheinHoehe = 0.01;
		System.out.println("Eingabebetrag: " + EINGABE_BETRAG + " EUR");
		System.out.println("Auszahlungsbetrag: " + ausgabeBetrag);
		System.out.println("Ausgabe:");

		int anzahlScheine[] = berechneMinimaleScheine(ausgabeBetrag);
		anzahl = berechneAnzahl(anzahlScheine);

		if (pruefenHoehe(anzahl)) {
			ausgabeHoehe(scheinHoehe, anzahl);
			ausgabeMinimaleSchein(anzahlScheine);

		} else {
			System.out.println(
					"Die Anzahl der Scheine ist groeßer 100 und kann daher nicht ausgegben werden. Wählen Sie einen kleineren Betrag.");
		}
		System.out.println();
		System.out.println("Varianten von Ausgaben:");
		berechneVarianten(ausgabeBetrag);

	}

	public static void eingeben() {
		// abzuhebender Geldbetrag wird gelesen
	}

	public static boolean pruefenHoehe(int anzahl5, int... anzahloptional) {
		// Lösung um optionalen Parameter zu ermöglichen; Stackoverflow
		// https://stackoverflow.com/questions/965690/java-optional-parameters
		int anzahl10 = anzahloptional.length > 0 ? anzahloptional[0] : 0;

		if (anzahl5 + anzahl10 <= 100) {
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

			if (pruefenHoehe(anzahl5, j)) {
				ausgabeVarianten(ausgabeBetrag, j, anzahl5);
				anzahlVarianten = anzahlVarianten + 1;
			}

		}
		ausgabeVariantenanzahl(anzahlVarianten, ausgabeBetrag);
	}

	public static int[] berechneMinimaleScheine(int ausgabeBetrag) {
		int anzahlScheine[] = new int[4];
		int restBetrag = ausgabeBetrag;
		int geldscheine[] = { 50, 20, 10, 5 };
		int i = 0;
		for (int element : geldscheine) {
			anzahlScheine[i] = restBetrag / element;
			restBetrag = restBetrag % element;
			i++;
		}
		return anzahlScheine;
	}

	public static void ausgabeVarianten(int ausgabeBetrag, int anzahl10, int anzahl5) {
		System.out.println(ausgabeBetrag + " koennen aus:");
		System.out.println("  " + anzahl10 + " mal 10-EUR und");
		System.out.println("  " + anzahl5 + " mal 5-EUR zusammengesetzt werden");

	}

	public static void ausgabeMinimaleSchein(int[] anzahlScheine) {
		System.out.println("  Anzahl 50-EUR-Scheine:" + anzahlScheine[0]);
		System.out.println("  Anzahl 20-EUR-Scheine:" + anzahlScheine[1]);
		System.out.println("  Anzahl 10-EUR-Scheine:" + anzahlScheine[2]);
		System.out.println("  Anzahl 5-EUR-Scheine:" + anzahlScheine[3]);
	}

	public static void ausgabeVariantenanzahl(int anzahl, int ausgabeBetrag) {
		if (anzahl == 0) {
			System.out.println("Fuer " + ausgabeBetrag + " EUR keine Kombination aus 10-\r\n"
					+ "EUR-Scheinen bzw. 5-EUR-Scheinen\r\n" + "moeglich");
		}
		System.out.println(anzahl + " Varianten");
	}

	public static void ausgabeHoehe(double scheinHoehe, int anzahl) {
		// format only 2 decimals
		// Formatierung um auf genau 2 Dezimalstellen zu runden; alternativ * 100
		// typecasten zu int und zu double und dann / 100
		// Lösung auf Stackoverflow von "Arun"
		// https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
		System.out.println("Hoehe: " + String.format("%.2f", scheinHoehe * anzahl) + " cm");
	}

	public static int berechneAnzahl(int[] anzahlScheine) {
		int anzahl = 0;
		for (int element : anzahlScheine) {
			anzahl = anzahl + element;
		}
		return anzahl;

	}

}
