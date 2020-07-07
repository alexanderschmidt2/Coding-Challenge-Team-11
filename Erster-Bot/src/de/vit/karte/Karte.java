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
	//das eigentliche Spielfeld mit allen Feldern
	private Feld[] map;
	//die momentane Position, wird regelmäßig aktualisiert
	private Koordinate aktuellePosition;
	
	public Feld[] getMap() {
		return map;
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
		this.map = new Feld[sizeX*sizeY];
		this.aktuellePosition = new Koordinate(startX, startY);
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void feldHinzufügen() {
		//TODO: Karte mit neuen Instanzen füllen
	}

	public Koordinate getAktuellePosition() {
		return aktuellePosition;
	}

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der LastActionResult
	 */
	public void aktualisierePosition1(String lastActionsResult) {
		
		switch (lastActionsResult)
		{
		case "OK NORTH":
			aktuellePosition.setKoordinate(aktuellePosition.getX(), ((aktuellePosition.getY() - 1) + sizeY) % sizeY);
			break;
		case "OK EAST":
			aktuellePosition.setKoordinate(((aktuellePosition.getX() + 1) + sizeX) % sizeX, aktuellePosition.getY());
			break;
		case "OK SOUTH":
			aktuellePosition.setKoordinate(aktuellePosition.getX(), ((aktuellePosition.getY() + 1) + sizeY) % sizeY);
			break;
		case "OK WEST":
			aktuellePosition.setKoordinate(((aktuellePosition.getX() - 1) + sizeX) % sizeX, aktuellePosition.getY());
			break;
		}
	}



}
