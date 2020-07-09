package de.vit.karte.felder;

import de.vit.karte.*;

/**
 * 
 * @author Laura
 *der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der Spieleumgebung für das Feld
 */
public abstract class Feld {
	/**
	 * protected, da erst in der Unterklasse initialisiert
	 */
	protected String name;
	private int entfernung;
	private int besuche = 0;
	
	//Getter und Setter
	/**
	 * kein Setter, da Name nicht geändert werden soll, bei Änderung der Feldart wird ein neues Objekt einer anderen Klasse angelegt
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

	/**
	 * die Koordinate wird mithilfe des Setters gesetzt, da beim Sachbearbeiter die Koordinate zunächst nicht bekannt ist
	 */
	public Feld()
	{
		
	}
}
