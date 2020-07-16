package de.vit.karte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import de.vit.karte.felder.*;
import de.vit.logik.*;

/**
 * 
 * @author Laura Klasse, die das Spielfeld und die aktuelle Position in Form von
 *         Koordinaten beinhaltet
 */
public class Karte implements navigierbar {
	private int[] size;
	private final int level;
	private final int playerId;
	private int formCount;
	private int[] dynamischesZiel;
	private final HashSet<int[]> statischeZiele;
	private int Spielphase = 0;
	public int getSpielphase() {
		return Spielphase;
	}

	public void setSpielphase(int spielphase) {
		Spielphase = spielphase;
	}

	//Wenn es zu viel wird eine Menge von Maps
	/**
	 * das eigentliche Spielfeld mit allen Feldern die erste Array-Ebene bezeichnet
	 * die x-Achse die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[][] karte;
	// die momentane Position, wird regelmaessig aktualisiert
	private int[] aktuellePosition = new int[2];

	// Getter und Setter
	public int[] getSize() {
		return size;
	}

	public int[] getDynamischesZiel() {
		return this.dynamischesZiel;
	}

	public void setDynamischesZiel(int[] koordinaten) {
		this.dynamischesZiel = koordinaten;
	}
	
	public HashSet<int[]> getStatischeZiele() {
		return statischeZiele;
	}

	/**
	 * diese Methode sucht ein Feld mittels bekannter Koordinaten
	 * 
	 * @param x Koordinate
	 * @param y Koordinate
	 * @return das gesuchte Feld
	 */
	public Feld getFeld(int[] position) {
		return this.karte[position[0]][position[1]];
	}

	/**
	 * diese Methode sucht die Koordinaten ein Feld mittels bekanntem Namen und
	 * evtl. Ids (aus dem Feldstatus)
	 * 
	 * @param feldtyp soll den kompletten String, den die getCellStatus-Methoden
	 *                ausgeben, entegegennehmen Ausnahme: wenn Dokument gekickt
	 *                wurde, dann übergeben wir eine geslicten String, ohne andere
	 *                Bots (ohne !)
	 * @return Achtung! Gibt -1|-1 zurück, wenn Feld nicht gefunden wurde!! TODO
	 *         Exception
	 */
	public int[] getFeld(String feldtyp) {
		int[] koordinaten = new int[] {-1, -1};
		for (int j = 0; j < this.getSize()[1]; j++) {
			for (int i = 0; i < this.getSize()[0]; i++) {
				if (feldtyp.contains(this.karte[i][j].getName())) {
					koordinaten[0] = i;
					koordinaten[1] = j;
					break;
				}
			}
		}
		return koordinaten;
	}

	public int[] getAktuellePosition() {
		return aktuellePosition;
	}

	/**
	 * da sich bei einer Bewegung nur eine koordinate aendert ist es sinnvoll der
	 * Methode anstatt eines Arrays von int direkt 2 int zu uebergeben. daher wird
	 * hier vom Standard abgewichen, wonach Methoden, die mit Koordinaten arbeiten
	 * immer ein Array mit 2 int uebergeben wird
	 * 
	 * @param x x-Koordinate der aktuellen Position
	 * @param y y-Koordinate der aktuellen Position
	 */
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

	public int getFormCount() {
		return formCount;
	}

	/**
	 * wird nur verwendet, wenn wir ein Formular entdecken und die FormularNr
	 * groeßer ist als der bisher gespeicherte formCount sobald Sachbearbeiter
	 * gefunden wurde, wird maximaler Wert gesetzt und Methode wird nicht mehr
	 * benoetigt
	 * 
	 * @param formCount
	 */
	public void setFormCount(int formCount) {
		if (this.formCount < formCount) {
			this.formCount = formCount;
		}
	}

	// TODO grafische ausgabe der Karte als String
	// mit Art entscheidet man, ob man die Entfernungen oder die Inhalte der Zellen
	// sehen moechte
	public String getKarte() {
		String karte = "";
		int i = 0;
		karte = karte.concat("aktuelle x-Koordinate: " + this.getAktuellePosition()[0] + ", aktuelle y-Koordinate: "
				+ this.getAktuellePosition()[1] + "\n");
		for (int y = 0; y < this.getSize()[1]; y++) {
			// hier wird die x-Achsenbeschriftung generiert
			while (i < this.getSize()[0]) {
				if (i == 0)
					karte = karte.concat(" |  " + i + " ");
				else if (i < 10) {
					karte = karte.concat("  |  " + i + " ");
				} else {
					karte = karte.concat(" |  " + i + " ");
				}
				i++;
			}
			karte = karte.concat("\n");

			// hier werden die Zwischenzeilen zur Struktur generiert
			for (int x = 0; x <= this.getSize()[0]; x++) {
				if (y != this.getSize()[1]) {
					if (x == 0)
						karte = karte.concat("-+");
					else if (x == this.getSize()[0]) {
						karte = karte.concat("------");
					} else {
						karte = karte.concat("------+");
					}
				}
			}
			karte = karte.concat("\n");
			karte = karte.concat("" + y + "|");

			// hier werden die Werte eingetragen...
			for (int x = 0; x < this.getSize()[0]; x++) {

				int e = this.getFeld(new int[] { x, y }).getEntfernung();
				if ((int) (Math.log10(e) + 1) == 3)
					karte = karte.concat(e + "");
				else if ((int) (Math.log10(e) + 1) == 2)
					karte = karte.concat(e + " ");
				else
					karte = karte.concat(e + "  ");

				if (this.karte[x][y].getName().equals("FLOOR"))
					karte = karte.concat("   ");
				else if (this.karte[x][y].getName().equals("WALL"))
					karte = karte.concat(" X ");
				else if (this.karte[x][y].getName().contains("FINISH"))
					karte = karte.concat(" S ");
				else if (this.karte[x][y].getName().contains("FORM"))
					karte = karte.concat(" F ");
				else
					karte = karte.concat(" ~ ");
				if (x != this.getSize()[0] - 1)
					karte = karte.concat("|");
			}
		}
		return karte;
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld fuellt
	 */
	public void setFeld(int[] position, String info) {//n Switch? y/n?
		if (info.contains("FLOOR")) {
			this.karte[position[0]][position[1]] = new Boden();
		} else if (info.contains("WALL")) {
			this.karte[position[0]][position[1]] = new Wand();
		} else if (info.contains("FINISH")) {
			this.karte[position[0]][position[1]] = new Sachbearbeiter(info);
		} else if (info.contains("FORM")) {
			this.karte[position[0]][position[1]] = new Dokument(info);
		} else {//es muss ein Papier sein
			this.karte[position[0]][position[1]] = new Papier();
		}
	}

	/**
	 * Die Methode entfernungenAktualisieren wird jede Runde aufgerufen um die
	 * Entfernungen der bereits bekannten Felder/Zellen in der Karte anzupassen. So
	 * entsteht eine Entfernungskarte, welche unserem Bot die Entscheidungsfähigkeit
	 * gibt den schnellsten/kuerzesten Weg innerhalb eines bekannten Abschnittes zu
	 * gehen.
	 * 
	 */
	public void aktualisiereEntfernung() {// TODO: einem Attribut ein dynamischesZiel mitgeben?

		// Reset der Entfernungs-Karte, da ja die Berechnung der Entfernungen jede Runde
		// voellig neu geschieht!
		for (int x = 0; x < this.getSize()[0]; x++) {
			for (int y = 0; y < this.getSize()[1]; y++) {
				int[] koordinaten = new int[] { x, y };
				this.getFeld(koordinaten).setEntfernung(500);
			}
		}

		// Hier wird die Entfernung des aktuellen Feldes, worauf der Bot steht auf
		// Entfernung = 0 gesetzt.
		this.getFeld(aktuellePosition).setEntfernung(0);

		boolean aenderung;
		int letzte_beste_entfernung = this.getFeld(new int[]{0,0}).getEntfernung();
		do {
			aenderung = false;
			for (int x = 0; x < this.getSize()[0]; x++) {
				for (int y = 0; y < this.getSize()[1]; y++) {
					// das int[] koordinaten legen wir hilfsweise an, damit man getNachbarn ein
					// int[] übergeben kann
					int[] derzeitige_koordinaten = new int[] { x, y };
					// Wir koennen nur an den Feldern die Entfernungen aktualisieren, welche wir
					// auch exploriert haben, oder welche keine Wand darstellen
					if (!((this.getFeld(derzeitige_koordinaten) instanceof Nebel)
							|| (this.getFeld(derzeitige_koordinaten) instanceof Wand))) { // Weder ein //If für
																							// entfernungen
						// Nebel, noch
						// eine Wand
						// Hier werden alle Entfernungen abgecheckt:
						int entfernungen_im_norden = this.getNachbarn(derzeitige_koordinaten)[0].getEntfernung();
						int entfernungen_im_osten = this.getNachbarn(derzeitige_koordinaten)[1].getEntfernung();
						int entfernungen_im_sueden = this.getNachbarn(derzeitige_koordinaten)[2].getEntfernung();
						int entfernungen_im_westen = this.getNachbarn(derzeitige_koordinaten)[3].getEntfernung();
						// Die ArrayList von Integer Werten dient nur dem Zweck der min() Methode der
						// Collection, um die kleinste Entfernung dieser 4 Werte zu bekommen
						List<Integer> liste_von_entfernungen = new ArrayList<>();

						liste_von_entfernungen.add(entfernungen_im_norden);
						liste_von_entfernungen.add(entfernungen_im_osten);
						liste_von_entfernungen.add(entfernungen_im_sueden);
						liste_von_entfernungen.add(entfernungen_im_westen);

						int kleinste_entfernung_eines_nachbarn = Collections.min(liste_von_entfernungen);

						letzte_beste_entfernung = this.dynamischesZielFinden(derzeitige_koordinaten, letzte_beste_entfernung);

						if (kleinste_entfernung_eines_nachbarn + 1 < this.getFeld(derzeitige_koordinaten)
								.getEntfernung()) {
							this.getFeld(derzeitige_koordinaten).setEntfernung(kleinste_entfernung_eines_nachbarn + 1);
							aenderung = true;
						}

					}

				}
			}
		} while (aenderung == true);

	}

	public int dynamischesZielFinden(int[] koordinaten, int entfernung) {//TODO: falls wir alles erkundet haben müssen wir auch damit umgehen können!
		for (int i = 0; i <= 3; i++) {
			if (this.getNachbarn(koordinaten)[i] instanceof Nebel) {
				if (this.getFeld(koordinaten).getEntfernung() < entfernung)

				{
					this.setDynamischesZiel(koordinaten);
					return this.getFeld(koordinaten).getEntfernung();
				}
			}
		}
		return entfernung;
	};

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * 
	 * @param lastActionsResult ist der Ausgabestring der LastActionResult
	 */
	public void aktualisierePosition(String lastActionsResult, String lastDoneAction) {

		switch (lastActionsResult) {
		case "OK NORTH":
			if (lastDoneAction.equals("go north")) {
				this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] - 1) + this.getSize()[1]) % this.getSize()[1]);
			}
			break;
		case "OK EAST":
			if (lastDoneAction.equals("go east")) {
				this.setAktuellePosition(((aktuellePosition[0] + 1) + this.getSize()[0]) % this.getSize()[0], aktuellePosition[1]);
			}
			break;
		case "OK SOUTH":
			if (lastDoneAction.equals("go south")) {
				this.setAktuellePosition(aktuellePosition[0], ((aktuellePosition[1] + 1) + this.getSize()[1]) % this.getSize()[1]);
			}
			break;
		case "OK WEST":
			if (lastDoneAction.equals("go west")) {
				this.setAktuellePosition(((aktuellePosition[0] - 1) + this.getSize()[0]) % this.getSize()[0], aktuellePosition[1]);
			}
			break;
		}
	}

	/**
	 * Methode, die den Feldstatus im Norden überprüft und aktualisert, wenn das
	 * Feld unbekannt (Nebel) ist, wenn bereits ein Feld existiert, wird zunächst
	 * ueberprueft, ob es mit dem angezeigten uebereinstimmt, wenn nicht (falls ein
	 * Dokument gekickt wurde), aktualisiert es das Feld
	 * 
	 * @param ri
	 */
	public void aktualisereNorden(String northCellStatus) {
		// man nehme sich die Koordinaten der aktuellen Position...
		// ...Uebergebe diese der Methode getNorden(), um die Koordinaten des
		// noerdlichen Felds zu erhalten
		int[] nord_koordinate = this.getNorden(this.aktuellePosition);
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_nord = this.getFeld(nord_koordinate).getName();
		// wenn das bereits angelegte noerdlichen Feld nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		if (!northCellStatus.contains(name_feld_nord)) { //!this.getFeld(nord_koordinate) instanceof northCellStatus.substring[
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde,
			// wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe
			// Dokument gespeichert hat
			if (northCellStatus.contains("FORM") && !(this.getFeld(northCellStatus.substring(0, 8))[0] == -1)) {
				// altes Formular finden
				int[] formular_koordinaten = this.getFeld(northCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
			}
			this.setFeld(nord_koordinate, northCellStatus);
		}
	}

	/**
	 * wie Norden, nur für Osten
	 * 
	 * @param ri
	 */
	public void aktualisereOsten(String eastCellStatus) {
		// man nehme sich die Koordinaten der aktuellen Position...
		// ...uebergebe diese der Methode getOsten(), um die Koordinaten des
		// oestlichen Felds zu erhalten
		int[] ost_koordinate = this.getOsten(aktuellePosition);
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_ost = new String(this.getFeld(ost_koordinate).getName());
		// wenn das bereits angelegte oestlichen Feld nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		if (!eastCellStatus.contains(name_feld_ost)) {
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde,
			// wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe
			// Dokument gespeichert hat
			if (eastCellStatus.contains("FORM") && !(this.getFeld(eastCellStatus.substring(0, 8))[0] == -1)) {
				// altes Formular finden
				int[] formular_koordinaten = this.getFeld(eastCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
			}
			this.setFeld(ost_koordinate, eastCellStatus);
		}
	}

	/**
	 * wie Norden, nur für Sueden
	 * 
	 * @param ri
	 */						
	public void aktualisereSueden(String southCellStatus) {
		// man nehme sich die Koordinaten der aktuellen Position...
		// ...uebergebe diese der Methode getSueden(), um die Koordinaten des
		// suedlichen Felds zu erhalten
		int[] sued_koordinate = this.getSueden(aktuellePosition);
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_sued = new String(this.getFeld(sued_koordinate).getName());
		// wenn das bereits angelegte suedlichen Feld nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		if (!southCellStatus.contains(name_feld_sued)) {
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde,
			// wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe
			// Dokument gespeichert hat
			if (southCellStatus.contains("FORM") && !(this.getFeld(southCellStatus.substring(0, 8))[0] == -1)) {
				// altes Formular finden
				int[] formular_koordinaten = this.getFeld(southCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
			}
			this.setFeld(sued_koordinate, southCellStatus);
		}
	}

	/**
	 * wie Norden, nur für Westen
	 * 
	 * @param ri
	 */
	public void aktualisereWesten(String westCellStatus) {
		// man nehme sich die Koordinaten der aktuellen Position...
		// ...uebergebe diese der Methode getWesten(), um die Koordinaten des
		// westlichen Felds zu erhalten
		int[] west_koordinate = this.getWesten(aktuellePosition);
		// getFeld() gibt das Objekt zurueck. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_west = new String(this.getFeld(west_koordinate).getName());
		// wenn das bereits angelegte westliche Feld nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		if (!westCellStatus.contains(name_feld_west)) {
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde,
			// wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe
			// Dokument gespeichert hat
			if (westCellStatus.contains("FORM") && !(this.getFeld(westCellStatus.substring(0, 8))[0] == -1)) {
				// altes Formular finden
				int[] formular_koordinaten = this.getFeld(westCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
			}
			this.setFeld(west_koordinate, westCellStatus);
		}
	}

	/**
	 * wie Norden, nur für aktuellePosition diese Methode ist nur relevant, wenn ein
	 * Dokument auf ein Feld gekickt wird, auf das wir uns entschieden haben zu
	 * gehen
	 * 
	 * @param ri
	 */
	public void aktualisereStandpunkt(String currentCellStatus) {
		// getFeld() gibt das Objekt des aktuellen Standpunkts zurueck. mit getName()
		// erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_unter_uns = new String(this.getFeld(aktuellePosition).getName());
		// wenn das bereits angelegte Feld "unter uns" nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		//if (this.getFeld(aktuellePosition) instanceof currentCellStatus.getClass())
		//	{
			//instanz instanceof klasse
		//	}
		if (!currentCellStatus.contains(name_feld_unter_uns)) {
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde,
			// wird das alte mit einem Bodenfeld ersetzt, damit man nicht mehrfach dasselbe
			// Dokument gespeichert hat
			if (currentCellStatus.contains("FORM") && !(this.getFeld(currentCellStatus.substring(0, 8))[0] == -1)) {
				// altes Formular finden
				int[] formular_koordinaten = this.getFeld(currentCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
			}
			this.setFeld(aktuellePosition, currentCellStatus);
		}
	}

	/**
	 * Methode, die ueber weitere Methodenaufrufe die umliegenden Felder in der
	 * Karte vermerkt
	 * 
	 * @param ri hierueber können die einzelnen Feldstatus abgerufen werden
	 */
	public void aktualisiereKarte(Rundeninformationen ri) {
		//TODO: Wenn irgendwas aktualisert wird und es ein statisches Ziel ist, was wir immer anpeilen soll das in den Stack gehauen werden.
		//WICHTIG! Alles, was unser Ziel sein *könnte* davon die Koordinaten
		//was ist wenn auf einmal ein Sheet kommt
		this.aktualisierePosition(ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisereNorden(ri.getNorthCellStatus());
		this.aktualisereOsten(ri.getEastCellStatus());
		this.aktualisereSueden(ri.getSouthCellStatus());
		this.aktualisereWesten(ri.getWestCellStatus());
		this.aktualisereStandpunkt(ri.getCurrentCellStatus());
	}

	/**
	 * Methode, die abhaengig vom übergebenen Standpunkt die Koordinaten des im
	 * Norden angrenzenden Objekts zurueckgibt
	 * 
	 * @return Koordinaten des Objekts noerdlich vom aktuellen Standpunkt, welches
	 *         eine Spezialisierung des Typs Feld ist
	 */
	public int[] getNorden(int[] position) {
		int[] nord_koordinaten = new int[2];
		nord_koordinaten[0] = position[0];
		nord_koordinaten[1] = ((position[1] - 1) + this.getSize()[1]) % this.getSize()[1];
		return nord_koordinaten;
	}

	/**
	 * s. Norden, nur mit Osten...
	 * 
	 * @return
	 */
	public int[] getOsten(int[] position) {
		int[] ost_koordinaten = new int[2];
		ost_koordinaten[0] = ((position[0] + 1) + this.getSize()[0]) % this.getSize()[0];
		ost_koordinaten[1] = position[1];
		return ost_koordinaten;
	}

	/**
	 * s. Norden, nur mit Sueden...
	 * 
	 * @return
	 */
	public int[] getSueden(int[] position) {
		int[] sued_koordinaten = new int[2];
		sued_koordinaten[0] = position[0];
		sued_koordinaten[1] = ((position[1] + 1) + this.getSize()[1]) % this.getSize()[1];
		return sued_koordinaten;
	}

	/**
	 * s. Norden, nur mit Westen...
	 * 
	 * @return
	 */
	public int[] getWesten(int[] position) {
		int[] west_koordinate = new int[2];
		west_koordinate[0] = ((position[0] - 1) + this.getSize()[0]) % this.getSize()[0];
		west_koordinate[1] = position[1];
		return west_koordinate;
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
	public Feld[] getNachbarn(int[] position) {
		Feld[] nachbar_felder = new Feld[4];
		nachbar_felder[0] = getFeld(this.getNorden(position));
		nachbar_felder[1] = getFeld(this.getOsten(position));
		nachbar_felder[2] = getFeld(this.getSueden(position));
		nachbar_felder[3] = getFeld(this.getWesten(position));
		return nachbar_felder;
	}

	/**
	 * Dieser Konstruktor erstellt das Spielfeld (Karte) entsprechend der
	 * Uebergebenen Groesse des Spielfelds und Startposition des Bots
	 * 
	 * @param sizeX  Groesse des Spielfelds in der horizontalen
	 * @param sizeY  Groesse des Spielfelds in der vertikalen
	 * @param startX Startposition des Bots auf der x-Achse
	 * @param startY Startposition des Bots auf der y-Achse
	 */
	public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
		this.size = new int[] {sizeX, sizeY};
		this.level = level;
		this.playerId = playerId;
		this.aktuellePosition[0] = startX;
		this.aktuellePosition[1] = startY;
		this.karte = new Feld[sizeX][sizeY];
		this.statischeZiele = new HashSet<int[]>();
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				karte[x][y] = new Nebel();
			}
		}
	}



}
