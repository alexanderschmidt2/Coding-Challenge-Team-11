package de.vit.karte;

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
	/**
	 * das eigentliche Spielfeld mit allen Feldern die erste Array-Ebene bezeichnet
	 * die x-Achse die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[][] karte;
	// die momentane Position, wird regelmäßig aktualisiert
	private int[] aktuellePosition = new int[2];

	// Getter und Setter
	public int[] getSize() {
		int[] size = new int[] { sizeX, sizeY };
		return size;
	}

	public Feld getFeld(int x, int y) {
		return this.karte[x][y];
	}

	public void setFeld(int x, int y, String feldtyp) {
		// TODO Feld anlegen; 2 Methoden (überladen) für SB, FORM und Boden, Wand
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

	// TODO grafische ausgabe der Karte als String
	// mit Art entscheidet man, ob man die Entfernungen oder die Inhalte der Zellen
	// sehen möchte
	public String getMap(String art) {
		return "bla";
	}

	/**
	 * erstellt Spielfeld entsprechend der übergebenen Größe des Spielfelds und
	 * Startposition des Bots
	 * 
	 * @param sizeX  Größe des Spielfelds in der horizontalen
	 * @param sizeY  Größe des Spielfelds in der vertikalen
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
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld f�llt
	 */
	public void entfernungenAktualisieren() {
		// Wir brauchen eine Methode, die uns die Nachbarn liefern abh�ngig von der
		// aktuellen Koordinate. Entweder Nebelzellen oder bereits implementierteZellen
		boolean aenderung;

		do {
			aenderung = false;
			for (int x = 0; x < this.getSize()[0]; x++) {
				for (int y = 0; y < this.getSize()[1]; y++) {
					if (karte[x][y].getEntfernung() != 0) {
					}
				}
			}
		} while (true);

	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld füllt
	 */
	public void feldHinzufuegen(int x, int y, String typ) {
		// TODO: Karte mit neuen Instanzen füllen
		// geht schwerlich, da man keinen neuen SB anlegen kann, da die levelId noch
		// nicht existiert
		// SB anlegen geht nur in MainMethode
	}

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult bestimmt
	 * 
	 * @param bewegung ist je nach Level der geslicte Ausgabestring der
	 *                 LastActionResult
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

	public void aktualisereNorden(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...übergebe diese der Methode getNorden(), um die x und y Koordinate des
		// nördlichen Felds zu erhalten
		// Die x-Koordinate wird mit getNorden()[0] ermittelt und getFeld() übergeben;
		// y-Koordinate mit getNorden()[1]
		int nordX = this.getNorden(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int nordY = this.getNorden(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurück. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(nordX, nordY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn nördlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		if (name.equals("NEBEL")) {
			// hier prüfen wir, ob das nördlich gelegege Feld eine Wand ist
			if (rundeninformation.getNorthCellStatus().equals("FLOOR")) {
				this.karte[nordX][nordY] = new Boden();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Boden ist
			else if (rundeninformation.getNorthCellStatus().equals("WALL")) {
				this.karte[nordX][nordY] = new Wand();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Dokument ist ist
			else if (rundeninformation.getNorthCellStatus().contains("FORM")) {
				// Dokument-String in dok speichern
				String dok = rundeninformation.getNorthCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = dok.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// dok slicen, um an nr zu kommen und in int casten
				String nr = dok.substring(9, 10);
				int formNr = Integer.valueOf(nr);
				// neues Dokument anlegen und dem richtigen Feld der Karte zuweisen
				Dokument dokument = new Dokument(spielerId2, formNr);
				this.karte[nordX][nordY] = dokument;
			}

			// das nördlich gelegege Feld muss also ein Sachbearbeiter sein
			else {
				// Sachbearbeiter-String in sb speichern
				String sb = rundeninformation.getNorthCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = sb.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// sb slicen, um an formCount zu kommen und in int casten
				String formCount = sb.substring(9, 10);
				int formCount2 = Integer.valueOf(formCount);
				// neuen Sachbearbeiter anlegen und dem richtigen Feld der Karte zuweisen
				Sachbearbeiter typi = new Sachbearbeiter(spielerId2, formCount2);
				this.karte[nordX][nordY] = typi;
				// die maximal einsammelbaren Dokumente aktualisieren
				typi.setFormCount(formCount2);
			}

		}
		// jetzt prüfen wir, ob das bereits angelegte Feld dem entspricht, was wir
		// grade sehen
		else {
			// TODO check, ob Feld gleich ist, wenn nein, aktualisieren
		}
	}

	public void aktualisereOsten(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...übergebe diese der Methode getNorden(), um die x und y Koordinate des
		// nördlichen Felds zu erhalten
		// Die x-Koordinate wird mit getNorden()[0] ermittelt und getFeld() übergeben;
		// y-Koordinate mit getNorden()[1]
		int ostX = this.getOsten(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int ostY = this.getOsten(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurück. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(ostX, ostY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn nördlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		if (name.equals("NEBEL")) {
			// hier prüfen wir, ob das nördlich gelegege Feld eine Wand ist
			if (rundeninformation.getEastCellStatus().equals("FLOOR")) {
				this.karte[ostX][ostY] = new Boden();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Boden ist
			else if (rundeninformation.getEastCellStatus().equals("WALL")) {
				this.karte[ostX][ostY] = new Wand();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Dokument ist ist
			else if (rundeninformation.getEastCellStatus().contains("FORM")) {
				// Dokument-String in dok speichern
				String dok = rundeninformation.getEastCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = dok.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// dok slicen, um an nr zu kommen und in int casten
				String nr = dok.substring(9, 10);
				int formNr = Integer.valueOf(nr);
				// neues Dokument anlegen und dem richtigen Feld der Karte zuweisen
				Dokument dokument = new Dokument(spielerId2, formNr);
				this.karte[ostX][ostY] = dokument;
			}

			// das nördlich gelegege Feld muss also ein Sachbearbeiter sein
			else {
				// Sachbearbeiter-String in sb speichern
				String sb = rundeninformation.getEastCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = sb.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// sb slicen, um an formCount zu kommen und in int casten
				String formCount = sb.substring(9, 10);
				int formCount2 = Integer.valueOf(formCount);
				// neuen Sachbearbeiter anlegen und dem richtigen Feld der Karte zuweisen
				Sachbearbeiter typi = new Sachbearbeiter(spielerId2, formCount2);
				this.karte[ostX][ostY] = typi;
				// die maximal einsammelbaren Dokumente aktualisieren
				typi.setFormCount(formCount2);
			}

		}
		// jetzt prüfen wir, ob das bereits angelegte Feld dem entspricht, was wir
		// grade sehen
		else {
			// TODO check, ob Feld gleich ist, wenn nein, aktualisieren
		}
	}

	public void aktualisereSueden(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...übergebe diese der Methode getNorden(), um die x und y Koordinate des
		// nördlichen Felds zu erhalten
		// Die x-Koordinate wird mit getNorden()[0] ermittelt und getFeld() übergeben;
		// y-Koordinate mit getNorden()[1]
		int suedX = this.getSueden(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int suedY = this.getSueden(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurück. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(suedX, suedY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn nördlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		if (name.equals("NEBEL")) {
			// hier prüfen wir, ob das nördlich gelegege Feld eine Wand ist
			if (rundeninformation.getSouthCellStatus().equals("FLOOR")) {
				this.karte[suedX][suedY] = new Boden();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Boden ist
			else if (rundeninformation.getSouthCellStatus().equals("WALL")) {
				this.karte[suedX][suedY] = new Wand();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Dokument ist ist
			else if (rundeninformation.getSouthCellStatus().contains("FORM")) {
				// Dokument-String in dok speichern
				String dok = rundeninformation.getSouthCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = dok.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// dok slicen, um an nr zu kommen und in int casten
				String nr = dok.substring(9, 10);
				int formNr = Integer.valueOf(nr);
				// neues Dokument anlegen und dem richtigen Feld der Karte zuweisen
				Dokument dokument = new Dokument(spielerId2, formNr);
				this.karte[suedX][suedY] = dokument;
			}

			// das nördlich gelegege Feld muss also ein Sachbearbeiter sein
			else {
				// Sachbearbeiter-String in sb speichern
				String sb = rundeninformation.getSouthCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = sb.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// sb slicen, um an formCount zu kommen und in int casten
				String formCount = sb.substring(9, 10);
				int formCount2 = Integer.valueOf(formCount);
				// neuen Sachbearbeiter anlegen und dem richtigen Feld der Karte zuweisen
				Sachbearbeiter typi = new Sachbearbeiter(spielerId2, formCount2);
				this.karte[suedX][suedY] = typi;
				// die maximal einsammelbaren Dokumente aktualisieren
				typi.setFormCount(formCount2);
			}

		}
		// jetzt prüfen wir, ob das bereits angelegte Feld dem entspricht, was wir
		// grade sehen
		else {
			// TODO check, ob Feld gleich ist, wenn nein, aktualisieren
		}
	}

	public void aktualisereWesten(Rundeninformationen rundeninformation) {
		// man nehme sich die x und y Koordinate der aktuellen Position...
		// ...übergebe diese der Methode getNorden(), um die x und y Koordinate des
		// nördlichen Felds zu erhalten
		// Die x-Koordinate wird mit getNorden()[0] ermittelt und getFeld() übergeben;
		// y-Koordinate mit getNorden()[1]
		int westX = this.getWesten(this.aktuellePosition[0], this.aktuellePosition[1])[0];
		int westY = this.getWesten(this.aktuellePosition[0], this.aktuellePosition[1])[1];
		// getFeld() gibt das Objekt zurück. mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name = new String(getFeld(westX, westY).getName());
		// name wird zum Vergleich mit NEBEL herangezogen
		// ergo: Wenn nördlich von der aktuellen Positon ein unbekanntes Feld (Nebel)
		// ist, gehen wir in die if-Verzweigung
		if (name.equals("NEBEL")) {
			// hier prüfen wir, ob das nördlich gelegege Feld eine Wand ist
			if (rundeninformation.getWestCellStatus().equals("FLOOR")) {
				this.karte[westX][westY] = new Boden();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Boden ist
			else if (rundeninformation.getWestCellStatus().equals("WALL")) {
				this.karte[westX][westY] = new Wand();
			}

			// hier prüfen wir, ob das nördlich gelegege Feld ein Dokument ist ist
			else if (rundeninformation.getWestCellStatus().contains("FORM")) {
				// Dokument-String in dok speichern
				String dok = rundeninformation.getWestCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = dok.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// dok slicen, um an nr zu kommen und in int casten
				String nr = dok.substring(9, 10);
				int formNr = Integer.valueOf(nr);
				// neues Dokument anlegen und dem richtigen Feld der Karte zuweisen
				Dokument dokument = new Dokument(spielerId2, formNr);
				this.karte[westX][westY] = dokument;
			}

			// das nördlich gelegege Feld muss also ein Sachbearbeiter sein
			else {
				// Sachbearbeiter-String in sb speichern
				String sb = rundeninformation.getWestCellStatus();
				// sb slicen, um an spielerId zu kommen und in int casten
				String spielerId = sb.substring(7, 8);
				int spielerId2 = Integer.valueOf(spielerId);
				// sb slicen, um an formCount zu kommen und in int casten
				String formCount = sb.substring(9, 10);
				int formCount2 = Integer.valueOf(formCount);
				// neuen Sachbearbeiter anlegen und dem richtigen Feld der Karte zuweisen
				Sachbearbeiter typi = new Sachbearbeiter(spielerId2, formCount2);
				this.karte[westX][westY] = typi;
				// die maximal einsammelbaren Dokumente aktualisieren
				typi.setFormCount(formCount2);
			}

		}
		// jetzt prüfen wir, ob das bereits angelegte Feld dem entspricht, was wir
		// grade sehen
		else {
			// TODO check, ob Feld gleich ist, wenn nein, aktualisieren
		}
	}

	public void aktualisiereKarte(Rundeninformationen rundeninformation) {
		// TODO: maximal 3 unbekannte neue Felder durch neue Informationen ersetzen
	}

	/**
	 * Methode, die abhängig vom aktuellen Standpunkt die Koordinaten des im Norden
	 * angrenzenden Objekts zurückgibt
	 * 
	 * @return Koordinaten des Objekts nördlich vom aktuellen Standpunkt, welches
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
	 * s. Norden, nur mit Süden...
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
	 * Methode, die ein Array von vier Feldern zurückgibt, welche den Feldern
	 * entsprechen, die an die aktuelle Position angrenzen die Reihenfolge der
	 * Objekte: Nord, Ost, Süd, West die Methode ruft die Methoden getNorden(),
	 * getOsten(), getSüden(), getWesten() auf
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
