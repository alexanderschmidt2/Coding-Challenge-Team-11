package de.vit.karte.felder;

public class Sachbearbeiter extends Feld {
	private final int playerId;
	private final int formCount;
	
	//Getter und Setter
	public int getPlayerId() {
		return playerId;
	}

	public int getFormCount() {
		return formCount;
	}
	
	public Sachbearbeiter(int playerId, int formCount)
	{
		super();
		this.name = "FINISH " + playerId + " 0";
		this.playerId = playerId;
		this.formCount = formCount;
	}


}
