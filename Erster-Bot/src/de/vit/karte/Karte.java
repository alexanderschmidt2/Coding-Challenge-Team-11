package de.vit.karte;

import de.vit.karte.felder.*;

/**
 * 
 * @author Laura Klasse, die das Spielfeld und die aktuelle Position in Form von
 *         Koordinaten beinhaltet
 */
public class Karte {
	private final Koordinate groesse;
	private Feld[][] aktuelleKarte;
	// das eigentliche Spielfeld mit allen Feldern
	private final int level;
	private final int playerId;
	// temporär
	private Koordinate start;
	// die momentane Position, wird regelmäßig aktualisiert
	private Koordinate aktuellePosition;

	/**
	 * erstellt Spielfeld entsprechend der übergebenen Größe des Spielfelds und
	 * Startposition des Bots
	 * 
	 * @param sizeX  Größe des Spielfelds in der horizontalen
	 * @param sizeY  Größe des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(Koordinate groesse, int level, int playerId, Koordinate start) {
		this.groesse = groesse;
		this.level = level;
		this.playerId = playerId;
		this.start = start;
		// this.aktuellePosition = new Koordinate(startX, startY);
	}

	public void karteGenerieren() {
		for (int i = 0; i < this.getGroesse().getX(); i++) {
			for (int j = 0; i < this.getGroesse().getY(); j++) {
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

	public Koordinate getStart() {
		return start;
	}

	public void setStart(Koordinate start) {
		this.start = start;
	}

	public Koordinate getGroesse() {
		return groesse;
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
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void feldHinzufuegen() {
		// TODO: Karte mit neuen Instanzen füllen
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
