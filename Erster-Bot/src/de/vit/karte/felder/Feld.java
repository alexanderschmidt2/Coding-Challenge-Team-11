package de.vit.karte.felder;

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
	
	public int[] getNorth(int sizeX, int sizeY)
	{
		int[] nord = new int[2];
		//TODO Koordinaten mit Übertritt Spielfeldrand errechnen...
		return nord;
	}
	
	public int[] getEast(int sizeX, int sizeY)
	{
		int[] ost = new int[2];
		//TODO Koordinaten mit Übertritt Spielfeldrand errechnen...
		return ost;
	}
	
	public int[] getSouth(int sizeX, int sizeY)
	{
		int[] sued = new int[2];
		//TODO Koordinaten mit Übertritt Spielfeldrand errechnen...
		return sued;
	}
	
	public int[] getWest(int sizeX, int sizeY)
	{
		int[] west = new int[2];
		//TODO Koordinaten mit Übertritt Spielfeldrand errechnen...
		return west;
	}
	
	public int[] getNachbar(int sizeX, int sizeY)
	{
		int[] nachbar = new int[8];
		return nachbar;
	}
	
	//probe-Main, wird noch gelöscht...
	public static void main (String[] args)
	{
		Feld[] test = new Feld[2];
		test[0] = new Boden();
		System.out.println(test[0].getName());
	}

}
