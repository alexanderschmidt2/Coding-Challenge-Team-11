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

	public static boolean alleFormulareAufgesammelt(Karte aktuelleKarte) {// Wir können erst dann zum Ziel laufen, wenn
																			// wir unseren SB haben
		int zaehler_aufgehobene_formulare = 0;
		for (int[] e : aktuelleKarte.getStatischeZiele()) {// Save nur unsere Dokumente drin
			if (aktuelleKarte.getFeld(e) instanceof Dokument) {
				Dokument aktuellesFormular = (Dokument) aktuelleKarte.getFeld(e);
				if (aktuellesFormular.isAufgenommen() == true) {
					zaehler_aufgehobene_formulare++;
				}
			}
		}
		if (zaehler_aufgehobene_formulare == aktuelleKarte.getFormCount()) {
			return true;
		}
		return false;
	}

	public static int papierAufsammeln(Karte aktuelleKarte) {
		if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Papier) {
			return 4;
		}
		for (int i = 0; i <= 3; i++) {
			if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Papier) {
				return i;
			}
		}
		return -1;

	}

	public static int istFinishMoeglich(Karte aktuelleKarte) {// Die karte tut in die Menge, obs unser Sachbearbeiter
																// ist!)
		if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Sachbearbeiter
				&& alleFormulareAufgesammelt(aktuelleKarte) == true) {
			return 4;
		} else {

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

	public static int zumZielLaufen(Karte aktuelleKarte) {// TODO:
		// Methode sinnnnnvoll umbennen
		// Methode, die schaut, welche Formulare noch rein müssen und daraus uns die
		// Ziele generiert bzw. sagt, dass exploration gestartet werden muss.
		// Methode, die prüft, ob finish irgendwie irgendwo möglich ist und ggf. dorthin
		// läuft bzw. finish callt.
		// Methode, die so ein blödes Blatt einsammelt.
		return exploration(aktuelleKarte);
	}

	public static int exploration(Karte aktuelleKarte) {
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}

	public static String bewegung(Karte aktuelleKarte, Rundeninformationen rundeninformationen) {

		String[] befehl_für_ausgabe = { "go north", "go east", "go south", "go west", "take", "finish" };
		String letzteGetaetigteAktion = "test";
		if (aktuelleKarte.getSpielphase() == 0) {
			if (aktuelleKarte.getStatischeZiele().isEmpty()) {
				letzteGetaetigteAktion = befehl_für_ausgabe[exploration(aktuelleKarte)];
			} else {
				aktuelleKarte.setSpielphase(1);
			}
		} else if (aktuelleKarte.getSpielphase() == 1) {//Wir suchen den Sachbearbeiter! 
			
		} else if (aktuelleKarte.getSpielphase() == 2) {
		} else if (aktuelleKarte.getSpielphase() == 3) {
		} else if (aktuelleKarte.getSpielphase() == 4) {
		} else {
		}
		;
		rundeninformationen.setLastDoneAction(letzteGetaetigteAktion);
		return letzteGetaetigteAktion;
	}
}