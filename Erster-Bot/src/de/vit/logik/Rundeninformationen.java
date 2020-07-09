package de.vit.logik;
/**
 * 
 * @author Alex
 * @Klasse speichert die Informationen der Runde in einem Objekt und aktualisiert diese jede Runde
 */
public class Rundeninformationen {
	
	String lastActionsResult;
	String currentCellStatus;
	String northCellStatus;
	String eastCellStatus;
	String southCellStatus;
	String westCellStatus;

	public String getLastActionsResult() {
		return lastActionsResult;
	}

	public String getCurrentCellStatus() {
		return currentCellStatus;
	}

	public String getNorthCellStatus() {
		return northCellStatus;
	}

	public String getEastCellStatus() {
		return eastCellStatus;
	}

	public String getSouthCellStatus() {
		return southCellStatus;
	}

	public String getWestCellStatus() {
		return westCellStatus;
	}
	public Rundeninformationen(String lastActionsResult, String currentCellStatus, String northCellStatus,
			String eastCellStatus, String southCellStatus, String westCellStatus) {
		super();
		this.lastActionsResult = lastActionsResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}
	

}
