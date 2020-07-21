package de.vit.logik;

/**
 * Diese Klasse speichert die Informationen der Runde in einem Objekt und
 * aktualisiert diese jede Runde. Warum werden hier keine Objekte hinterlegt?
 * Grund für den Garbage Kollektor, was ist performanter ein Objektvergleich
 * oder ein Stringvergleich?
 * 
 * @author Alexander Schmidt und Franz Bogmann
 *
 */
public class Rundeninformationen {

	String lastActionsResult;
	String currentCellStatus;
	String northCellStatus;
	String eastCellStatus;
	String southCellStatus;
	String westCellStatus;
	String lastDoneAction = "";

	// eigentlich überflüssig, da zunächst ein leeres Objekt angelegt werden soll,
	// dessen Attribute später durch Setter in der Schleife initialisiert werden
	// sollen
	// Wozu brauchen wir diesen Konstruktor?
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

	public Rundeninformationen() {
	}

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
	 * Diese Methode soll in jeder Runde e alle Attribute der Klasse außer
	 * lastDoneAction aktualisieren
	 * 
	 * @param lastActionResult  Das Ergebnis der Aktion in der letzten Runde
	 * @param currentCellStatus Das Feld, worauf der Bot steht
	 * @param northCellStatus   Das Feld im Norden des Bots
	 * @param eastCellStatus    Das Feld im Osten des Bots
	 * @param southCellStatus   Das Feld im Sueden des Bots
	 * @param westCellStatus    Das Feld im Westen des Bots
	 */
	public void setInputs(String lastActionsResult, String currentCellStatus, String northCellStatus,
			String eastCellStatus, String southCellStatus, String westCellStatus) {

		this.lastActionsResult = lastActionsResult;
		this.currentCellStatus = currentCellStatus;
		this.northCellStatus = northCellStatus;
		this.eastCellStatus = eastCellStatus;
		this.southCellStatus = southCellStatus;
		this.westCellStatus = westCellStatus;
	}
}
