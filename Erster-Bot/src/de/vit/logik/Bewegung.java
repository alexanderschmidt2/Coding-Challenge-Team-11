package de.vit.logik;

import java.util.ArrayList;

import de.vit.karte.Karte;
import de.vit.karte.felder.*;

/**
 * @author Franz
 *
 *         Diese Klasse, soll die Bewegung mittels system.out.println() in der
 *         main Methode der Klasse ErsterBot/Team11Bot ausfuehren, indem die
 *         Methode Bewegung.bewegung() dort aufgerufen wird, welche einen String
 *         (Aktion) zurueckgibt.
 *         
 *         Die Klasse ist <abstract>, da sie nicht instanziierbar ist und nur die 
 *         Bewegungsmethode dieser Klasse für die main-Methode Team11Bot notwendig ist.
 * 
 */

public abstract class Bewegung {

	/**
	 * Die Methode istFinishMoeglich prueft, ob ein "finish" moeglich ist, sprich ob
	 * es bei einem Sachbearbeiter Feld/FINISH Feld um unseren Sachbearbeiter
	 * (unsere playerId) handelt und ob alle noetigen Formulare aufgesammelt wurde,
	 * sodass man faktisch fertig ist.
	 * 
	 * @param aktuellesFeld Es muss das aktuelle Feld (currentCell) uebergeben
	 *                      werden, auf welchem sich der Bot befindet
	 * @param aktuelleKarte Es muss die aktuelle Karte uebergeben werden
	 * @return boolean
	 */
	
	// Hier werden Prioritaeten gemaess des Programmablaufplans (PAP) abgeprueft:
	
	// 1.) Prioritaet: finish auf currenctCell
	public static boolean istFinishMoeglich(Feld aktuellesFeld, Karte aktuelleKarte) {
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

//	// 2.) Prioritaet: finish auf nachbarCell, damit "go nachbar" und dann 1.) greift
	public static int istFinishMoeglichNachbarfeld(Feld aktuellesFeld, Karte aktuelleKarte) {
		for (int i = 0; i < 4; i++) {	
			if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Sachbearbeiter) {
				Sachbearbeiter nachbarSachbearbeiterFeld = (Sachbearbeiter) aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i];
				if (nachbarSachbearbeiterFeld.getPlayerId() == aktuelleKarte.getPlayerId()
						&& nachbarSachbearbeiterFeld.getFormCount() == aktuelleKarte.getFormCount()) {
					return i;
				} else { return 404;}
			} 
		} return 404;
	} 
	

	public static int schrittZumZiel(int[] aktuelleKoordinaten, Karte aktuelleKarte) {
		for (int i = 0; i <= 3; i++) {
			if (aktuelleKarte.getNachbarn(aktuelleKoordinaten)[i].getEntfernung() == 0) {
				return i;
			}
		}
		if (aktuelleKarte.getNachbarn(aktuelleKoordinaten)[0]
				.getEntfernung() == aktuelleKarte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(aktuelleKarte.getNorden(aktuelleKoordinaten), aktuelleKarte);
			
		} else if (aktuelleKarte.getNachbarn(aktuelleKoordinaten)[1]
				.getEntfernung() == aktuelleKarte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(aktuelleKarte.getOsten(aktuelleKoordinaten), aktuelleKarte);
			
		} else if (aktuelleKarte.getNachbarn(aktuelleKoordinaten)[2]
				.getEntfernung() == aktuelleKarte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(aktuelleKarte.getSueden(aktuelleKoordinaten), aktuelleKarte);
			
		} else if (aktuelleKarte.getNachbarn(aktuelleKoordinaten)[3]
				.getEntfernung() == aktuelleKarte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(aktuelleKarte.getWesten(aktuelleKoordinaten), aktuelleKarte);
		}
		return 10; //TODO: Wenn 5 kommt funktioniert die Rekursion nicht
	}

	public static int zumZielLaufen(ArrayList<Integer[]> ziele, Karte aktuelleKarte) {// TODO: Methode sinnnnnvoll umbennen
		
		// Ziele im Array mit der gerinsten Zahl(index) haben die höchste Priorität
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) +2)%4;
	}

	public void exploration(Karte aktuelleKarte) {

	}

	/**
	 * Die Methode bewegung() soll die Aktion des Bots je Runde in der main Methode
	 * ausfuehren. Dabei wird die Methode mit allen dynamischen Parametern, sprich
	 * die Parameter, welche sich jede Runde aendern, aufgerufen, welche dann in der
	 * Methode verarbeitet werden (Karte und Feldinformationen aktualisieren).
	 * 
	 * @return Aktion als String fuer system.out.println()
	 */

	public static String bewegung(Karte aktuelleKarte, Rundeninformationen rundeninformationen) {

		// 1.) Pruefen ob FINISH <playerId> <formCount> auf currentCellStatus equals
		// true, dann return "finish".
		// <playerId> der Methode "Spielinformationen" entnehmen; <formCount> evtl. auch
		// dort
		// Hier kann auch, wenn der Bot auf seinem! SB/Finish steht der <formCount> in
		// "Spielinformationen" abgespeichert werden.
		// Wenn die <playerId>, die eines anderens Bots ist, koennte man den <formCount>
		// abspeichern, mit der Annahme, dass alle Spieler/Bots denselben <formCount>
		// haben.
		
		

		String[] kompass = { "go north", "go east", "go south", "go west" };
		ArrayList<Integer[]> ziele = new ArrayList<Integer[]>();
		
		// 1. Prioritaet: finish auf currentCell
		if (istFinishMoeglich(aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()), aktuelleKarte)) {
			return "finish";
		}
		
		// 2. Prioritaet: finish auf nachbarCell
		for (int i = 0; i < 4; i++) {
			if (istFinishMoeglichNachbarfeld(aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()), aktuelleKarte) == i) {
				return kompass[i];
			}
		}
		
		String letzteGetaetigteAktion = kompass[zumZielLaufen(ziele, aktuelleKarte)];
		rundeninformationen.setLastDoneAction(letzteGetaetigteAktion);
		//aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()).setEntfernung(1);
		return letzteGetaetigteAktion;
	}

	// dynamic list = arrayList
	// concav Füllung
	// (enum) Variable mit Zuständen mit Werten für unterschiedliche Modi

	// Array mit Feldern: SB, WALL, FLOOR
	// ArrayList: Dokumente werden mit NR angefügt

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

}
