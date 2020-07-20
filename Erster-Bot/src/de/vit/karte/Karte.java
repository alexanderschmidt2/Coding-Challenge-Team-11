package de.vit.karte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.vit.karte.felder.*;
import de.vit.logik.*;
import de.vit.karte.typen.ZielMap;

/**
 * Klasse, die das Spielfeld und die aktuelle Position in Form von Koordinaten
 * beinhaltet
 * 
 * @author Laura Fenzl
 * @author Constantin Graedtke
 */
public class Karte implements Inavigierbar {
        private int[] spielfeldAbmessung;
        private final int level;
        private final int spielerId;
        private int formularZaehler = 0;
        private int[] dynamischesZiel;
        private ZielMap statischeZiele;
        private int blattZaehler;
        /**
         * das eigentliche Spielfeld mit allen Feldern die erste Array-Dimension bezeichnet
         * die x-Achse die zweite Array-Dimension bezeichnet die y-Achse
         */
        private Feld[][] karte;
        /**
         * die momentane Position; wird in jeder Runde aktualisiert
         */
        private int[] aktuellePosition = new int[2];

        /**
		 * Dieser Konstruktor erstellt das Spielfeld (Karte) entsprechend der
		 * uebergebenen Groesse des Spielfelds und fuellt diese mit Nebel
		 * 
		 * @param sizeX    Groesse des Spielfelds in der horizontalen
		 * @param sizeY    Groesse des Spielfelds in der vertikalen
		 * @param level    Level des Spiels
		 * @param playerId id des Spielers in diesem Spiel
		 * @param startX   Startposition des Bots auf der x-Achse
		 * @param startY   Startposition des Bots auf der y-Achse
		 */
		public Karte(int sizeX, int sizeY, int level, int playerId, int startX, int startY) {
		        this.spielfeldAbmessung = new int[] { sizeX, sizeY };
		        this.level = level;
		        this.spielerId = playerId;
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

		public int[] getSpielfeldAbmessung() {
                return spielfeldAbmessung;
        }

        public int getLevel() {
		        return level;
		}

		public int getSpielerId() {
		        return spielerId;
		}

		public int getFormularZaehler() {
		        return formularZaehler;
		}

		/**
		 * wird nur verwendet, wenn wir ein Formular entdecken und die FormularNr
		 * groeßer ist als der bisher gespeicherte formularZaehler. Sobald Sachbearbeiter
		 * gefunden wurde, wird maximaler Wert gesetzt und Methode wird nicht mehr
		 * benoetigt
		 * 
		 * @param formularZaehler
		 */
		public void setFormularZaehler(int formularZaehler) {
		        if (this.formularZaehler < formularZaehler) {
		                this.formularZaehler = formularZaehler;
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



        public int getBlattZaehler() {
                return blattZaehler;
        }

        public void setBlattZaehler(int blattZaehler) {
		        this.blattZaehler = blattZaehler;
		}

		public void erhoeheBlattZaehler() {
		        this.blattZaehler++;
		}

		public void reduziereBlattZaehler() {
                this.blattZaehler--;
        }

        /**
         * grafische Ausgabe der Karte inklusive Entfernungen als String (nur zu Debug-Zwecken)
         * @return
         */
        public String getKarte() {
                String karte = "";
                int i = 0;
                karte = karte.concat("aktuelle x-Koordinate: " + this.getAktuellePosition()[0] + ", aktuelle y-Koordinate: "
                                + this.getAktuellePosition()[1] + "\n");
                for (int y = 0; y < this.spielfeldAbmessung[1]; y++) {
                        // hier wird die x-Achsenbeschriftung generiert
                        while (i < this.spielfeldAbmessung[0]) {
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
                        for (int x = 0; x <= this.spielfeldAbmessung[0]; x++) {
                                if (y != this.spielfeldAbmessung[1]) {
                                        if (x == 0)
                                                karte = karte.concat("-+");
                                        else if (x == this.spielfeldAbmessung[0]) {
                                                karte = karte.concat("------");
                                        } else {
                                                karte = karte.concat("------+");
                                        }
                                }
                        }
                        karte = karte.concat("\n");
                        karte = karte.concat("" + y + "|");

                        // hier werden die Werte eingetragen...
                        for (int x = 0; x < this.spielfeldAbmessung[0]; x++) {

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
                                if (x != this.spielfeldAbmessung[0] - 1)
                                        karte = karte.concat("|");
                        }
                }
                return karte;
        }

        public int[] getAktuellePosition() {
		        return aktuellePosition;
		}

		/**
		 * da sich bei einer Bewegung nur eine Koordinate aendert ist es sinnvoll der
		 * Methode anstatt eines Integer-Arrays direkt zwei int zu uebergeben. Daher wird
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

		/**
		 * Methode, die ein Array von vier Feldern zurueckgibt, welche den Feldern
		 * entsprechen, die an die aktuelle Position angrenzen
		 * 
		 * @param position hierüber soll gesteuert werden, von welchem Feld die Nachbarn
		 *                 gefunden werden sollen
		 * @return die Reihenfolge der Objekte: Nord, Ost, Sued, West
		 */
		public Feld[] getNachbarn(int[] position) {
		        Feld[] nachbarFelder = new Feld[4];
		        nachbarFelder[0] = getFeld(this.getNorden(position));
		        nachbarFelder[1] = getFeld(this.getOsten(position));
		        nachbarFelder[2] = getFeld(this.getSueden(position));
		        nachbarFelder[3] = getFeld(this.getWesten(position));
		        return nachbarFelder;
		}

		/**
		 * Methode, die abhaengig vom uebergebenen Standpunkt die Koordinaten des im
		 * Norden angrenzenden Objekts zurueckgibt
		 * 
		 * @return Koordinaten des Objekts noerdlich vom aktuellen Standpunkt
		 */
		public int[] getNorden(int[] position) {
		        int[] nordKoordinaten = new int[2];
		        nordKoordinaten[0] = position[0];
		        nordKoordinaten[1] = ((position[1] - 1) + this.getSpielfeldAbmessung()[1]) % this.getSpielfeldAbmessung()[1];
		        return nordKoordinaten;
		}

		/**
		 * Methode, die abhaengig vom uebergebenen Standpunkt die Koordinaten des im
		 * Osten angrenzenden Objekts zurueckgibt
		 * 
		 * @return Koordinaten des Objekts oestlich vom aktuellen Standpunkt
		 */
		public int[] getOsten(int[] position) {
		        int[] ostKoordinaten = new int[2];
		        ostKoordinaten[0] = ((position[0] + 1) + this.getSpielfeldAbmessung()[0]) % this.getSpielfeldAbmessung()[0];
		        ostKoordinaten[1] = position[1];
		        return ostKoordinaten;
		}

		/**
		 * Methode, die abhaengig vom uebergebenen Standpunkt die Koordinaten des im
		 * Sueden angrenzenden Objekts zurueckgibt
		 * 
		 * @return Koordinaten des Objekts suedlich vom aktuellen Standpunkt
		 */
		public int[] getSueden(int[] position) {
		        int[] suedKoordinaten = new int[2];
		        suedKoordinaten[0] = position[0];
		        suedKoordinaten[1] = ((position[1] + 1) + this.getSpielfeldAbmessung()[1]) % this.getSpielfeldAbmessung()[1];
		        return suedKoordinaten;
		}

		/**
		 * Methode, die abhaengig vom uebergebenen Standpunkt die Koordinaten des im
		 * Westen angrenzenden Objekts zurueckgibt
		 * 
		 * @return Koordinaten des Objekts westlich vom aktuellen Standpunkt
		 */
		public int[] getWesten(int[] position) {
		        int[] westKoordinate = new int[2];
		        westKoordinate[0] = ((position[0] - 1) + this.getSpielfeldAbmessung()[0]) % this.getSpielfeldAbmessung()[0];
		        westKoordinate[1] = position[1];
		        return westKoordinate;
		}

		/**
		 * diese Methode sucht ein Feld mittels bekannter Koordinaten
		 * 
		 * @param x Koordinate
		 * @param y Koordinate
		 * @return das Objekt des gesuchte Feld
		 */
		public Feld getFeld(int[] position) {
		        return this.karte[position[0]][position[1]];
		}

		/**
		 * diese Methode sucht die Koordinaten eines Felds mittels bekanntem Namen und
		 * evtl. Ids (aus dem Feldstatus)
		 * 
		 * @param cellStatus soll den kompletten oder geslicten (ohne Entfernung anderer Bots) String,
		 * den die getCellStatus-Methoden ausgeben, entegegennehmen
		 * 
		 * @return Gibt -1|-1 zurück, wenn Feld nicht gefunden wurde
		 */
		public int[] getFeld(String cellStatus) {
		        int[] koordinaten = new int[] { -1, -1 };
		        for (int j = 0; j < this.spielfeldAbmessung[1]; j++) {
		                for (int i = 0; i < this.spielfeldAbmessung[0]; i++) {
		                        if (cellStatus.contains(this.karte[i][j].getName())) {
		                                koordinaten[0] = i;
		                                koordinaten[1] = j;
		                                break;
		                        }
		                }
		        }
		        return koordinaten;
		}

		/**
         * instanziiert eine Spezialisierung von Feld
         * 
         * @param koordinaten definiert die Stelle in der Karte
         * @param cellStatus gibt an, welche Spezialisierung instanziiert werden soll
         */
        public void setFeld(int[] koordinaten, String cellStatus) {
                if (cellStatus.contains("FLOOR")) {
                        this.karte[koordinaten[0]][koordinaten[1]] = new Boden();
                } else if (cellStatus.contains("WALL")) {
                        this.karte[koordinaten[0]][koordinaten[1]] = new Wand();
                } else if (cellStatus.contains("FINISH")) {
                        this.karte[koordinaten[0]][koordinaten[1]] = new Sachbearbeiter(cellStatus);
                } else if (cellStatus.contains("FORM")) {
                        this.karte[koordinaten[0]][koordinaten[1]] = new Formular(cellStatus);
                } else if (cellStatus.contains("NEBEL")) {
                        this.karte[koordinaten[0]][koordinaten[1]] = new Nebel();
                } else {// es muss ein Papier sein
                        this.karte[koordinaten[0]][koordinaten[1]] = new Blatt();
                }
        }
        
        /**
		 * Methode, die ueber weitere Methodenaufrufe die umliegenden Felder in der
		 * Karte vermerkt
		 * 
		 * @param ri hierueber können die einzelnen Feldstatus abgerufen werden
		 */
		public void aktualisiereKarte(Rundeninformationen ri) {
		        this.aktualisierePosition(ri.getLastActionsResult(), ri.getLastDoneAction());
		        this.aktualisiereNorden(ri.getNorthCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		        this.aktualisiereOsten(ri.getEastCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		        this.aktualisiereSueden(ri.getSouthCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		        this.aktualisiereWesten(ri.getWestCellStatus(), ri.getLastActionsResult(), ri.getLastDoneAction());
		        this.aktualisiereStandpunkt(ri.getCurrentCellStatus(), ri.getLastDoneAction());
		}

	/**
     * Methode, die die aktuelle Position aufgrund der LastActionResult und
     * lastDoneAction (um auszuschließen, dass zurvor nur erfolgreich gekickt wurde)
     * bestimmt.
     * Wenn ein Formular oder ein Blatt erfolgreich aufgenommen wurde, wird der formularZaehler
     * der aufgesammelten Formulare oder der blattZaehler erhoeht.
     * Der blattZaehler wird verringert, wenn ein Blatt erfolgreich abgelegt wurde.
     * Falls nach der Position gefragt wird, wird die gespeicherte aktuellePostion aktualisiert,
     * sofern sie von der erhaltenen abweicht.
     * 
     * @param lastActionsResult ist der Ausgabestring des Resultats der letzten Aktion
     * @param lastDoneAction    ist der Ausgabestring der letzten versuchten Aktion
     */
    public void aktualisierePosition(String lastActionsResult, String lastDoneAction) {

            switch (lastActionsResult) {
            //erfolgreiche Bewegung
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
            case "OK FORM": //erfolgreich ein Formular aufgenommen
                    this.getStatischeZiele().addAufgesammelteFormulare();
                    Formular dokument = (Formular) this.getFeld(this.getAktuellePosition());
                    this.getStatischeZiele().remove(dokument.getName());
                    break;                        
            case "OK SHEET":   //erfolgreich ein Blatt aufgenommen                      
                    this.erhoeheBlattZaehler();
                    break;                
            case "OK ": // Blatt abgelegt
                    if (lastDoneAction.equals("put")) {
                            this.reduziereBlattZaehler();
                    }
                    break;
            // Position abgefragt        
            default: //da das lastActionResult bei der Positionsabfrage dynamisch ist, verwenden wir hier den default-case und eine if-Abfrage
                    // die letze Aktion war nach der eigenen Position zu fragen und wir haben eine positive Rueckmeldung erhalten
                    if (lastDoneAction.equals("position") && lastActionsResult.contains("OK") ) {
                            String koordinaten = lastActionsResult.substring(4, lastActionsResult.length());
                            int erhalteneXKoordinate = Integer.parseInt(koordinaten.substring(0, koordinaten.indexOf(" ")));
                            int erhalteneYKoordinate = Integer.parseInt(koordinaten.substring(koordinaten.indexOf(" ")+1, koordinaten.length() ));
                            
                            if (!Arrays.equals(aktuellePosition, new int[] {erhalteneXKoordinate, erhalteneYKoordinate})) {
                                    this.setAktuellePosition(erhalteneXKoordinate, erhalteneYKoordinate);
                            }
                    }
                    break;  
            }
    }
    
     /**
     * Methode, die den Feldstatus im Norden ueberprueft und aktualisert, wenn das
     * angezeigte Feld nicht mit dem uebereinstimmt, welches wir gespeichert haben.
     * Falls ein Dokument oder Sachbearbeiter gefunden wurde, aktualisiert es das
     * Set statischeZiele. Falls ein Dokument an einer anderen Stelle wiedergefunden
     * wurde (weil es gekickt wurde), dann wird das Dokument an der alten Stelle
     * geloescht (Boden wird instanziiert) und an der aktuellen Stelle neu angelegt.
     * Blaetter, die von uns erfolgreich gekickt wurden, bekommen den Status gekickt.
     * 
     * @param northCellStatus   das Feld, das wir tatsaechlich "sehen"
     * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
     * @param lastDoneAction    die letzte von uns getaetigte Aktion
     */
    public void aktualisiereNorden(String northCellStatus, String lastActionsResult, String lastDoneAction) {
            int[] nordKoordinaten = new int[] {this.getNorden(this.aktuellePosition)[0],this.getNorden(this.aktuellePosition)[1]} ;
            String nameFeldNord = this.getFeld(nordKoordinaten).getName();
            if (!northCellStatus.contains(nameFeldNord)) {
                    
                    if (northCellStatus.contains("FORM") && !(this.getFeld(northCellStatus.substring(0, 8))[0] == -1)) { //wenn Formular an anderer Stelle wiedergefunden wurde
                            int[] formularKoordinaten = this.getFeld(northCellStatus.substring(0, 8));
                            this.setFeld(formularKoordinaten, "FLOOR");
                            this.statischeZiele.remove(northCellStatus.substring(0, 8));
                    }
                    
                    this.setFeld(nordKoordinaten, northCellStatus); // hier wird das eigentliche Objekt angelegt
                    Feld neuesFeld = this.getFeld(nordKoordinaten);

                    if (neuesFeld instanceof Sachbearbeiter) {
                            Sachbearbeiter sb = (Sachbearbeiter) neuesFeld;
                            this.setFormularZaehler(sb.getFormularZaehler()); // formularZehler erhoehen
                            if (sb.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(nordKoordinaten)) {
                                    this.statischeZiele.put(northCellStatus.substring(0, 10), nordKoordinaten); // unseren SB der Abbildung statischeZiele hinzufuegen
                            }
                    }
                    
                    else if (neuesFeld instanceof Formular) {
                            Formular form = (Formular) neuesFeld;
                            this.setFormularZaehler(form.getNr()); // formularZehler erhoehen (wenn moeglich)
                            if (form.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(nordKoordinaten)) {
                                    this.statischeZiele.put(northCellStatus.substring(0, 8), nordKoordinaten); // unser Formular der Abbildung statischeZiele hinzufuegen
                            }
                    }

                    else if (neuesFeld instanceof Blatt && lastActionsResult.equals("OK NORTH")
                                    && lastDoneAction.equals("kick north")) {
                            Blatt blatt = (Blatt) neuesFeld;
                            blatt.setGekickt(true); //selbst gekicktes Blatt bekommt den Status gekickt
                    }
            }
    }

    /**
     * Methode, die den Feldstatus im Osten ueberprueft und aktualisert, wenn das
     * angezeigte Feld nicht mit dem uebereinstimmt, welches wir gespeichert haben.
     * Falls ein Dokument oder Sachbearbeiter gefunden wurde, aktualisiert es das
     * Set statischeZiele. Falls ein Dokument an einer anderen Stelle wiedergefunden
     * wurde (weil es gekickt wurde), dann wird das Dokument an der alten Stelle
     * geloescht (Boden wird instanziiert) und an der aktuellen Stelle neu angelegt.
     * Blaetter, die von uns erfolgreich gekickt wurden, bekommen den Status gekickt.
     * 
     * @param eastCellStatus    das Feld, das wir tatsaechlich "sehen"
     * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
     * @param lastDoneAction    die letzte von uns getaetigte Aktion
     */
    public void aktualisiereOsten(String eastCellStatus, String lastActionsResult, String lastDoneAction) {
            int[] ostKoordinate = new int[] {this.getOsten(this.aktuellePosition)[0],this.getOsten(this.aktuellePosition)[1]} ;
            String nameFeldOst = new String(this.getFeld(ostKoordinate).getName());
            if (!eastCellStatus.contains(nameFeldOst)) {
                    if (eastCellStatus.contains("FORM") && !(this.getFeld(eastCellStatus.substring(0, 8))[0] == -1)) {  //wenn Formular an anderer Stelle wiedergefunden wurde
                            int[] formularKoordinaten = this.getFeld(eastCellStatus.substring(0, 8));
                            this.setFeld(formularKoordinaten, "FLOOR");
                            this.statischeZiele.remove(eastCellStatus.substring(0, 8));
                    }
                    
                    this.setFeld(ostKoordinate, eastCellStatus); // hier wird das eigentliche Objekt angelegt
                    Feld neuesFeld = this.getFeld(ostKoordinate);

                    if (neuesFeld instanceof Sachbearbeiter) {
                            Sachbearbeiter sb = (Sachbearbeiter) neuesFeld;
                            this.setFormularZaehler(sb.getFormularZaehler());  // formularZehler erhoehen
                            if (sb.getSpielerId() == this.getSpielerId() && !this.getStatischeZiele().containsValue(ostKoordinate)) {
                                    this.statischeZiele.put(eastCellStatus.substring(0, 10), ostKoordinate); // unseren SB der Abbildung statischeZiele hinzufuegen
                            }
                    }
                    
                    else if (neuesFeld instanceof Formular) {
                            Formular form = (Formular) neuesFeld;
                            this.setFormularZaehler(form.getNr());  // formularZehler erhoehen (wenn moeglich)
                            if (form.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(ostKoordinate)) {
                                    this.statischeZiele.put(eastCellStatus.substring(0, 8), ostKoordinate); // unser Formular der Abbildung statischeZiele hinzufuegen
                            }
                    }

                    else if (neuesFeld instanceof Blatt && lastActionsResult.equals("OK EAST")
                                    && lastDoneAction.equals("kick east")) {
                            Blatt blatt = (Blatt) neuesFeld;
                            blatt.setGekickt(true);  //selbst gekicktes Blatt bekommt den Status gekickt
                    }
            }
    }

    /**
     * Methode, die den Feldstatus im Sueden ueberprueft und aktualisert, wenn das
     * angezeigte Feld nicht mit dem uebereinstimmt, welches wir gespeichert haben.
     * Falls ein Dokument oder Sachbearbeiter gefunden wurde, aktualisiert es das
     * Set statischeZiele. Falls ein Dokument an einer anderen Stelle wiedergefunden
     * wurde (weil es gekickt wurde), dann wird das Dokument an der alten Stelle
     * geloescht (Boden wird instanziiert) und an der aktuellen Stelle neu angelegt.
     * Blaetter, die von uns erfolgreich gekickt wurden, bekommen den Status gekickt.
     * 
     * @param southCellStatus   das Feld, das wir tatsaechlich "sehen"
     * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
     * @param lastDoneAction    die letzte von uns getaetigte Aktion
     */
    public void aktualisiereSueden(String southCellStatus, String lastActionsResult, String lastDoneAction) {
            int[] suedKoordinate = new int[] {this.getSueden(this.aktuellePosition)[0],this.getSueden(this.aktuellePosition)[1]} ;
            String nameFeldSued = new String(this.getFeld(suedKoordinate).getName());
            if (!southCellStatus.contains(nameFeldSued)) {

                    if (southCellStatus.contains("FORM") && !(this.getFeld(southCellStatus.substring(0, 8))[0] == -1)) { //wenn Formular an anderer Stelle wiedergefunden wurde
                            int[] formularKoordinaten = this.getFeld(southCellStatus.substring(0, 8));
                            this.setFeld(formularKoordinaten, "FLOOR");
                            this.statischeZiele.remove(southCellStatus.substring(0, 8));
                    }
                    
                    this.setFeld(suedKoordinate, southCellStatus); // hier wird das eigentliche Objekt angelegt
                    Feld neuesFeld = this.getFeld(suedKoordinate);
                    
                    if (neuesFeld instanceof Sachbearbeiter) {
                            Sachbearbeiter sb = (Sachbearbeiter) neuesFeld;                                
                            this.setFormularZaehler(sb.getFormularZaehler()); // formularZaehler erhoehen
                            if (sb.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(suedKoordinate)) {
                                    this.statischeZiele.put(southCellStatus.substring(0, 10), suedKoordinate);  // unseren SB der Abbildung statischeZiele hinzufuegen
                            }
                    }
                    
                    else if (neuesFeld instanceof Formular) {
                            Formular form = (Formular) neuesFeld; 
                            this.setFormularZaehler(form.getNr()); // formularZaehler erhoehen (wenn moeglich)
                            if (form.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(suedKoordinate)) {
                                    this.statischeZiele.put(southCellStatus.substring(0, 8), suedKoordinate);  // unser Formular der Abbildung statischeZiele hinzufuegen
                            }
                    }
                    
                    else if (neuesFeld instanceof Blatt && lastActionsResult.equals("OK SOUTH")
                                    && lastDoneAction.equals("kick south")) {
                            Blatt blatt = (Blatt) neuesFeld;
                            blatt.setGekickt(true);  //selbst gekicktes Blatt bekommt den Status gekickt
                    }
            }
    }

    /**
     * Methode, die den Feldstatus im Westen ueberprueft und aktualisert, wenn das
     * angezeigte Feld nicht mit dem uebereinstimmt, welches wir gespeichert haben.
     * Falls ein Dokument oder Sachbearbeiter gefunden wurde, aktualisiert es das
     * Set statischeZiele. Falls ein Dokument an einer anderen Stelle wiedergefunden
     * wurde (weil es gekickt wurde), dann wird das Dokument an der alten Stelle
     * geloescht (Boden wird instanziiert) und an der aktuellen Stelle neu angelegt.
     * Blaetter, die von uns erfolgreich gekickt wurden, bekommen den Status gekickt.
     * 
     * @param westCellStatus    das Feld, das wir tatsaechlich "sehen"
     * @param lastActionsResult das Resultat unserer letzten getaetigten Aktion
     * @param lastDoneAction    die letzte von uns getaetigte Aktion
     */
    public void aktualisiereWesten(String westCellStatus, String lastActionsResult, String lastDoneAction) {
            int[] westKoordinate = new int[] {this.getWesten(this.aktuellePosition)[0],this.getWesten(this.aktuellePosition)[1]} ;
            String nameFeldWest = new String(this.getFeld(westKoordinate).getName());
            if (!westCellStatus.contains(nameFeldWest)) {

                    if (westCellStatus.contains("FORM") && !(this.getFeld(westCellStatus.substring(0, 8))[0] == -1)) { //wenn Formular an anderer Stelle wiedergefunden wurde
                            int[] formularKoordinaten = this.getFeld(westCellStatus.substring(0, 8));
                            this.setFeld(formularKoordinaten, "FLOOR");
                            this.statischeZiele.remove(westCellStatus.substring(0, 8));
                    }
                    
                    this.setFeld(westKoordinate, westCellStatus); // hier wird das eigentliche Objekt angelegt
                    Feld neuesFeld = this.getFeld(westKoordinate);

                    if (neuesFeld instanceof Sachbearbeiter) {
                            Sachbearbeiter sb = (Sachbearbeiter) neuesFeld;
                            this.setFormularZaehler(sb.getFormularZaehler()); // formularZaehler erhoehen
                            if (sb.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(westKoordinate)) {

                                    this.statischeZiele.put(westCellStatus.substring(0, 10), westKoordinate); // unseren SB der Abbildung statischeZiele hinzufuegen
                            }
                    } 
                    
                    else if (neuesFeld instanceof Formular) {

                            Formular form = (Formular) neuesFeld;

                            this.setFormularZaehler(form.getNr());  // formularZaehler erhoehen (wenn moeglich)
                            if (form.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(westKoordinate)) {
                                    this.statischeZiele.put(westCellStatus.substring(0, 8), westKoordinate);  // unser Formular der Abbildung statischeZiele hinzufuegen
                            }
                    }

                    else if (neuesFeld instanceof Blatt && lastActionsResult.equals("OK WEST")
                                    && lastDoneAction.equals("kick west")) {
                            Blatt blatt = (Blatt) neuesFeld;
                            blatt.setGekickt(true);  //selbst gekicktes Blatt bekommt den Status gekickt
                    }
            }
    }

    /**
     * Methode, die den Feldstatus am aktuellen Standpunkt ueberprueft und aktualisert 
     * Diese Methode ist nur relevant, wenn ein Dokument auf ein Feld gekickt wird, 
     * auf das wir uns entschieden haben zu gehen oder wir ein Blatt erfolgreich gelegt haben
     * 
     * @param currentCellStatus das Feld, das wir tatsaechlich "sehen"
     */
    public void aktualisiereStandpunkt(String currentCellStatus, String lastDoneAction) {
            
                    int[] standPunktKoordinaten = new int[] { aktuellePosition[0], aktuellePosition[1] };
            String nameFeldUnterUns = new String(this.getFeld(aktuellePosition).getName());
            if (!currentCellStatus.contains(nameFeldUnterUns)) {

                    if (currentCellStatus.contains("FORM") && !(this.getFeld(currentCellStatus.substring(0, 8))[0] == -1)) { //wenn Formular an anderer Stelle wiedergefunden wurde
                            int[] formularKoordinaten = this.getFeld(currentCellStatus.substring(0, 8));
                            this.setFeld(formularKoordinaten, "FLOOR");
                            this.statischeZiele.remove(currentCellStatus.substring(0, 8));
                    }

                    this.setFeld(aktuellePosition, currentCellStatus); // hier wird das eigentliche Objekt angelegt
                    Feld neuesFeld = this.getFeld(aktuellePosition);

                    if (neuesFeld instanceof Formular) {
                            Formular form = (Formular) neuesFeld;
                            this.setFormularZaehler(form.getNr());   // formularZaehler erhoehen (wenn moeglich)
                            if (form.getSpielerId() == this.getSpielerId()
                                            && !this.getStatischeZiele().containsValue(aktuellePosition)) {
                                    this.statischeZiele.put(currentCellStatus.substring(0, 8), standPunktKoordinaten); // unser Formular der Abbildung statischeZiele hinzufuegen
                            }
                    }

                    else if (neuesFeld instanceof Blatt && lastDoneAction.equals("put")) {
                            Blatt blatt = (Blatt) neuesFeld;
                            blatt.setGekickt(true); //selbst gelegtes Blatt bekommt den Status gekickt (damit wir es nicht gleich wieder kicken)
                    }
            }
    }

        /**
		 * Die Methode entfernungenAktualisieren wird jede Runde aufgerufen, um die
		 * Entfernungen der bereits bekannten Felder/Zellen in der Karte anzupassen. So
		 * entsteht eine Entfernungskarte, welche unserem Bot die Entscheidungsfähigkeit
		 * gibt, den schnellsten/kuerzesten Weg innerhalb eines bekannten Abschnittes zu
		 * gehen.
		 * 
		 * @authors Alexander Schmidt und Franz Bogmann
		 */
		public void aktualisiereEntfernung() {
		
		        // Reset der Entfernungs-Karte, da ja die Berechnung der Entfernungen jede Runde
		        // voellig neu geschieht!
		        for (int x = 0; x < this.getSpielfeldAbmessung()[0]; x++) {
		                for (int y = 0; y < this.getSpielfeldAbmessung()[1]; y++) {
		                        int[] koordinaten = new int[] { x, y };
		                        this.getFeld(koordinaten).setEntfernung(500);
		                }
		        }
		
		        // Hier wird die Entfernung des aktuellen Feldes, worauf der Bot steht, auf
		        // Entfernung = 0 gesetzt.
		        this.getFeld(aktuellePosition).setEntfernung(0);
		
		        boolean aenderung;
		        int letzteBesteEntfernung = this.getFeld(new int[] { 0, 0 }).getEntfernung();
		        do {
		                aenderung = false;
		                for (int x = 0; x < this.getSpielfeldAbmessung()[0]; x++) {
		                        for (int y = 0; y < this.getSpielfeldAbmessung()[1]; y++) {
		                                // das int[] koordinaten legen wir hilfsweise an, damit man getNachbarn ein
		                                // int[] übergeben kann
		                                int[] derzeitigeKoordinate = new int[] { x, y };
		                                // Wir koennen nur an den Feldern die Entfernungen aktualisieren, welche wir
		                                // auch exploriert haben, oder welche keine Wand darstellen
		                                if (!((this.getFeld(derzeitigeKoordinate) instanceof Nebel)                        // Weder ein Nebel, noch
		                                                || (this.getFeld(derzeitigeKoordinate) instanceof Wand))) { // eine Wand
		                                        
		                                        // Hier werden alle Entfernungen abgeprueft:
		                                        int entfernungImNorden = this.getNachbarn(derzeitigeKoordinate)[0].getEntfernung();
		                                        int entfernungImOsten = this.getNachbarn(derzeitigeKoordinate)[1].getEntfernung();
		                                        int entfernungImSueden = this.getNachbarn(derzeitigeKoordinate)[2].getEntfernung();
		                                        int entfernungImWesten = this.getNachbarn(derzeitigeKoordinate)[3].getEntfernung();
		                                        // Die ArrayList von Integer Werten dient nur dem Zweck der min() Methode der
		                                        // Collection, um die kleinste Entfernung dieser 4 Werte zu bekommen
		                                        List<Integer> listeVonEntfernungen = new ArrayList<>();
		
		                                        listeVonEntfernungen.add(entfernungImNorden);
		                                        listeVonEntfernungen.add(entfernungImOsten);
		                                        listeVonEntfernungen.add(entfernungImSueden);
		                                        listeVonEntfernungen.add(entfernungImWesten);
		
		                                        int kleinsteEntfernungEinesNachbarn = Collections.min(listeVonEntfernungen);
		
		                                        letzteBesteEntfernung = this.dynamischesZielFinden(derzeitigeKoordinate,
		                                                        letzteBesteEntfernung);
		
		                                        if (kleinsteEntfernungEinesNachbarn + 1 < this.getFeld(derzeitigeKoordinate)
		                                                        .getEntfernung()) {
		                                                this.getFeld(derzeitigeKoordinate).setEntfernung(kleinsteEntfernungEinesNachbarn + 1);
		                                                aenderung = true;
		                                        }
		
		                                }
		
		                        }
		                }
		        } while (aenderung == true);
		}

		/**
		 * Diese Methode setzt ein dynamisches Ziel, das wie folgt definiert ist: 
		 * das von uns aus gesehene naechste(naheliegendste) freie Feld mit einem Nebel Nachbarn ODER 
		 * ein nicht gekicktes Feld, worauf ein Papier liegt, was ebenfalls nahe dran ist. Diese Methode wird bei einer 
		 * Entfernungsaktualisierung mehrfach aufgerufen und liefert immer die optimalste Entfernung zurueck, um beim naechsten 
		 * Aufruf Entfernungen vergleichen zu koennen.
		 * 
		 * @authors Alexander Schmidt und Franz Bogmann
		 * @param koordinaten, die in der Schleife von entfernungenAktualisieren gerade zum betrachteten Feld gehoeren
		 * @param entfernung
		 * @return temporaereEntfernung, die verglichen werden soll; bleibt gleich, wenn es keine bessere gibt
		 */
		public int dynamischesZielFinden(int[] koordinaten, int entfernung) {
		        int temporaereEntfernung = entfernung;
		        Feld aktuellesFeld = this.getFeld(koordinaten);
		        if (aktuellesFeld instanceof Blatt) {
		                Blatt papier = (Blatt) aktuellesFeld;
		                if (papier.getEntfernung() < entfernung && !papier.isGekickt()) {
		                        this.setDynamischesZiel(koordinaten);
		                        temporaereEntfernung = papier.getEntfernung();
		                }
		        }
		        for (int i = 0; i <= 3; i++) {
		                if (this.getNachbarn(koordinaten)[i] instanceof Nebel) {
		                        if (aktuellesFeld.getEntfernung() < entfernung)
		
		                        {
		                                this.setDynamischesZiel(koordinaten);
		                                temporaereEntfernung = aktuellesFeld.getEntfernung();
		                        }
		                }
		        }
		        return temporaereEntfernung;
		}

		/**
         * setzt alle nicht Wand-Felder, die mit 2 oder 4 Schritten erreichbar sind,
         * wieder auf Nebel, damit ein eventuell nicht gefundenes Formular erneut
         * gesucht werden kann
         */
        public void vernebleUmgebung() {
                for (int x = 0; x < spielfeldAbmessung[0]; x++) {
                        for (int y = 0; y < spielfeldAbmessung[1]; y++) {
                                int[] koordinaten = new int[] { x, y };
                                // es werden nur Felder vernebelt, die...
                                // 2 Felder entfernt
                                if ((this.getFeld(koordinaten).getEntfernung() == 2) &&
                                // und kein Dokument oder Sachbearbeiter sind (also ein Ziel sind)
                                                !((this.getFeld(koordinaten) instanceof Formular)
                                                                || (this.getFeld(koordinaten) instanceof Sachbearbeiter))
                                                &&
                                                // und kein Nadeloehr sind, also entweder im Norden und im Sueden ein Wandfeld haben

                                                !((this.getNachbarn(koordinaten)[0].getEntfernung() == 500000000
                                                                && this.getNachbarn(koordinaten)[2].getEntfernung() == 500000000) ||
                                                // oder im Osten und Westen ein Wandfeld haben
                                                                (this.getNachbarn(koordinaten)[1].getEntfernung() == 500000000
                                                                                && this.getNachbarn(koordinaten)[3].getEntfernung() == 500000000))) {
                                        this.setFeld(koordinaten, "NEBEL");
                                }
                        }
                }
                this.aktualisiereEntfernung();
        }
        /*
         * WorstCase, wir finden das Formular nicht und müssen von vorne Suchen, oder unsere Exploration ist zu Ende
         */
        public void vernebleKarte() {
                this.getStatischeZiele().clear();
                for (int x = 0; x < spielfeldAbmessung[0]; x++) {
                        for (int y = 0; y < spielfeldAbmessung[1]; y++) {
                                int[] koordinaten = new int[] { x, y };
                                // es werden alle Felder egal ob SB oder nicht vernebelt, das heißt, dass
                                if (!(this.getFeld(koordinaten) instanceof Wand)) {
                                        this.setFeld(koordinaten, "NEBEL");
                                }
                        }
                }
                this.aktualisiereEntfernung();
        }
}