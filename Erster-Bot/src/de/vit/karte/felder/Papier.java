package de.vit.karte.felder;

/**
 * Klasse, deren Instanzen ein Feld verdeckt von einem Papier in der Karte darstellen
 * @author Laura
 * @author Constantin
 */
public class Papier extends Feld{
	private boolean gekickt = false;
	public Papier()
	{
		super();
		this.name = "SHEET";
	}
	public boolean isGekickt() {
		return gekickt;
	}
	public void setGekickt(boolean gekickt) {
		this.gekickt = gekickt;
	}
}
