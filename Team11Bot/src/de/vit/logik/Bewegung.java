package de.vit.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.vit.karte.Inavigierbar;
import de.vit.karte.felder.Feld;
import de.vit.karte.felder.Boden;
import de.vit.karte.felder.Sachbearbeiter;
import de.vit.karte.felder.Formular;
import de.vit.karte.felder.Blatt;

import de.vit.karte.typen.ZielMap;

/**
 * Die Klasse Bewegung, fuehrt die Bewegung mittels system.out.println() in der
 * main Methode der Klasse Team11Bot aus, indem die Methode Bewegung.bewegung() 
 * dort aufgerufen wird, welche einen String (Aktion) zurueckgibt.
 * 
 * Die Klasse ist abstrakt, da sie nicht instanziierbar ist und nur die Bewegungsmethode 
 * dieser Klasse für die main-Methode des Team11Bots notwendig ist.
 * 
 * @author Franz Bogmann und Alexander Schmidt
 *
 */
public abstract class Bewegung {

	// Alle moeglichen Aktionen des Bots, werden in einer Liste von Strings gespeichert und anhand ihrer Indizes aufgerufen:
	private static final String[] aktionen = { 	"go north", 	// 0: Bot geht nach Norden
												"go east", 		// 1: Bot geht nach Osten
												"go south", 	// 2: Bot geht nach Sueden
												"go west", 		// 3: Bot geht nach Westen
												"kick north", 	// 4: Bot kickt nach Norden
												"kick east", 	// 5: Bot kickt nach Osten
												"kick south",	// 6: Bot kickt nach Sueden
												"kick west", 	// 7: Bot kickt nach Westen
												"put", 			// 8: Bot legt ein Papier hin
												"take", 		// 9: Bot nimmt ein Papier oder Formular auf
												"finish", 		// 10: Bot ist fertig und beendet sein Spiel
												"position" }; 	// 11: Bot fragt nach seiner Position (Koordinaten)

	private static String getaetigteAktion; // Die Ausgabe der Bewegung, die getaetigte Aktion des Bots

	/**
	 * Das Herzstueck der Bewegung ist die Rekursion (Backtracking), welche bei
	 * gegebenen Zielen den kuerzesten Weg zum Ziel geht, jeweils ein Schritt naeher
	 * zum Ziel je Runde.
	 * 
	 * @param zielKoordinaten Die Koordinaten des Zielfeldes
	 * @param karte           Die Karteninstanz mit dem Zielfeld
	 * @return 0 bis 3 fuer eine Bewegung nach Norden (bei 0), Osten (bei 1), Sueden
	 *         (bei 2) oder Westen (bei 3). Die Bewegung (0 bis 3) muss in der
	 *         Aktion +2 % 4 gerechnet werden, da man vom Ziel rueckwaerts gesehen
	 *         den Schritt unternimmt.
	 */
	public static int schrittZumZiel(int[] zielKoordinaten, Inavigierbar karte) {
		
		int zielFeldEntfernung = karte.getFeld(zielKoordinaten).getEntfernung();
		Feld[] nachbarFeld = karte.getNachbarn(zielKoordinaten);
		
		for (int i = 0; i <= 3; i++) {
			// die Abbruchbedingung der Rekursion
			if (nachbarFeld[i].getEntfernung() == 0) { 
				return i;
			}
		}
		if (nachbarFeld[0].getEntfernung() == zielFeldEntfernung - 1) {
			return schrittZumZiel(karte.getNorden(zielKoordinaten), karte);

		} else if (nachbarFeld[1].getEntfernung() == zielFeldEntfernung - 1) {
			return schrittZumZiel(karte.getOsten(zielKoordinaten), karte);

		} else if (nachbarFeld[2].getEntfernung() == zielFeldEntfernung - 1) {
			return schrittZumZiel(karte.getSueden(zielKoordinaten), karte);

		} else if (nachbarFeld[3].getEntfernung() == zielFeldEntfernung - 1) {
			return schrittZumZiel(karte.getWesten(zielKoordinaten), karte);
		}
		return -1;
	}

	/**
	 * Die explorationsAktion laesst den Bot bei gezielt die Karte erkunden, wenn
	 * der Bot zurzeit keine Ziele anvisiert.
	 * 
	 * @param karte Die Karteninstanz wo der Bot neue Felder erkunden soll
	 * @return 0 bis 3 fuer eine Bewegung nach Norden (bei 0), Osten (bei 1), Sueden
	 *         (bei 2) oder Westen (bei 3)
	 */
	public static int explorationsAktion(Inavigierbar karte) {
		return (schrittZumZiel(karte.getDynamischesZiel(), karte) + 2) % 4; //dynamische Ziele sind die nahesten Boden Felder mit min. einem Nebel Nachbarn
	}

	/**
	 * Die finishAktion laesst den Bot eine Aktion unternehmen, wenn er zu seinem
	 * Sachbearbeiter geht oder auf ihm das Spiel beendet ("finish").
	 * 
	 * @param karte Die Karteninstanz, auf welcher sich das "FINISH" Feld, der
	 *              Sachbearbeiter, befindet.
	 * @return 10 fuer "finish" oder 0 bis 3 fuer den Schritt zum "FINISH" hin.
	 */
	public static int finishAktion(Inavigierbar karte) {
		
		Feld aktuellesFeld = karte.getFeld(karte.getAktuellePosition());
		int[] aktuelleKoordinaten = karte.getAktuellePosition();
		ZielMap ziele = karte.getStatischeZiele();
		
		// Bei Level 1 ohne Formulare
		if (karte.getLevel() == 1) {
			if (aktuellesFeld instanceof Sachbearbeiter && ziele.isKoordinatenVorhanden(aktuelleKoordinaten, karte)) {
				return 10;
			} else if (ziele.getKoordinatenSb(karte) != null) {
				return (schrittZumZiel(ziele.getKoordinatenSb(karte), karte) + 2) % 4;
			}
		// Bei Level > 1 mit Formularen
		} else {
			// Pruefung ob alle Formulare aufgesammelt wurden, bevor man "finish" gibt
			if (karte.getFormularZaehler() == (ziele.getAufgesammelteFormulare() - 1) && ziele.getKoordinatenSb(karte) != null) {
				if (aktuellesFeld instanceof Sachbearbeiter && ziele.isKoordinatenVorhanden(aktuelleKoordinaten, karte)) {
					return 10;
				} else {
					return (schrittZumZiel(ziele.getKoordinatenSb(karte), karte) + 2) % 4;
				}
			}
		}
		return -1;
	}

	/**
	 * Die formularAktion laesst den Bot eine Aktion auf einem Formular unternehmen
	 * oder auf ein gesuchtes Formular gezielt zugehen.
	 * 
	 * @param karte      Die Karteninstanz, auf welcher sich die Formularfelder
	 *                   befinden
	 * @param rundeninfo Die Rundeninformationen des Bots fuer die Pruefung, ob in
	 *                   letzter Runde ein Papier gelegt wurde
	 * @return 9 wenn ein Formular oder Papier aufgesammelt wird; 8 wenn ein Papier
	 *         gelegt wird
	 */
	public static int formularAktion(Inavigierbar karte, Rundeninformationen rundeninfo) {
		
		Feld aktuellesFeld = karte.getFeld(karte.getAktuellePosition());
		int[] aktuelleKoordinaten = karte.getAktuellePosition();
		ZielMap ziele = karte.getStatischeZiele();
		
		if (aktuellesFeld instanceof Formular) {
			Formular formular = (Formular) aktuellesFeld;
			if (ziele.isKoordinatenVorhanden(aktuelleKoordinaten, karte)) {
				// Pruefung ob das gefundene Formular an der richtigen Reihenfolge ist und aufgenommen werden kann
				if (formular.getNr() == ziele.getAufgesammelteFormulare()) {
					return 9;
				}
			// else bedeutet hier, dass es nicht unser Formular ist, da es nicht in der ZielMap gespeichert wurde
			// hier legt der Bot dort ein Blatt hin
			} else {
				if (karte.getBlattZaehler() > 0) {
					return 8;
				}
			}
		// Sonderfall: gespeichertes Formular wurde gekickt und ist nicht mehr an der gespeicherten Stelle,
		// dann werden die Felder mit der Entfernung 2 von den gespeicherten Koordinaten des gesuchten Formulars gesehen,
		// vernebelt, damit der Bot den Bereich abgehen kann/soll
		} else if (aktuellesFeld instanceof Boden && ziele.isKoordinatenVorhanden(aktuelleKoordinaten, karte)) {

			karte.vernebleUmgebung();

			return -1;
		// else, wenn wir nicht auf dem Formular stehen, jedoch ein gespeichertes Formular suchen, 
		// dann gehe gezielt dort hin mittels der schrittZumZiel Methode
		} else {
			int gesuchtesFormular = ziele.getAufgesammelteFormulare();
			if (ziele.getKoordinatenFormular(gesuchtesFormular, karte) != null) {
				return (schrittZumZiel(ziele.getKoordinatenFormular(gesuchtesFormular, karte), karte) + 2) % 4;
			}
		}
		return -1;
	}

	/**
	 * Die papierAktion laesst den Bot eine Aktion auf einem Papierfeld ausfuehren,
	 * wie ein Papier zu kicken (Grundsatz) oder es aufzunehmen (Sonderfall).
	 * 
	 * @param karte Die Karteninstanz, auf welcher sich die Papierfelder befinden
	 * @return 4 bis 7 fuer ein "kick north" (bei 4), "kick east" (bei 5), "kick
	 *         south" (bei 6) oder "kick west" (bei 7); 9 fuer das Aufnehmen eines
	 *         Papiers
	 */
	public static int blattAktion(Inavigierbar karte) {
		
		Feld aktuellesFeld = karte.getFeld(karte.getAktuellePosition());
		
		// Nur bei Level 5 sollen Blaetter im Spiel betrachtet werden!
		if (karte.getLevel() == 5) {
			if (aktuellesFeld instanceof Blatt) {
				Blatt blatt = (Blatt) aktuellesFeld;
				int freieRichtung = -1;
				int[] aktuelleKoordinaten = karte.getAktuellePosition();
				// die Richtungspruefung, wohin das Blatt gekickt werden soll
				for (int i = 0; i < 3; i++) {
					if (karte.getNachbarn(aktuelleKoordinaten)[i] instanceof Boden && !blatt.isGekickt()) {
						freieRichtung = i;
					}
				}
				// es wird in die erste freie Richtung gekickt
				if (freieRichtung > -1) {
					return (freieRichtung + 4);
				// falls keine Richtung frei ist, 
				// (Sackgasse und am einzigen freien Feld steht ein Sachbearbeiter),
				// wird das Blatt aufgenommen
				} else {
					if (!blatt.isGekickt()) {
						return 9;
					}
				}

			}

		}
		return -1;
	}

	/**
	 * Die fehlgeschlageneAktion laesst den Bot eine Aktion aus der letzten Runde
	 * wiederholen, wenn der Bot sich mit einem anderen Bot verquatscht hat und so
	 * seine Aktion nicht ausfuehren konnte.
	 * 
	 * @param karte      Die Karteninstanz, auf welcher die Aktion der letzten Runde
	 *                   haette passieren sollen
	 * @param rundeninfo Die Rundeninformationen, hier die der letzten Runde
	 *                   (LastActionsResult)
	 * @return Eine Aktion aus der aktionen Stringliste, welche in der neuen Runde
	 *         wiederholt werden soll.
	 */
	public static int fehlgeschlageneAktion(Inavigierbar karte, Rundeninformationen rundeninfo) {
		
		if (rundeninfo.getLastActionsResult().equals("NOK TALKING")) {
			return Arrays.asList(aktionen).indexOf(rundeninfo.getLastDoneAction()); //exakt derselbe Aufruf der Aktion aus der letzten Runde
		}
		return -1;
	}

	/**
	 * Die Methode bestimmeBewegung enthaelt eine Prioritaetsliste, welche die
	 * einzelnen Aktionen (Methoden) fuer unterschiedliche Gegebenheiten enthaelt.
	 * Diese sind nach der Prioritaet der Bewegungslogik sortiert. Welche
	 * Methode/Aktion erfolgreich durchlaeuft, (!= -1) zurueckgibt, wird als
	 * Bewegung (Ausgabe) bestimmt.
	 * 
	 * @param karte      Die Karteninstanz auf welcher die Bewegung bestimmt werden
	 *                   soll
	 * @param rundeninfo Die Rundeninformationen fuer die fehlgeschlageneAktion,
	 *                   sowie die formularAktion
	 * @return
	 */
	public static String bestimmeBewegung(Inavigierbar karte, Rundeninformationen rundeninfo) {

		List<Integer> prioritaetsListe = new ArrayList<Integer>();

		prioritaetsListe.add(fehlgeschlageneAktion(karte, rundeninfo)); // hoechste Prioritaet: Wiederholung der (fehlgeschlagenen) Aktion, bietet Robustheit!
		prioritaetsListe.add(finishAktion(karte)); 						// hohe Prioritaet: Das Spiel zu beenden, falls alle Bedinungen dafuer erfuellt sind!
		prioritaetsListe.add(formularAktion(karte, rundeninfo));		// mittlere Prioritaet: Die noetigen Formulare zu sammeln
		prioritaetsListe.add(blattAktion(karte));						// niedrigere Prioritaet: Die Blaetter zu kicken
		prioritaetsListe.add(explorationsAktion(karte));				// unterste Prioritaet/default Case: Die Karte systematisch erkunden!

		for (int erfolgreicheAusgabe : prioritaetsListe) {
			// Welche der oben genannten Methoden (Prioritaeten) als erste einen positiven Wert liefert, wird zur Aktion des Bots!
			if (erfolgreicheAusgabe != -1) {
				return aktionen[erfolgreicheAusgabe];
			}
		}
		// Backup Fall: Falls keine Aktion moeglich ist (positiven Wert liefert) soll alles von neu beginnen.
		karte.vernebleKarte();
		System.err.println("ERROR: Keine Aktion moeglich, die Karte wird resettet. Das Spiel beginnt von vorne!");
		return aktionen[explorationsAktion(karte)];
	}

	/**
	 * Die eigentliche Bewegung wird mit dieser Methode ausgegeben. Zusaetzlich wird
	 * die getaetigteAktion als letzteGetaetigteAktion (LastDoneAction) in den
	 * Rundeninformationen gespeichert
	 * 
	 * @param karte      Die Karteninstanz, auf welcher die Bewegung ausgefuehrt
	 *                   werden soll
	 * @param rundeninfo Die Rundeninformationen, welche die getaetigteAktion als
	 *                   letzteGetaetigteAktion (LastDoneAction) gesetzt bekommen
	 * @return getaetigteAktion als String wie "go north"; "take" oder "finish"
	 */
	public static String bewegung(Inavigierbar karte, Rundeninformationen rundeninfo) {
		getaetigteAktion = bestimmeBewegung(karte, rundeninfo);
		rundeninfo.setLastDoneAction(getaetigteAktion); //wichtige Speicherung der ausgegeben Aktion fuer Verarbeitung in der Klasse Karte
		return getaetigteAktion;
	}
}