package de.vit.logik;
import java.util.*;
import de.vit.typen.Koordinaten;
import de.vit.typen.Zelle;

public abstract class Bewegungslogik {

//Also da mir nichts Kluges einfällt werden wir es Brute-Force machen, damit wir eine Basis haben
//Die Idee ist Folgende: Wir laufen <bold> immer </bold> die selbe Reihenfolge an Bewegungen ab, 
//die wir nach Lust und Laune festlegen. 

	// Norden, Osten, Süden, Westen
//ggf. müssen wir mit Vererbungen arbeiten bei den Actions
//Kriteriensuche-->gem. Kriterien, Sackgasse,
//Wege kennen-->Karte führen, da gab es Formular Deijkztra?, A*
//Reden wir über einige Fälle: 1. Wir spawnen auf dem SB->am Anfang den Check
//							   2. Wir laufen in eine Sackgasse->Wir merken uns alle bisherigen Knoten
//								und laufen dann zu dem Ersten zurück, wo wir eine Entscheidung hatten(link oder Rechts oder so)
//							   3.???
//							   4.Profit.
	public static String bewegung(Zelle zelle, Koordinaten koordinaten, int playerId, Speicherlogik speicher) {

		String[] kompass = { "go north", "go east", "go south", "go west" };
		Stack<Integer> verlauf = new Stack<>();
		// Das heißt wir haben uns erstmal bewegt, am Anfang wird noch nix gepushed, da
		// der lastActionResult nur OK war
		// Wir brauchen eine Möglichkeit uns zu merken, wo wir waren, an welcher Zelle
		if (zelle.getCurrentCellStatus().equals("FINISH " + playerId + " 0")) {
			return "finish";
		}
		//FIXME: LOGIKFEHLER, sehr groß, ich weiß nicht wo
		if (zelle.getLastActionsResult().equals("OK NORTH") && speicher.getIgnore() != 0) {
			verlauf.push(0); // Norden unsere Letzte Aktion
		} else if (zelle.getLastActionsResult().equals("OK EAST") && speicher.getIgnore() != 1 ) {
			verlauf.push(1); // Osten unsere Letzte Aktion
		} else if (zelle.getLastActionsResult().equals("OK SOUTH") && speicher.getIgnore() != 2) {
			verlauf.push(2); // Süden unsere Letzte Aktion
		} else if (zelle.getLastActionsResult().equals("OK WEST") && speicher.getIgnore() != 3) {
			verlauf.push(3); // Westen unsere Letzte Aktion
		} else if (zelle.getLastActionsResult().equals("OK")) {
			verlauf.push(5);}//5 Pushen, falls wir im ersten Turn sind
		// Hier müssen wir prüfen, was jetzt unsere Umgebung ist, unabhängig vom Anfang,
		// aber wissend, wo wir hergekommen sind
		if (!zelle.getNorthCellStatus().equals("WALL") && verlauf.peek() != 2) { // Norden ist keine Wand und wir
																					// kommen nicht aus dem Süden, im Sinne von wir kommen da grad her
			return "go north";
		} else if (!zelle.getEastCellStatus().equals("WALL") && verlauf.peek() != 3) {
			return "go east";
		} else if (!zelle.getSouthCellStatus().equals("WALL") && verlauf.peek() != 0) {
			return "go south";
		} else if (!zelle.getWestCellStatus().equals("WALL") && verlauf.peek() != 1) {
			return "go west";
		
		} else {
			int temp_ignore = verlauf.pop() + 2 % 4;
			speicher.setIgnore(temp_ignore);
			return kompass[temp_ignore]; // Wir sind in einer Sackgasse und wir müssen zurück
		}
	}
}
