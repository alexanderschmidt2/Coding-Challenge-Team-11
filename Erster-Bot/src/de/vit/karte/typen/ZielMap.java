package de.vit.karte.typen;

import java.util.Arrays;
import java.util.HashMap;

import de.vit.karte.Inavigierbar;

import de.vit.karte.felder.Formular;
import de.vit.karte.felder.Sachbearbeiter;

/**
 * Die Klasse ZielMap, welche eine erweiterte HashMap darstellt, dient zur
 * Speicherung der gefundenen Ziele auf der Karte, wie Sachbearbeiter oder
 * Formulare, in Form von einem String Namen und den zugehoerigen Koordinaten
 * des Feldes.
 * 
 * @author Alexander Schmidt und Franz Bogmann
 *
 */
@SuppressWarnings("serial") // diese Unterdrueckung verstehen wir nicht vollstaendig, jedoch ohne ist Klasse gelb markiert.
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
				if (karte.getFeld(e) instanceof Formular) {
					Formular dokument = (Formular) karte.getFeld(e);
					if (dokument.getNr() == formId) {
						return e;
					}
				}
			}
		}
		return null; // Wenn keine Formulare und damit deren Koordinaten vorhanden sind
	}

	public boolean isKoordinatenVorhanden(int[] koordinaten, Inavigierbar karte) {
		if (!this.isEmpty()) {
			for (int[] e : this.values()) {
				if (Arrays.equals(e, koordinaten)) {
					return true;
				}
			}
		}

		return false; // Wenn kein Zielfeld und damit dessen Koordinaten vorhanden sind
	}

	public int[] getKoordinatenSb(Inavigierbar karte) {
		if (!this.isEmpty()) {
			for (int[] e : this.values()) {
				if (karte.getFeld(e) instanceof Sachbearbeiter) {
					return e;
				}
			}
		}
		return null; // Wenn keine Sachbearbeiter und damit dessen Koordinaten vorhanden sind
	}

}
