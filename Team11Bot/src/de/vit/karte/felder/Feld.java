package de.vit.karte.felder;

/**
 * Der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der
 * Spieleumgebung fuer das Feld
 * 
 * @author Laura Fenzl
 * @author Constantin Graedtke
 */
public abstract class Feld {
	/**
	 * protected, da erst in der Unterklasse initialisiert
	 */
	protected String name;
	private int entfernung = 500000000;

	public Feld() {
	}

	/**
	 * kein Setter, da Name nicht geaendert werden soll, bei Aenderung der Feldart
	 * wird ein neues Objekt einer anderen Klasse angelegt
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public int getEntfernung() {
		return entfernung;
	}

	public void setEntfernung(int entfernung) {
		this.entfernung = entfernung;
	}

}
