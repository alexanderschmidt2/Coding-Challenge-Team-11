package de.vit.logik;

import de.vit.karte.navigierbar;
import de.vit.karte.felder.*;

/**
 * @author Franz
 *
 *         Diese Klasse, soll die Bewegung mittels system.out.println() in der
 *         main Methode der Klasse ErsterBot/Team11Bot ausfuehren, indem die
 *         Methode Bewegung.bewegung() dort aufgerufen wird, welche einen String
 *         (Aktion) zurueckgibt.
 * 
 */

public abstract class Bewegung {
	
	/**
	 * Die Methode istFinishMoeglich prueft, ob ein "finish" moeglich ist, sprich ob es bei einem Sachbearbeiter Feld/FINISH Feld um unseren Sachbearbeiter (unsere playerId) handelt
	 * und ob alle noetigen Formulare aufgesammelt wurde, sodass man faktisch fertig ist.
	 * @param aktuellesFeld Es muss das aktuelle Feld (currentCell) uebergeben werden, auf welchem sich der Bot befindet
	 * @param aktuelleKarte Es muss die aktuelle Karte uebergeben werden
	 * @return boolean
	 */

	public static boolean istFinishMoeglich(Feld aktuellesFeld, navigierbar aktuelleKarte) {
		if (aktuellesFeld instanceof Sachbearbeiter) {
			Sachbearbeiter aktuellesSachbearbeiterFeld = (Sachbearbeiter) aktuellesFeld;
			if (aktuellesSachbearbeiterFeld.getPlayerId() == aktuelleKarte.getPlayerId()
					&& aktuellesSachbearbeiterFeld.getFormCount() == aktuelleKarte.getFormCount()) {
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}
//	
//	public static boolean istFinishMoeglichNachbarfeld(Feld aktuellesFeld, navigierbar aktuelleKarte) {
//		if (aktuellesFeld instanceof Sachbearbeiter) {
//			Sachbearbeiter aktuellesSachbearbeiterFeld = (Sachbearbeiter) aktuellesFeld;
//			if (aktuellesSachbearbeiterFeld.getPlayerId() == aktuelleKarte.getPlayerId()
//					&& aktuellesSachbearbeiterFeld.getFormCount() == aktuelleKarte.getFormCount()) {
//				return true;
//			} else
//				return false;
//		} else {
//			return false;
//		}
//	}
	
	public void exploration(navigierbar aktuelleKarte) {
		
	}

	/**
	 * Die Methode bewegung() soll die Aktion des Bots je Runde in der main Methode
	 * ausfuehren. Dabei wird die Methode mit allen dynamischen Parametern, sprich
	 * die Parameter, welche sich jede Runde aendern, aufgerufen, welche dann in der
	 * Methode verarbeitet werden (Karte und Feldinformationen aktualisieren).
	 * 
	 * @return Aktion als String fuer system.out.println()
	 */

	public static String bewegung(navigierbar aktuelleKarte) {

		// 1.) Pruefen ob FINISH <playerId> <formCount> auf currentCellStatus equals
		// true, dann return "finish".
		// <playerId> der Methode "Spielinformationen" entnehmen; <formCount> evtl. auch
		// dort
		// Hier kann auch, wenn der Bot auf seinem! SB/Finish steht der <formCount> in
		// "Spielinformationen" abgespeichert werden.
		// Wenn die <playerId>, die eines anderens Bots ist, koennte man den <formCount>
		// abspeichern, mit der Annahme, dass alle Spieler/Bots denselben <formCount>
		// haben.

		if (istFinishMoeglich(spielKarte.getFeld(0,0), spielKarte)) { // FINISH <playerId> <formCount> //Basically 5x pr�fen, ob finish m�glich
			return "finish";

		}
		return "c";

		
		
	}
	
	// dynamic list = arrayList
	// concav F�llung
	// (enum) Variable mit Zust�nden mit Werten f�r unterschiedliche Modi
	
	// Array mit Feldern: SB, WALL, FLOOR
	// ArrayList: Dokumente werden mit NR angef�gt
	
	// Erstmal den Raum erkunden...danach die Prioliste!

	// Vor der Bewegung und vor der Prioritaetsliste fuer Abfragen: Weitere
	// Befuellung der Karte mit Zellinformationen den Koordinaten zugeordnet.
	// Auch bei erneutem Besuchen der Zellen, sollen die Zellinformationen i.V.m.
	// den Koordinaten aktualisiert werden, falls Aenderungen in der Map entstehen.

	// Vor der Bewegung und vor der Prioritaetsliste fuer Abfragen: Entfernung fuer
	// Entfernungskarte hinterlegen, angefangen mit 0 fuer currentCell

	// Vor der Bewegung: Prioritaetsliste fuer Abfragen, bevor die Bewegungslogik
	// greift um eine Bewegung zu machen!

	// Es ist ein NOT OK

	// 2.) Pruefen ob FINISH <playerId> <anzahlNotwendigerFormulare> auf
	// north/west/south/eastCellStatus (hier logisches ODER) equals true, dann gehe
	// dahin, damit 1.) greift.
	// Hier kann auch, wenn der Bot seinen! SB/Finish sieht der <formCount> in
	// "Spielinformationen" abgespeichert werden.
	// Wie bei 1.) Wenn die <playerId>, die eines anderens Bots ist, koennte man den
	// <formCount> abspeichern, mit der Annahme, dass alle Spieler/Bots denselben
	// <formCount> haben.

	// 3.) Pruefen ob

}
