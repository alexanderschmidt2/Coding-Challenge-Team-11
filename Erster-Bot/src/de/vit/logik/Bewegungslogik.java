package de.vit.logik;

import de.vit.typen.Position;

public class Bewegungslogik {
//Also da mir nichts Kluges einf�llt werden wir es Brute-Force machen, damit wir eine Basis haben
//Die Idee ist Folgende: Wir laufen <bold> immer </bold> die selbe Reihenfolge an Bewegungen ab, 
//die wir nach Lust und Laune festlegen. Unten, Rechts , Oben, Links.  
//ggf. m�ssen wir mit Vererbungen arbeiten bei den Actions
//Kriteriensuche-->gem. Kriterien, Sackgasse,
//Wege kennen-->Karte f�hren, da gab es Formular Deijkztra?, A*
//Reden wir �ber einige F�lle: 1. Wir spawnen auf dem SB->am Anfang den Check
//							   2. Wir laufen in eine Sackgasse->Wir merken uns alle bisherigen Knoten
//								und laufen dann zu dem Ersten zur�ck, wo wir eine Entscheidung hatten(link oder Rechts oder so)
//							   3.???
//							   4.Profit.
	public static String bewegung(Position position) {//TODO: Kl�ger l�sen, Direction als Param anh�ngen ggf.?
		switch (position.getLastActionsResult()) {
			case ("OK"): //das allererste Feld, hier gibt es eine Initialisierung oder es ist unser Sachbearbeiter?
			case ("NOK BLOKED"): 
			case ("NOK NOTYOURS"):
			case ("NOK EMPTY")://irgendwas hat nicht funktioniert
			default: //der GO-DirectionCase, TODO: Schlauer l�sen
		}

		return ("a");
	};
}
