package de.vit.initialisierung;

public final class Spielfeld extends Initialisierung {
	/*
	 * Diese Klasse soll die Spielfeldinformationen aus der Main Methode rausholen. Die Spielfeldinformationen sind Fest und 
	 * dürfen nicht länger verändert werden
	 */
	int sizeX;
	int sizeY;
	int level;
	
	public Spielfeld(int sizeX, int sizeY, int level) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
	}
}
