package de.vit.typen;
//Wir benötigen eine Klasse für unsere Koordinaten. Hier können wir deutlich mehr auf Fehler prüfen, als in einem Array oder so, außerdem können wir so die Zuordnungen besser mappen.
//TODO: hier muss eine dicke Prüflogik mit gaaaaaaaaanz vielen Exceptions hin. Bitte merken, die Dozenten sind böse
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
