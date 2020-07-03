package de.vit.logik;

import java.util.*;
import de.vit.typen.Koordinaten;
import de.vit.typen.Position;

public abstract class Bewegungslogik {

//Also da mir nichts Kluges einf�llt werden wir es Brute-Force machen, damit wir eine Basis haben
//Die Idee ist Folgende: Wir laufen <bold> immer </bold> die selbe Reihenfolge an Bewegungen ab, 
//die wir nach Lust und Laune festlegen. 

	// Norden, Osten, S�den, Westen
//ggf. m�ssen wir mit Vererbungen arbeiten bei den Actions
//Kriteriensuche-->gem. Kriterien, Sackgasse,
//Wege kennen-->Karte f�hren, da gab es Formular Deijkztra?, A*
//Reden wir �ber einige F�lle: 1. Wir spawnen auf dem SB->am Anfang den Check
//							   2. Wir laufen in eine Sackgasse->Wir merken uns alle bisherigen Knoten
//								und laufen dann zu dem Ersten zur�ck, wo wir eine Entscheidung hatten(link oder Rechts oder so)
//							   3.???
//							   4.Profit.
	public static String bewegung(Position position, Koordinaten koordinaten, int playerId) {

		String[] kompass = { "go north", "go east", "go south", "go west" };

		Stack<Integer> verlauf = new Stack<>();
		// Das hei�t wir haben uns erstmal bewegt, am Anfang wird noch nix gepushed, da
		// der lastActionResult nur OK war
		// Wir brauchen eine M�glichkeit uns zu merken, wo wir waren, an welcher Zelle

		if (position.getCurrentCellStatus().equals("FINISH " + playerId + " 0")) {
			return "finish";
		}

		if (position.getLastActionsResult().equals("OK NORTH")) {
			verlauf.push(0); // Norden unsere Letzte Aktion
		} else if (position.getLastActionsResult().equals("OK EAST")) {
			verlauf.push(1); // Osten unsere Letzte Aktion
		} else if (position.getLastActionsResult().equals("OK SOUTH")) {
			verlauf.push(2); // S�den unsere Letzte Aktion
		} else if (position.getLastActionsResult().equals("OK WEST")) {
			verlauf.push(3); // Westen unsere Letzte Aktion
		} else if (position.getLastActionsResult().equals("OK")) {
			verlauf.push(5);}
		// Hier m�ssen wir pr�fen, was jetzt unsere Umgebung ist, unabh�ngig vom Anfang,
		// aber wissend, wo wir hergekommen sind
		if (!position.getNorthCellStatus().equals("WALL") && verlauf.peek() != verlauf.peek()+2 % 3) { // Norden ist keine Wand und wir
																					// kommen nicht aus dem S�den, im Sinne von wir kommen da grad her
			return "go north";
		} else if (!position.getEastCellStatus().equals("WALL") && verlauf.peek() != verlauf.peek()+2 % 3) {
			
		} else if (!position.getSouthCellStatus().equals("WALL") && verlauf.peek() != verlauf.peek()+2 % 3) {
			return "go south";
		} else if (!position.getWestCellStatus().equals("WALL") && verlauf.peek() != verlauf.peek()+2 % 3) {
			return "go west";
		} else {
			return kompass[verlauf.pop() + 2 % 3]; // Wir sind in einer Sackgasse und wir m�ssen zur�ck
		}
		return "position"; 
	}
}
