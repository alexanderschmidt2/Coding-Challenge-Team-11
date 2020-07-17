package de.vit.karte.felder;

/**
 * pro Dokument wird eine weitere Instanz angelegt mit entsprechender PlayerId und Anzahl der Formulare
 * @author Laura
 * @author Constantin
 */
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

	public Sachbearbeiter(String info)
	{
		super();
		//info slicen, um an spielerId zu kommen und in int casten
		String spielerId_string = info.substring(7, 8);
		int spielerId_int = Integer.valueOf(spielerId_string);
		//info slicen, um an formCount zu kommen und in int casten
		String formCount_string = info.substring(9, 10);
		int formCount_int = Integer.valueOf(formCount_string);

		this.name = "FINISH " + spielerId_string + " " + formCount_string;
		this.playerId = spielerId_int;
		this.formCount = formCount_int;
	}
}
