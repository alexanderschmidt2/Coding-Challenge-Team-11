package de.vit.botmain;
import java.util.*; 
//Wir benötige eine Positionslogik die sich merkt, wo wir sind was unsere Zellen angeht
public class Positionslogik {
	//in einer Liste wird eine eindeutige Koordinate einer Position zugeordnet in Form einer Map
	private List<Map<Koordinaten, Position>> hauptspeicher;

	public List<Map<Koordinaten, Position>> getHauptspeicher() {
		return hauptspeicher;
	}

	public void setHauptspeicher(List<Map<Koordinaten, Position>> hauptspeicher) {
		this.hauptspeicher = hauptspeicher;
	}
}
