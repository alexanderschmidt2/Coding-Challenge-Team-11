package de.vit.karte.felder;

public class Sachbearbeiter extends Feld {

	private final int playerId;
	private final int formCount;
<<<<<<< HEAD
	private boolean unserSB;

	//Setter werden nicht ben�tigt, da beim Konstruktor Aufruf die playerId und der formCount unver�nderbar sind.

=======
	
	//Getter und Setter
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen
	public int getPlayerId() {
		return playerId;
	}

	public int getFormCount() {
		return formCount;
	}
<<<<<<< HEAD

	public Sachbearbeiter(int playerId, int formCount) {
=======
	
	public Sachbearbeiter(int playerId, int formCount)
	{
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen
		super();
		this.playerId = playerId;
		this.formCount = formCount;
	}
}
