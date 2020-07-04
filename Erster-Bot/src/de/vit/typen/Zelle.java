package de.vit.typen;

public class Zelle {
	// TODO: hier muss eine dicke Prüflogik mit gaaaaaaaaanz vielen Exceptions hin.
	// Bitte merken, die Dozenten sind böse
	// die Position ist eine eigene Klasse

	private String lastActionsResult; // Das Ergebnis der letzten Aktion/Bewegung
	private String currentCellStatus; // Der Status der Zelle selbst
	private String northCellStatus; // Die Zelle kennt den Status der Zelle im Norden,
	private String eastCellStatus; // im Osten,
	private String southCellStatus; // im Süden,
	private String westCellStatus; // und im Westen von ihr.
	private Koordinaten koordinaten; // Einer konkreten Zelle sind eindeutig Koordinaten (x, y) zugeordnet
	private int visited = 0; // Zähler für Anzahl der "Zellbegehungen". Jede Zelle ist von Beginn an als "nicht besucht" definiert.
	
	/**
	 * Eine Zelle wird mit diesem Konstruktor initialisiert:
	 * Welche Koordinaten eine Zelle besitzt wird im Speicher, der Klasse Speicherlogik, hinterlegt
	 */
	
	public Zelle(String lastActionsResult, String currentCellStatus, String northCellStatus, String eastCellStatus, String southCellStatus, String westCellStatus) {
		this.lastActionsResult = lastActionsResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}

	public Koordinaten getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(Koordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}

	public int getVisited() {
		return visited;
	}

	public void setVisited(int visited) {
		this.visited = visited;
	}

	public String getLastActionsResult() {
		return lastActionsResult;
	}

	public void setLastActionsResult(String lastActionsResult) {
		this.lastActionsResult = lastActionsResult;
	}

	public String getCurrentCellStatus() {
		return currentCellStatus;
	}

	public void setCurrentCellStatus(String currentCellStatus) {
		this.currentCellStatus = currentCellStatus;
	}

	public String getNorthCellStatus() {
		return northCellStatus;
	}

	public void setNorthCellStatus(String northCellStatus) {
		this.northCellStatus = northCellStatus;
	}

	public String getEastCellStatus() {
		return eastCellStatus;
	}

	public void setEastCellStatus(String eastCellStatus) {
		this.eastCellStatus = eastCellStatus;
	}

	public String getSouthCellStatus() {
		return southCellStatus;
	}

	public void setSouthCellStatus(String southCellStatus) {
		this.southCellStatus = southCellStatus;
	}

	public String getWestCellStatus() {
		return westCellStatus;
	}

	public void setWestCellStatus(String westCellStatus) {
		this.westCellStatus = westCellStatus;
	}

}
