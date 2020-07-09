package de.vit.karte.felder;

import de.vit.karte.*;
/**
 * 
 * @author Laura
 *der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der Spieleumgebung für das Feld
 */
public abstract class Feld {
	//protected, da erst in der Unterklasse initialisiert
	protected String name;
	protected String typ;
	//wie oft bin ich bereits über dieses Feld gelaufen?
	private int besuche = 0;
	private int entfernung = 500000000;

	/**
	 * kein Setter, da Name nicht geändert werden soll
	 * @return
	 */
	public String getName() {
		return name;
	}
	public String getTyp() {
		return typ;
	};

	public int getBesuche() {
		return besuche;
	}
	public void setBesuche(int besuche) {
		this.besuche = besuche;
	}
	public int getEntfernung() {
		return entfernung;
	}
	public void setEntfernung(int entfernung) {
		this.entfernung = entfernung;
	}
	/**
	 * die Koordinate wird mithilfe des Setters gesetzt, da beim Sachbearbeiter die Koordinate zunächst nicht bekannt ist
	 */
	public Feld()
	{
		this.position = new Koordinate();
	}

}
