package de.vit.logik;
/**
 * 
 * @author Alex
 * @Klasse speichert die Informationen der Runde in einem Objekt und aktualisiert diese jede Runde
 */
public class Rundeninformationen {
	
	String lastActionResult;
	String currentCellStatus;
	String northCellStatus;
	String eastCellStatus;
	String southCellStatus;
	String westCellStatus;
	String lastDoneAction;

	public String getLastActionResult() {
		return lastActionResult;
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

	public void setLastActionResult(String lastActionResult) {
		this.lastActionResult = lastActionResult;
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
	 * soll in jeder Runde alle Attribute der Klasse außer lastDoneAction aktualisieren
	 * @param lastActionResult
	 * @param currentCellStatus
	 * @param northCellStatus
	 * @param eastCellStatus
	 * @param southCellStatus
	 * @param westCellStatus
	 */
	public void setInputs(String lastActionResult, String currentCellStatus, String northCellStatus,
			String eastCellStatus, String southCellStatus, String westCellStatus)
	{
		this.lastActionResult = lastActionResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}
	
	//eigentlich überflüssig, da zunächst ein leeres Objekt angelegt werden soll,
	//dessen Attribute später durch Setter in der Schleife initialisiert werden sollen
	public Rundeninformationen(String lastActionResult, String currentCellStatus, String northCellStatus,
			String eastCellStatus, String southCellStatus, String westCellStatus) {
		super();
		this.lastActionResult = lastActionResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}
	
	public Rundeninformationen()
	{}
	

}
