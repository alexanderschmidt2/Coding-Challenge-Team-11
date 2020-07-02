package de.vit.initialisierung;

import de.vit.logik.Bewegungslogik;
import de.vit.typen.Koordinaten;
import de.vit.typen.Position;

public class Spieler extends Initialisierung {
	/*
	 * Diese Klasse soll die Initialen Informationen zum Spieler entgegennehmen
	 * 
	 */
	private final int playerId;
	private final Spielfeld spielfeld;
	private Koordinaten koordinaten;
	private Position position;

	public Koordinaten getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(Koordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Spieler(int playerId, Koordinaten koordinaten, Spielfeld spielfeld) {
		this.playerId = playerId;
		this.koordinaten = koordinaten;
		this.spielfeld = spielfeld;
	}

	public String aktion(Position position) {
		this.position = position;
		String bewegung = Bewegungslogik.bewegung(this.position, this.koordinaten, this.getPlayerId());
		return bewegung;
	};
	

}
