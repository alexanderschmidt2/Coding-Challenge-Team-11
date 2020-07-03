package de.vit.initialisierung;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen. Die Spielfeldinformationen sind Fest und 
	 * d�rfen nicht l�nger ver�ndert werden
	 */
	int sizeX;
	int sizeY;
	int level; //welche cases sind in diesem Level �berhaupt m�glich
	//level 1: FINSIH_PLAYERID_O; WALL; FLOOR
	
	public Spielfeld(int sizeX, int sizeY, int level) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
	}

}
