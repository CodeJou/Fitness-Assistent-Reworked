import java.util.Scanner;

public class fitness_assistent_reworked {

	public static void main(String[] args) {

// AB HIER STARTET EINGABEBEREICH
		// (1) Begrüßung
		System.out.println("Hallo, ich bin dein Fitnessassistent. Wie heißt du?");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("");
		System.out.println("Hey " + name + ", hier kannst du deinen BMI (Body-Mass-Index) berechnen.");

		// (2)Abfrage Größe
		System.out.println("Wie größ bist du? (z.B 1.64 m)");

		// Eingabe lesen/prüfen
		double groeße = leseGroeße(scanner);

		// (3) Abfrage Gewicht
		System.out.println("");
		System.out.println("Wieviel wiegst du? (Gib dein Gewicht in kg an)");

		// Eingabe lesen/prüfen
		double gewicht = leseGewicht(scanner);

		// (4) Abfrage Alter
		System.out.println("");
		System.out.println("Wie alt bist du? (Runde dein Alter auf eine ganze Zahl auf)");

		// Eingabe lesen/prüfen
		int alter = leseAlter(scanner);

		// (5) Abfrage Geschlecht
		System.out.println("");
		System.out.println("Was möchtest du für ein Geschlecht angeben?");
		System.out.println("Folgende Auswahlmöglichkeiten hast du:");
		System.out.println("");
		System.out.println("m steht für männlich");
		System.out.println("w steht für weiblich");
		System.out.println("d steht für divers");
		System.out.println("");
		System.out.println("Bitte gib jetzt den für dich passenden Buchstaben ein:");

		// Eingabe lesen/prüfen
		String geschlecht = leseGeschlecht(scanner);

		// (6) Abfrage PAL Wert Aktivitätsfaktor
		System.out.println("");
		System.out.println("Wähle dein ungefaires Aktivitätsniveau.");
		System.out.println("1 - Nur sitzend, kaum Freizeitaktivität zB Bürojob");
		System.out.println("2 - Sitzend, etwas Aktivität z.B Studierende, Verkäufer");
		System.out.println("3 - Stehend und gehend, z.B. Handwerker, Gastronomie");
		System.out.println("4 - Körperlich anstrengend, z.B. Bauarbeiter, Leistungssportler");
		System.out.println("");
		System.out.println("Bitte gib jetzt die für dich passende Zahl ein:");

		// Eingabe lesen/prüfen
		int pal = lesePAL(scanner);

//AB HIER WERTE INTERPRETIEREN UND VERARBEITEN
		// (7) BMI berechnen
		double bmi = berechneBMI(gewicht, groeße);

		// (8) Altersgruppe festlegen
		String altersgruppe = bestimmeAltersgruppe(alter);

		// (9) Grundumsatz berechnen
		int grundumsatz = berechneGrundumsatz(gewicht, groeße, alter, geschlecht);

		// (10) Leistungsumsatz berechnen
		int leistungsumsatz = berechneLeistungsumsatz(grundumsatz, pal);

		// (11) Gesamtumsatz berechnen
		int gesamtumsatz = berechneGesamtumsatz(grundumsatz, leistungsumsatz);

//AB HIER AUSGABE
		// Ausgabe Profil
		ausgabeProfil(name, alter, groeße, gewicht, geschlecht);

		// Ausgabe der BMI-Tabelle
		ausgabeBMItabelle(altersgruppe, bmi, groeße);

		// Angabe Infos+Energiebedarf
		ausgabeEnergiebedarf(grundumsatz, leistungsumsatz, gesamtumsatz, geschlecht);

		// Ausgabe Makros-Tabelle
		ausgabeMakrotabelle(gesamtumsatz);
	}

//AB HIER METHODEN	
//Methoden zum Bereich "Eingabe"
	// (2)
	public static double bereinigegroeße(String groeße_string)// Hilfsmethode bereinigt String Größe
	{
		groeße_string = groeße_string.replace("m", "");// Maßeinheit entfernen
		groeße_string = groeße_string.replace(" ", "");// Leerzeichen entfernen
		groeße_string = groeße_string.replace(",", ".");// Komma durch Punkt ersetzen

		double groeße = Double.parseDouble(groeße_string);// Datentyp String in Double umwandeln
		return groeße;
	}

	// (2)
	public static double pruefeWertebereichGroeße(double groeße, Scanner scanner)// Hilfsmethode prüft Wertebereich
																					// Größe
	{
		String groeße_string;

		while (groeße > 2.1 || groeße < 1.0)// While Schleife wenn Zahlenwerte außerhalb 1.00 und 2.10 Metern angegeben
											// sind
		{
			System.out.println("");
			System.out.println(
					"[ Meldung: Bitte gib einen Zahlenwert zwischen 1.00 Meter und 2.10 Meter an (z.B 1.64m ]");
			groeße_string = scanner.nextLine();
			groeße = bereinigegroeße(groeße_string);
		}
		return groeße;
	}

	// (2)
	public static double leseGroeße(Scanner scanner)// Größe einlesen/validieren
	{
		String groeße_string;
		double groeße;
		try {
			groeße_string = scanner.nextLine();
			groeße = bereinigegroeße(groeße_string);
			groeße = pruefeWertebereichGroeße(groeße, scanner);
		} catch (java.lang.NumberFormatException exception_2)// Fehlermeldung abfangen bei fehlerhafter Eingabe (Bei
																// Eingabe "cm")
		{
			System.out.println();
			System.out.println("[ Meldung: Bitte gib deine Größe als Zahlenwert in m (z.B 1.64m) an ]");
			groeße_string = scanner.nextLine();
			groeße = bereinigegroeße(groeße_string);
			groeße = pruefeWertebereichGroeße(groeße, scanner);
		}
		return groeße;
	}

	// (3)
	public static double bereinigeGewicht(String gewicht_string)// Hilfsmethode Gewicht
	{
		gewicht_string = gewicht_string.replace(" kg", "");// Maßeinheit entfernen
		gewicht_string = gewicht_string.replace(" ", "");// Leerzeichen entfernen
		gewicht_string = gewicht_string.replace(",", ".");// Komma durch Punkt ersetzen

		double gewicht = Double.parseDouble(gewicht_string);// Datentyp String zu Double umwandeln
		return gewicht;
	}

	// (3)
	public static double leseGewicht(Scanner scanner)// Gewicht einlesen und validieren
	{
		String gewicht_string;
		double gewicht;
		try {
			gewicht_string = scanner.nextLine();
			gewicht = bereinigeGewicht(gewicht_string);
		} catch (java.lang.NumberFormatException exception_3) {
			System.out.println("");
			System.out.println("[ Meldung: Bitte gib dein Gewicht in kg (z.B 65,5 kg) an ]");// Meldung wenn Eingabe
																								// keinen Zahlenwert
																								// beinhaltet
			gewicht_string = scanner.nextLine();
			gewicht = bereinigeGewicht(gewicht_string);
		}
		return gewicht;
	}

	// (4)
	public static int pruefeWertebereichAlter(int alter, Scanner scanner)// Hilfsmethode um Wertebereich Alter zu prüfen
	{
		while (alter < 19 || alter > 99)// Wenn Eingabe außerhalb 19 bis 99 liegt
		{
			System.out.println();
			System.out.println("[ Meldung : Bitte gib ein Alter zwischen 19 und 99 an ]");
			alter = scanner.nextInt();
		}
		scanner.nextLine();// Scanner leeren
		return alter;
	}

	// (5)
	public static int leseAlter(Scanner scanner) {
		int alter;
		try {
			alter = scanner.nextInt();
			alter = pruefeWertebereichAlter(alter, scanner);
		} catch (java.util.InputMismatchException expection_4) {
			System.out.println("");
			System.out.println("[ Meldung: Bitte gib dein Alter als Zahlenwert (zwischen 19 und 99 ) ein! ]");
			scanner.nextLine();// Scanner leeren
			alter = scanner.nextInt();
			alter = pruefeWertebereichAlter(alter, scanner);
		}
		return alter;
	}

	// (5)
	public static String leseGeschlecht(Scanner scanner) {
		String geschlecht;
		geschlecht = scanner.nextLine();

		if (geschlecht.equalsIgnoreCase("m")) {
			geschlecht = "männlich";
		} else if (geschlecht.equalsIgnoreCase("w")) {
			geschlecht = "weiblich";
		} else if (geschlecht.equalsIgnoreCase("d")) {
			geschlecht = "divers";
		} else {
			geschlecht = "Keine Angabe";
		}
		return geschlecht;
	}

	// (6)
	public static int pruefeWertebereichPAL(int pal, Scanner scanner)// Hilfsmethode prüft Wertebereich PAL
	{
		while (pal < 1 || pal > 4)// Wenn eingegebener Zahlenwert außerhalb 1 - 4 liegt
		{
			System.out.println("");
			System.out.println("[ Meldung: Bitte gib einen Zahlenwert von 1 bis 4 an ]");
			pal = scanner.nextInt();
		}
		return pal;
	}

	// (6)
	public static int lesePAL(Scanner scanner) {
		int pal;
		try {
			pal = scanner.nextInt();// Antwort scnannen
			pal = pruefeWertebereichPAL(pal, scanner);

		} catch (java.util.InputMismatchException exception_6)// Wenn Eingabe kein Zahlenwert Typ int entspricht
		{
			scanner.nextLine();// Scanner leeren
			System.out.println("");
			System.out.println("[ Meldung: Bitte gib einen ganzen Zahlenwert von 1 bis 4 an ]");
			pal = scanner.nextInt();
			pal = pruefeWertebereichPAL(pal, scanner);
		}
		return pal;
	}

//Methoden zum Bereich "Interpretieren und Verarbeiten"
	// (7)
	public static double berechneBMI(double gewicht, double groeße) {
		double bmi;
		bmi = gewicht / (groeße * groeße);
		bmi = Math.round(bmi * 100.0) / 100.0;// Auf zwei Nachkommastellen runden.
		return bmi;
	}

	// (8)
	public static String bestimmeAltersgruppe(int alter) {
		String altersgruppeA = "Altersgruppe 19 bis 24 Jahre";
		String altersgruppeB = "Altersgruppe 25 bis 34 Jahre";
		String altersgruppeC = "Altersgruppe 35 bis 44 Jahre";
		String altersgruppeD = "Altersgruppe 45 bis 54 Jahre";
		String altersgruppeE = "Altersgruppe 55 bis 64 Jahre";
		String altersgruppeF = "Altersgruppe ab 65 Jahre";

		String altersgruppe;

		// Altersgruppe bestimmen
		if (alter <= 24) {
			altersgruppe = altersgruppeA;
		} else if (alter <= 34) {
			altersgruppe = altersgruppeB;
		} else if (alter <= 44) {
			altersgruppe = altersgruppeC;
		} else if (alter <= 54) {
			altersgruppe = altersgruppeD;
		} else if (alter <= 64) {
			altersgruppe = altersgruppeE;
		} else {
			altersgruppe = altersgruppeF;
		}

		return altersgruppe;
	}

	public static double ordneBMIderAltersgruppezu(String altersgruppe, double bmi) {
		String altersgruppeA = "Altersgruppe 19 bis 24 Jahre";
		String altersgruppeB = "Altersgruppe 25 bis 34 Jahre";
		String altersgruppeC = "Altersgruppe 35 bis 44 Jahre";
		String altersgruppeD = "Altersgruppe 45 bis 54 Jahre";
		String altersgruppeE = "Altersgruppe 55 bis 64 Jahre";
		double bminormalgewicht_ug;

		if (altersgruppe == altersgruppeA) {
			bminormalgewicht_ug = 19.00;
		} else if (altersgruppe == altersgruppeB) {
			bminormalgewicht_ug = 20.00;
		} else if (altersgruppe == altersgruppeC) {
			bminormalgewicht_ug = 21.00;
		} else if (altersgruppe == altersgruppeD) {
			bminormalgewicht_ug = 22.00;
		} else if (altersgruppe == altersgruppeE) {
			bminormalgewicht_ug = 23.00;
		} else {
			bminormalgewicht_ug = 24.00;
		}

		return bminormalgewicht_ug;
	}

	// (9)
	public static int berechneGrundumsatz(double gewicht, double groeße, int alter, String geschlecht) {
		double groeße_cm = groeße * 100;
		double grundumsatz_d = (10 * gewicht) + (6.25 * groeße_cm) - (5 * alter);

		if (geschlecht == "männlich") {
			grundumsatz_d = grundumsatz_d + 5;
		} else if (geschlecht == "weiblich") {
			grundumsatz_d = grundumsatz_d - 161;
		} else {
			grundumsatz_d = grundumsatz_d - 83;
		} // Bei Eingabe divers+keine Angabe wird der Mittelwert berechnet

		int grundumsatz = (int) grundumsatz_d;// Alles hinter dem Komma wird "abgeschnitten"

		return grundumsatz;
	}

	// (10)
	public static int berechneLeistungsumsatz(int grundumsatz, double pal) {
		double pal_Wert;
		if (pal == 1) {
			pal_Wert = 1.4;
		} else if (pal == 2) {
			pal_Wert = 1.6;
		} else if (pal == 3) {
			pal_Wert = 1.8;
		} else {
			pal_Wert = 2.0;
		}

		double leistungsumsatz_d = grundumsatz * (pal_Wert - 1);
		int leistungsumsatz = (int) leistungsumsatz_d;// alles hinterm Komma wird "abgeschnitten"

		return leistungsumsatz;
	}

	// (11)
	public static int berechneGesamtumsatz(int grundumsatz, int leistungsumsatz) {
		int gesamtumsatz = grundumsatz + leistungsumsatz;
		return gesamtumsatz;
	}

//Methoden zum Bereich Ausgabe
	public static void ausgabeProfil(String name, int alter, double groeße, double gewicht, String geschlecht) {
		System.out.println("");
		System.out.println("");
		System.out.println(
				"————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("");
		System.out.println("Danke für deine Angaben.");
		System.out
				.println("Nachfolgend siehst du deine Körperwerte und den daraus resultierenden BMI (Body-Mass-Index)");
		System.out.println("");
		System.out.println("                  Name       : " + name);
		System.out.println("                  Alter      : " + alter + " Jahre");
		System.out.println("                  Größe      : " + groeße + " m");
		System.out.println("                  Gewicht    : " + gewicht + " kg");
		System.out.println("                  Geschlecht : " + geschlecht);
		System.out.println("");

	}

	public static void ausgabeBMItabelle(String altersgruppe, double bmi, double groeße) {
		double bminormalgewicht_ug = ordneBMIderAltersgruppezu(altersgruppe, bmi);// BMI Wert abhängig vom Alter zuordnen
																					
		// BMI Werte für die Tabelle errechnen
		double bminormalgewicht_og = bminormalgewicht_ug + 5;
		double bmiübergewicht_ug = bminormalgewicht_ug + 5.1;
		double bmiübergewicht_og = bminormalgewicht_ug + 10;
		double bmiadipositas = bminormalgewicht_ug + 10.1;

		// Gewichte für die Tabelle errechnen
		double normalgewicht_ug = bminormalgewicht_ug * (groeße * groeße);
		normalgewicht_ug = Math.round(normalgewicht_ug * 10) / 10;

		double normalgewicht_og = bminormalgewicht_og * (groeße * groeße);
		normalgewicht_og = Math.round(normalgewicht_og * 10) / 10;

		double übergewicht_ug = normalgewicht_og + 0.1;

		double übergewicht_og = bmiübergewicht_og * (groeße * groeße);
		übergewicht_og = Math.round(übergewicht_og * 10) / 10;

		double adipositas = übergewicht_og + 0.1;

		// BMI Wert der passenden Kategorie zuordnen
		String einordnungBMI;

		if (bmi < bminormalgewicht_ug) {
			einordnungBMI = " → Untergewicht";
		}

		else if (bmi <= bminormalgewicht_og) {
			einordnungBMI = " → Normalgewicht";
		}

		else if (bmi <= bmiübergewicht_og) {
			einordnungBMI = " → Übergewicht";
		}

		else {
			einordnungBMI = " → Adipositas";
		}

		System.out.println("");

		System.out.println("                  Dein BMI   : " + bmi + einordnungBMI);
		System.out.println("                  Der BMI für die " + altersgruppe + " wird folgendermaßen interpretiert:");
		System.out.println("");
		System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
		System.out.println("                      KATEGORIE/BMI                    GEWICHT                 ");
		System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
		System.out.println("                      Untergewicht  <" + bminormalgewicht_ug + "         ⁞ unter "
				+ normalgewicht_ug + " Kg");
		System.out.println("                      Normalgewicht  " + bminormalgewicht_ug + "  - " + bminormalgewicht_og
				+ " ⁞ von   " + normalgewicht_ug + " - " + normalgewicht_og + " Kg");
		System.out.println("                      Übergewicht    " + bmiübergewicht_ug + " - " + bmiübergewicht_og
				+ "  ⁞ von   " + übergewicht_ug + " - " + übergewicht_og + " Kg");
		System.out.println(
				"                      Adipositas    >" + bmiadipositas + "         ⁞ ab    " + adipositas + " Kg");
		System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
	}

	public static void ausgabeEnergiebedarf(int grundumsatz, int leistungsumsatz, int gesamtumsatz, String geschlecht) {
		System.out.println("");
		if (geschlecht == "divers" || geschlecht == "Keine Angabe") {
			System.out.println(
					"         [ Info ] Die Mifflin-St. Jeor-Formel berechnet den Grundumsatz anhand des angegebenen Geschlechts.");
			System.out.println(
					"                  Da die Formel in ihrer berechnung nur zwischen Mann und Frau unterscheidet wurde für deine Berechnung,");
			System.out.println("                  der Mittelwert aus beiden Formeln herangezogen.");
		}
		System.out.println("");
		System.out.println(
				"         [ Info ] Die Körperzusammensetzung jedes Menschens ist sehr individuell und von vielen verschiedenen Faktoren abhängig.");
		System.out.println("                  Angegebene Werte stellen nur einen groben Richtwert dar.");
		System.out.println("");
		System.out.println(
				"                  Auf Grundlage deiner eingegebenen Daten und Mithilfe der Mifflin-St. Jeor-Formel wurde dein Grundumsatz ");
		System.out.println("                  Leistungsumsatz sowie Gesamtumsatz wie folgt berechnet :");
		System.out.println("");
		System.out.println("");
		System.out.println("                  Dein Grundumsatz beträgt     → " + grundumsatz
				+ " kcal pro Tag (Energiebedarf in Ruhe)");
		System.out.println("                  Dein Leistungsumsatz beträgt → " + leistungsumsatz
				+ " kcal pro Tag (Energiebedarf zusätzlich durch Aktivitätsniveau)");
		System.out.println("");
		System.out.println("                  Dein Gesamtumsatz beträgt    → " + gesamtumsatz
				+ " kcal pro Tag (Gesamter Energiebedarf)");
		System.out.println("");
		System.out.println("");
	}

	public static void ausgabeMakrotabelle(int gesamtumsatz) {// Nährstoffzusammensetzung berechnen (Anhand der
																// Richtwerte der DGE)
		double kh_d = gesamtumsatz * 0.50;// 50% Kohlenhydrate
		int kh = (int) kh_d;
		double e_d = gesamtumsatz * 0.20;// 20% Eiweiß
		int e = (int) e_d;
		double f_d = gesamtumsatz * 0.30;// 30% Fett
		int f = (int) f_d;

		// Nährstoffzusammensetzung in Gramm berechnen
		double kh_g = kh / 4;
		double e_g = e / 4;
		double f_g = f / 9;

		System.out.println(
				"                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
		System.out.println("                        NÄHRSTOFFGRUPPE            NÄRSTOFFVERTEILUNG IN KCAL/GRAMM");
		System.out.println(
				"                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
		System.out.println(
				"                        Kohlenhydrate (50%)	⁞  " + kh + " kcal/täglich ⁞ " + kh_g + " g/täglich");
		System.out.println(
				"                        Fette         (30%)	⁞  " + f + "  kcal/täglich ⁞ " + e_g + " g/täglich");
		System.out.println(
				"                        Eiweß         (20%)	⁞  " + e + "  kcal/täglich ⁞ " + f_g + "  g/täglich");
		System.out.println(
				"                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
		System.out.println("");
		System.out.println("");
		System.out.println(
				"————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("");
		System.out.println("");

	}

}// schließt Klasse ab
