/**
* Bingel Jonas
* 27.10.2018, V2.0 final
* Die Geldschein Klasse erfuellt die Anforderungen der 1. Aufgabe 'Geldautomat' vom Modul Prog1 AWIS18 WS18/19 an der HS Mainz.
* Ein Eingabebetrag wird auf ein Vielfaches von Fuenf gerundet, die dafuer minimal notwendige Scheinanzahl aus 50/20/10/5ern,
*  sowie alle moeglichen Varianten aus 10/5ern wird ausgegeben.
*/
package pack;

public class Geldscheine {

	/**
	 * Diese Funktion bekommt den Ausgabebetrag als Parameter uebergeben und
	 * berechnet die minimal notwendige Scheinanzahl, um diesen Betrag darzustellen.
	 * Anschliessend wird die Gesamthoehe/-anzahl der Scheine berechnet und wenn
	 * diese kleiner/gleich 100 ist, wird die Ausgabefunktion aufgerufen.
	 * 
	 * @param ausgabeBetrag: Ausgabebetrag, der auf Vielfaches von Fuenf gerundet
	 *        wurde.
	 */
	public static void berechneMinimaleScheine(int ausgabeBetrag) {
		int restBetrag = ausgabeBetrag;
		int anzahl = 0;
		int anzahl5 = 0;
		int anzahl10 = 0;
		int anzahl20 = 0;
		int anzahl50 = 0;
		int geldscheine[] = { 50, 20, 10, 5 };

		/*
		 * Ein Array mit den Scheingroessen, das mittels For-Each-Schleife iteriert
		 * wird, um die entsprechende Scheinanzahl zu berechnen.
		 */

		for (int element : geldscheine) {
			anzahl = restBetrag / element;
			restBetrag = restBetrag % element;

			switch (element) {
			case 50:
				anzahl50 = anzahl;
			case 20:
				anzahl20 = anzahl;
			case 10:
				anzahl10 = anzahl;
			case 5:
				anzahl5 = anzahl;
			default:

			}
		}

		anzahl = berechneAnzahl(anzahl5, anzahl10, anzahl20, anzahl50);

		/*
		 * Gesamtscheinanzahl wird mit pruefenHoehe() geprueft. Bei Anzahl <= 100 wird
		 * die Ausgabefunktion fuer Hoehe und Minimalscheinanzahl aufgerufen.
		 * Andernfalls wird ausgegeben, dass die Scheinanzahl zu hoch ist.
		 */

		if (pruefenHoehe(anzahl)) {
			ausgabeHoehe(anzahl);
			ausgabeMinimaleSchein(anzahl5, anzahl10, anzahl20, anzahl50);
		} else {
			System.out.println(
					"Die Anzahl der Scheine ist groeßer 100 und kann daher nicht ausgegben werden. Wählen Sie einen kleineren Betrag.");
		}
	}

	/**
	 * Die Funktion berechnet alle moeglichen Varianten um den ausgabeBetrag mit
	 * 10/5ern darzustellen. Solange die Gesamtscheinanzahl <= 100 ist, werden die
	 * Varianten ausgegeben, sonst ein Hinweis, dass es keine moeglichen
	 * Kombinationen gibt.
	 * 
	 * @param ausgabeBetrag:Ausgabebetrag, der auf Vielfaches von Fuenf gerundet
	 *        wurde.
	 */
	public static void berechneVarianten(int ausgabeBetrag) {
		int anzahlVarianten = 0;
		int anzahl5 = 0;

		/*
		 * Eine For-Schleife zaehlt die Anzahl der 10 Euro-Schein beginnend bei 0 hoch.
		 * Die Menge an 10ern wird vom Ausgabebetrag abgezogen und die Anzahl an 5ern
		 * durch Divsion des Restbetrags durch Fuenf ermittelt.
		 */

		for (int j = 0; j < (ausgabeBetrag / 10) + 1; j++) {
			anzahl5 = (ausgabeBetrag - j * 10) / 5;

			if (pruefenHoehe(berechneAnzahl(anzahl5, j, 0, 0))) {
				ausgabeVarianten(ausgabeBetrag, j, anzahl5);
				anzahlVarianten = anzahlVarianten + 1;
			}

		}
		ausgabeVariantenanzahl(anzahlVarianten, ausgabeBetrag);
	}

	/**
	 * berechneAnzahl() bildet die Summe der Eingabeparameter und gibt diese als
	 * Rueckgabewert zurueck.
	 * 
	 * @param anzahl5: Anzahl der Fuenf Euro-Scheine
	 * @param anzahl10: Anzahl der Zehn Euro-Scheine
	 * @param anzahl20: Anzahl der Zwanzig Euro-Scheine.
	 * @param anzahl50: Anzahl der Fuenfzig Euro-Scheine. Bei berechneVarianten()
	 *        wird 0 als Parameter fuer anzahl20 und anzahl50 ubergeben, damit die
	 *        Funktion verwendet werden kann.
	 * @return: Summe der Eingabeparameter
	 */
	public static int berechneAnzahl(int anzahl5, int anzahl10, int anzahl20, int anzahl50) {
		int anzahl = anzahl5 + anzahl10 + anzahl20 + anzahl50;
		return anzahl;
	}

	/**
	 * pruefenHoehe() prueft, ob die Scheinanzahl, d.h. angegebener Parameter, <=
	 * 100 ist.
	 * 
	 * @param anzahl: Geldscheinanzahl
	 * @return: Ein boolschen Wert. True wenn anzahl <= 100 ist, sonst false.
	 */
	public static boolean pruefenHoehe(int anzahl) {

		if (anzahl <= 100) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ausgabeMinimaleSchein() gibt die Kombination fuer minimale Scheinanzahl aus.
	 * 
	 * @param anzahl5: Anzahl der Fuenf Euro-Scheine
	 * @param anzahl10: Anzahl der Zehn Euro-Scheine
	 * @param anzahl20: Anzahl der Zwanzig Euro-Scheine.
	 * @param anzahl50: Anzahl der Fuenfzig Euro-Scheine.
	 */
	public static void ausgabeMinimaleSchein(int anzahl5, int anzahl10, int anzahl20, int anzahl50) {
		System.out.println("  Anzahl 50-EUR-Scheine:" + anzahl50);
		System.out.println("  Anzahl 20-EUR-Scheine:" + anzahl20);
		System.out.println("  Anzahl 10-EUR-Scheine:" + anzahl10);
		System.out.println("  Anzahl 5-EUR-Scheine:" + anzahl5);
	}

	/**
	 * ausgabeVarianten() gibt eine Variante fuer die Darstellung des Ausgabebetrags
	 * in 10/5ern aus.
	 * 
	 * @param ausgabeBetrag: Betrag, der ausgegeben werden soll.
	 * @param anzahl5: Anzahl der Fuenf Euro-Scheine
	 * @param anzahl10: Anzahl der Zehn Euro-Scheine
	 */
	public static void ausgabeVarianten(int ausgabeBetrag, int anzahl10, int anzahl5) {
		System.out.println(ausgabeBetrag + " koennen aus:");
		System.out.println("  " + anzahl10 + " mal 10-EUR und");
		System.out.println("  " + anzahl5 + " mal 5-EUR zusammengesetzt werden");
	}

	/**
	 * ausgabeVariantenanzahl() gibt die Anzahl der moeglichen Kombinationen aus,
	 * die in berechneVarianten() kalkuliert wurde.
	 * 
	 * @param anzahl: Anzahl aller moeglichen Kombinationen.
	 * @param ausgabeBetrag: Betrag, der ausgegeben werden soll.
	 */
	public static void ausgabeVariantenanzahl(int anzahl, int ausgabeBetrag) {
		if (anzahl == 0) {
			System.out.println("Fuer " + ausgabeBetrag + " EUR keine Kombination aus 10-\r\n"
					+ "EUR-Scheinen bzw. 5-EUR-Scheinen\r\n" + "moeglich");
		}
		System.out.println(anzahl + " Varianten");
	}

	/**
	 * ausgabeHoehe() bekommt die Scheinanzahl uebergeben und gibt anhand dieser die
	 * Hoehe aus.
	 * 
	 * @param anzahl: Scheinanzahl
	 */
	public static void ausgabeHoehe(int anzahl) {
		// Formatierung um auf genau 2 Dezimalstellen zu runden
		// https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
		System.out.println("Hoehe: " + String.format("%.2f", 0.01 * anzahl) + " cm");
	}

	/**
	 * Main-Methode der Geldschein-Klasse. Der Eingabebetrag wird hier angegeben und
	 * anhand daran der Ausgabebetrag berechnet. Anschliessend werden die einzelnen
	 * Funktionen aufgerufen.
	 * 
	 * @param args: Standardparameter der Main-Methode, hier nicht weiter
	 *        Implementierung.
	 */
	public static void main(String[] args) {
		final int EINGABE_BETRAG = 936;
		int ausgabeBetrag = (EINGABE_BETRAG - (EINGABE_BETRAG % 5));
		System.out.println("Eingabebetrag: " + EINGABE_BETRAG + " EUR");
		System.out.println("Auszahlungsbetrag: " + ausgabeBetrag);
		System.out.println("Ausgabe:");

		berechneMinimaleScheine(ausgabeBetrag);
		System.out.println();
		System.out.println("Varianten von Ausgaben:");
		berechneVarianten(ausgabeBetrag);

	}

}
