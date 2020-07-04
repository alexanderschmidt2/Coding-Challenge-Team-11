package de.vit.initialisierung;

import java.util.*;

import de.vit.typen.*;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen.
	 * Die Spielfeldinformationen sind Fest und d�rfen nicht l�nger ver�ndert werden
	 */
	// FIXME initialsierung �ber Koordinaten
	private final Koordinaten gr��e;
	private final int level;

	private List<Zelle> haupt_karte = new ArrayList<>(); //Wir k�nnen das hier sp�ter in den Speicher auslagern, noch soll es aber im Spielfeld bleiben
	// welche cases sind in diesem Level �berhaupt m�glich
	// level 1: FINSIH_PLAYERID_O; WALL; FLOOR

	public Spielfeld(Koordinaten gr��e, int level) {
		super();
		this.gr��e = gr��e;
		this.level = level;
	}

	public Koordinaten getGr��e() {
		return gr��e;
	}

	public int getLevel() {
		return level;
	}

	private void haupt_karte_generieren() {
		for (int i = 0; i < this.getGr��e().getX(); i++) {
			for (int j = 0; j < this.getGr��e().getY(); j++) {
				this.haupt_karte.add(new Zelle(new Koordinaten(i, j)));
			}
		}
	}

	public void karte_ausgeben() {
		for (Zelle e : this.haupt_karte) {
			System.err.println(e.getCurrentCellStatus());
		}
	}
	public void setze_neue_Zelle(int index, Zelle zelle) {
		haupt_karte.add(index, zelle);
	}

}
