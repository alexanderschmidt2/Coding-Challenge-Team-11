package de.vit.initialisierung;

import java.util.*;

import de.vit.typen.*;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen.
	 * Die Spielfeldinformationen sind Fest und dürfen nicht länger verändert werden
	 */
	// FIXME initialsierung über Koordinaten
	private final Koordinaten größe;
	private final int level;

	private List<Zelle> haupt_karte = new ArrayList<>(); //Wir können das hier später in den Speicher auslagern, noch soll es aber im Spielfeld bleiben
	// welche cases sind in diesem Level überhaupt möglich
	// level 1: FINSIH_PLAYERID_O; WALL; FLOOR

	public Spielfeld(Koordinaten größe, int level) {
		super();
		this.größe = größe;
		this.level = level;
	}

	public Koordinaten getGröße() {
		return größe;
	}

	public int getLevel() {
		return level;
	}

	private void haupt_karte_generieren() {
		for (int i = 0; i < this.getGröße().getX(); i++) {
			for (int j = 0; j < this.getGröße().getY(); j++) {
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
