package de.vit.initialisierung;

import java.util.*;

import de.vit.typen.*;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen.
	 * Die Spielfeldinformationen sind Fest und dürfen nicht länger verändert werden
	 */
	// FIXME initialsierung über Koordinaten
	private final Koordinaten groeße;
	private final int level;
	private Zelle zuletzt_gesetzte_Zelle;

	private List<Zelle> haupt_karte = new ArrayList<>(); // Wir können das hier später in den Speicher auslagern, noch
															// soll es aber im Spielfeld bleiben
	// welche cases sind in diesem Level überhaupt möglich
	// level 1: FINSIH_PLAYERID_O; WALL; FLOOR

	public Spielfeld(Koordinaten groeße, int level) {
		super();
		this.groeße = groeße;
		this.level = level;
		System.err.println(this.groeße.getX()+" "+ this.groeße.getY());
		this.hauptKarteGenerieren(); // wir generieren für ein neues Spielfeldobjekt eine Hauptkarte in der ersten
										// Runde, wenn wir unsere Karte initialisierne
	}

	public Koordinaten getGroeße() {
		return groeße;
	}

	public int getLevel() {
		return level;
	}

	private void hauptKarteGenerieren() {
		for (int i = 0; i < this.getGroeße().getX(); i++) {
			for (int j = 0; j < this.getGroeße().getY(); j++) {
				this.haupt_karte.add(new Zelle(new Koordinaten(i, j))); //FIXME: Die erste Koordinate wäre also 0/0 und die müsste den Index 0 haben in der
			}
		}
	}

	public Zelle getZelle(int index) {
		return haupt_karte.get(index);
	}

	public void karteAusgeben() {
		for (Zelle e : this.haupt_karte) {
			System.err.println(e.getCurrentCellStatus());
		}
	}

	public void setzeNeueZelle(int index, Zelle zelle) {
		zelle.setVisited(1);
		this.zuletzt_gesetzte_Zelle = zelle;
		haupt_karte.add(index, zelle);
	}

	public Zelle getZuletztGesetzteZelle() {
		return zuletzt_gesetzte_Zelle;
	}

}
