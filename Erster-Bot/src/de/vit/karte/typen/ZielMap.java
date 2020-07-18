package de.vit.karte.typen;

import java.util.Arrays;
import java.util.HashMap;

import de.vit.karte.Karte;
import de.vit.karte.felder.Dokument;
import de.vit.karte.felder.Sachbearbeiter;

@SuppressWarnings("serial")
public class ZielMap extends HashMap<String, int[]> {
	
	private int dokumentenZaehler = 1;

	public int getDokumentenZaehler() {
		return dokumentenZaehler;
	}

	public void addDokumentenZaehler() {
		this.dokumentenZaehler++;
	}

	public ZielMap(int level) {
		super();
		if (level == 1) {
			this.dokumentenZaehler = 0;
		}
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
