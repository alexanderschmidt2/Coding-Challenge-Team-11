package de.vit.karte.felder;

/**
 * pro Sachbearbeiter wird eine weitere Instanz angelegt mit entsprechender spielerId und Anzahl der Formulare
 * @author Laura Fenzl
 * @author Constantin Graedtke
 */
public class Sachbearbeiter extends Feld {
        private final int spielerId;
        private int formularZaehler;
        
        /**
         * nimmt den cellStatus-String entgegen und slict die spielerId und Anzahl der Formulare heraus
         * und speichert diese als int in den jeweiligen Attributen
         * 
         * @param cellStatus String aus der Spieleumgebung
         */
        public Sachbearbeiter(String cellStatus)
        {
                super();
                String spielerIdString = cellStatus.substring(7, 8);
                int spielerIdInt = Integer.valueOf(spielerIdString);
                String formularZaehlerString = cellStatus.substring(9, 10);
                int formularZaehlerInt = Integer.valueOf(formularZaehlerString);

                this.name = "FINISH " + spielerIdString + " " + formularZaehlerString;
                this.spielerId = spielerIdInt;
                this.formularZaehler = formularZaehlerInt;
        }
        
        public int getSpielerId() {
                return spielerId;
        }

        public int getFormularZaehler() {
                return formularZaehler;
        }
        
        public void setFormularZaehler(int formularZaehler) {
                this.formularZaehler = formularZaehler;
        }
}

