package de.vit.karte.felder;

/**
 * 
 * @author Laura
 *pro Dokument wird eine weitere Instanz angelegt mit entsprechender Nummer
 */
public class Dokument extends Feld {
	private final int playerId;
	private final int nr;
	
	//Getter und Setter
	public int getPlayerId() {
		return playerId;
	}

	public int getNr() {
		return nr;
	}

	public Dokument(int playerId, int nr)
	{
		super();
		this.name = "FORM " + playerId + " " + nr;
		this.playerId = playerId;
		this.nr = nr;
	}

}
