package de.vit.karte;

public class Koordinate {
	//x bestimmt die horizontale Position, y die verikale Position
	int x;
	int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public void setKoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	public Koordinate () {}
	
	public Koordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

}
