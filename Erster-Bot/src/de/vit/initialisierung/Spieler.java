package de.vit.initialisierung;

import de.vit.logik.Bewegungslogik;
import de.vit.logik.Speicherlogik;
import de.vit.typen.Koordinaten;
import de.vit.typen.Zelle;

public class Spieler extends Initialisierung {
	/*
	 * Diese Klasse soll die Initialen Informationen zum Spieler entgegennehmen
	 * 
	 */
	private final int playerId;
	private final Spielfeld spielfeld;
	private Koordinaten koordinaten;
	private Zelle zelle;
	private Speicherlogik speicher;

	public Koordinaten getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(Koordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}

	public Zelle getPosition() {
		return zelle;
	}

	public void setPosition(Zelle zelle) {
		this.zelle = zelle;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Spieler(int playerId, Koordinaten koordinaten, Spielfeld spielfeld) {
		this.playerId = playerId;
		this.koordinaten = koordinaten;
		this.spielfeld = spielfeld;
	}
	public String aktion(Zelle zelle,Speicherlogik speicher) {
		this.zelle = zelle;
		String bewegung = Bewegungslogik.bewegung(this.zelle, this.koordinaten, this.getPlayerId(), speicher);
		return bewegung;
	};
	

}
