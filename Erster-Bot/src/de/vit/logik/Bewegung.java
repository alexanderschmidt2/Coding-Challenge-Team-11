package de.vit.logik;

import java.util.ArrayList;
import java.util.HashSet;

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
 *         Die Klasse ist <abstract>, da sie nicht instanziierbar ist und nur
 *         die Bewegungsmethode dieser Klasse für die main-Methode Team11Bot
 *         notwendig ist.
 * 
 */

public abstract class Bewegung {// TODO: SEHR GROß, schauen, dass wir nur die Parameter verwenden, die wir auch
								// tatsächlich brauchen!

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
	
	
	public static int istFinishMoeglich(Karte aktuelleKarte) {
		if(aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Sachbearbeiter) {
			return 5;
		}else {
			return 500000000;
		}
			
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
		return 10; // TODO: Wenn 5 kommt funktioniert die Rekursion nicht
	}

	public static int zumZielLaufen(int[] zielkoordinaten, Karte aktuelleKarte) {// TODO: Methode sinnnnnvoll umbennen

		// Ziele im Array mit der gerinsten Zahl(index) haben die höchste Priorität
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}

	public static int exploration(Karte aktuelleKarte) {
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}

	public static String bewegung(Karte aktuelleKarte, Rundeninformationen rundeninformationen) {


		String[] befehl_für_ausgabe = { "go north", "go east", "go south", "go west", "finish" };

	

		String letzteGetaetigteAktion;
		try {
			letzteGetaetigteAktion = befehl_für_ausgabe[istFinishMoeglich(aktuelleKarte)];
		} catch (Exception e) {//TODO: was wenn kicken nicht möglich ist, weil dort auf einmal ein Bot hingekommen ist? Gibt es den Usecase? NOKBLOKED kann passieren
		} finally {
			letzteGetaetigteAktion = befehl_für_ausgabe[exploration(aktuelleKarte)];
		}

		rundeninformationen.setLastDoneAction(letzteGetaetigteAktion);
		// aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()).setEntfernung(1);
		return letzteGetaetigteAktion;
	}
}
