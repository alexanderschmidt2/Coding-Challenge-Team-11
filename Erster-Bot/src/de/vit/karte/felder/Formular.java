package de.vit.karte.felder;

/**
 * pro Formular wird eine weitere Instanz angelegt 
 * mit entsprechender Nummer und SpielerId
 * 
 * @author Laura Fenzl
 * @author Constantin Graedtke
 */
public class Formular extends Feld {
	
	private final int spielerId;
	private final int nr;
	private boolean aufgenommen = false;

	/**
	 * nimmt den cellStatus-String entgegen und slict die spielerId und
	 * Formularnummer heraus und speichert diese als int in den jeweiligen
	 * Attributen
	 * 
	 * @param cellStatus String aus der Spieleumgebung
	 */
	public Formular(String cellStatus) {
		super();
		String spielerIdString = cellStatus.substring(5, 6);
		int spielerIdInt = Integer.valueOf(spielerIdString);
		String formNrString = cellStatus.substring(7, 8);
		int formNrInt = Integer.valueOf(formNrString);

		this.name = "FORM " + spielerIdString + " " + formNrString;
		this.spielerId = spielerIdInt;
		this.nr = formNrInt;
	}

	public int getSpielerId() {
		return spielerId;
	}

	public int getNr() {
		return nr;
	}

	public boolean isAufgenommen() {
		return aufgenommen;
	}

	public void setAufgenommen(boolean aufgenommen) {
		this.aufgenommen = aufgenommen;
	}
}