package de.vit.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
			"kick east", "kick south", "kick west", "put", "take", "finish" };
	private static String letzteGetaetigteAktion;

	public static int papierHandlung(Karte aktuelleKarte) {// TODO: das beschissene Papier auch kartentechnisch
		if (aktuelleKarte.getLevel() == 5) { // kicken können und nie das selbe Papier 2x kicken
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
//			for (int i = 0; i <= 3; i++) {
//				if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Papier) {
//					Papier papier = (Papier) aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i];
//					if (!papier.isGekickt()) {
//						return i;
//					}
//				}
//			}
		}
		return -1;
	}

	public static int finishHandlung(Karte aktuelleKarte) {
		if (aktuelleKarte.getLevel() == 1) {
			if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Sachbearbeiter && aktuelleKarte
					.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(), aktuelleKarte)) {// Einne
																														// koordinate,
																														// die
																														// übergeben
																														// wird
																														// mit
																														// einer
																														// Koordinate
																														// aus
																														// den
																														// Zielen
																														// übereinstimt?
				return 10;
			} else {
				return (schrittZumZiel(aktuelleKarte.getStatischeZiele().gibKoordinatenSB(aktuelleKarte), aktuelleKarte)
						+ 2) % 4; // Die gemappten Koordinaten des Sachbearbeiters
			}
		} else {
			if (aktuelleKarte.getFormCount() == aktuelleKarte.getStatischeZiele().aktuellesDokument(aktuelleKarte)
					&& aktuelleKarte.getStatischeZiele().gibKoordinatenSB(aktuelleKarte) != null) {
				if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Sachbearbeiter
						&& aktuelleKarte.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(),
								aktuelleKarte)) {
					return 10;
				} else {
					return (schrittZumZiel(aktuelleKarte.getStatischeZiele().gibKoordinatenSB(aktuelleKarte),
							aktuelleKarte) + 2) % 4;
				}
			}
		}
		return -1;
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

	public static int formularHandlung(Karte aktuelleKarte) {// Ein Formular
																// gönnen
		if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Dokument) {
			Dokument dokument = (Dokument) aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition());
			if (aktuelleKarte.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(),
					aktuelleKarte)) {
				if (dokument.getNr() == aktuelleKarte.getStatischeZiele().aktuellesDokument(aktuelleKarte)) {
					dokument.setAufgenommen(true);
					return 9;
				}
			} else {
				if (aktuelleKarte.getSheetCount() > 0) {
					aktuelleKarte.reduziereSheetCount();
					return 8;
				}
			}
		} else if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Boden && aktuelleKarte
				.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(), aktuelleKarte)) {
			aktuelleKarte.vernebleKarte(); // TODO: prüfen ob wir ggf. doch die ganze Karte aktualisieren können, müssen
											// wir :(
			return explorationsHandlung(aktuelleKarte);
		} else {
			// wenn die Dokumentnr == dokument was wir nicht in der Liste haben nummer, dann
			// explorieren
			// wenn Dokumentnr == was wir in der Liste haben und nicht-aufgenommne, dann
			// gehe zu diesem Dokument
			int ges_dokuments_nr = aktuelleKarte.getStatischeZiele().aktuellesDokument(aktuelleKarte);
			if (aktuelleKarte.getStatischeZiele().gibKoordinatenDokument(ges_dokuments_nr, aktuelleKarte) != null) {
				return (schrittZumZiel(
						aktuelleKarte.getStatischeZiele().gibKoordinatenDokument(ges_dokuments_nr, aktuelleKarte),
						aktuelleKarte) + 2) % 4;
			}
		}
		return -1;
	}

	public static int explorationsHandlung(Karte aktuelleKarte) {
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}


	public static String bewegung(Karte aktuelleKarte, Rundeninformationen rundeninformationen) {
		List<Integer> prioritäts_liste = new ArrayList<Integer>();
		prioritäts_liste.add(finishHandlung(aktuelleKarte));
		prioritäts_liste.add(formularHandlung(aktuelleKarte));
		prioritäts_liste.add(papierHandlung(aktuelleKarte));
		prioritäts_liste.add(explorationsHandlung(aktuelleKarte));

		for (int moegliche_ausgabe : prioritäts_liste) {
			if (moegliche_ausgabe != -1) {
				letzteGetaetigteAktion = befehl_für_ausgabe[moegliche_ausgabe];
				rundeninformationen.setLastDoneAction(letzteGetaetigteAktion);
				
			}
		}return letzteGetaetigteAktion;
	}
}