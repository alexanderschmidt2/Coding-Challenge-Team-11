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
	private static final String[] befehl_für_ausgabe = { "go north", "go east", "go south", "go west", "kick north",
			"kick east", "kick south", "kick west", "take", "put", "finish" };

	public static boolean alleFormulareAufgesammelt(Karte aktuelleKarte) {// Wir können erst dann zum Ziel laufen, wenn
																			// wir unseren SB haben
		return true;

	}

	public static int dokumentZaehler(Karte aktuelleKarte) {// returned die Nummer des Dokuments, welches wir als nächstes Aufnehmen sollen
		int dokumentCounter = 0;
		if (aktuelleKarte.getLevel() == 1) {
			return dokumentCounter; //Er bricht sofort ab, wenns Level 1 ist, dann ist nämlich kein DOkument mehr da
		} else {
			dokumentCounter = 1;
		}
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {
			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {
				if (aktuelleKarte.getFeld(e) instanceof Dokument) {
					Dokument dokument = (Dokument) aktuelleKarte.getFeld(e);
					if (dokument.getNr() == 1 && dokument.isAufgenommen()) {
						dokumentCounter++;
					} else if (dokument.getNr() == 2 && dokument.isAufgenommen()) {
						dokumentCounter++;
					} else if (dokument.getNr() == 3 && dokument.isAufgenommen()) {
						dokumentCounter++;
					} else if (dokument.getNr() == 4 && dokument.isAufgenommen()) {
						dokumentCounter++;
					} else if (dokument.getNr() == 5 && dokument.isAufgenommen()) {
						dokumentCounter++;
					}

				}
			}
		}
		return dokumentCounter;
	}

	public static boolean aufnahmeMoeglich(Karte aktuelleKarte, Dokument dokument) {
		if (aktuelleKarte.getStatischeZiele().containsKey(dokument.getName())) {
			if (dokumentZaehler(aktuelleKarte) == dokument.getNr()) {
				return true;
			} return false;

		} return false;
	}

	public static int papierHandlung(Karte aktuelleKarte) {// TODO: das beschissene Papier auch kartentechnisch
															// kicken können und nie das selbe Papier 2x kicken
		if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Papier) {
			Papier papier = (Papier) aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition());
			for (int i = 0; i < 3; i++) {
				if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Boden
						&& !papier.isGekickt()) {
					papier.setGekickt(true);
					return (i + 4);
				}
			}
		}
		for (int i = 0; i <= 3; i++) {
			if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Papier) {
				Papier papier = (Papier) aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i];
				if (!papier.isGekickt()) {
					return i;
				}

			} else if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Dokument) {
				Dokument dokument = (Dokument) aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i];
				if (!(aktuelleKarte.getStatischeZiele().containsKey(dokument.getName())) && aktuelleKarte.getShee) {
					return i;
				}
				;
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

			return -1;
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

	public static int exploration(Karte aktuelleKarte) {
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}

	public static String bewegung(Karte aktuelleKarte, Rundeninformationen rundeninformationen) {

		String letzteGetaetigteAktion = "test";
		if (aktuelleKarte.getSpielphase() == 0) {
			if (aktuelleKarte.getStatischeZiele().isEmpty()) {

				letzteGetaetigteAktion = befehl_für_ausgabe[exploration(aktuelleKarte)];
			} else {
				aktuelleKarte.setSpielphase(1);
			}
		} else if (aktuelleKarte.getSpielphase() == 1) {// Wir suchen den Sachbearbeiter!

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