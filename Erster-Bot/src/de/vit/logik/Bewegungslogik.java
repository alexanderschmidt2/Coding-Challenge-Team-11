package de.vit.logik;

import de.vit.typen.Information;
import de.vit.typen.Koordinaten;
import de.vit.typen.Position;

public abstract class Bewegungslogik {

//Also da mir nichts Kluges einfällt werden wir es Brute-Force machen, damit wir eine Basis haben
//Die Idee ist Folgende: Wir laufen <bold> immer </bold> die selbe Reihenfolge an Bewegungen ab, 
//die wir nach Lust und Laune festlegen. Unten, Rechts , Oben, Links.  
//ggf. müssen wir mit Vererbungen arbeiten bei den Actions
//Kriteriensuche-->gem. Kriterien, Sackgasse,
//Wege kennen-->Karte führen, da gab es Formular Deijkztra?, A*
//Reden wir über einige Fälle: 1. Wir spawnen auf dem SB->am Anfang den Check
//							   2. Wir laufen in eine Sackgasse->Wir merken uns alle bisherigen Knoten
//								und laufen dann zu dem Ersten zurück, wo wir eine Entscheidung hatten(link oder Rechts oder so)
//							   3.???
//							   4.Profit.
	public static String bewegung(Position position, Koordinaten koordinaten, Information informationen) {// TODO:
																											// Klüger
																											// lösen,
																											// Direction
																											// als
																											// Param
																											// anhängen
																											// ggf.?
																											// checke,
																											// ob es
																											// nicht
																											// wall
																											// ist! Als
																											// Funktion
		String[] Regel = { "go south", "go west", "go north", "go east", "finish" }; // 0123
		if (position.getCurrentCellStatus().equals("FINISH " + informationen.getPlayerId() + " 0")) {
			return Regel[4];
		} // Usecase für wir sind auf den SB gelaufen sei es durch SPAWN oder durch
			// zufälliges rumlaufen
		else if ((!position.getSouthCellStatus().equals("WALL"))) {
			return Regel[0];
		} else if ((!position.getWestCellStatus().equals("WALL"))) {
			return Regel[1];
		} else if ((!position.getNorthCellStatus().equals("WALL"))) {
			return Regel[2];
		} else if ((!position.getEastCellStatus().equals("WALL"))) {
			return Regel[3];
		} else
			return "position";

	};
}
