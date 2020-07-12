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
	String lastDoneAction;

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
	
	public String getLastDoneAction() {
		return lastDoneAction;
	}

	public void setLastActionsResult(String lastActionsResult) {
		this.lastActionsResult = lastActionsResult;
	}

	public void setCurrentCellStatus(String currentCellStatus) {
		this.currentCellStatus = currentCellStatus;
	}

	public void setNorthCellStatus(String northCellStatus) {
		this.northCellStatus = northCellStatus;
	}

	public void setEastCellStatus(String eastCellStatus) {
		this.eastCellStatus = eastCellStatus;
	}

	public void setSouthCellStatus(String southCellStatus) {
		this.southCellStatus = southCellStatus;
	}

	public void setWestCellStatus(String westCellStatus) {
		this.westCellStatus = westCellStatus;
	}

	public void setLastDoneAction(String lastDoneAction) {
		this.lastDoneAction = lastDoneAction;
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
