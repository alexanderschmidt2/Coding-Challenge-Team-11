package de.vit.typen;
//Wir ben�tigen eine Klasse f�r unsere Koordinaten. Hier k�nnen wir deutlich mehr auf Fehler pr�fen, als in einem Array oder so, au�erdem k�nnen wir so die Zuordnungen besser mappen.
//TODO: hier muss eine dicke Pr�flogik mit gaaaaaaaaanz vielen Exceptions hin. Bitte merken, die Dozenten sind b�se
public class Koordinaten {
	int X;
	int Y;
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public Koordinaten(int x, int y) {
		X = x;
		Y = y;
	}
	public void laufen(int wertX, int wertY) {
		this.X = this.X + wertX;
		this.Y = this.Y + wertY;
	}
}
