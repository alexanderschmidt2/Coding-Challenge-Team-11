package de.vit.initialisierung;

import java.util.*;

import de.vit.typen.*;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen.
	 * Die Spielfeldinformationen sind Fest und d�rfen nicht l�nger ver�ndert werden
	 */
	//FIXME initialsierung �ber Koordinaten
	private final int sizeX;
	private final int sizeY;
	private final int level; 
	
	private List<Zelle> karte = new ArrayList<>();
	// welche cases sind in diesem Level �berhaupt m�glich
	// level 1: FINSIH_PLAYERID_O; WALL; FLOOR

	public Spielfeld(int sizeX, int sizeY, int level) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
	}

	private void karte_generieren() {
		for(int i = 0; i < this.sizeX; i++) {
			for(int j = 0; j < this.sizeY; j++) {
				this.karte.add(new Zelle(new Koordinaten(i,j)));
			}
		}
	}
}
