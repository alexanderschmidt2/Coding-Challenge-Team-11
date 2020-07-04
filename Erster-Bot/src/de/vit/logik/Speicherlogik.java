package de.vit.logik;
import java.util.*;

import de.vit.typen.Koordinaten;
import de.vit.typen.Zelle; 
//Wir benötige eine Positionslogik die sich merkt, wo wir sind was unsere Zellen angeht
public class Speicherlogik{
	
	//in einer Liste wird eine eindeutige Koordinate einer Position zugeordnet in Form einer Map
	
	private HashMap<Koordinaten, Zelle> karte; //Wenn eine Zelle in der Karte ist, dann muss zelle.getVisited() >= 1 sein!
	
	public HashMap<Koordinaten, Zelle> getKarte() {
		return karte;
	}
	
	public void setKarte(HashMap<Koordinaten, Zelle> karte) {
		this.karte = karte;
	}
	
}
