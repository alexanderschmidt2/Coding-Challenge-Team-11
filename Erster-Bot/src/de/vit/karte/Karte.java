package de.vit.karte;

import de.vit.karte.felder.*;

/**
 * 
 * @author Laura Klasse, die das Spielfeld und die aktuelle Position in Form von
 *         Koordinaten beinhaltet
 */
public class Karte implements navigierbar{
	private final int sizeX;
	private final int sizeY;
	private Feld[][] aktuelleKarte;
	// das eigentliche Spielfeld mit allen Feldern
	private final int level;
	private final int playerId;
	// tempor�r
	private final int startX;
	private final int startY;
	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	// die momentane Position, wird regelm��ig aktualisiert
	private Koordinate aktuellePosition;

	/**
	 * erstellt Spielfeld entsprechend der �bergebenen Gr��e des Spielfelds und
	 * Startposition des Bots
	 * 
	 * @param sizeX  Gr��e des Spielfelds in der horizontalen
	 * @param sizeY  Gr��e des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
		this.playerId = playerId;
		this.startX = startX;
		this.startY = startY;
		// this.aktuellePosition = new Koordinate(startX, startY);
	}

	public void karteGenerieren() {
		for (int i = 0; i < this.getSizeX(); i++) {
			for (int j = 0; i < this.getSizeY(); j++) {
				this.aktuelleKarte[i][j] = new Nebel();
			}
		}
	};

	public Feld[][] getAktuelleKarte() {
		return aktuelleKarte;
	}

	public void setAktuelleKarte(Feld[][] aktuelleKarte) {
		this.aktuelleKarte = aktuelleKarte;
	}

	public int getLevel() {
		return level;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setAktuellePosition(Koordinate aktuellePosition) {
		this.aktuellePosition = aktuellePosition;
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld f�llt
	 */
	public void feldHinzufuegen() {
		// TODO: Karte mit neuen Instanzen f�llen
		
	}

	public Koordinate getAktuellePosition() {
		return aktuellePosition;
	}

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * 
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der
	 *                 LastActionResult
	 */
//	public void aktualisierePosition1(String lastActionsResult) {
//		
//		switch (lastActionsResult)
//		{
//		case "OK NORTH":
//			aktuellePosition.setKoordinate(aktuellePosition.getX(), ((aktuellePosition.getY() - 1) + sizeY) % sizeY);
//			break;
//		case "OK EAST":
//			aktuellePosition.setKoordinate(((aktuellePosition.getX() + 1) + sizeX) % sizeX, aktuellePosition.getY());
//			break;
//		case "OK SOUTH":
//			aktuellePosition.setKoordinate(aktuellePosition.getX(), ((aktuellePosition.getY() + 1) + sizeY) % sizeY);
//			break;
//		case "OK WEST":
//			aktuellePosition.setKoordinate(((aktuellePosition.getX() - 1) + sizeX) % sizeX, aktuellePosition.getY());
//			break;
//		}
//	}

}
