package de.vit.bot;

import java.util.Scanner;
import de.vit.karte.*;
import de.vit.karte.felder.*;
import de.vit.logik.Bewegung;

public class Team11Bot {
	/**
	 * Hauptmethode zum Ausführen des Team11Bots
	 *
	 * @authors Alexander Schmidt, Franz Bogmann, Laura Fenzl und Constantin Graedtke
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); // Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert

	// INIT - Auslesen der Initialdaten
	// 1. Zeile: Maze Infos
		/*int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile*/
		
		//Initialisierung von Testwerten
		int sizeX = 10;
		int sizeY = 10;
		int level = 1;

		// 2. Zeile: Player Infos

		/*int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players //second 3 parts
		input.nextLine(); // Beenden der zweiten Zeile*/
		
		//Initialisierung von Testwerten
		int playerId = 1;
		int startX = 3;
		int startY = 4;
		
		//Labyrinth ist initialisiert, wir stehen auf dem Spielfeld. Wie finden wir uns zurecht?
		
		//1. Instanziieren der Karte mit den übergebenen Werten inklusive vermerken der eigenen Position
		Karte karte1 = new Karte(sizeX, sizeY, startX, startY);
		
		//2. Anlegen eines Sachbearbeiters (weil es ja einen geben muss) mit leeren Koordinaten, da noch unbekannt
		Sachbearbeiter ziel = new Sachbearbeiter(playerId);

		// TURN (Wiederholung je Runde notwendig)
		while (true) {
			// Rundeninformationen auslesen
			/*String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();*/
			
			//Initialisierung von Testwerten
			String lastActionsResult = "OK NORTH";
			String currentCellStatus = "FLOOR";
			String northCellStatus = "WALL";
			String eastCellStatus = "FLOOR";
			String southCellStatus = "FLOOR";
			String westCellStatus = "WALL";
			
			//die Runde hat begonnen, wir haben Informationen über 5 Felder erhalten: unter uns, nördlich, östlich, südlich, westlich
			//und was die letzte Aktion ergeben hat
			
			//1. eigene Position aktualisieren (überflüssig bei erstem Zug), den "Besucht-Zähler" in Feld hochsetzen
			karte1.aktualisierePosition1(lastActionsResult);
			System.out.println(karte1.getAktuellePosition().getX());
			System.out.println(karte1.getAktuellePosition().getY());
			break;
			
			//2. die neuen entdeckten Felder der Karte hinzufügen, dabei prüfen, ob "entdecktes" Feld nicht schon existiert

			// Debug Information ausgeben (optional möglich)

			// Rundenaktion ausgeben
			//System.out.println(Bewegung.bewegung(lastActionsResult, currentCellStatus, northCellStatus, eastCellStatus, southCellStatus, westCellStatus));

		}
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}
}
