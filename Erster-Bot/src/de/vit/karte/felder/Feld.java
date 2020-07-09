package de.vit.karte.felder;

import de.vit.karte.*;

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
	 * kein Setter, da Name nicht ge�ndert werden soll, bei �nderung der Feldart wird ein neues Objekt einer anderen Klasse angelegt
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
	
	/**
	 * Methode, die abh�ngig vom aktuellen Standpunkt das Objekt des im Norden angrenzenden Feldes zur�ckgibt
	 * zun�chst werden die Koordinaten bestimmt, um mit diesen das konkrete Objekt zu ermitteln
	 * @param karte �bergibt die aktuelle Position und die Gr��e des Spielfelds
	 * @return  n�rdliches Objekt vom aktuellen Standpunkt, welches eine Spezialisierung des Typs Feld ist
	 */
	public Feld getNorden(Karte karte)
	{
		int[] nord = new int[2];
		nord[0] = karte.getAktuellePosition()[0];
		nord[1] = ((karte.getAktuellePosition()[1] - 1) + karte.getSize()[1]) % karte.getSize()[1];
		return karte.getFeld(nord[0], nord[1]);
	}
	
	/**
	 * s. Norden, nur mit Osten...
	 * @param karte
	 * @return
	 */
	public Feld getOsten(Karte karte)
	{
		int[] ost = new int[2];
		ost[0] = ((karte.getAktuellePosition()[0] + 1) + karte.getSize()[0]) % karte.getSize()[0];
		ost[1] = karte.getAktuellePosition()[1];
		return karte.getFeld(ost[0], ost[1]);
	}
	
	/**
	 * s. Norden, nur mit S�den...
	 * @param karte
	 * @return
	 */
	public Feld getSueden(Karte karte)
	{
		int[] sued = new int[2];
		sued[0] = karte.getAktuellePosition()[0];
		sued[1] = ((karte.getAktuellePosition()[1] + 1) + karte.getSize()[1]) % karte.getSize()[1];
		return karte.getFeld(sued[0], sued[1]);
	}
	
	/**
	 * s. Norden, nur mit Westen...
	 * @param karte
	 * @return
	 */
	public Feld getWesten(Karte karte)
	{
		int[] west = new int[2];
		west[0] = ((karte.getAktuellePosition()[0] - 1) + karte.getSize()[0]) % karte.getSize()[0];
		west[1] = karte.getAktuellePosition()[1];
		return karte.getFeld(west[0], west[1]);
	}
	
	/**
	 * Methode, die ein Array von vier Feldern zur�ckgibt, welche den Feldern entsprechen, die an die aktuelle Position angrenzen
	 * die Reihenfolge der Objekte: Nord, Ost, S�d, West
	 * die Methode ruft die Methoden getNorden(), getOsten(), getS�den(), getWesten() auf
	 * @param karte
	 * @return
	 */
	public Feld[] getNachbarn(Karte karte)
	{
		Feld[] nachbarn = new Feld[4];
		nachbarn[0] = getNorden(karte);
		nachbarn[1] = getOsten(karte);
		nachbarn[2] = getSueden(karte);
		nachbarn[3] = getWesten(karte);
		return nachbarn;
	}
	
	//probe-Main, wird noch gel�scht...
	public static void main (String[] args)
	{
		Feld[] test = new Feld[2];
		test[0] = new Boden();
		System.out.println(test[0].getName());
	}

}
