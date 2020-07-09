package de.vit.karte;

import de.vit.karte.felder.*;
import de.vit.logik.*;

/**
 * 
 * @author Laura
 *Klasse, die das Spielfeld und die aktuelle Position in Form von Koordinaten beinhaltet
 */
public class Karte implements navigierbar{
	private int sizeX;
	private int sizeY;
	private final int level;
	/**
	 * das eigentliche Spielfeld mit allen Feldern
	 * die erste Array-Ebene bezeichnet die x-Achse
	 * die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[] [] karte;
	//die momentane Position, wird regelmäßig aktualisiert
	private int [] aktuellePosition = new int [2];
	
	
	//Getter und Setter
	public int[] getSize() {
		int [] size = new int[] {sizeX, sizeY};
		return size;
	}
	
	public Feld getFeld(int x, int y)
	{
		return this.karte [x] [y];
	}

	public int[] getAktuellePosition() {
		return aktuellePosition;
	}

	public void setAktuellePosition(int x, int y) {
		this.aktuellePosition[0] = x;
		this.aktuellePosition[1] = y;	
	}
	
	public int getLevel()
	{
		return level;
	}

	//TODO grafische ausgabe der Karte als String
	//mit Art entscheidet man, ob man die Entfernungen oder die Inhalte der Zellen sehen möchte
	public String getMap(String art) {
		return "bla";
	}
	
	/**
	 * erstellt Spielfeld entsprechend der übergebenen Größe des Spielfelds und Startposition des Bots
	 * @param sizeX Größe des Spielfelds in der horizontalen
	 * @param sizeY Größe des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(int sizeX, int sizeY, int level, int startX, int startY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.karte = new Feld[sizeX] [sizeY];
		this.level = level;
		this.aktuellePosition[0] = startX;
		this.aktuellePosition[1] = startY;
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void feldHinzufuegen(int x, int y, String typ) {
		//TODO: Karte mit neuen Instanzen füllen
		//geht schwerlich, da man keinen neuen SB anlegen kann, da die levelId noch nicht existiert
		//SB anlegen geht nur in MainMethode
	}
	
	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der LastActionResult
	 */
	public void aktualisierePosition(String lastActionsResult) {
		
		switch (lastActionsResult)
		{
		case "OK NORTH":
			this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] - 1) + sizeY) % sizeY);
			break;
		case "OK EAST":
			this.setAktuellePosition(((aktuellePosition[0] + 1) + sizeX) % sizeX, aktuellePosition[1]);
			break;
		case "OK SOUTH":
			this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] + 1) + sizeY) % sizeY);
			break;
		case "OK WEST":
			this.setAktuellePosition(((aktuellePosition[0] - 1) + sizeX) % sizeX, aktuellePosition[1]);
			break;
		}
	}
	
	public void aktualisiereKarte(Rundeninformationen rundeninformation)
	{
		//TODO: maximal 3 unbekannte neue Felder durch neue Informationen ersetzen
	}
	
	/**
	 * Methode, die abhängig vom aktuellen Standpunkt die Koordinaten des im Norden angrenzenden Objekts zurückgibt
	 * @return  Koordinaten des Objekts nördlich vom aktuellen Standpunkt, welches eine Spezialisierung des Typs Feld ist
	 */
	public int[] getNorden(int x, int y)
	{
		int[] nord = new int[2];
		nord[0] = x;
		nord[1] = ((y - 1) + this.getSize()[1]) % this.getSize()[1];
		return nord;
	}
	
	/**
	 * s. Norden, nur mit Osten...
	 * @return
	 */
	public int[] getOsten(int x, int y)
	{
		int[] ost = new int[2];
		ost[0] = ((x + 1) + this.getSize()[0]) % this.getSize()[0];
		ost[1] = y;
		return ost;
	}
	
	/**
	 * s. Norden, nur mit Süden...
	 * @return
	 */
	public int[] getSueden(int x, int y)
	{
		int[] sued = new int[2];
		sued[0] = x;
		sued[1] = ((y + 1) + this.getSize()[1]) % this.getSize()[1];
		return sued;
	}
	
	/**
	 * s. Norden, nur mit Westen...
	 * @return
	 */
	public int[] getWesten(int x, int y)
	{
		int[] west = new int[2];
		west[0] = ((x - 1) + this.getSize()[0]) % this.getSize()[0];
		west[1] = y;
		return west;
	}
	
	/**
	 * Methode, die ein Array von vier Feldern zurückgibt, welche den Feldern entsprechen, die an die aktuelle Position angrenzen
	 * die Reihenfolge der Objekte: Nord, Ost, Süd, West
	 * die Methode ruft die Methoden getNorden(), getOsten(), getSüden(), getWesten() auf
	 * @param karte
	 * @return
	 */
	public Feld[] getNachbarn(int x, int y)
	{
		Feld[] nachbarn = new Feld[4];
		nachbarn[0] = getFeld(this.getNorden(x, y)[0], this.getNorden(x, y)[1]);
		nachbarn[1] = getFeld(this.getOsten(x, y)[0], this.getOsten(x, y)[1]);
		nachbarn[2] = getFeld(this.getSueden(x, y)[0], this.getSueden(x, y)[1]);
		nachbarn[3] = getFeld(this.getWesten(x, y)[0], this.getWesten(x, y)[1]);
		return nachbarn;
	}

}
