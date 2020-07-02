package de.vit.botmain;

import java.util.Scanner;
import de.vit.logik.*;
import de.vit.typen.Information;
import de.vit.typen.Koordinaten;
import de.vit.typen.Position;

public class ErsterBot {
	/**
	 * Hauptmethode zum Ausführen des Bots
	 *
	 * @author Alexander Schmidt
	 * @param args
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

		Koordinaten koordinaten = new Koordinaten(startX, startY);
		Information information = new Information(playerId, level);

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

			Position position = new Position(lastActionsResult, currentCellStatus,
			northCellStatus, eastCellStatus,
			southCellStatus, westCellStatus);


			// Debug Information ausgeben (optional möglich)
			// System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			// Rundenaktion ausgeben
			System.out.println(Bewegungslogik.bewegung(position, koordinaten, information));

		}
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}
}
