package de.vit.bot;

import java.util.Scanner;
import de.vit.karte.*;
import de.vit.karte.felder.*;
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
		
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und
		// Rundendaten liefert
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
		Karte karte = new Karte(sizeX, sizeY, level, playerId, startX, startY);

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();
			
			// TODO: //Karte muss sich selbst aktualisieren, hier aufrufen
			karte.aktualisiereKarte(new Rundeninformationen(lastActionsResult, currentCellStatus, northCellStatus, eastCellStatus, southCellStatus, westCellStatus));
			
			//Die Entfernungen der Felder relativ zur Position des Bots werden je Runde aktualisiert:
			karte.entfernungenAktualisieren();

			// Rundenaktion ausgeben
			System.out.println(Bewegung.bewegung(karte));
		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}