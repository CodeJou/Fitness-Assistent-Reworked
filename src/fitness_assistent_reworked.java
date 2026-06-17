import java.util.Scanner;

public class fitness_assistent_reworked {

	public static void main(String[] args) {
		// Hier startet der überarbeitete Code des Fitness Assistenten
		
	//(1) BEGRÜßUNG
	System.out.println("Hallo, ich bin dein Fitnessassistent. Wie heißt du?");
	Scanner scanner = new Scanner(System.in);
	String name = scanner.nextLine();
	System.out.println("");
	System.out.println("Hey "+name+", hier kannst du deinen BMI (Body-Mass-Index) berechnen.");

	
	//(2) ABFRAGE GRÖßE
	System.out.println("Wie größ bist du? (z.B 1.64 m)");
			
	//(2) Variablen deklarieren
    double Größe;
    String größe;
	    
	//(2) Try/catch Eingabevalidierung
	try
    { größe = scanner.nextLine();//scannen
	  größe = pruefeGroeße(größe);
	  Größe = Double.parseDouble(größe);// Datentyp String in Double umwandeln
		        
      while (Größe>2.1||Größe<1.0)//While Schleife wenn Zahlenwerte außerhalb 1.00 und 2.10 Metern angegeben sind
	  {System.out.println("");
       System.out.println("[ Meldung: Bitte gib einen Zahlenwert zwischen 1.00 Meter und 2.10 Meter an (z.B 1.64m ]");
	   größe = scanner.nextLine();
		
	  größe = pruefeGroeße(größe);
	  Größe = Double.parseDouble(größe);// Datentyp String in Double umwandeln
		  }
	   }
  
    catch (java.lang.NumberFormatException exception_2)//Fehlermeldung abfangen bei fehlerhafter Eingabe (Bei Eingabe "cm")
	{System.out.println();  
     System.out.println("[ Meldung: Bitte gib deine Größe als Zahlenwert in m (z.B 1.64m) an ]");
	 größe = scanner.nextLine();
		    
	  größe = pruefeGroeße(größe);
	  Größe = Double.parseDouble(größe);// Datentyp String in Double umwandeln
		    
	 while (Größe>2.1||Größe<1.0)//While Schleife wenn Zahlenwerte außerhalb 1.00 und 2.10 Metern angegeben sind
	 {System.out.println();
      System.out.println("[ Meldung: Bitte gib einen Zahlenwert zwischen 1.00 Meter und 2.10 Meter an (z.B 1.64m ]");
	  größe = scanner.nextLine();
		    
	  größe = pruefeGroeße(größe);
	  Größe = Double.parseDouble(größe);// Datentyp String in Double umwandeln
		 }
	  }	
    
	//(3) ABFRAGE GEWICHT
	System.out.println("");
	System.out.println("Wieviel wiegst du? (Gib dein Gewicht in kg an)");
		
	//(3) Variable deklarieren
	double Gewicht;
	String gewicht;
	
	
	//(3) Try/Catch Eingabevalidierung
	try 
	{ gewicht = scanner.nextLine();
	  gewicht = pruefeGewicht(gewicht);
	  Gewicht = Double.parseDouble(gewicht);//Datentyp String zu Double umwandeln
	    }
		
	catch (java.lang.NumberFormatException exception_3)
	{ System.out.println("");
	  System.out.println("[ Meldung: Bitte gib dein Gewicht in kg (z.B 65,5 kg) an ]");//Meldung wenn Eingabe keinen Zahlenwert beinhaltet
	  gewicht = scanner.nextLine();
			
	  gewicht = pruefeGewicht(gewicht);
	  Gewicht = Double.parseDouble(gewicht);
		}
    
	//(4) ABFRAGE ALTER                               
	System.out.println("");
	System.out.println("Wie alt bist du? (Runde dein Alter auf eine ganze Zahl auf)");
		
	//(4) Variable deklarieren
	int alter;
	
	//(4) Eingabevalidierung try/catch
	try 
	{ alter = scanner.nextInt();
	  if (alter<19||alter>99)//Wenn Eingabe außerhalb 19 bis 99 liegt
	  {System.out.println();
	   System.out.println("[ Meldung : Bitte gib ein Alter zwischen 19 und 99 an ]");
	   alter = scanner.nextInt();
		  }
	   }
	
	catch (java.util.InputMismatchException expection_4)
	{System.out.println("");
	 System.out.println("[ Meldung: Bitte gib dein Alter als Zahlenwert (zwischen 19 und 99 ) ein! ]");
	 scanner.nextLine();// Scanner leeren
	 alter = scanner.nextInt();
	   }
		
	scanner.nextLine();//scanner leeren
    
	//(5) ABFRAGE GESCHLECHT
	System.out.println("");
	System.out.println("Was möchtest du für ein Geschlecht angeben?");
	System.out.println("Folgende Auswahlmöglichkeiten hast du:");
	System.out.println("");
	System.out.println("m steht für männlich");
	System.out.println("w steht für weiblich");
	System.out.println("d steht für divers");
	System.out.println("");
	System.out.println("Bitte gib jetzt den für dich passenden Buchstaben ein:");
	
	//(5) Variable deklarieren
	String geschlecht;
	
	//(5) Antwort scannen
	geschlecht = scanner.nextLine();
	
	//(5) if else Anweisung für die Wertezuweisung        
	if (geschlecht.equalsIgnoreCase("m"))
	{geschlecht = "männlich";}
	else if (geschlecht.equalsIgnoreCase("w"))
	{geschlecht = "weiblich";}
	else 
	{geschlecht = "divers";}
	
	//(6) Abfrage PAL Wert Aktivitätsfaktor
	System.out.println("");
	System.out.println("Wähle dein ungefaires Aktivitätsniveau.");
	System.out.println("1 - Nur sitzend,zB Bürojob");
	System.out.println("2 - Sitzend, etwas Aktivität");
	System.out.println("3 - Stehend und gehend, z.B. Verkauf");
	System.out.println("4 - Körperlich anstrengend, z.B. Bauarbeiter");
	System.out.println("");
	System.out.println("Bitte gib jetzt die für dich passende Zahl ein:");
	
	//(6) Variable für PAL Wert deklarieren
	int PAL;
	
	//(6) Eingabenvalidierung
	try 
	{ PAL = scanner.nextInt();//Antwort scnannen
	  while (PAL<1||PAL>4)//Wenn eingegebener Zahlenwert außerhalb 1 - 4 liegt
	  {System.out.println("");
	   System.out.println("[ Meldung: Bitte gib einen Zahlenwert von 1 bis 4 an ]");
	   PAL = scanner.nextInt();
	    }
	 }
	
	catch (java.util.InputMismatchException exception_6)//Wenn Eingabe kein Zahlenwert Typ int entspricht
	{ scanner.nextLine();//Scanner leeren
	  System.out.println("");
	  System.out.println("[ Meldung: Bitte gib einen ganzen Zahlenwert von 1 bis 4 an ]");
	  PAL = scanner.nextInt();
	 
	  while (PAL<1||PAL>4)
	  { System.out.println("");
	    System.out.println("[ Meldung: Bitte gib einen Zahlenwert von 1 bis 4 an ]");
	    PAL = scanner.nextInt();
	    }
	}
	
	//AB HIER WERTE INTERPRETIEREN UND VERARBEITEN
	
	//(7) BMI berechnen
	double bmi;
	bmi = Gewicht/(Größe*Größe);
	bmi = Math.round(bmi*100.0)/100.0;//Auf zwei Nachkommastellen runden. 
	
	//(7) Altersgruppe festlegen
	String Altersgruppe;
	
	String AltersgruppeA = "Altersgruppe 19 bis 24 Jahre";
	String AltersgruppeB = "Altersgruppe 25 bis 34 Jahre";
	String AltersgruppeC = "Altersgruppe 35 bis 44 Jahre";
	String AltersgruppeD = "Altersgruppe 45 bis 54 Jahre";
	String AltersgruppeE = "Altersgruppe 55 bis 64 Jahre";
	String AltersgruppeF = "Altersgruppe ab 65 Jahre";

	//(7) Altersgruppe bestimmen
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
	
	//(7) BMI Werte festlegen
	double BMInormalgewicht_ug;
	double BMInormalgewicht_og;
	double BMIübergewicht_ug;
	double BMIübergewicht_og;
	double BMIadipositas;
	
	//BMI Wert abhängig vom Alter zuordnen 
	
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
	
	//BMI Werte für die Tabelle errechnen
	  BMInormalgewicht_og = berechnehöherenBMI(BMInormalgewicht_ug);
	  BMIübergewicht_ug   = BMInormalgewicht_og+0.1;//Untere Grenze der nächsthöheren klasse sind plus 0.1
	  BMIübergewicht_og   = BMInormalgewicht_og+5;//
	  BMIadipositas       = BMIübergewicht_og+0.1;
	
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

	
	//(8) Grundumsatz/Leistungsumsatz ermitteln
	double PAL_Wert;//Variable zur Einordnung der Angabe zum Aktivitätsniveau
	
	//(8) Aktivitätsfaktor PAL dem entsprechenden Wert zuordnen (Mittelwerte zur Berechnung verwendet)
	if (PAL==1)
	{ PAL_Wert = 1.45;} 
	else if (PAL==2)
	{ PAL_Wert = 1.65;}
	else if (PAL==3)
	{ PAL_Wert = 1.85;}
	else
	{ PAL_Wert = 2.2;}
	
	//(8) Grundumsatz und Leistungsumsatz berechnen mit klassischer Formel (Harris-Benedict)
	double Größe_cm = Größe*100;
	
	double Grundumsatz = 66.47+(13.7*Gewicht)+(5*Größe_cm)-(6.8*alter);
	int GUgerundet = (int)Grundumsatz;//Alles hinter dem Komma wird "abgeschnitten"
	
	double Leistungsumsatz = Grundumsatz*PAL_Wert;
	int LUgerundet = (int)Leistungsumsatz;//alles hinterm Komma wird "abgeschnitten"
	
	//(9) Aufführung der eingegebenen Parameter
	System.out.println("");
	System.out.println("Danke für deine Angaben.");
	System.out.println("Nachfolgend siehst du deine Körperwerte und den daraus resultierenden BMI (Body-Mass-Index)");
	System.out.println("");
	System.out.println("                  Name       : "+name);
	System.out.println("                  Alter      : "+alter+" Jahre");
	System.out.println("                  Größe      : "+Größe+" m");
	System.out.println("                  Gewicht    : "+Gewicht+" kg");
	System.out.println("                  Geschlecht : "+geschlecht);
	System.out.println("");
	
	
	
	//(10) Tabelle BMI ausgeben
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
				
    //(11) Angabe zum Grundumsatz
	System.out.println("");
	System.out.println("");
	System.out.println("                  Auf Grundlage deiner eingegebenen Daten und Mithilfe der klassischen Harris-Benedict-Formel konnte dein Grundumsatz, ");
	System.out.println("                  sowie dein Leistungsumsatz wie folgt berechnet werden :");
	System.out.println("");
	System.out.println("                  Dein Grundumsatz beträgt → "+GUgerundet+" kcal/täglich");
	System.out.println("                  Dein Leistungsumsatz beträgt → "+LUgerundet+" kcal/täglich");
	
	
	
	}//Schließt main ab
	
public static String pruefeGroeße(String größe)//prüft ob Leerzeichen und/oder "m" eingegeben wurde
{ 	if (größe.contains(" m"))//if else Anweisung wenn Eingabe Maßeinheit + Leerzeichen beinhaltet
     {  größe = größe.replace(" m","");//Maßeinheit entfernen
		größe = größe.toUpperCase();//Wert nach Entfernung der Maßeinheit neu zuweisen
     	}

	else
	{  	größe = größe.replace("m","");
		größe = größe.toUpperCase();
		}   
   
	if (größe.contains(","))//wenn Komma eingegeben ist
	{	größe = größe.replace(",",".");//Komma durch Punkt ersetzen
		größe = größe.toUpperCase();//Variable neu zuordnen
		}
	return größe;
}
public static String pruefeGewicht(String gewicht)
{  if (gewicht.contains(" kg"))//if else Anweisung wenn Maßeinheit mit Leerzeichen oder/und Komma statt Punkt eingegeben ist
	{  gewicht = gewicht.replace(" kg","");//Maßeinheit löschen
	   gewicht = gewicht.toUpperCase();//Variable neu zuordnen
		}

   else
	{  gewicht = gewicht.replace("kg","");//Wenn Maßeinheit ohne Leerzeichen oder/und Komma statt Punkt eingegeben ist/Maßeinheit löschen
	   gewicht = gewicht.toUpperCase();//Variable neu zuordnen
		}

   if (gewicht.contains(","))//wenn Komma eingegeben ist
	{  gewicht = gewicht.replace(",",".");//Komma durch Punkt ersetzen
	   gewicht = gewicht.toUpperCase();//Variable neu zuordnen
		}
   return gewicht;
}
public static double berechnehöherenBMI (double BMInormalgewicht_ug)
{  double nächsteKlasse = BMInormalgewicht_ug+5;
   return nächsteKlasse;


}
}//schließt Klasse ab
