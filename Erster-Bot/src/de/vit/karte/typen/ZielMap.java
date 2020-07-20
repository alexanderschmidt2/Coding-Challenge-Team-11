package de.vit.karte.typen;

import java.util.Arrays;
import java.util.HashMap;

import de.vit.karte.Inavigierbar;

import de.vit.karte.felder.Dokument;
import de.vit.karte.felder.Sachbearbeiter;

@SuppressWarnings("serial")
public class ZielMap extends HashMap<String, int[]> {
	
	private int aufgesammelteFormulare = 1;

	public int getAufgesammelteFormulare() {
		return aufgesammelteFormulare;
	}

	public void addAufgesammelteFormulare() {
		this.aufgesammelteFormulare++;
	}

	public ZielMap(int level) {
		super();
		if (level == 1) {
			this.aufgesammelteFormulare = 0;
		}
	}


	public int[] getKoordinatenFormular(int formId, Inavigierbar karte) {
		if (!this.isEmpty()) {
			for (int[] e : this.values()) {
				if (karte.getFeld(e) instanceof Dokument) {
					Dokument dokument = (Dokument) karte.getFeld(e);
					if (dokument.getNr() == formId) {
						return e;
					}
				}
			}
		}
		return null; // noch nicht gefunden es gibt keine Form ID bei Null
	}

	public boolean isKoordinatenVorhanden(int[] koordinaten, Inavigierbar karte) {
		if (!this.isEmpty()) {
			for (int[] e : this.values()) {
				if (Arrays.equals(e, koordinaten)) {
					return true;
				}
			}
		}

		return false; // noch nicht gefunden es gibt keine Form ID bei Null
	}

	public int[] getKoordinatenSb(Inavigierbar karte) {
		if (!this.isEmpty()) {
			for (int[] e : this.values()) {
				if (karte.getFeld(e) instanceof Sachbearbeiter) {
					return e;
				}
			}
		}
		return null; // noch nicht gefunden es gibt keine Form ID bei Null
	}

}
