package de.vit.karte.felder;

import de.vit.botmain.*;

public class Sachbearbeiter extends Feld {
	private int playerId;
	
	public Sachbearbeiter(int playerId)
	{
		super();
		this.name = "FINISH " + playerId + " 0";
	}
}
