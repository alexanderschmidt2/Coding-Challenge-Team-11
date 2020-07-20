package de.vit.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.vit.karte.Inavigierbar;
import de.vit.karte.felder.*;

/**
 * Die Klasse Bewegung, soll die Bewegung mittels system.out.println() in der main Methode der Klasse Team11Bot ausfuehren, indem die
 * Methode Bewegung.bewegung() dort aufgerufen wird, welche einen String (Aktion) zurueckgibt.
 * 
 * Die Klasse ist abstrakt, da sie nicht instanziierbar ist und nur die Bewegungsmethode dieser Klasse für die main-Methode des Team11Bots notwendig ist.
 * 
 * @author Franz Bogmann und Alexander Schmidt
 *
 */
public abstract class Bewegung {
	
	// Alle moeglichen Aktionen des Bots, werden in einer Liste von Strings gespeichert und anhand ihrer Indizes aufgerufen:
	private static final String[] aktionen = { 	"go north", 	//0:  Bot geht nach Norden
												"go east", 		//1:  Bot geht nach Osten
												"go south", 	//2:  Bot geht nach Sueden
												"go west", 		//3:  Bot geht nach Westen
												"kick north", 	//4:  Bot kickt nach Norden
												"kick east", 	//5:  Bot kickt nach Osten
												"kick south", 	//6:  Bot kickt nach Sueden
												"kick west", 	//7:  Bot kickt nach Westen
												"put", 			//8:  Bot legt ein Papier hin
												"take", 		//9:  Bot nimmt ein Papier oder Formular auf
												"finish",		//10: Bot ist fertig und beendet sein Spiel
												"position" };	//11: Bot fragt nach seiner Position (Koordinaten)
	
	private static String getaetigteAktion;	// Die Ausgabe der Bewegung, die getaetigte Aktion des Bots
	
	/**
	 * Das Herzstück der Bewegung ist die Rekursion (Backtracking), welche bei gegebenen Zielen
	 * den kuerzesten Weg zum Ziel geht, jeweils ein Schritt naeher zum Ziel je Runde.
	 * 
	 * @param zielKoordinaten Die Koordinaten des Zielfeldes
	 * @param karte Die Karteninstanz mit dem Zielfeld
	 * @return 0 bis 3 fuer eine Bewegung nach Norden (bei 0), Osten (bei 1), Sueden (bei 2) oder Westen (bei 3).
	 * Die Bewegung (0 bis 3) muss in der Aktion +2 % 4 gerechnet werden, da man vom Ziel rueckwaerts gesehen den Schritt unternimmt.
	 */
	public static int schrittZumZiel(int[] zielKoordinaten, Inavigierbar karte) {
		for (int i = 0; i <= 3; i++) {
			if (karte.getNachbarn(zielKoordinaten)[i].getEntfernung() == 0) {
				return i;
			}
		}
		if (karte.getNachbarn(zielKoordinaten)[0]
				.getEntfernung() == karte.getFeld(zielKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getNorden(zielKoordinaten), karte);

		} else if (karte.getNachbarn(zielKoordinaten)[1]
				.getEntfernung() == karte.getFeld(zielKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getOsten(zielKoordinaten), karte);

		} else if (karte.getNachbarn(zielKoordinaten)[2]
				.getEntfernung() == karte.getFeld(zielKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getSueden(zielKoordinaten), karte);

		} else if (karte.getNachbarn(zielKoordinaten)[3]
				.getEntfernung() == karte.getFeld(zielKoordinaten).getEntfernung() - 1) {
			return schrittZumZiel(karte.getWesten(zielKoordinaten), karte);
		}
		return 100;
	}

	/**
	 * Die explorationsAktion laesst den Bot bei gezielt die Karte erkunden, wenn der Bot zurzeit keine Ziele anvisiert.
	 * @param karte Die Karteninstanz wo der Bot neue Felder erkunden soll
	 * @return 0 bis 3 fuer eine Bewegung nach Norden (bei 0), Osten (bei 1), Sueden (bei 2) oder Westen (bei 3)
	 */
	public static int explorationsAktion(Inavigierbar karte) {
		return (schrittZumZiel(karte.getDynamischesZiel(), karte) + 2) % 4;
	}
	
	/**
	 * Die finishAktion laesst den Bot eine Aktion unternehmen, wenn er zu seinem Sachbearbeiter geht 
	 * oder auf ihm das Spiel beendet ("finish").
	 * @param karte Die Karteninstanz, auf welcher sich das "FINISH" Feld, der Sachbearbeiter, befindet.
	 * @return 10 fuer "finish" oder 0 bis 3 fuer den Schritt zum "FINISH" hin.
	 */
	public static int finishAktion(Inavigierbar karte) {
		if (karte.getLevel() == 1) {
			if (karte.getFeld(karte.getAktuellePosition()) instanceof Sachbearbeiter && karte
					.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(), karte)) {
				return 10;
			} else if (karte.getStatischeZiele().getKoordinatenSb(karte) != null) {
				return (schrittZumZiel(karte.getStatischeZiele().getKoordinatenSb(karte), karte) + 2) % 4; // Die gemappten Koordinaten des Sachbearbeiters
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
	
	/**
	 * Die formularAktion laesst den Bot eine Aktion auf einem Formular unternehmen
	 * oder auf ein gesuchtes Formular gezielt zugehen.
	 * @param karte Die Karteninstanz, auf welcher sich die Formularfelder befinden
	 * @param rundeninfo Die Rundeninformationen des Bots fuer die Pruefung, ob in letzter Runde ein Papier gelegt wurde
	 * @return 9 wenn ein Formular oder Papier aufgesammelt wird; 8 wenn ein Papier gelegt wird
	 */
	public static int formularAktion(Inavigierbar karte, Rundeninformationen rundeninfo) {

		if (karte.getFeld(karte.getAktuellePosition()) instanceof Dokument) {
			Dokument dokument = (Dokument) karte.getFeld(karte.getAktuellePosition());
			if (karte.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(),
					karte)) {
				if (dokument.getNr() == karte.getStatischeZiele().getAufgesammelteFormulare()) {
					return 9;
				}
			} else {
				if (karte.getSheetCount() > 0) {
					return 8;
				}
			}
		} else if (karte.getFeld(karte.getAktuellePosition()) instanceof Boden && karte
				.getStatischeZiele().isKoordinatenVorhanden(karte.getAktuellePosition(), karte)) {
			
			karte.vernebleUmgebung(); // TODO: Pruefen ob wir ggf. doch die ganze Karte aktualisieren koennen/muessen!

			return -1;
		} else {
			int ges_dokuments_nr = karte.getStatischeZiele().getAufgesammelteFormulare();
			if (karte.getStatischeZiele().getKoordinatenFormular(ges_dokuments_nr, karte) != null) {
				
				return (schrittZumZiel(
						karte.getStatischeZiele().getKoordinatenFormular(ges_dokuments_nr, karte),
						karte) + 2) % 4;
			}
		}
		return -1;
	}
	
	/**
	 * Die papierAktion laesst den Bot eine Aktion auf einem Papierfeld ausfuehren, wie ein Papier zu kicken oder es aufzunehmen
	 * @param karte Die Karteninstanz, auf welcher sich die Papierfelder befinden
	 * @return 4 bis 7 fuer ein "kick north" (bei 4), "kick east" (bei 5), "kick south" (bei 6) oder "kick west" (bei 7); 9 fuer das Aufnehmen eines Papiers
	 */
	public static int papierAktion(Inavigierbar karte) {
		
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

	/**
	 * Die fehlgeschlageneAktion laesst den Bot eine Aktion aus der letzten Runde wiederholen, wenn der Bot sich
	 * mit einem anderen Bot verquatscht hat und so seine Aktion nicht ausfuehren konnte.
	 * @param karte Die Karteninstanz, auf welcher die Aktion der letzten Runde haette passieren sollen
	 * @param rundeninformationen Die Rundeninformationen, hier die der letzten Runde (LastActionsResult)
	 * @return Eine Aktion aus der aktionen Stringliste, welche in der neuen Runde wiederholt werden soll.
	 */
	public static int fehlgeschlageneAktion(Inavigierbar karte, Rundeninformationen rundeninformationen) {
		if (rundeninformationen.getLastActionsResult().equals("NOK TALKING")) {
			
			return Arrays.asList(aktionen).indexOf(rundeninformationen.getLastDoneAction());
		}
		return -1;
	}

	/**
	 * Die Methode bestimmeBewegung enthaelt eine Prioritaetsliste, welche die einzelnen Aktionen (Methoden)
	 * fuer unterschiedliche Gegebenheiten enthaelt. Diese sind nach der Prioritaet der Bewegungslogik sortiert.
	 * Welche Methode/Aktion erfolgreich durchlaeuft, (!= -1) zurueckgibt, wird als Bewegung (Ausgabe) bestimmt.
	 * @param karte Die Karteninstanz auf welcher die Bewegung bestimmt werden soll
	 * @param rundeninfo Die Rundeninformationen fuer die fehlgeschlageneAktion, sowie die formularAktion
	 * @return
	 */
	public static String bestimmeBewegung(Inavigierbar karte, Rundeninformationen rundeninfo) {
		
		List<Integer> prioritaets_liste = new ArrayList<Integer>();
		
		prioritaets_liste.add(fehlgeschlageneAktion(karte, rundeninfo));
		prioritaets_liste.add(finishAktion(karte));
		prioritaets_liste.add(formularAktion(karte, rundeninfo));
		prioritaets_liste.add(papierAktion(karte));
		prioritaets_liste.add(explorationsAktion(karte));
		
		for (int erfolgreiche_ausgabe : prioritaets_liste) {
			if (erfolgreiche_ausgabe != -1) {
				return aktionen[erfolgreiche_ausgabe];
			}
		}
		karte.vernebleKarte();
		System.err.println("DIE GESAMTE KARTE WURDE VERNEBELT");
		return aktionen[explorationsAktion(karte)];
	}

	/**
	 * Die eigentliche Bewegung wird mit dieser Methode ausgegeben.
	 * Zusaetzlich wird die getaetigteAktion als letzteGetaetigteAktion (LastDoneAction) in den Rundeninformationen gespeichert
	 * @param karte Die Karteninstanz, auf welcher die Bewegung ausgefuehrt werden soll
	 * @param rundeninfo Die Rundeninformationen, welche die getaetigteAktion als letzteGetaetigteAktion (LastDoneAction) gesetzt bekommen
	 * @return getaetigteAktion als String wie "go north"; "take" oder "finish"
	 */
	public static String bewegung(Inavigierbar karte, Rundeninformationen rundeninfo) {
		getaetigteAktion = bestimmeBewegung(karte, rundeninfo);
		rundeninfo.setLastDoneAction(getaetigteAktion);
		return getaetigteAktion;
	}
}