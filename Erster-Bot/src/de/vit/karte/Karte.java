package de.vit.karte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.vit.karte.felder.*;
import de.vit.logik.*;
import de.vit.karte.typen.ZielMap;
/**
 *  Klasse, die das Spielfeld und die aktuelle Position in Form von Koordinaten beinhaltet
 * @author Laura
 * @author Constantin
 */
public class Karte implements navigierbar {
	private int[] size;
	private final int level;
	private final int playerId;
	private int formCount = 0;
	private int[] dynamischesZiel;
	private ZielMap statischeZiele;
	private int Spielphase = 0;
	private int sheetCount;
	/**
	 * das eigentliche Spielfeld mit allen Feldern die erste Array-Ebene bezeichnet
	 * die x-Achse die zweite Array-Ebene bezeichnet die y-Achse
	 */
	private Feld[][] karte;
	/**
	 * die momentane Position; wird in jeder Runde aktualisiert
	 */
	private int[] aktuellePosition = new int[2];

	// Getter und Setter	
	public int[] getSize() {
		return size;
	}

	/**
	 * diese Methode sucht ein Feld mittels bekannter Koordinaten
	 * @param x Koordinate
	 * @param y Koordinate
	 * @return das Objekt des gesuchte Feld
	 */
	public Feld getFeld(int[] position) {
		return this.karte[position[0]][position[1]];
	}

	/**
	 * diese Methode sucht die Koordinaten ein Feld mittels bekanntem Namen und
	 * evtl. Ids (aus dem Feldstatus)
	 * @param feldtyp soll den kompletten String, den die getCellStatus-Methoden
	 *                ausgeben, entegegennehmen Ausnahme: wenn Dokument gekickt
	 *                wurde, dann übergeben wir eine geslicten String, ohne andere
	 *                Bots (ohne !)
	 * @return Achtung! Gibt -1|-1 zurück, wenn Feld nicht gefunden wurde!!
	 */
	public int[] getFeld(String feldtyp) {
		int[] koordinaten = new int[] {-1, -1};
		for (int j = 0; j < this.size[1]; j++) {
			for (int i = 0; i < this.size[0]; i++) {
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
	 * @param formCount
	 */
	public void setFormCount(int formCount) {
		if (this.formCount < formCount) {
			this.formCount = formCount;
		}
	}
	
	public int[] getDynamischesZiel() {
		return this.dynamischesZiel;
	}

	public void setDynamischesZiel(int[] koordinaten) {
		this.dynamischesZiel = koordinaten;
	}
	
	public ZielMap getStatischeZiele() {
		return statischeZiele;
	}
	
	public int getSpielphase() {
		return Spielphase;
	}

	public void setSpielphase(int spielphase) {
		Spielphase = spielphase;
	}
	
	public int getSheetCount() {
		return sheetCount;
	}

	
	public void reduziereSheetCount() {
		this.sheetCount--;
	}
	
	public void erhoeheSheetCount() {
		this.sheetCount++;
	}
	
	public void setSheetCount(int sheetCount) {
		this.sheetCount = sheetCount;
	}

	//grafische Ausgabe der Karte inklusive Entfernungen als String
	public String getKarte() {
		String karte = "";
		int i = 0;
		karte = karte.concat("aktuelle x-Koordinate: " + this.getAktuellePosition()[0] + ", aktuelle y-Koordinate: "
				+ this.getAktuellePosition()[1] + "\n");
		for (int y = 0; y < this.size[1]; y++) {
			// hier wird die x-Achsenbeschriftung generiert
			while (i < this.size[0]) {
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
			for (int x = 0; x <= this.size[0]; x++) {
				if (y != this.size[1]) {
					if (x == 0)
						karte = karte.concat("-+");
					else if (x == this.size[0]) {
						karte = karte.concat("------");
					} else {
						karte = karte.concat("------+");
					}
				}
			}
			karte = karte.concat("\n");
			karte = karte.concat("" + y + "|");

			// hier werden die Werte eingetragen...
			for (int x = 0; x < this.size[0]; x++) {

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
				if (x != this.size[0] - 1)
					karte = karte.concat("|");
			}
		}
		return karte;
	}

	/**
	 * Methode, die die Karte mit einem weiteren, noch nicht entdeckten Feld fuellt
	 */
	public void setFeld(int[] position, String info) {
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
		int temp_entfernung = entfernung;
		if (this.getFeld(koordinaten) instanceof Papier) {
			Papier papier = (Papier) this.getFeld(koordinaten);
			if (papier.getEntfernung() < entfernung && !papier.isGekickt()) {
				this.setDynamischesZiel(koordinaten);
				temp_entfernung = papier.getEntfernung();
			}
		}
		for (int i = 0; i <= 3; i++) {
			if (this.getNachbarn(koordinaten)[i] instanceof Nebel) {
				if (this.getFeld(koordinaten).getEntfernung() < entfernung)

				{
					this.setDynamischesZiel(koordinaten);
					temp_entfernung = this.getFeld(koordinaten).getEntfernung();
				}
			}
		}
		return temp_entfernung;
	};

	/**
	 * Methode, die die aktuelle Position aufgrund der LastActionResult und lastDoneAction (um auszuschließen,
	 * dass zurvor nur erfolgreich gekickt wurde) bestimmt
	 * @param lastActionsResult ist der Ausgabestring des Resultats der letzten Aktion
	 * @param lastDoneAction ist der Ausgabestring der letzten versuchten Aktion
	 */
	public void aktualisierePosition(String lastActionsResult, String lastDoneAction) {

		switch (lastActionsResult) {
		case "OK NORTH":
			if (lastDoneAction.equals("go north")) {
				this.setAktuellePosition(this.getNorden(aktuellePosition)[0], this.getNorden(aktuellePosition)[1]);
			}
			break;
		case "OK EAST":
			if (lastDoneAction.equals("go east")) {
				this.setAktuellePosition(this.getOsten(aktuellePosition)[0], this.getOsten(aktuellePosition)[1]);
			}
			break;
		case "OK SOUTH":
			if (lastDoneAction.equals("go south")) {
				this.setAktuellePosition(this.getSueden(aktuellePosition)[0], this.getSueden(aktuellePosition)[1]);
			}
			break;
		case "OK WEST":
			if (lastDoneAction.equals("go west")) {
				this.setAktuellePosition(this.getWesten(aktuellePosition)[0], this.getWesten(aktuellePosition)[1]);
			}
			break;
		}
	}

	/**
	 * Methode, die den Feldstatus im Norden überprüft und aktualisert, wenn das
	 * angezeigte Feld nicht mit dem uebereinstimmt, welches wir gespeichert haben
	 * falls ein Dokument oder Sachbearbeiter gefunden wurde, aktualisiert es das
	 * Set statischeZiele. Falls ein Dokument an einer anderen Stelle wiedergefunden
	 * wurde (weil es gekickt wurde), dann wird das Dokument an der alten Stelle geloescht
	 * (Boden wird instanziiert) und an der neuen neu angelegt
	 * @param northCellStatus das Feld, das wir tatsaechlich "sehen"
	 * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
	 * @param lastDoneAction die letzte von uns getaetigte Aktion
	 */
	public void aktualisiereNorden(String northCellStatus, String lastActionsResult, String lastDoneAction) {
		// man nehme sich die Koordinaten der aktuellen Position...
		// ...uebergebe diese der Methode getNorden(), um die Koordinaten des
		// noerdlichen Felds zu erhalten
		int[] nord_koordinate = this.getNorden(this.aktuellePosition);
		// getFeld() gibt das Objekt zurueck. Mit getName() erhalten wir den Namen des
		// Objekts und speichern diesen im String name
		String name_feld_nord = this.getFeld(nord_koordinate).getName();
		// wenn das bereits angelegte noerdlichen Feld nicht dem aktuellen entspricht,
		// gehen wir in die if-Verzweigung
		if (!northCellStatus.contains(name_feld_nord)) {
			// falls ein Formular gefunden wird, welches bereits auf der Karte vermerkt
			// wurde, aber weggekickt wurde, wird das alte mit einem Bodenfeld ersetzt,
			//damit man nicht mehrfach dasselbe Dokument gespeichert hat
			if (northCellStatus.contains("FORM") && !(this.getFeld(northCellStatus.substring(0, 8))[0] == -1)) {
				// Koordinaten des alten Formulars finden
				int[] formular_koordinaten = this.getFeld(northCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
				//das alte Formular wird auch aus der Abbildung statischeZiele gelöscht
				this.statischeZiele.remove(northCellStatus.substring(0, 8));
			}
			//hier wird das eigentliche Objekt angelegt
			this.setFeld(nord_koordinate, northCellStatus);
			
			//wenn es sich um einen Sachbearbeiter oder ein Dokument handelt,
			//fuegen wir der Abbildung statischeZiele ein Element hinzu
			if (this.getFeld(nord_koordinate) instanceof Sachbearbeiter)
			{
				//da wir durch getFeld ein Feld erhalten, muss dieses zunaechst in einen
				//Sachbearbeiter gecastet werden, damit wir getPlayerId anwenden koennen
				Sachbearbeiter sb = (Sachbearbeiter) this.getFeld(nord_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(sb.getFormCount());
				if (sb.getPlayerId() == this.getPlayerId())
					{
					//der Abbildung statischeZiele hinzufuegen
					this.statischeZiele.put(northCellStatus.substring(0, 10), nord_koordinate);
					}
			}
			else if (this.getFeld(nord_koordinate) instanceof Dokument)
			{
				//s. Prüfung instanceof Sachbearbeiter
				Dokument dok = (Dokument) this.getFeld(nord_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(dok.getNr());
				if (dok.getPlayerId() == this.getPlayerId())
				{
					this.statischeZiele.put(northCellStatus.substring(0, 8), nord_koordinate);
				}
			}
			//hier bekommt ein Papier, welches von uns gekickt wurde, den status gekickt, damit wir es nicht noch einmal kicken
			else if (this.getFeld(nord_koordinate) instanceof Papier && lastActionsResult.equals("OK NORTH") && lastDoneAction.equals("kick north"))
			{
				//s. Prüfung instanceof Sachbearbeiter
				Papier papier = (Papier) this.getFeld(nord_koordinate);
				papier.setGekickt(true);
			}
		}
	}

	/**
	 * wie Norden, nur für Osten
	 * @param eastCellStatus das Feld, das wir tatsaechlich "sehen"
	 * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
	 * @param lastDoneAction die letzte von uns getaetigte Aktion
	 */
	public void aktualisiereOsten(String eastCellStatus, String lastActionsResult, String lastDoneAction) {
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
				// Koordinaten des alten Formulars finden
				int[] formular_koordinaten = this.getFeld(eastCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
				//das alte Formular wird auch aus der Abbildung statischeZiele gelöscht
				this.statischeZiele.remove(eastCellStatus.substring(0, 8));
			}
			//hier wird das eigentliche Objekt angelegt
			this.setFeld(ost_koordinate, eastCellStatus);
			
			//wenn es sich um einen Sachbearbeiter oder ein Dokument handelt,
			//fuegen wir der Abbildung statischeZiele ein Element hinzu
			if (this.getFeld(ost_koordinate) instanceof Sachbearbeiter)
			{
				//da wir durch getFeld ein Feld erhalten, muss dieses zunaechst in einen
				//Sachbearbeiter gecastet werden, damit wir getPlayerId anwenden koennen
				Sachbearbeiter sb = (Sachbearbeiter) this.getFeld(ost_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(sb.getFormCount());
				if (sb.getPlayerId() == this.getPlayerId())
					{
					//der Abbildung statischeZiele hinzufuegen
					this.statischeZiele.put(eastCellStatus.substring(0, 10), ost_koordinate);
					}
			}
			else if (this.getFeld(ost_koordinate) instanceof Dokument)
			{
				//s. Prüfung instanceof Sachbearbeiter
				Dokument dok = (Dokument) this.getFeld(ost_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(dok.getNr());
				if (dok.getPlayerId() == this.getPlayerId())
				{
					this.statischeZiele.put(eastCellStatus.substring(0, 8), ost_koordinate);
				}
			}
			//hier bekommt ein Papier, welches von uns gekickt wurde, den status gekickt, damit wir es nicht noch einmal kicken
			else if (this.getFeld(ost_koordinate) instanceof Papier && lastActionsResult.equals("OK EAST") && lastDoneAction.equals("kick east"))
			{
				//s. Prüfung instanceof Sachbearbeiter
				Papier papier = (Papier) this.getFeld(ost_koordinate);
				papier.setGekickt(true);
			}
		}
	}

	/**
	 * wie Norden, nur für Sueden
	 * @param southCellStatus das Feld, das wir tatsaechlich "sehen"
	 * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
	 * @param lastDoneAction die letzte von uns getaetigte Aktion
	 */						
	public void aktualisiereSueden(String southCellStatus, String lastActionsResult, String lastDoneAction) {
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
				// Koordinaten des alten Formulars finden
				int[] formular_koordinaten = this.getFeld(southCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
				//das alte Formular wird auch aus der Abbildung statischeZiele gelöscht
				this.statischeZiele.remove(southCellStatus.substring(0, 8));
			}
			//hier wird das eigentliche Objekt angelegt
			this.setFeld(sued_koordinate, southCellStatus);
			
			//wenn es sich um einen Sachbearbeiter oder ein Dokument handelt,
			//fuegen wir der Abbildung statischeZiele ein Element hinzu
			if (this.getFeld(sued_koordinate) instanceof Sachbearbeiter)
			{
				//da wir durch getFeld ein Feld erhalten, muss dieses zunaechst in einen
				//Sachbearbeiter gecastet werden, damit wir getPlayerId anwenden koennen
				Sachbearbeiter sb = (Sachbearbeiter) this.getFeld(sued_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(sb.getFormCount());
				if (sb.getPlayerId() == this.getPlayerId())
					{
					//der Abbildung statischeZiele hinzufuegen
					this.statischeZiele.put(southCellStatus.substring(0, 10), sued_koordinate);
					}
			}
			else if (this.getFeld(sued_koordinate) instanceof Dokument)
			{
				//s. Prüfung instanceof Sachbearbeiter
				Dokument dok = (Dokument) this.getFeld(sued_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(dok.getNr());
				if (dok.getPlayerId() == this.getPlayerId())
				{
					this.statischeZiele.put(southCellStatus.substring(0, 8), sued_koordinate);
				}
			}
			//hier bekommt ein Papier, welches von uns gekickt wurde, den status gekickt, damit wir es nicht noch einmal kicken
			else if (this.getFeld(sued_koordinate) instanceof Papier && lastActionsResult.equals("OK SOUTH") && lastDoneAction.equals("kick south"))
			{
				//s. Prüfung instanceof Sachbearbeiter
				Papier papier = (Papier) this.getFeld(sued_koordinate);
				papier.setGekickt(true);
			}
		}
	}

	/**
	 * wie Norden, nur für Westen
	 * @param westCellStatus das Feld, das wir tatsaechlich "sehen"
	 * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
	 * @param lastDoneAction die letzte von uns getaetigte Aktion
	 */
	public void aktualisiereWesten(String westCellStatus, String lastActionsResult, String lastDoneAction) {
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
				// Koordinaten des alten Formulars finden
				int[] formular_koordinaten = this.getFeld(westCellStatus.substring(0, 8));
				// altes Formular "loeschen", also durch Floor ersetzen (da Dokument nicht auf
				// SB liegen kann)
				this.setFeld(formular_koordinaten, "FLOOR");
				//das alte Formular wird auch aus der Abbildung statischeZiele gelöscht
				this.statischeZiele.remove(westCellStatus.substring(0, 8));
			}
			//hier wird das eigentliche Objekt angelegt
			this.setFeld(west_koordinate, westCellStatus);
			
			//wenn es sich um einen Sachbearbeiter oder ein Dokument handelt,
			//fuegen wir der Abbildung statischeZiele ein Element hinzu
			if (this.getFeld(west_koordinate) instanceof Sachbearbeiter)
			{
				//da wir durch getFeld ein Feld erhalten, muss dieses zunaechst in einen
				//Sachbearbeiter gecastet werden, damit wir getPlayerId anwenden koennen
				Sachbearbeiter sb = (Sachbearbeiter) this.getFeld(west_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(sb.getFormCount());
				if (sb.getPlayerId() == this.getPlayerId())
					{
					//der Abbildung statischeZiele hinzufuegen
					this.statischeZiele.put(westCellStatus.substring(0, 10), west_koordinate);
					}
			}
			else if (this.getFeld(west_koordinate) instanceof Dokument)
			{
				//s. Prüfung instanceof Sachbearbeiter...
				Dokument dok = (Dokument) this.getFeld(west_koordinate);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(dok.getNr());
				if (dok.getPlayerId() == this.getPlayerId())
				{
					this.statischeZiele.put(westCellStatus.substring(0, 8), west_koordinate);
				}
			}
			//hier bekommt ein Papier, welches von uns gekickt wurde, den status gekickt, damit wir es nicht noch einmal kicken
			else if (this.getFeld(west_koordinate) instanceof Papier && lastActionsResult.equals("OK WEST") && lastDoneAction.equals("kick west"))
			{
				//s. Prüfung instanceof Sachbearbeiter
				Papier papier = (Papier) this.getFeld(west_koordinate);
				papier.setGekickt(true);
			}
		}
	}

	/**
	 * wie Norden, nur für aktuellePosition. Diese Methode ist nur relevant, wenn ein
	 * Dokument auf ein Feld gekickt wird, auf das wir uns entschieden haben zu
	 * gehen
	 * @param currentCellStatus das Feld, das wir tatsaechlich "sehen"
	 */
	public void aktualisiereStandpunkt(String currentCellStatus) {
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
				//das alte Formular wird auch aus der Abbildung statischeZiele gelöscht
				this.statischeZiele.remove(currentCellStatus.substring(0, 8));
			}
			//hier wird das eigentliche Objekt angelegt
			this.setFeld(aktuellePosition, currentCellStatus);
			
			if (this.getFeld(aktuellePosition) instanceof Dokument)
			{
				//wenn es sich um ein Dokument handelt, fuegen wir der Abbildung statischeZiele ein Element hinzu
				Dokument dok = (Dokument) this.getFeld(aktuellePosition);
				//dabei wird der formCount erhoeht (wenn moeglich)
				this.setFormCount(dok.getNr());
				if (dok.getPlayerId() == this.getPlayerId())
				{
					//der Abbildung statischeZiele hinzufuegen
					this.statischeZiele.put(currentCellStatus.substring(0, 8), aktuellePosition);
				}
			}
			//hier bekommt ein Papier, welches von uns hingelegt wurde, den status gekickt, damit wir es nicht wegkicken
			else if (this.getFeld(aktuellePosition) instanceof Papier)
			{
				Papier papier = (Papier) this.getFeld(aktuellePosition);
				papier.setGekickt(true);
			}
		}
	}

	/**
	 * Methode, die ueber weitere Methodenaufrufe die umliegenden Felder in der
	 * Karte vermerkt
	 * @param ri hierueber können die einzelnen Feldstatus abgerufen werden
	 */
	public void aktualisiereKarte(Rundeninformationen ri) {
		this.aktualisierePosition(ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisiereNorden(ri.getNorthCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisiereOsten(ri.getEastCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisiereSueden(ri.getSouthCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisiereWesten(ri.getWestCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		this.aktualisiereStandpunkt(ri.getCurrentCellStatus());
	}

	/**
	 * Methode, die abhaengig vom übergebenen Standpunkt die Koordinaten des im
	 * Norden angrenzenden Objekts zurueckgibt
	 * @return Koordinaten des Objekts noerdlich vom aktuellen Standpunkt
	 */
	public int[] getNorden(int[] position) {
		int[] nord_koordinaten = new int[2];
		nord_koordinaten[0] = position[0];
		nord_koordinaten[1] = ((position[1] - 1) + this.getSize()[1]) % this.getSize()[1];
		return nord_koordinaten;
	}

	/**
	 * s. Norden, nur mit Osten...
	 * @return Koordinaten des Objekts oestlich vom aktuellen Standpunkt
	 */
	public int[] getOsten(int[] position) {
		int[] ost_koordinaten = new int[2];
		ost_koordinaten[0] = ((position[0] + 1) + this.getSize()[0]) % this.getSize()[0];
		ost_koordinaten[1] = position[1];
		return ost_koordinaten;
	}

	/**
	 * s. Norden, nur mit Sueden...
	 * @return Koordinaten des Objekts suedlich vom aktuellen Standpunkt
	 */
	public int[] getSueden(int[] position) {
		int[] sued_koordinaten = new int[2];
		sued_koordinaten[0] = position[0];
		sued_koordinaten[1] = ((position[1] + 1) + this.getSize()[1]) % this.getSize()[1];
		return sued_koordinaten;
	}

	/**
	 * s. Norden, nur mit Westen...
	 * @return Koordinaten des Objekts westlich vom aktuellen Standpunkt
	 */
	public int[] getWesten(int[] position) {
		int[] west_koordinate = new int[2];
		west_koordinate[0] = ((position[0] - 1) + this.getSize()[0]) % this.getSize()[0];
		west_koordinate[1] = position[1];
		return west_koordinate;
	}

	/**
	 * Methode, die ein Array von vier Feldern zurueckgibt, welche den Feldern
	 * entsprechen, die an die aktuelle Position angrenzen
	 * @param position hierüber soll gesteuert werden, von welchem Feld die
	 * 		  Nachbarn gefunden werden sollen
	 * @return die Reihenfolge der Objekte: Nord, Ost, Sued, West
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
	 * setzt alle nicht Wand-Felder, die mit 2 oder 4 Schritten erreichbar sind, wieder auf Nebel,
	 * damit ein eventuell nicht gefundenes Formular erneut gesucht werden kann
	 */
	public void vernebleKarte()
	{		
		for (int i = 0; i < size[1]; i++)
		{
			for (int j = 0; j < size[0]; j++)
			{
				//es werden nur Felder vernebelt, die...
				//entweder 2 oder 4 Felder entfernt sind
				if ((this.karte[j] [i].getEntfernung() == 2 || this.karte[j] [i].getEntfernung() == 4) &&
						//oder kein Dokument oder Sachbearbeiter sind (also ein Ziel sind)
						!((this.karte[j] [i] instanceof Dokument) || (this.karte[j] [i] instanceof Sachbearbeiter)) &&
						//oder kein Nadeloehr sind, also nicht im Norden und im Sueden ein Wandfeld haben
						(!(this.getNachbarn(new int [] {j,i})[0].getEntfernung() == 500000000 && this.getNachbarn(new int [] {j,i})[2].getEntfernung() == 500000000) ||
						//oder nicht im Osten und Westen ein Wandfeld haben
						!(this.getNachbarn(new int [] {j,i})[1].getEntfernung() == 500000000 && this.getNachbarn(new int [] {j,i})[3].getEntfernung() == 500000000)))
				{
						this.karte[j] [i] = new Nebel();		
				}
			}
		}
		this.aktualisiereEntfernung();
	}

	/**
	 * Dieser Konstruktor erstellt das Spielfeld (Karte) entsprechend der
	 * uebergebenen Groesse des Spielfelds und fuellt diese mit Nebel
	 * @param sizeX  Groesse des Spielfelds in der horizontalen
	 * @param sizeY  Groesse des Spielfelds in der vertikalen
	 * @param level  Level des Spiels
	 * @param playerId  id des Spielers in diesem Spiel
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
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				karte[x][y] = new Nebel();
			}
		}
		this.statischeZiele = new ZielMap(this.level);
	}
}
