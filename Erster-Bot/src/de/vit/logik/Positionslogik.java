package de.vit.logik;
import java.util.*;

import de.vit.typen.Koordinaten;
import de.vit.typen.Position; 
//Wir ben�tige eine Positionslogik die sich merkt, wo wir sind was unsere Zellen angeht
public class Positionslogik {
	//in einer Liste wird eine eindeutige Koordinate einer Position zugeordnet in Form einer Map
	private List<Map<Koordinaten, Position>> hauptspeicher;

	public List<Map<Koordinaten, Position>> getHauptspeicher() {
		return hauptspeicher;
	}
	protected Stack<String> mainstack;

	public void setHauptspeicher(List<Map<Koordinaten, Position>> hauptspeicher) {
		this.hauptspeicher = hauptspeicher;
	}
}
