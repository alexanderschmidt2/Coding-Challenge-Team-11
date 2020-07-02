package de.vit.logik;

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
	public static String bewegung(Position position, Koordinaten koordinaten, int playerId) {
		String[] Regel = { "go south", "go west", "go north", "go east", "finish" }; // 0123}
		String[] Umgebung = {position.getSouthCellStatus(), position.getWestCellStatus(), position.getNorthCellStatus(), position.getEastCellStatus()};
		String[] Status = {"WALL", "FLOOR"};
		if (position.getCurrentCellStatus().equals("FINISH " + playerId + " 0")) {
			return Regel[4];
		} // Usecase für wir sind auf den SB gelaufen sei es durch SPAWN oder durch
			// zufälliges rumlaufen
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		mainloop: while(true) {
			if(Umgebung[0] == Status[a] &&  Umgebung[1] == Status[b] && Umgebung[2] == Status[c] && Umgebung[3] == Status[d]) {
				break mainloop;
			}
			
		}
		return("x");
		
		
		
		
		
		
		

	};
}
