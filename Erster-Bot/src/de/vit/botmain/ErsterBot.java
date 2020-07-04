package de.vit.botmain;

import java.util.Scanner;

import de.vit.initialisierung.*;
import de.vit.logik.*;
import de.vit.typen.*;

public class ErsterBot {
	/**
	 * Hauptmethode zum Ausführen des Team11Bots
	 *
	 * @authors Alexander Schmidt, Franz Bogmann, Laura Fenzl und Constantin Graedtke
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); // Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert

//		// INIT - Auslesen der Initialdaten
//		// 1. Zeile: Maze Infos
//		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
//		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
//		int level = input.nextInt(); // Level des Matches 
		Spielfeld spielfeld = new Spielfeld(new Koordinaten(input.nextInt(), input.nextInt()), input.nextInt()); // Die Information über die Größe und das Level des Maze werden im Spielfeld hinterlegt
		input.nextLine(); // Beenden der ersten Zeile

		// 2. Zeile: Player Infos

//		int playerId = input.nextInt(); // id dieses Players / Bots
//		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
//		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players //second 3 parts
		Spieler spieler = new Spieler(input.nextInt(), new Koordinaten(input.nextInt(), input.nextInt()), spielfeld);
		Speicherlogik speicher = new Speicherlogik();
		input.nextLine(); // Beenden der zweiten Zeile

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			// Rundeninformationen auslesen
//			String lastActionsResult = input.nextLine();
//			String currentCellStatus = input.nextLine();
//			String northCellStatus = input.nextLine();
//			String eastCellStatus = input.nextLine();
//			String southCellStatus = input.nextLine();
//			String westCellStatus = input.nextLine();

			System.out.println(spieler.aktion(new Zelle(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(),
					input.nextLine(), input.nextLine()),spielfeld));

			// Debug Information ausgeben (optional möglich)
			// System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			// Rundenaktion ausgeben

		}
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}
}
