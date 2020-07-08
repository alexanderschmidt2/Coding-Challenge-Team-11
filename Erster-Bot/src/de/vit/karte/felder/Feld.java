package de.vit.karte.felder;

import de.vit.karte.*;
import de.vit.botmain.*;
/**
 * 
 * @author Laura
 *der Name der jeweiligen Spezialisierung entspricht der Bezeichnung der Spieleumgebung f�r das Feld
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
	 * kein Setter, da Name nicht ge�ndert werden soll
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
	 * die Koordinate wird mithilfe des Setters gesetzt, da beim Sachbearbeiter die Koordinate zun�chst nicht bekannt ist
	 */
	public Feld()
	{
		
	}
	
	//probe-Main, wird noch gel�scht...
	public static void main (String[] args)
	{
		Feld[] test = new Feld[2];
		test[0] = new Boden();
		System.out.println(test[0].getName());
	}

}
