package de.vit.logik;

import de.vit.typen.Position;

public class Bewegungslogik {
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
	public static String bewegung(Position position) {//TODO: Klüger lösen, Direction als Param anhängen ggf.?
		switch (position.getLastActionsResult()) {
			case ("OK"): //das allererste Feld, hier gibt es eine Initialisierung oder es ist unser Sachbearbeiter?
			case ("NOK BLOKED"): 
			case ("NOK NOTYOURS"):
			case ("NOK EMPTY")://irgendwas hat nicht funktioniert
			default: //der GO-DirectionCase, TODO: Schlauer lösen
		}

		return ("a");
	};
}
