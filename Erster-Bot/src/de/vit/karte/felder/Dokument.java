package de.vit.karte.felder;

/**
 * 
 * @author Laura
 *pro Dokument wird eine weitere Instanz angelegt mit entsprechender Nummer
 */
public class Dokument extends Feld {
	private final int playerId;
	private final int nr;
	private boolean aufgenommen = false;
	
	//Getter und Setter
	public int getPlayerId() {
		return playerId;
	}

	public int getNr() {
		return nr;
	}
	
	
	
	public Dokument(String info)
	{
		super();
		//info slicen, um an spielerId zu kommen und in int casten
		String spielerId_string = info.substring(5, 6);
		int spielerId_int = Integer.valueOf(spielerId_string);
		//info slicen, um an nr zu kommen und in int casten
		String formNr_string = info.substring(7, 8);
		int formNr_int = Integer.valueOf(formNr_string);

		this.name = "FORM " + spielerId_string + " " + formNr_string;
		this.playerId = spielerId_int;
		this.nr = formNr_int;
	}

	public boolean isAufgenommen() {
		return aufgenommen;
	}

	public void setAufgenommen(boolean aufgenommen) {
		this.aufgenommen = aufgenommen;
	}

}
