package de.vit.karte.felder;

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
