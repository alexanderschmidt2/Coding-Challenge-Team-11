package de.vit.initialisierung;

import java.util.*;

import de.vit.typen.*;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen.
	 * Die Spielfeldinformationen sind Fest und d�rfen nicht l�nger ver�ndert werden
	 */
	// FIXME initialsierung �ber Koordinaten
	private final Koordinaten groe�e;
	private final int level;
	private Zelle zuletzt_gesetzte_Zelle;

	private List<Zelle> haupt_karte = new ArrayList<>(); // Wir k�nnen das hier sp�ter in den Speicher auslagern, noch
															// soll es aber im Spielfeld bleiben
	// welche cases sind in diesem Level �berhaupt m�glich
	// level 1: FINSIH_PLAYERID_O; WALL; FLOOR

	public Spielfeld(Koordinaten groe�e, int level) {
		super();
		this.groe�e = groe�e;
		this.level = level;
		System.err.println(this.groe�e.getX()+" "+ this.groe�e.getY());
		this.hauptKarteGenerieren(); // wir generieren f�r ein neues Spielfeldobjekt eine Hauptkarte in der ersten
										// Runde, wenn wir unsere Karte initialisierne
	}

	public Koordinaten getGroe�e() {
		return groe�e;
	}

	public int getLevel() {
		return level;
	}

	private void hauptKarteGenerieren() {
		for (int i = 0; i < this.getGroe�e().getX(); i++) {
			for (int j = 0; j < this.getGroe�e().getY(); j++) {
				this.haupt_karte.add(new Zelle(new Koordinaten(i, j))); //FIXME: Die erste Koordinate w�re also 0/0 und die m�sste den Index 0 haben in der
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
