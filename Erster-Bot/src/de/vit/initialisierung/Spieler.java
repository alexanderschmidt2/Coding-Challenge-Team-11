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
	private Spielfeld aktuelle_karte;
	private Koordinaten koordinaten;
	private Zelle aktuelle_zelle; //TODO: aktuelle Zellenkoordinaten stimmen mit den Spielerkoordinaten überein


	public Koordinaten getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(Koordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}

	public Zelle getAktuelleZelle() {
		return aktuelle_zelle;
	}

	public void setAktuelleZelle(Zelle zelle) {
		this.aktuelle_zelle = zelle;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Spieler(int playerId, Koordinaten koordinaten, Spielfeld spielfeld) {
		this.playerId = playerId;
		this.koordinaten = koordinaten;
		this.aktuelle_karte = spielfeld;
	}
	public String aktion(Zelle aktuelle_zelle,Spielfeld aktuelle_karte) {//Das, was sich aktualisiert hat bzw. auf dem aktuellen Stand bleiben muss //TODO: prüfen, ob wir wirklich 																														//Spielfeld brauchen
		this.aktuelle_zelle = aktuelle_zelle;
		this.aktuelle_karte = aktuelle_karte;
		String bewegung = Bewegungslogik.bewegung(this, aktuelle_karte); //Achtung, da wir auch hier dem spieler die Aktuellste uns bekannte Kartenversion zuordnen würde this reichen
		return bewegung;
	};
	

}
