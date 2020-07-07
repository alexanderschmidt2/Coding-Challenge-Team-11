package de.vit.Team11.bot;

import java.util.Scanner;
import de.vit.Team11.development.*;

public class Team11Bot {
	/**
	 * Hauptmethode zum Ausfuehren des Team11Bots
	 * @author Alexander Schmidt, Franz Bogmann
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
		
		input.nextLine(); // Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();

			// Debug Information ausgeben (optional m�glich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);

			// Rundenaktion ausgeben
			System.out.println(Bewegung.bewegung(lastActionsResult, currentCellStatus, northCellStatus, eastCellStatus, southCellStatus, westCellStatus));
		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
