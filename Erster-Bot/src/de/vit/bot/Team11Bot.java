package de.vit.bot;

import java.util.Scanner;
import de.vit.karte.Karte;
import de.vit.logik.Bewegung;
import de.vit.logik.Rundeninformationen;

public class Team11Bot {
	/**
	 * Hauptmethode zum Ausführen des Team11Bots
	 *
	 * @authors Alexander Schmidt, Franz Bogmann, Laura Fenzl und Constantin Graedtke
	 * 
	 */

	public static void main(String[] args) {
		
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
		input.nextLine(); // Beenden der zweiten Zeile
		
		// Karte mit Initialdaten instanziieren und generieren
		Karte karte = new Karte(sizeX, sizeY, level, playerId, startX, startY);
		
		//Rundeninformationen anlegen (leer)
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
			
			// Postition und Karte aktualisieren
			karte.aktualisiereKarte(runde);

			// Entfernungen der Felder auf der Karte aktualisieren
			karte.aktualisiereEntfernung();
			
			//Karte als String Konstrukt zeigen
			System.err.println(karte.getKarte());

			// Rundenaktion ausgeben; die eigentliche Aktion und setLastDoneAction
			System.out.println(Bewegung.bewegung(karte,runde));

		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}