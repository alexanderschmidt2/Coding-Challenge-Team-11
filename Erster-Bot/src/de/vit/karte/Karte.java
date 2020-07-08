package de.vit.karte;

import de.vit.karte.felder.*;
import de.vit.botmain.*;

/**
 * 
 * @author Laura
 *Klasse, die das Spielfeld und die aktuelle Position in Form von Koordinaten beinhaltet
 */
public class Karte {
	private int sizeX;
	private int sizeY;
	/**
	 * das eigentliche Spielfeld mit allen Feldern
	 * die erste Array-Ebene bezeichnet die x-Achse
	 * die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[] [] karte;
	//die momentane Position, wird regelmäßig aktualisiert
	private int [] aktuellePosition = new int [2];
	
	
	//Getter und Setter
	public int[] getAktuellePosition() {
		return aktuellePosition;
	}

	public void setAktuellePosition(int x, int y) {
		this.aktuellePosition[0] = x;
		this.aktuellePosition[1] = y;
		
	}

	//TODO grafische ausgabe der Karte als String
	//mit art entscheidet man, ob man die Entfernungen oder die Inhalte der Zellen sehen möchte
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
	public Karte(int sizeX, int sizeY, int startX, int startY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.karte = new Feld[sizeX] [sizeY];
		this.aktuellePosition[0] = startX;
		this.aktuellePosition[1] = startY;
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void feldHinzufügen() {
		//TODO: Karte mit neuen Instanzen füllen
	}
	
	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der LastActionResult
	 */
	public void aktualisierePosition1(String lastActionsResult) {
		
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
	


}
