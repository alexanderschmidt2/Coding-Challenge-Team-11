package de.vit.logik;
import java.util.*;

import de.vit.initialisierung.Spieler;
import de.vit.initialisierung.Spielfeld;
import de.vit.typen.Koordinaten;
import de.vit.typen.Zelle;

public abstract class Bewegungslogik {
	//TODO: die Indexberechnung gehört in Positionslogik oder so, aber übersichtshalber mal hier
	public static int zellenindex_berechnen(Koordinaten koordinaten, Spielfeld aktuelle_karte) {
		return (aktuelle_karte.getGröße().getX()*koordinaten.getY())+koordinaten.getX()+1;
	}
	
	
	
	
	public static String bewegung(Spieler spieler, Spielfeld aktuelle_karte) {

		String[] kompass = { "go north", "go east", "go south", "go west" };
		Stack<Integer> verlauf = new Stack<>();
		if (spieler.getAktuelleZelle().getCurrentCellStatus().equals("FINISH " + spieler.getPlayerId() + " 0")) {
			return "finish";
		}
		//FIXME: LOGIKFEHLER,hier muss noch implementiert werden, anhand von "visited" der Klasse Zelle, dass wir auf keinen Fall 2x zurückgehen dürfen
		//TODO: Die Buchhaltung mit den Koordinaten muss erst nach der Aktion der letzen Runde pasieren
		
		
		if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK NORTH") && spieler.getAktuelleZelle().getVisited() < 2) {
			verlauf.push(0); 
			//hier muss genau das passsiere, was auch im ersten Schritt passiert, nur mit entsprechenden Veränderungen im Zellenindex und Visited etc. 
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK EAST") && spieler.getAktuelleZelle().getVisited() < 2) {
			verlauf.push(1);
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK SOUTH") && spieler.getAktuelleZelle().getVisited() < 2) {
			verlauf.push(2); 
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK WEST") && spieler.getAktuelleZelle().getVisited() < 2) {
			verlauf.push(3);
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK")) {//Der erste Schritt: 
			spieler.getAktuelleZelle().setKoordinaten(spieler.getKoordinaten());	//Spielerkoordinaten den aktuellen Zellenkoordinaten zuordnen
			aktuelle_karte.setze_neue_Zelle(zellenindex_berechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte), spieler.getAktuelleZelle());} //Aktuelle Zelle in unsere MAP einfüegn 
			//noch ein Visited für die aktielle Zelle dazurechnen

		if (!spieler.getAktuelleZelle().getNorthCellStatus().equals("WALL") && verlauf.peek() != 2) {		
			return "go north";
		} else if (!spieler.getAktuelleZelle().getEastCellStatus().equals("WALL") && verlauf.peek() != 3) {
			return "go east";
		} else if (!spieler.getAktuelleZelle().getSouthCellStatus().equals("WALL") && verlauf.peek() != 0) {
			return "go south";
		} else if (!spieler.getAktuelleZelle().getWestCellStatus().equals("WALL") && verlauf.peek() != 1) {
			return "go west";
		
		} else {
			return kompass[verlauf.pop() + 2 % 4]; // Wir sind in einer Sackgasse und wir müssen zurück, //TODO: Sichergehen, das ein und das selbe Element nicht auf den Stack 
																										 //raufkommt. Wir dürfen nicht 2x zurückgehen, denn dann stimmt was nicht!
		}
	}
}
