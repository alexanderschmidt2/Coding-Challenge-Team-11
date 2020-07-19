package de.vit.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.vit.karte.Inavigierbar;
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
			"kick east", "kick south", "kick west", "put", "take", "finish","position" };
	private static String getaetigteAktion;

	public static int papierHandlung(Inavigierbar aktuelleKarte) {// TODO: das beschissene Papier auch kartentechnisch
		if (aktuelleKarte.getLevel() == 5) { // kicken können und nie das selbe Papier 2x kicken
			if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Papier) {
				Papier papier = (Papier) aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition());
				int h = -1;
				for (int i = 0; i < 3; i++) {
					if (aktuelleKarte.getNachbarn(aktuelleKarte.getAktuellePosition())[i] instanceof Boden
							&& !papier.isGekickt()) {
						h = i;
					}
				}
				if (h > -1) {
					papier.setGekickt(true);
					return (h + 4);
				} else {
					if (!papier.isGekickt()) {
						return 9;
					}
				}

			}

		}
		return -1;

	}

	public static int finishHandlung(Inavigierbar aktuelleKarte) {
		if (aktuelleKarte.getLevel() == 1) {
			if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Sachbearbeiter && aktuelleKarte
					.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(), aktuelleKarte)) {
				return 10;
			} else if (aktuelleKarte.getStatischeZiele().gibKoordinatenSB(aktuelleKarte) != null) {
				return (schrittZumZiel(aktuelleKarte.getStatischeZiele().gibKoordinatenSB(aktuelleKarte), aktuelleKarte)
						+ 2) % 4; // Die gemappten Koordinaten des Sachbearbeiters
			}
		} else {
			if (aktuelleKarte.getFormCount() == (aktuelleKarte.getStatischeZiele().getDokumentenZaehler() - 1)
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

	public static int schrittZumZiel(int[] aktuelleKoordinaten, Inavigierbar aktuelleKarte) {
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
		return 100;
	}

	public static int formularHandlung(Inavigierbar aktuelleKarte, Rundeninformationen rundeninfo) {

		if (rundeninfo.getLastDoneAction().equals("put")) {
			if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Papier) {
				Papier papier = (Papier) aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition());
				papier.setGekickt(true);
			}
		}
		if (aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition()) instanceof Dokument) {
			Dokument dokument = (Dokument) aktuelleKarte.getFeld(aktuelleKarte.getAktuellePosition());
			if (aktuelleKarte.getStatischeZiele().isKoordinatenVorhanden(aktuelleKarte.getAktuellePosition(),
					aktuelleKarte)) {
				if (dokument.getNr() == aktuelleKarte.getStatischeZiele().getDokumentenZaehler()) {
					aktuelleKarte.getStatischeZiele().addDokumentenZaehler();
					aktuelleKarte.getStatischeZiele().remove(dokument.getName());
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
			System.err.println("Ich verneble");
			aktuelleKarte.vernebleKarte(); // TODO: prüfen ob wir ggf. doch die ganze Karte aktualisieren können, müssen

			return -1;
		} else {
			// wenn die Dokumentnr == dokument was wir nicht in der Liste haben nummer, dann
			// explorieren
			int ges_dokuments_nr = aktuelleKarte.getStatischeZiele().getDokumentenZaehler();
			if (aktuelleKarte.getStatischeZiele().gibKoordinatenDokument(ges_dokuments_nr, aktuelleKarte) != null) {
				System.err.println("Ich navigiere zum gesuchten Dokument");
				return (schrittZumZiel(
						aktuelleKarte.getStatischeZiele().gibKoordinatenDokument(ges_dokuments_nr, aktuelleKarte),
						aktuelleKarte) + 2) % 4;
			}
		}
		return -1;
	}

	public static int explorationsHandlung(Inavigierbar aktuelleKarte) {
		return (schrittZumZiel(aktuelleKarte.getDynamischesZiel(), aktuelleKarte) + 2) % 4;
	}

	public static int fehlgeschlageneHandlung(Inavigierbar aktuelleKarte, Rundeninformationen rundeninformationen) {
		if (rundeninformationen.getLastActionsResult().equals("NOK TALKING")) {
			
			return Arrays.asList(befehl_für_ausgabe).indexOf(rundeninformationen.getLastDoneAction());
		}
		return -1;
	}

	public static String bestimmeBewegung(Inavigierbar aktuelleKarte, Rundeninformationen rundeninformationen) {
		List<Integer> prioritäts_liste = new ArrayList<Integer>();
		prioritäts_liste.add(fehlgeschlageneHandlung(aktuelleKarte, rundeninformationen));
		prioritäts_liste.add(finishHandlung(aktuelleKarte));
		prioritäts_liste.add(formularHandlung(aktuelleKarte, rundeninformationen));
		prioritäts_liste.add(papierHandlung(aktuelleKarte));
		prioritäts_liste.add(explorationsHandlung(aktuelleKarte));
		for (int moegliche_ausgabe : prioritäts_liste) {
			if (moegliche_ausgabe != -1) {

				return befehl_für_ausgabe[moegliche_ausgabe];
			}
		}
		aktuelleKarte.vernebleKarte();
		System.err.println("DIE GESAMTE KARTE WURDE VERNEBELT");
		return befehl_für_ausgabe[explorationsHandlung(aktuelleKarte)];

	}

	public static String bewegung(Inavigierbar aktuelleKarte, Rundeninformationen rundeninformationen) {
		getaetigteAktion = bestimmeBewegung(aktuelleKarte, rundeninformationen);
		rundeninformationen.setLastDoneAction(getaetigteAktion);
		return getaetigteAktion;
	}
}