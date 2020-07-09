package de.vit.karte.felder;

public class Sachbearbeiter extends Feld {

	private final int playerId;
	private final int formCount;
	private boolean unserSB;

	//Setter werden nicht ben�tigt, da beim Konstruktor Aufruf die playerId und der formCount unver�nderbar sind.

	public int getPlayerId() {
		return playerId;
	}

	public int getFormCount() {
		return formCount;
	}

	public Sachbearbeiter(int playerId, int formCount) {
		super();
		this.playerId = playerId;
		this.formCount = formCount;
	}
}
