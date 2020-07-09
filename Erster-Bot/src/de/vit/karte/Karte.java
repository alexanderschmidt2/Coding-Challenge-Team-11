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
<<<<<<< HEAD
	private final int playerId;
	private int[] aktuellePosition;

	public int getSizeX() {
		return sizeX;
=======
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
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen
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
<<<<<<< HEAD

	public int getPlayerId() {
		return playerId;
	}
=======
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen

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
<<<<<<< HEAD
	public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
=======
	public Karte(int sizeX, int sizeY, int level, int startX, int startY)
	{
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
<<<<<<< HEAD
		this.playerId = playerId;
		this.setAktuellePosition(startX, startY);
		karteGenerieren();

=======
		this.aktuellePosition[0] = startX;
		this.aktuellePosition[1] = startY;
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen
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
<<<<<<< HEAD
=======
	
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
>>>>>>> parent of 2b35463... Update und Methode aktualisereNorden angefangen

}
