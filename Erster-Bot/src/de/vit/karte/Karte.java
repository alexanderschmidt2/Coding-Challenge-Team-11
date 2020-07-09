package de.vit.karte;

import de.vit.karte.felder.*;

/**
 * 
 * @author Laura Klasse, die das Spielfeld und die aktuelle Position in Form von
 *         Koordinaten beinhaltet
 */
//karte muss sich selbst aktualisieren
public class Karte implements navigierbar {
	private final int sizeX;
	private final int sizeY;
	private Feld[][] aktuelleKarte;
	// das eigentliche Spielfeld mit allen Feldern
	private final int level;
	private final int playerId;
	private int[] aktuellePosition;

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

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

	public void setAktuellePosition(int x, int y) {
		this.aktuellePosition[0] = x;
		this.aktuellePosition[1] = y;

	}

	// die momentane Position, wird regelmäßig aktualisiert

	/**
	 * erstellt Spielfeld entsprechend der übergebenen Größe des Spielfelds und
	 * Startposition des Bots
	 * 
	 * @param sizeX  Größe des Spielfelds in der horizontalen
	 * @param sizeY  Größe des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
		this.playerId = playerId;
		this.setAktuellePosition(startX, startY);
		karteGenerieren();

	}

	public void karteGenerieren() {
		for (int i = 0; i < this.getSizeX(); i++) {
			for (int j = 0; i < this.getSizeY(); j++) {
				this.aktuelleKarte[i][j] = new Nebel();
			}
		}

	};

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void entfernungenAktualisieren() {
		//Wir brauchen eine Methode, die uns die Nachbarn liefern abhängig von der aktuellen Koordinate. Entweder Nebelzellen oder bereits implementierteZellen
		boolean aenderung;

		do{
			aenderung = false;
			for (int x = 0; x < this.getSizeX(); x++) {
				for (int y = 0; y < this.getSizeY(); y++) {
					if(aktuelleKarte[x][y].getEntfernung())
				}
			}
		}
		
	}

	public void feldHinzufuegen(int x, int y, String typ) {
		switch (typ) {
		case ("FLOOR"):
			aktuelleKarte[x][y] = new Boden();
		}

	}

	public int[] getAktuellePosition() {
		return aktuellePosition;
	}

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * 
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der
	 *                 LastActionResult
	 */
	public void aktualisierePosition(String lastActionsResult) {
		switch (lastActionsResult) {
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
