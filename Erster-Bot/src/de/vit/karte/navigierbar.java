package de.vit.karte;

import de.vit.logik.Rundeninformationen;

public interface navigierbar {
	public int getLevel();
	public int[] getAktuellePosition();
	public void feldHinzufuegen(int x, int y, String typ);
	public void aktualisierePosition(String lastActionsResult);
	public void aktualisiereKarte(Rundeninformationen rundeninformation);
	//Ihr m�sst gleichzeitig 5 Zellen setzen. �berpr�fen, ob die Zellen Neben sind oder nicht. 
	//Wenn ihr ein neues Objekt anlegt, dann m�sst ihr das, was ihr an CellStatus habt nur anlegen, wenn A. sich eure Position ver�ndert hat, also ok
	//und b den cellstatus m�sst ihr slicen! und dann in einzelne Attribute umwandeln
	//super().get irgendwas hilft euch dabei, wenn ihr in den einzelnen Feldern Sachen hinzuf�gt. Eure Positionsidee ist erstmal sehr gut! 
}
