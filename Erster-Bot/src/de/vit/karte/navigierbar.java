package de.vit.karte;

import de.vit.karte.felder.Feld;
import de.vit.logik.Rundeninformationen;

public interface navigierbar {
	public int getLevel();

	public int getPlayerId();
	

	public int getFormCount();

	public Feld getFeld(int x, int y);
	
	public int[] getSize();
	
	public Feld[] getNachbarn(int x, int y);

	public int[] getNorden(int x, int y);
	public int[] getOsten(int x, int y);
	public int[] getSueden(int x, int y);
	public int[] getWesten(int x, int y);

	// Ihr müsst gleichzeitig 5 Zellen setzen. Überprüfen, ob die Zellen Neben sind
	// oder nicht.
	// Wenn ihr ein neues Objekt anlegt, dann müsst ihr das, was ihr an CellStatus
	// habt nur anlegen, wenn A. sich eure Position verändert hat, also ok
	// und b den cellstatus müsst ihr slicen! und dann in einzelne Attribute
	// umwandeln
	// super().get irgendwas hilft euch dabei, wenn ihr in den einzelnen Feldern
	// Sachen hinzufügt. Eure Positionsidee ist erstmal sehr gut!

}
