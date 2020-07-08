package de.vit.karte.felder;

/**
 * 
 * @author Laura
 *pro Dokument wird eine weitere Instanz angelegt mit entsprechender Nummer
 */
public class Dokument extends Feld {
	private int playerId;
	private int Nr;
	
	public Dokument(int playerId, int Nr)
	{
		super();
		this.name = "FORM " + playerId + " " + Nr;
	}

}
