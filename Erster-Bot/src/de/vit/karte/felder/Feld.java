package de.vit.karte.felder;

/**
 * der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der Spieleumgebung fuer das Feld
 * @author Laura
 * @author Constantin
 */
public abstract class Feld {
	/**
	 * protected, da erst in der Unterklasse initialisiert
	 */
	protected String name;
	private int entfernung = 500000000;
	private int besuche = 0;
	
	//Getter und Setter
	/**
	 * kein Setter, da Name nicht geaendert werden soll, bei Aenderung der Feldart wird ein neues Objekt einer anderen Klasse angelegt
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

	public int getBesuche() {
		return besuche;
	}

	public void setBesuche(int besuche) {
		this.besuche = besuche;
	}

	public Feld()
	{
	}
}
