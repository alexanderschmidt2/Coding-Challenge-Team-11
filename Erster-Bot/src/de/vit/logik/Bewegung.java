package de.vit.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import de.vit.karte.Inavigierbar;
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

	public static int papierHandlung(Inavigierbar karte) {// TODO: das beschissene Papier auch kartentechnisch
		if (karte.getLevel() == 5) { // kicken können und nie das selbe Papier 2x kicken
			if (karte.getFeld(karte.getAktuellePosition()) instanceof Papier) {
				Papier papier = (Papier) karte.getFeld(karte.getAktuellePosition());
				int h = -1;
				for (int i = 0; i < 3; i++) {
					if (karte.getNachbarn(karte.getAktuellePosition())[i] instanceof Boden
							&& !papier.isGekickt()) {
						h = i;
					}
				}
				if (h > -1) {
					//papier.setGekickt(true);
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

	public static int finishHandlung(Inavigierbar karte) {
		if (karte.getLevel() == 1) {
			if (karte.getFeld(karte.getAktuellePosition()) instanceof Sachbearbeiter && karte
					.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(), karte)) {
				return 10;
			} else if (karte.getStatischeZiele().getKoordinatenSb(karte) != null) {
				return (schrittZumZiel(karte.getStatischeZiele().getKoordinatenSb(karte), karte)
						+ 2) % 4; // Die gemappten Koordinaten des Sachbearbeiters
			}
		} else {
			if (karte.getFormCount() == (karte.getStatischeZiele().getAufgesammelteFormulare() - 1)
					&& karte.getStatischeZiele().getKoordinatenSb(karte) != null) {
				if (karte.getFeld(karte.getAktuellePosition()) instanceof Sachbearbeiter
						&& karte.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(),
								karte)) {
					return 10;
				} else {
					return (schrittZumZiel(karte.getStatischeZiele().getKoordinatenSb(karte),
							karte) + 2) % 4;
				}
			}
		}
		return -1;
	}

	public static int schrittZumZiel(int[] aktuelleKoordinaten, Inavigierbar karte) {
		for (int i = 0; i <= 3; i++) {
			if (karte.getNachbarn(aktuelleKoordinaten)[i].getEntfernung() == 0) {
				return i;
			}
		}
		if (karte.getNachbarn(aktuelleKoordinaten)[0]
				.getEntfernung() == karte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getNorden(aktuelleKoordinaten), karte);

		} else if (karte.getNachbarn(aktuelleKoordinaten)[1]
				.getEntfernung() == karte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getOsten(aktuelleKoordinaten), karte);

		} else if (karte.getNachbarn(aktuelleKoordinaten)[2]
				.getEntfernung() == karte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getSueden(aktuelleKoordinaten), karte);

		} else if (karte.getNachbarn(aktuelleKoordinaten)[3]
				.getEntfernung() == karte.getFeld(aktuelleKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getWesten(aktuelleKoordinaten), karte);
		}
		return 100;
	}

	public static int formularHandlung(Inavigierbar karte, Rundeninformationen rundeninfo) {
		
		/*
		if (rundeninfo.getLastDoneAction().equals("put")) {
			
			
			if (karte.getFeld(karte.getAktuellePosition()) instanceof Papier) {
				Papier papier = (Papier) karte.getFeld(karte.getAktuellePosition());
				papier.setGekickt(true);
			}
		}
		*/
		if (karte.getFeld(karte.getAktuellePosition()) instanceof Dokument) {
			Dokument dokument = (Dokument) karte.getFeld(karte.getAktuellePosition());
			if (karte.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(),
					karte)) {
				if (dokument.getNr() == karte.getStatischeZiele().getAufgesammelteFormulare()) {
					//karte.getStatischeZiele().addAufgesammelteFormulare();
					//karte.getStatischeZiele().remove(dokument.getName());
					System.err.println("ich sammle das "+karte.getFormCount()+" Dokument auf");
					return 9;
				}
			} else {
				if (karte.getSheetCount() > 0) {
					//karte.reduziereSheetCount();
					System.err.println("ich putte bei SheetCount: " + karte.getSheetCount());
					return 8;
				}
			}
		} else if (karte.getFeld(karte.getAktuellePosition()) instanceof Boden && karte
				.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(), karte)) {
			//System.err.println("Ich verneble");
			karte.vernebleKarte(); // TODO: prüfen ob wir ggf. doch die ganze Karte aktualisieren können, müssen

			return -1;
		} else {
			// wenn die Dokumentnr == dokument was wir nicht in der Liste haben nummer, dann
			// explorieren
			int ges_dokuments_nr = karte.getStatischeZiele().getAufgesammelteFormulare();
			if (karte.getStatischeZiele().getKoordinatenFormular(ges_dokuments_nr, karte) != null) {
				System.err.println("Ich navigiere zum gesuchten Dokument");
				return (schrittZumZiel(
						karte.getStatischeZiele().getKoordinatenFormular(ges_dokuments_nr, karte),
						karte) + 2) % 4;
			}
		}
		return -1;
	}

	public static int explorationsHandlung(Inavigierbar karte) {
		return (schrittZumZiel(karte.getDynamischesZiel(), karte) + 2) % 4;
	}

	public static int fehlgeschlageneHandlung(Inavigierbar karte, Rundeninformationen rundeninformationen) {
		if (rundeninformationen.getLastActionsResult().equals("NOK TALKING")) {
			
			return Arrays.asList(befehl_für_ausgabe).indexOf(rundeninformationen.getLastDoneAction());
		}
		return -1;
	}

	public static String bestimmeBewegung(Inavigierbar karte, Rundeninformationen rundeninformationen) {
		List<Integer> prioritäts_liste = new ArrayList<Integer>();
		prioritäts_liste.add(fehlgeschlageneHandlung(karte, rundeninformationen));
		prioritäts_liste.add(finishHandlung(karte));
		prioritäts_liste.add(formularHandlung(karte, rundeninformationen));
		prioritäts_liste.add(papierHandlung(karte));
		prioritäts_liste.add(explorationsHandlung(karte));
		for (int moegliche_ausgabe : prioritäts_liste) {
			if (moegliche_ausgabe != -1) {

				return befehl_für_ausgabe[moegliche_ausgabe];
			}
		}
		karte.vernebleKarte();
		System.err.println("DIE GESAMTE KARTE WURDE VERNEBELT");
		return befehl_für_ausgabe[explorationsHandlung(karte)];

	}

	public static String bewegung(Inavigierbar karte, Rundeninformationen rundeninformationen) {
		getaetigteAktion = bestimmeBewegung(karte, rundeninformationen);
		rundeninformationen.setLastDoneAction(getaetigteAktion);
		return getaetigteAktion;
	}
}