package de.vit.bot;

import java.util.Scanner;
import de.vit.karte.Karte;
import de.vit.logik.Bewegung;
import de.vit.logik.Rundeninformationen;

public class Team11Bot {
	/**
	 * Hauptmethode zum Ausfuehren des Team11Bots
	 *
	 * @authors Team11Bot: Alexander Schmidt, Franz Bogmann, Laura Fenzl und Constantin Graedtke
	 * 
	 */

	public static void main(String[] args) {
		
		int sheetCount = 0; // Lokale Variable sheetCount initialisieren, damit diese genutzt werden kann. In Level 1 bis 4 spielen wir ohne Sheets.
		
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert
		Scanner input = new Scanner(System.in);

		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches

		input.nextLine();// Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		if (level == 5) { //wenn level 5: dann SheetCount abfragen
			sheetCount = input.nextInt();//Anzahl der Papiere zu Beginn des Spiels
		}
		input.nextLine(); // Beenden der zweiten Zeile
		
		// Level 1 bis 5: Karte mit Initialdaten instanziieren (Level 1 bis 4: ohne sheetCount)
		Karte karte = new Karte(sizeX, sizeY, level, playerId, startX, startY);
		// Level 5 Zusatz: sheetCount
		karte.setSheetCount(sheetCount); 

		// Rundeninformationen anlegen (leer)
		Rundeninformationen runde = new Rundeninformationen();

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();
			
			// Rundeninformationen aktualisieren
			runde.setInputs(lastActionsResult, currentCellStatus, northCellStatus, eastCellStatus, southCellStatus, westCellStatus);
			
			// Position und Karte aktualisieren
			karte.aktualisiereKarte(runde);

			// Entfernungen der Felder auf der Karte aktualisieren
			karte.aktualisiereEntfernung();
			
			// Karte als String Konstrukt zeigen (nur zu Debug-Zwecken)
			//System.err.println(karte.getKarte());

			// Rundenaktion ausgeben: Die Aktion des Bots je Runde
			System.out.println(Bewegung.bewegung(karte,runde));

		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}