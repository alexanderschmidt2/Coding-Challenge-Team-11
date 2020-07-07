package de.vit.karte.felder;

import de.vit.karte.*;
import de.vit.botmain.*;
/**
 * 
 * @author Laura
 *der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der Spieleumgebung für das Feld
 */
public abstract class Feld {
	//protected, da erst in der Unterklasse initialisiert
	protected String name;
	private Koordinate position;
	//wie oft bin ich bereits über dieses Feld gelaufen?
	private int besuche = 0;

	/**
	 * kein Setter, da Name nicht geändert werden soll
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * die Koordinate wird mithilfe des Setters gesetzt, da beim Sachbearbeiter die Koordinate zunächst nicht bekannt ist
	 */
	public Feld()
	{
		this.position = new Koordinate();
	}
	
	//probe-Main, wird noch gelöscht...
	public static void main (String[] args)
	{
		Feld[] test = new Feld[2];
		test[0] = new Floor();
		System.out.println(test[0].getName());
	}

}
