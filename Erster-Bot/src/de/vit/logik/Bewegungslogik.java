package de.vit.logik;
import java.util.*;

import de.vit.initialisierung.Spieler;
import de.vit.initialisierung.Spielfeld;
import de.vit.typen.Koordinaten;
import de.vit.typen.Zelle;

public abstract class Bewegungslogik{
	//TODO: die Indexberechnung, Koordinatenberechnung gehört in Positionslogik oder so, aber übersichtshalber mal hier
	public static int zellenindexBerechnen(Koordinaten zellen_koordinaten, Koordinaten groeße_aktuelle_karte) {//An welcher Stelle soll die Zelle rein?
		return (groeße_aktuelle_karte.getX()*zellen_koordinaten.getY())+zellen_koordinaten.getX()+1; //hier wird ein int Wert zurückgegeben //FIXME bitte hier debuggen!!!!!! Stimmt die Indexberechnung?
	}
	public static Koordinaten koordinatenBerechnen(String neue_richtung, Koordinaten alte_koordinaten) {//TODO: Merten fragen, wie man genau 
		switch(neue_richtung) {																			
			case("OK NORTH"):
					return new Koordinaten(alte_koordinaten.getX(), alte_koordinaten.getY()-1);
			case("OK EAST"):
				return new Koordinaten(alte_koordinaten.getX()+1, alte_koordinaten.getY());
			case("OK SOUTH"):
				return new Koordinaten(alte_koordinaten.getX(), alte_koordinaten.getY()+1);
			default: //westen ist default case
				return new Koordinaten(alte_koordinaten.getX()-1, alte_koordinaten.getY());
		}
	};
	public static String bewegung(Spieler spieler, Spielfeld aktuelle_karte) {

		String[] kompass = { "go north", "go east", "go south", "go west" };
		Stack<Integer> verlauf = new Stack<>();
		if (spieler.getAktuelleZelle().getCurrentCellStatus().equals("FINISH " + spieler.getPlayerId() + " 0")) {
			return "finish";
		}
		//FIXME: LOGIKFEHLER,hier muss noch implementiert werden, anhand von "visited" der Klasse Zelle, dass wir auf keinen Fall 2x zurückgehen dürfen
		//TODO: Die Buchhaltung mit den Koordinaten muss erst nach der Aktion der letzen Runde pasieren
		
		
		if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK NORTH") && aktuelle_karte.getZuletztGesetzteZelle().getVisited() >= 1) {//Wenn wir eine Sache schon 2x gevisited haben, dann soll die nicht auf den Stack drauf
			spieler.getAktuelleZelle().setKoordinaten(koordinatenBerechnen(spieler.getAktuelleZelle().getLastActionsResult(), aktuelle_karte.getZuletztGesetzteZelle().getKoordinaten())); 
			aktuelle_karte.setzeNeueZelle(zellenindexBerechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte.getGroeße()), spieler.getAktuelleZelle());
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK EAST") && aktuelle_karte.getZuletztGesetzteZelle().getVisited() >= 1) {
			verlauf.push(1);
			spieler.getAktuelleZelle().setKoordinaten(koordinatenBerechnen(spieler.getAktuelleZelle().getLastActionsResult(), aktuelle_karte.getZuletztGesetzteZelle().getKoordinaten())); //Die Koordinaten der neuen Zelle berechnen
			aktuelle_karte.setzeNeueZelle(zellenindexBerechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte.getGroeße()), spieler.getAktuelleZelle());
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK SOUTH") && aktuelle_karte.getZuletztGesetzteZelle().getVisited() >= 1) {
			verlauf.push(2); 
			spieler.getAktuelleZelle().setKoordinaten(koordinatenBerechnen(spieler.getAktuelleZelle().getLastActionsResult(), aktuelle_karte.getZuletztGesetzteZelle().getKoordinaten())); //Die Koordinaten der neuen Zelle berechnen
			aktuelle_karte.setzeNeueZelle(zellenindexBerechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte.getGroeße()), spieler.getAktuelleZelle());	
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK WEST") && aktuelle_karte.getZuletztGesetzteZelle().getVisited() >= 1) {
			verlauf.push(3);
			spieler.getAktuelleZelle().setKoordinaten(koordinatenBerechnen(spieler.getAktuelleZelle().getLastActionsResult(), aktuelle_karte.getZuletztGesetzteZelle().getKoordinaten())); //Die Koordinaten der neuen Zelle berechnen
			aktuelle_karte.setzeNeueZelle(zellenindexBerechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte.getGroeße()), spieler.getAktuelleZelle());		
		} else if (spieler.getAktuelleZelle().getLastActionsResult().equals("OK")) {
			verlauf.push(5);//Der erste Schritt: 
			spieler.getAktuelleZelle().setKoordinaten(spieler.getKoordinaten());	//Spielerkoordinaten den aktuellen Zellenkoordinaten zuordnen
			aktuelle_karte.setzeNeueZelle(zellenindexBerechnen(spieler.getAktuelleZelle().getKoordinaten(), aktuelle_karte.getGroeße()), spieler.getAktuelleZelle());} //Aktuelle Zelle in unsere MAP einfüegn 
			//noch ein Visited für die aktielle Zelle dazurechnen

		if (!spieler.getAktuelleZelle().getNorthCellStatus().equals("WALL") && verlauf.peek() != 2) {		
			return "go north";
		} else if (!spieler.getAktuelleZelle().getEastCellStatus().equals("WALL") && verlauf.peek() != 3 ){
			return "go east";
		} else if (!spieler.getAktuelleZelle().getSouthCellStatus().equals("WALL") && verlauf.peek() != 0) {
			return "go south";
		} else if (!spieler.getAktuelleZelle().getWestCellStatus().equals("WALL") && verlauf.peek() != 1) {
			return "go west";
		} else {
			aktuelle_karte.getZuletztGesetzteZelle().setVisited(1);
			return kompass[(verlauf.pop() + 2) % 4]; // Wir sind in einer Sackgasse und wir müssen zurück, //TODO: Sichergehen, das ein und das selbe Element nicht auf den Stack 
																										 
		}
	}
}
