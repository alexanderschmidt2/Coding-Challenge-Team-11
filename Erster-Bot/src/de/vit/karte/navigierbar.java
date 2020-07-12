package de.vit.karte;

import de.vit.karte.felder.Feld;

public interface navigierbar {
	public int getLevel();

	public int getPlayerId();
	

	public int getFormCount();

	public Feld getFeld(int[] position);
	
	public int[] getSize();
	
	public Feld[] getNachbarn(int[] position);

	public int[] getNorden(int[] position);
	public int[] getOsten(int[] position);
	public int[] getSueden(int[] position);
	public int[] getWesten(int[] position);

	// Ihr m�sst gleichzeitig 5 Zellen setzen. �berpr�fen, ob die Zellen Nebel sind
	// oder nicht.
	// Wenn ihr ein neues Objekt anlegt, dann m�sst ihr das, was ihr an CellStatus
	// habt nur anlegen, wenn A. sich eure Position ver�ndert hat, also ok
	// und b den cellstatus m�sst ihr slicen! und dann in einzelne Attribute
	// umwandeln
	// super().get irgendwas hilft euch dabei, wenn ihr in den einzelnen Feldern
	// Sachen hinzuf�gt. Eure Positionsidee ist erstmal sehr gut!

}
