package de.vit.karte;

public interface navigierbar {
	public void setzeNeuesKartenfeld();
	public int getEntfernung();
	public int setEntfernung();
	public Koordinaten getStart();
	public int setKartenfeld(Koordinaten koordinaten);
	public int getKartenfeld(Koordinaten koordinaten);
}
