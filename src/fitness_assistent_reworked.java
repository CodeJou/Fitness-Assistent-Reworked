import java.util.Scanner;

public class fitness_assistent_reworked {

	public static void main(String[] args) {
		
// AB HIER STARTET EINGABEBEREICH
    //(1) Begrüßung
	System.out.println("Hallo, ich bin dein Fitnessassistent. Wie heißt du?");
	Scanner scanner = new Scanner(System.in);
	String name = scanner.nextLine();
	System.out.println("");
	System.out.println("Hey "+name+", hier kannst du deinen BMI (Body-Mass-Index) berechnen.");

    
	//(2)Abfrage Größe
	System.out.println("Wie größ bist du? (z.B 1.64 m)");
			
    //Eingabe lesen/prüfen
	double Größe = leseGröße(scanner);
    
    
    //(3) Abfrage Gewicht
	System.out.println("");
	System.out.println("Wieviel wiegst du? (Gib dein Gewicht in kg an)");
		
    //Eingabe lesen/prüfen
	double Gewicht = leseGewicht(scanner);
    
    
	//(4) Abfrage Alter                               
	System.out.println("");
	System.out.println("Wie alt bist du? (Runde dein Alter auf eine ganze Zahl auf)");
		
	//Eingabe lesen/prüfen
	int Alter = leseAlter(scanner);
	
    
    //(5) Abfrage Geschlecht
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
	String Geschlecht = leseGeschlecht(scanner);
	
	
	//(6) Abfrage PAL Wert Aktivitätsfaktor
	System.out.println("");
	System.out.println("Wähle dein ungefaires Aktivitätsniveau.");
	System.out.println("1 - Nur sitzend, kaum Freizeitaktivität zB Bürojob");
	System.out.println("2 - Sitzend, etwas Aktivität z.B Studierende, Verkäufer");
	System.out.println("3 - Stehend und gehend, z.B. Handwerker, Gastronomie");
	System.out.println("4 - Körperlich anstrengend, z.B. Bauarbeiter, Leistungssportler");
	System.out.println("");
	System.out.println("Bitte gib jetzt die für dich passende Zahl ein:");
	
	//Eingabe lesen/prüfen
	int PAL = lesePAL(scanner);
	
    
//AB HIER WERTE INTERPRETIEREN UND VERARBEITEN
	//(7) BMI berechnen
	double bmi = berechneBMI (Gewicht,Größe); 
	
	//(8) Altersgruppe festlegen
	String Altersgruppe = bestimmeAltersgruppe(Alter);
	
	//(9) Grundumsatz berechnen 
	int Grundumsatz = berechneGrundumsatz(Gewicht,Größe,Alter,Geschlecht);
	
	//(10) Leistungsumsatz berechnen
	int Leistungsumsatz = berechneLeistungsumsatz(Grundumsatz,PAL);
	
	//(11) Gesamtumsatz berechnen
	int Gesamtumsatz = berechneGesamtumsatz (Grundumsatz,Leistungsumsatz);
	
//AB HIER AUSGABE
	// Ausgabe Profil
	AusgabeProfil(name,Alter,Größe,Gewicht,Geschlecht);
	
	// Ausgabe der BMI-Tabelle
	AusgabeBMItabelle(Altersgruppe,bmi,Größe);
				
    // Angabe Infos+Energiebedarf
	AusgabeEnergiebedarf(Grundumsatz,Leistungsumsatz,Gesamtumsatz,Geschlecht);
	
	//Ausgabe Makros-Tabelle
	AusgabeMakrotabelle(Gesamtumsatz);
}

//AB HIER METHODEN	
//Methoden zum Bereich "Eingabe"
	//(2)
public static double bereinigegröße(String größe)//Hilfsmethode bereinigt String Größe
{ 	größe = größe.replace("m","");//Maßeinheit entfernen
    größe = größe.replace(" ","");//Leerzeichen entfernen
    größe = größe.replace(",",".");//Komma durch Punkt ersetzen
    
    double Größe = Double.parseDouble(größe);// Datentyp String in Double umwandeln
	return Größe;
}
    //(2)
public static double prüfeWertebereichGröße (double Größe, Scanner scanner)//Hilfsmethode prüft Wertebereich Größe
{   String größe;

	while (Größe>2.1||Größe<1.0)//While Schleife wenn Zahlenwerte außerhalb 1.00 und 2.10 Metern angegeben sind
	{System.out.println("");
     System.out.println("[ Meldung: Bitte gib einen Zahlenwert zwischen 1.00 Meter und 2.10 Meter an (z.B 1.64m ]");
	 größe = scanner.nextLine();
	 Größe = bereinigegröße(größe);
		  }
	return Größe;
}
    //(2)
public static double leseGröße(Scanner scanner)//Größe einlesen/validieren
{ String größe;
  double Größe;
  try
  { größe = scanner.nextLine();
    Größe = bereinigegröße(größe);
	Größe = prüfeWertebereichGröße(Größe,scanner);
	   }
  catch (java.lang.NumberFormatException exception_2)//Fehlermeldung abfangen bei fehlerhafter Eingabe (Bei Eingabe "cm")
	{System.out.println();  
     System.out.println("[ Meldung: Bitte gib deine Größe als Zahlenwert in m (z.B 1.64m) an ]");
	 größe = scanner.nextLine();
	 Größe = bereinigegröße(größe);	    
	 Größe = prüfeWertebereichGröße(Größe,scanner);
	  }	
	return Größe;
}
    //(3)
public static double bereinigeGewicht(String gewicht)//Hilfsmethode Gewicht
{   gewicht = gewicht.replace(" kg","");//Maßeinheit entfernen
	gewicht = gewicht.replace(" ","");//Leerzeichen entfernen
	gewicht = gewicht.replace(",",".");//Komma durch Punkt ersetzen
	   
	double Gewicht = Double.parseDouble(gewicht);//Datentyp String zu Double umwandeln
    return Gewicht;
}
    //(3)
public static double leseGewicht(Scanner scanner)//Gewicht einlesen und validieren
{ String gewicht;
  double Gewicht;
  try 
  { gewicht = scanner.nextLine();
	Gewicht = bereinigeGewicht(gewicht);
     }
  catch (java.lang.NumberFormatException exception_3)
  { System.out.println("");
	System.out.println("[ Meldung: Bitte gib dein Gewicht in kg (z.B 65,5 kg) an ]");//Meldung wenn Eingabe keinen Zahlenwert beinhaltet
	gewicht = scanner.nextLine();
	Gewicht = bereinigeGewicht(gewicht);
	 }	
	return Gewicht;
}
   //(4)
public static int prüfeWertebereichAlter(int Alter, Scanner scanner)//Hilfsmethode um Wertebereich Alter zu prüfen
{ if (Alter<19||Alter>99)//Wenn Eingabe außerhalb 19 bis 99 liegt
	{ System.out.println();
	  System.out.println("[ Meldung : Bitte gib ein Alter zwischen 19 und 99 an ]");
	  Alter = scanner.nextInt(); 
	  }
   scanner.nextLine();//Scanner leeren
   return Alter;
}
   //(5)
public static int leseAlter(Scanner scanner) 
{ int Alter;
  try 
  { Alter = scanner.nextInt();
	Alter = prüfeWertebereichAlter(Alter,scanner);
	   }
  catch (java.util.InputMismatchException expection_4)
  { System.out.println("");
	System.out.println("[ Meldung: Bitte gib dein Alter als Zahlenwert (zwischen 19 und 99 ) ein! ]");
	scanner.nextLine();//Scanner leeren
	Alter = scanner.nextInt();
	Alter = prüfeWertebereichAlter(Alter,scanner);
	    }
	return Alter;
}
   //(5)
public static String leseGeschlecht(Scanner scanner)
{ String Geschlecht;
  Geschlecht = scanner.nextLine();
	
  if (Geschlecht.equalsIgnoreCase("m"))
  { Geschlecht = "männlich";}
  else if (Geschlecht.equalsIgnoreCase("w"))
  { Geschlecht = "weiblich";}
  else 
  { Geschlecht = "divers";}
	
	return Geschlecht;
}
   //(6)
public static int prüfeWertebereichPAL(int PAL,Scanner scanner)//Hilfsmethode prüft Wertebereich PAL
{  
	while (PAL<1||PAL>4)//Wenn eingegebener Zahlenwert außerhalb 1 - 4 liegt
	{ System.out.println("");
	  System.out.println("[ Meldung: Bitte gib einen Zahlenwert von 1 bis 4 an ]");
	  PAL = scanner.nextInt();
	}
	return PAL;
}
   //(6)
public static int lesePAL(Scanner scanner)
{ int PAL;
  try 
  { PAL = scanner.nextInt();//Antwort scnannen
	PAL = prüfeWertebereichPAL(PAL,scanner);
	    
	}
  catch (java.util.InputMismatchException exception_6)//Wenn Eingabe kein Zahlenwert Typ int entspricht
  { scanner.nextLine();//Scanner leeren
	System.out.println("");
	System.out.println("[ Meldung: Bitte gib einen ganzen Zahlenwert von 1 bis 4 an ]");
	PAL = scanner.nextInt();
	PAL = prüfeWertebereichPAL(PAL,scanner);
	}
	return PAL;
}
//Methoden zum Bereich "Interpretieren und Verarbeiten"
    //(7)
public static double berechneBMI (double Gewicht, double Größe)
{ double bmi;
  bmi = Gewicht/(Größe*Größe);
  bmi = Math.round(bmi*100.0)/100.0;//Auf zwei Nachkommastellen runden.
  return bmi;
}
    //(8)
public static String bestimmeAltersgruppe(int alter)
{   String AltersgruppeA = "Altersgruppe 19 bis 24 Jahre";
	String AltersgruppeB = "Altersgruppe 25 bis 34 Jahre";
	String AltersgruppeC = "Altersgruppe 35 bis 44 Jahre";
	String AltersgruppeD = "Altersgruppe 45 bis 54 Jahre";
	String AltersgruppeE = "Altersgruppe 55 bis 64 Jahre";
	String AltersgruppeF = "Altersgruppe ab 65 Jahre";
	
	String Altersgruppe;
	
	//Altersgruppe bestimmen
	if (alter<=24)
	{ Altersgruppe = AltersgruppeA;
	}
	else if (alter<=34)
	{ Altersgruppe = AltersgruppeB;
	}
	else if (alter<=44)
	{ Altersgruppe = AltersgruppeC;
	}
	else if (alter<=54)
	{ Altersgruppe = AltersgruppeD;
	}
	else if (alter<=64)
	{ Altersgruppe = AltersgruppeE;
	}
	else
	{ Altersgruppe = AltersgruppeF;
	}	
	
	return Altersgruppe;
}
public static double ordneBMIderAltersgruppezu(String Altersgruppe, double bmi)
{	String AltersgruppeA = "Altersgruppe 19 bis 24 Jahre";
	String AltersgruppeB = "Altersgruppe 25 bis 34 Jahre";
	String AltersgruppeC = "Altersgruppe 35 bis 44 Jahre";
	String AltersgruppeD = "Altersgruppe 45 bis 54 Jahre";
	String AltersgruppeE = "Altersgruppe 55 bis 64 Jahre";
	double BMInormalgewicht_ug;
	
	if (Altersgruppe==AltersgruppeA)
	{ BMInormalgewicht_ug = 19.00;
	}
	else if (Altersgruppe==AltersgruppeB)
	{ BMInormalgewicht_ug = 20.00;
	  }
	else if (Altersgruppe==AltersgruppeC)
	{ BMInormalgewicht_ug = 21.00;
      }
	else if (Altersgruppe==AltersgruppeD)
	{ BMInormalgewicht_ug = 22.00;
	 }
	else if (Altersgruppe==AltersgruppeE)
	{ BMInormalgewicht_ug = 23.00;
	 }
	else 
	{ BMInormalgewicht_ug = 24.00;
	  }
	
	return BMInormalgewicht_ug;
}
    //(9)
public static int berechneGrundumsatz (double Gewicht,double Größe,int alter,String Geschlecht)
{
	double Größe_cm = Größe*100;
	double Grundumsatz_d = (10*Gewicht)+(6.25*Größe_cm)-(5*alter);
	
	if (Geschlecht=="männlich")
	{ Grundumsatz_d = Grundumsatz_d+5;}
	else if (Geschlecht=="weiblich")
	{ Grundumsatz_d = Grundumsatz_d-161;}
	else
	{ Grundumsatz_d = Grundumsatz_d-83;}//Bei Eingabe divers wird der Mittelwert berechnet
	
    int Grundumsatz = (int)Grundumsatz_d;//Alles hinter dem Komma wird "abgeschnitten"
    
    return Grundumsatz ;
}
    //(10)
public static int berechneLeistungsumsatz(int Grundumsatz, double PAL)
{   double PAL_Wert;
    if (PAL==1)
    { PAL_Wert = 1.4;} 
    else if (PAL==2)
    { PAL_Wert = 1.6;}
    else if (PAL==3)
    { PAL_Wert = 1.8;}
    else
    { PAL_Wert = 2.0;}
	
	double Leistungsumsatz_d = Grundumsatz*(PAL_Wert-1);
    int Leistungsumsatz = (int)Leistungsumsatz_d;//alles hinterm Komma wird "abgeschnitten"
	
    return Leistungsumsatz;
}
    //(11)
public static int berechneGesamtumsatz (int Grundumsatz, int Leistungsumsatz)
{   int Gesamtumsatz = Grundumsatz+Leistungsumsatz;
	return Gesamtumsatz;
}
//Methoden zum Bereich Ausgabe
public static void AusgabeProfil(String name, int Alter, double Größe,double Gewicht, String Geschlecht)
{
	System.out.println("");
	System.out.println("");
	System.out.println("————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
	System.out.println(""); 
	System.out.println("Danke für deine Angaben.");
	System.out.println("Nachfolgend siehst du deine Körperwerte und den daraus resultierenden BMI (Body-Mass-Index)");
	System.out.println("");
	System.out.println("                  Name       : "+name);
	System.out.println("                  Alter      : "+Alter+" Jahre");
	System.out.println("                  Größe      : "+Größe+" m");
	System.out.println("                  Gewicht    : "+Gewicht+" kg");
	System.out.println("                  Geschlecht : "+Geschlecht);
	System.out.println("");
	
}
public static double berechnehöherenBMI (double BMInormalgewicht_ug)
{  double nächsteKlasse = BMInormalgewicht_ug+5;
   return nächsteKlasse;}
public static void AusgabeBMItabelle(String Altersgruppe,double bmi,double Größe)
{
    double BMInormalgewicht_ug = ordneBMIderAltersgruppezu (Altersgruppe,bmi);//BMI Wert abhängig vom Alter zuordnen
	//BMI Werte für die Tabelle errechnen
	double BMInormalgewicht_og = berechnehöherenBMI(BMInormalgewicht_ug);
	double BMIübergewicht_ug   = BMInormalgewicht_og+0.1;//Untere Grenze der nächsthöheren klasse sind plus 0.1
	double BMIübergewicht_og   = BMInormalgewicht_og+5;//
	double BMIadipositas       = BMIübergewicht_og+0.1;
		
    //Gewichte für die Tabelle errechnen
	double normalgewicht_ug = BMInormalgewicht_ug*(Größe*Größe); 
	normalgewicht_ug = Math.round(normalgewicht_ug*10)/10;
		
	double normalgewicht_og = BMInormalgewicht_og*(Größe*Größe);
	normalgewicht_og = Math.round(normalgewicht_og*10)/10;
		
	double übergewicht_ug = normalgewicht_og+0.1;
		
	double übergewicht_og = BMIübergewicht_og*(Größe*Größe);
	übergewicht_og = Math.round(übergewicht_og*10)/10;
		
	double adipositas = übergewicht_og+0.1;
		
    //BMI Wert der passenden Kategorie zuordnen
	String einordnungBMI;
		
	if (bmi<BMInormalgewicht_ug)
	{ einordnungBMI = " → Untergewicht";}
					
	else if (bmi<=BMInormalgewicht_og)
	{ einordnungBMI = " → Normalgewicht";}
					
	else if (bmi<=BMIübergewicht_og)
	{ einordnungBMI = " → Übergewicht";}
					
	else 
	{ einordnungBMI = " → Adipositas";}
		
	System.out.println("");
	System.out.println("                  Dein BMI   : "+bmi+einordnungBMI);
	System.out.println("                  Der BMI für die "+Altersgruppe+" wird folgendermaßen interpretiert:");
	System.out.println("");
	System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
	System.out.println("                      KATEGORIE/BMI                    GEWICHT                 ");
	System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
	System.out.println("                      Untergewicht  <"+BMInormalgewicht_ug+                         "         ⁞ unter "+normalgewicht_ug+" Kg");
	System.out.println("                      Normalgewicht  "+BMInormalgewicht_ug+"  - "+BMInormalgewicht_og+      " ⁞ von   "+normalgewicht_ug+" - "+normalgewicht_og+" Kg");
	System.out.println("                      Übergewicht    "+BMIübergewicht_ug+" - "+BMIübergewicht_og+          "  ⁞ von   "+übergewicht_ug+" - "+übergewicht_og+" Kg");
	System.out.println("                      Adipositas    >"+BMIadipositas+                               "         ⁞ ab    "+adipositas+" Kg");
	System.out.println("                  …………………………………………………………………………………………………………………………………………     ");
}

public static void AusgabeEnergiebedarf(int Grundumsatz,int Leistungsumsatz, int Gesamtumsatz, String geschlecht)
{
	System.out.println("");
	if (geschlecht=="divers")
   {System.out.println("         [ Info ] Die Mifflin-St. Jeor-Formel berechnet den Grundumsatz anhand des angegebenen Geschlechts.");
	System.out.println("                  Da die Formel in ihrer berechnung nur zwischen Mann und Frau unterscheidet wurde für deine Berechnung,");
    System.out.println("                  der Mittelwert aus beiden Formeln herangezogen.");
   }
	System.out.println("");
	System.out.println("         [ Info ] Die Körperzusammensetzung jedes Menschens ist sehr individuell und von vielen verschiedenen Faktoren abhängig.");
	System.out.println("                  Angegebene Werte stellen nur einen groben Richtwert dar.");
	System.out.println("");
	System.out.println("                  Auf Grundlage deiner eingegebenen Daten und Mithilfe der Mifflin-St. Jeor-Formel wurde dein Grundumsatz ");
	System.out.println("                  Leistungsumsatz sowie Gesamtumsatz wie folgt berechnet :");
	System.out.println("");
	System.out.println("");
	System.out.println("                  Dein Grundumsatz beträgt     → "+Grundumsatz+" kcal pro Tag (Energiebedarf in Ruhe)");
	System.out.println("                  Dein Leistungsumsatz beträgt → "+Leistungsumsatz+" kcal pro Tag (Energiebedarf zusätzlich durch Aktivitätsniveau)");
	System.out.println("");
	System.out.println("                  Dein Gesamtumsatz beträgt    → "+Gesamtumsatz+" kcal pro Tag (Gesamter Energiebedarf)");
	System.out.println("");
	System.out.println("");	
}
public static void AusgabeMakrotabelle(int Gesamtumsatz)
{// Nährstoffzusammensetzung berechnen (Anhand der Richtwerte der DGE)
	double KH_d = Gesamtumsatz*0.50;//50% Kohlenhydrate
	int KH = (int)KH_d;
	double E_d  = Gesamtumsatz*0.20;//20% Eiweiß
	int E = (int)E_d;
	double F_d  = Gesamtumsatz*0.30;//30% Fett
	int F = (int)F_d;
	
	//Nährstoffzusammensetzung in Gramm berechnen
	double KH_g = KH/4;
	double E_g = E/4;
	double F_g = F/9;
	
	System.out.println("                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
	System.out.println("                        NÄHRSTOFFGRUPPE            NÄRSTOFFVERTEILUNG IN KCAL/GRAMM");
	System.out.println("                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
	System.out.println("                        Kohlenhydrate (50%)	⁞  "+KH+" kcal/täglich ⁞ "+KH_g+" g/täglich");
	System.out.println("                        Fette         (30%)	⁞  "+F+"  kcal/täglich ⁞ "+E_g+" g/täglich");
	System.out.println("                        Eiweß         (20%)	⁞  "+E+"  kcal/täglich ⁞ "+F_g+"  g/täglich");
	System.out.println("                  …………………………………………………………………………………………………………………………………………………………………………………………………………………………………………     ");
	System.out.println("");
	System.out.println("");
	System.out.println("————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
	System.out.println("");
	System.out.println("");
	 
}

}//schließt Klasse ab
