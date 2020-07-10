package de.vit.karte.felder;

public class Sachbearbeiter extends Feld {
	private final int playerId;
	private int formCount;
	
	//Getter und Setter
	public int getPlayerId() {
		return playerId;
	}

	public int getFormCount() {
		return formCount;
	}
	
	public void setFormCount(int formCount) {
		this.formCount = formCount;
	}

	public Sachbearbeiter(String info) //TODO String sachbearbeiterdaten uebergeben und slicen
	{
		super();
		//info slicen, um an spielerId zu kommen und in int casten
		String spielerId = info.substring(7, 8);
		int spielerId2 = Integer.valueOf(spielerId);
		//info slicen, um an formCount zu kommen und in int casten
		String formCount = info.substring(9, 10);
		int formCount2 = Integer.valueOf(formCount);

		this.name = "FINISH " + spielerId + " " + formCount;
		this.playerId = spielerId2;
		this.formCount = formCount2;
	}


}
