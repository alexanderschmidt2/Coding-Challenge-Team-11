package de.vit.karte.felder;

/**
 * Klasse, deren Instanzen ein Feld verdeckt von einem Blatt in der Karte darstellen
 * @author Laura Fenzl
 * @author Constantin Graedtke
 */
public class Blatt extends Feld{
    private boolean gekickt = false;
    
    public Blatt()
    {
        super();
        this.name = "SHEET";
    }
    
    public boolean isGekickt() {
        return gekickt;
    }
    
    public void setGekickt(boolean gekickt) {
        this.gekickt = gekickt;
    }
}

