package de.vit.karte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.vit.karte.felder.*;
import de.vit.logik.*;

/**
 * 
 * @author Laura Klasse, die das Spielfeld und die aktuelle Position in Form von
 *         Koordinaten beinhaltet
 */
public class Karte implements navigierbar{
	private int sizeX;
	private int sizeY;
	private final int level;
	private final int playerId;
	private int formCount;
	/**
	 * das eigentliche Spielfeld mit allen Feldern die erste Array-Ebene bezeichnet
	 * die x-Achse die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[][] karte;
	// die momentane Position, wird regelmaessig aktualisiert
	private int[] aktuellePosition = new int[2];

	// Getter und Setter
	public int[] getSize() {
		int[] size = new int[] { sizeX, sizeY };
		return size;
	}
	
	/**
	 * diese Methode sucht ein Feld mittels bekannter Koordinaten
	 * @param x Koordinate
	 * @param y Koordinate
	 * @return das gesuchte Feld
	 */
	public Feld getFeld(int x, int y) {
		return this.karte[x][y];
	}
	
	/**
	 * diese Methode sucht die Koordinaten ein Feld mittels
	 * bekanntem Namen und evtl. Ids (aus dem Feldstatus)
	 * @param feldtyp soll den kompletten String, den die getCellStatus-Methoden ausgeben, entegegennehmen
	 * Ausnahme: wenn Dokument gekickt wurde, dann übergeben wir eine geslicten String, ohne andere Bots (ohne !)
	 * @return Achtung! Gibt -1|-1 zurück, wenn Feld nicht gefunden wurde!! TODO Exception
	 */
	public int[] getFeld(String feldtyp) {
		int [] koordinaten = new int[2];
		int koordinateX = -1;
		int koordinateY = -1;
		for (int j= 0; j<sizeY; j++) {
			for (int i= 0; i<sizeX; i++) {	
				if (feldtyp.contains(this.karte[i][j].getName())) {
					koordinateX=i;
					koordinateY=j;
					break;
				}
			}
		}
		koordinaten[0] = koordinateX;
		koordinaten[1] = koordinateY;
		return koordinaten;
	}

	public int[] getAktuellePosition() {
		return aktuellePosition;
	}

	public void setAktuellePosition(int x, int y) {
		this.aktuellePosition[0] = x;
		this.aktuellePosition[1] = y;
	}

	public int getLevel() {
		return level;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public int getFormCount()
	{
		return formCount;
	}
	
	/**
	 * wird nur verwendet, wenn wir ein Formular entdecken und die FormularNr groeßer ist als der bisher gespeicherte formCount
	 * sobald Sachbearbeiter gefunden wurde, wird maximaler Wert gesetzt und Methode wird nicht mehr benoetigt
	 * @param formCount
	 */
	public void setFormCount(int formCount)
	{
		if (this.formCount < formCount)
		{
			this.formCount = formCount;
		}
	}

	// TODO grafische ausgabe der Karte als String
	// mit Art entscheidet man, ob man die Entfernungen oder die Inhalte der Zellen
	// sehen moechte
	public String getKarte() {
		String karte = "";
		int i = 0;
		for (int y=0; y<sizeY; y++) {
			//hier wird die x-Achsenbeschriftung generiert
			while (i < sizeX)
			{
				if (i==0)
					karte = karte.concat(" |" + i + " ");	
				else if (i<10)
				{
					karte = karte.concat(" |" + i + " ");
				}
				else
				{
					karte = karte.concat("|" + i + " ");
				}
				i++;
			}
			karte = karte.concat("\n");
			karte = karte.concat("" + y + "|");
			
			//hier werden die Werte eingetragen...
			for (int x=0; x<sizeX; x++) {
				if (this.karte[x] [y].getName().equals("FLOOR"))
					karte = karte.concat("   ");
				else if (this.karte[x] [y].getName().equals("WALL"))
					karte = karte.concat(" X ");
				else if (this.karte[x] [y].getName().contains("FINISH"))
					karte = karte.concat(" S ");
				else if (this.karte[x] [y].getName().contains("FORM"))
					karte = karte.concat(" F ");
				else
					karte = karte.concat(" ~ ");
				if (x!=sizeX-1)
					karte = karte.concat("|");
			}
			karte = karte.concat("\n");
			
			//hier werden die Zwischenzeilen zur Struktur generiert
			for (int x=0; x<=sizeX; x++) {
				if (y!=sizeY-1)
				{
					if (x==0)
						karte = karte.concat("-+");
					else if (x==sizeX) {
						karte = karte.concat("---");
					}
					else {
						karte = karte.concat("---+");
					}
				}	
			}
		}
		return karte;
	}

	/**
	 * Dieser Konstruktor erstellt das Spielfeld (Karte) entsprechend der Uebergebenen Groesse des Spielfelds und
	 * Startposition des Bots
	 * 
	 * @param sizeX  Groesse des Spielfelds in der horizontalen
	 * @param sizeY  Groesse des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.level = level;
		this.playerId = playerId;
		this.aktuellePosition[0] = startX;
		this.aktuellePosition[1] = startY;
		this.karte = new Feld[sizeX][sizeY];
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				karte[x][y] = new Nebel();
			}
		}
	}


	/**
	 * Die Methode entfernungenAktualisieren wird jede Runde aufgerufen um die Entfernungen der bereits bekannten Felder/Zellen
	 * in der Karte anzupassen. So entsteht eine Entfernungskarte, welche unserem Bot die Entscheidungsfähigkeit gibt
	 * den schnellsten/kuerzesten Weg innerhalb eines bekannten Abschnittes zu gehen.
	 * 
	 */
	public void entfernungenAktualisieren() {
		
		//Hier wird die Entfernung des aktuellen Feldes, worauf der Bot steht auf Entfernung = 0 gesetzt.
		this.getFeld(this.aktuellePosition[0],this.aktuellePosition[1]).setEntfernung(0);

		boolean aenderung; 
		
		do {
			aenderung = false;
			for (int x = 0; x < sizeX; x++) {
				for (int y = 0; y < sizeY; y++) {
					//Wir koennen nur an den Feldern die Entfernungen aktualisieren, welche wir auch exploriert haben, oder welche keine Wand darstellen
					if (!karte[x][y].getName().equals("FOG") || karte[x][y].getName().equals("WALL")) { 
						// Hier werden alle Entfernungen abgecheckt:
						int entfernungImNorden = this.getNachbarn(x,y)[0].getEntfernung();
						int entfernungImOsten = this.getNachbarn(x,y)[1].getEntfernung();
						int entfernungImSueden = this.getNachbarn(x,y)[2].getEntfernung();
						int entfernungImWester = this.getNachbarn(x,y)[3].getEntfernung();
						//Die ArrayList von Integer Werten dient nur dem Zweck der min() Methode der Collection, um die kleinste Entfernung dieser 4 Werte zu bekommen
						List<Integer> list = new ArrayList<>();
						
						list.add(entfernungImNorden); 
				        list.add(entfernungImOsten); 
				        list.add(entfernungImSueden); 
				        list.add(entfernungImWester);
				        
				        int minEntfernungEinesNachbarn = Collections.min(list);
						
						int entfernungBeiMir = karte[x][y].getEntfernung();
						
						if (minEntfernungEinesNachbarn + 1 < entfernungBeiMir) {
							entfernungBeiMir = minEntfernungEinesNachbarn + 1;
							aenderung = true;
						}
					}
					
				}
			}
		} while (aenderung == true);

	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld fuellt
	 */
	public void feldHinzufuegen(int x, int y, String info) {
		if (info.contains("FLOOR"))
				{
					this.karte[x][y] = new Boden();
				}
		else if (info.contains("WALL"))
		{
			this.karte[x][y] = new Wand();
		}
		else if (info.contains("FINISH"))
		{
			this.karte[x][y] = new Sachbearbeiter(info);
		}
		else //es muss ein Formular sein
		{
			this.karte[x][y] = new Dokument(info);
		}
		// TODO: Karte mit neuen Instanzen fuellen
		// geht schwerlich, da man keinen neuen SB anlegen kann, da die levelId noch
		// nicht existiert
		// SB anlegen geht nur in MainMethode
	}

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * 
	 * @param lastActionsResult ist der Ausgabestring der LastActionResult
	 */
	public void aktualisierePosition(String lastActionsResult) {

		switch (lastActionsResult) {
		case "OK NORTH":
			this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] - 1) + sizeY) % sizeY);
			break;
		case "OK EAST":
			this.setAktuellePosition(((aktuellePosition[0] + 1) + sizeX) % sizeX, aktuellePosition[1]);
			break;
		case "OK SOUTH":
			this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] + 1) + sizeY) % sizeY);
			break;
		case "OK WEST":
			this.setAktuellePosition(((aktuellePosition[0] - 1) + sizeX) % sizeX, aktuellePosition[1]);
			break;
		}
	}

	/**
	 * Methode, die den Feldstatus im Norden überprüft und aktualisert, wenn das Feld unbekannt (Nebel) ist,
	 * wenn bereits ein Feld existiert, wird zunächst ueberprueft, ob es mit dem angezeigten uebereinstimmt,
	 * wenn nicht (falls ein Dokument gekickt wurde), aktualisiert es das Feld
	 * @param rundeninformation
	 */
	public void aktualisereNorden(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...Uebergebe diese der Methode getNorden(), um die x und y Koordinate des
		// noerdlichen Felds zu erhalten
		// Die x-Koordinate wird mit getNorden()[0] ermittelt und getFeld() Uebergeben;
		// y-Koordinate mit getNorden()[1]
		int nordX = this.getNorden(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int nordY = this.getNorden(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = getFeld(nordX, nordY).getName();
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn noerdlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist oder das bereits angelegte Feld dem aktuellen entspricht, gehen wir in die if-Verzweigung
		String info = rundeninformation.getNorthCellStatus();
		if (name.equals("NEBEL") || !info.contains(name)) {
			//falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt wurde, aber weggekickt wurde,
			//wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe Dokument gespeichert hat
			if (info.contains("FORM") && !(this.getFeld(info.substring(0,8))[0]==-1))
			{
				//altes Formular finden
				int [] form = this.getFeld(info.substring(0,8));
				//altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf SB liegen kann)
				this.feldHinzufuegen(form[0], form[1], "FLOOR");				
			}
			this.feldHinzufuegen(nordX, nordY, info);
		}
	}

	public void aktualisereOsten(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...uebergebe diese der Methode getOsten(), um die x und y Koordinate des
		// oestlichen Felds zu erhalten
		// Die x-Koordinate wird mit getOsten()[0] ermittelt und getFeld() uebergeben;
		// y-Koordinate mit getOsten()[1]
		int ostX = this.getOsten(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int ostY = this.getOsten(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(ostX, ostY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn oestlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		String info = rundeninformation.getEastCellStatus();
		if (name.equals("NEBEL") || !info.contains(name)) {
			//falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt wurde, aber weggekickt wurde,
			//wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe Dokument gespeichert hat
			if (info.contains("FORM") && !(this.getFeld(info.substring(0,8))[0]==-1))
			{
				//altes Formular finden
				int [] form = this.getFeld(info.substring(0,8));
				//altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf SB liegen kann)
				this.feldHinzufuegen(form[0], form[1], "FLOOR");				
			}
			this.feldHinzufuegen(ostX, ostY, info);
		}
	}

	public void aktualisereSueden(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...uebergebe diese der Methode getSueden(), um die x und y Koordinate des
		// suedlichen Felds zu erhalten
		// Die x-Koordinate wird mit getSueden()[0] ermittelt und getFeld() uebergeben;
		// y-Koordinate mit getSueden()[1]
		int suedX = this.getSueden(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int suedY = this.getSueden(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(suedX, suedY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn suedlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		String info = rundeninformation.getSouthCellStatus();
		if (name.equals("NEBEL") || !info.contains(name)) {
			//falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt wurde, aber weggekickt wurde,
			//wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe Dokument gespeichert hat
			if (info.contains("FORM") && !(this.getFeld(info.substring(0,8))[0]==-1))
			{
				//altes Formular finden
				int [] form = this.getFeld(info.substring(0,8));
				//altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf SB liegen kann)
				this.feldHinzufuegen(form[0], form[1], "FLOOR");				
			}
			this.feldHinzufuegen(suedX, suedY, info);
		}
	}

	public void aktualisereWesten(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...uebergebe diese der Methode getWesten(), um die x und y Koordinate des
		// westlichen Felds zu erhalten
		// Die x-Koordinate wird mit getWesten()[0] ermittelt und getFeld() uebergeben;
		// y-Koordinate mit getWesten()[1]
		int westX = this.getWesten(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int westY = this.getWesten(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(westX, westY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn westlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		String info = rundeninformation.getWestCellStatus();
		if (name.equals("NEBEL") || !info.contains(name)) {
			//falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt wurde, aber weggekickt wurde,
			//wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe Dokument gespeichert hat
			if (info.contains("FORM") && !(this.getFeld(info.substring(0,8))[0]==-1))
			{
				//altes Formular finden
				int [] form = this.getFeld(info.substring(0,8));
				//altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf SB liegen kann)
				this.feldHinzufuegen(form[0], form[1], "FLOOR");				
			}
			this.feldHinzufuegen(westX, westY, info);
		}
	}

	/**
	 * Methode, die ueber weitere Methodenaufrufe die umliegenden Felder in der Karte vermerkt
	 * @param rundeninformation hierueber können die einzelnen Feldstatus abgerufen werden
	 */
	public void aktualisiereKarte(Rundeninformationen rundeninformation) {
		this.aktualisereNorden(rundeninformation);
		this.aktualisereOsten(rundeninformation);
		this.aktualisereSueden(rundeninformation);
		this.aktualisereWesten(rundeninformation);
	}

	/**
	 * Methode, die abhaengig vom aktuellen Standpunkt die Koordinaten des im Norden
	 * angrenzenden Objekts zurueckgibt
	 * 
	 * @return Koordinaten des Objekts noerdlich vom aktuellen Standpunkt, welches
	 *         eine Spezialisierung des Typs Feld ist
	 */
	public int[] getNorden(int x, int y) {
		int[] nord = new int[2];
		nord[0] = x;
		nord[1] = ((y - 1) + this.getSize()[1]) % this.getSize()[1];
		return nord;
	}

	/**
	 * s. Norden, nur mit Osten...
	 * 
	 * @return
	 */
	public int[] getOsten(int x, int y) {
		int[] ost = new int[2];
		ost[0] = ((x + 1) + this.getSize()[0]) % this.getSize()[0];
		ost[1] = y;
		return ost;
	}

	/**
	 * s. Norden, nur mit Sueden...
	 * 
	 * @return
	 */
	public int[] getSueden(int x, int y) {
		int[] sued = new int[2];
		sued[0] = x;
		sued[1] = ((y + 1) + this.getSize()[1]) % this.getSize()[1];
		return sued;
	}

	/**
	 * s. Norden, nur mit Westen...
	 * 
	 * @return
	 */
	public int[] getWesten(int x, int y) {
		int[] west = new int[2];
		west[0] = ((x - 1) + this.getSize()[0]) % this.getSize()[0];
		west[1] = y;
		return west;
	}

	/**
	 * Methode, die ein Array von vier Feldern zurueckgibt, welche den Feldern
	 * entsprechen, die an die aktuelle Position angrenzen die Reihenfolge der
	 * Objekte: Nord, Ost, Sued, West die Methode ruft die Methoden getNorden(),
	 * getOsten(), getSueden(), getWesten() auf
	 * 
	 * @param karte
	 * @return
	 */
	public Feld[] getNachbarn(int x, int y) {
		Feld[] nachbarn = new Feld[4];
		nachbarn[0] = getFeld(this.getNorden(x, y)[0], this.getNorden(x, y)[1]);
		nachbarn[1] = getFeld(this.getOsten(x, y)[0], this.getOsten(x, y)[1]);
		nachbarn[2] = getFeld(this.getSueden(x, y)[0], this.getSueden(x, y)[1]);
		nachbarn[3] = getFeld(this.getWesten(x, y)[0], this.getWesten(x, y)[1]);
		return nachbarn;
	}

}
