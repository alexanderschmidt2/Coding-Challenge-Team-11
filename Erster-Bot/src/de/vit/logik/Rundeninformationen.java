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
	String lastDoneAction = "";

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
	
	/**
	 * soll in jeder Runde alle Attribute der Klasse au�er lastDoneAction aktualisieren
	 * @param lastActionResult
	 * @param currentCellStatus
	 * @param northCellStatus
	 * @param eastCellStatus
	 * @param southCellStatus
	 * @param westCellStatus
	 */
	public void setInputs(String lastActionsResult, String currentCellStatus, String northCellStatus,
			String eastCellStatus, String southCellStatus, String westCellStatus)
	{
		this.lastActionsResult = lastActionsResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}
	
	//eigentlich �berfl�ssig, da zun�chst ein leeres Objekt angelegt werden soll,
	//dessen Attribute sp�ter durch Setter in der Schleife initialisiert werden sollen
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
	
	public Rundeninformationen()
	{}
	

}
