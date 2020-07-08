package de.vit.karte;

public interface navigierbar {
	public void setzeNeuesKartenfeld();
	public int getEntfernung();
	public int setEntfernung();
	public Koordinate getStart();
	public int setKartenfeld(Koordinate koordinaten);
	public int getKartenfeld(Koordinate koordinaten);
}
