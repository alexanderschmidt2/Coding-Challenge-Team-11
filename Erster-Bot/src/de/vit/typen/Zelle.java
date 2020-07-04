package de.vit.typen;

public class Zelle {
	// TODO: hier muss eine dicke Prüflogik mit gaaaaaaaaanz vielen Exceptions hin.
	// Bitte merken, die Dozenten sind böse
	// die Position ist eine eigene Klasse

	private Koordinaten koordinaten;

	private String lastActionsResult;

	private String currentCellStatus; // WALL oder so
	private int visited = 0; // wie oft besucht

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

	private String northCellStatus;
	private String eastCellStatus;
	private String southCellStatus;
	private String westCellStatus;

	public Zelle(String lastActionsResult, String currentCellStatus, String northCellStatus, String eastCellStatus,
			String southCellStatus, String westCellStatus) {
		this.lastActionsResult = lastActionsResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus; // oben //Objekt Wand
		this.eastCellStatus = eastCellStatus; // rechts //FINISCHED <playerID> 0
		this.southCellStatus = southCellStatus; // unten
		this.westCellStatus = westCellStatus; // links
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
