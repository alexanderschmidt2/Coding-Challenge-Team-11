package de.vit.karte.typen;

import java.util.Arrays;
import java.util.HashMap;

import de.vit.karte.Karte;
import de.vit.karte.felder.Dokument;
import de.vit.karte.felder.Sachbearbeiter;
import de.vit.logik.*;

@SuppressWarnings("serial")
public class ZielMap extends HashMap<String, int[]> {

	public ZielMap() {
		super();
	}

	public Sachbearbeiter gibSachbearbeiter(Karte aktuelleKarte) {
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {
			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {
				if (aktuelleKarte.getFeld(e) instanceof Sachbearbeiter)
					return (Sachbearbeiter) aktuelleKarte.getFeld(e);
			}
		}
		return null; // noch nicht gefunden
	}

	public int[] gibKoordinatenDokument(int formId, Karte aktuelleKarte) {
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {
			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {
				if (aktuelleKarte.getFeld(e) instanceof Dokument) {
					Dokument dokument = (Dokument) aktuelleKarte.getFeld(e);
					if (dokument.getNr() == formId) {
						return e;
					}
				}
			}
		}
		return null; // noch nicht gefunden es gibt keine Form ID bei Null
	}

	public int aktuellesDokument(Karte aktuelleKarte) {// returned die Nummer des Dokuments, welches wir als
		// nächstes Aufnehmen sollen
		int dokument_zaehler = 0;
		if (aktuelleKarte.getLevel() == 1) {
			return dokument_zaehler;
		} else {
			dokument_zaehler = 1;
		}
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {
			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {
				if (aktuelleKarte.getFeld(e) instanceof Dokument) {
					Dokument dokument = (Dokument) aktuelleKarte.getFeld(e);
					if (dokument.getNr() == 1 && dokument.isAufgenommen()) {
						dokument_zaehler++;
					} else if (dokument.getNr() == 2 && dokument.isAufgenommen()) {
						dokument_zaehler++;
					} else if (dokument.getNr() == 3 && dokument.isAufgenommen()) {
						dokument_zaehler++;
					} else if (dokument.getNr() == 4 && dokument.isAufgenommen()) {
						dokument_zaehler++;
					} else if (dokument.getNr() == 5 && dokument.isAufgenommen()) {
						dokument_zaehler++;
					}

				}
			}
		}
		return dokument_zaehler;
	}

	public boolean isKoordinatenVorhanden(int[] koordinaten, Karte aktuelleKarte) {
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {

			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {

				if (Arrays.equals(e, koordinaten)) {
					return true;
				}
			}
		}

		return false; // noch nicht gefunden es gibt keine Form ID bei Null
	}

	public int[] gibKoordinatenSB(Karte aktuelleKarte) {
		if (!aktuelleKarte.getStatischeZiele().isEmpty()) {
			for (int[] e : aktuelleKarte.getStatischeZiele().values()) {
				if (aktuelleKarte.getFeld(e) instanceof Sachbearbeiter) {
					return e;		
				}
			}
		}
		return null; // noch nicht gefunden es gibt keine Form ID bei Null
	}


}
