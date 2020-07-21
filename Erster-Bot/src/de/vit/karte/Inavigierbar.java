package de.vit.karte;

import de.vit.karte.felder.Feld;
import de.vit.karte.typen.ZielMap;

/**
 * Dieses Interface wurde als "Bruecke"/Vertrag zwischen der Klasse Karte und der Klasse Bewegung implementiert.
 * Zu Beginn der Coding Challenge hat sich unser 4er-Team in 2 Gruppen geteilt. Eine Gruppe uebernahm die Implementierung
 * der Karte mit zugehoerigen Feldern und die andere Gruppe die Bewegungslogik, Rekursion und Entfernungsberechnung.
 * So konnte die Gruppe Bewegung durch neue Methoden im Interface der Gruppe Karte ihre Forderungen entgegenbringen,
 * welche dann umgesetzt wurden.
 * 
 * Durch dieses Interface wird auch sichergestellt, dass die Klasse Bewegung nur die noetigsten Methoden
 * der Klasse Karte nutzen darf und nicht frei auf die gesamte Klasse Karte Zugriff hat.
 * 
 * @author Franz Bogmann und Alexander Schmidt
 *
 */
public interface Inavigierbar {

        public int getLevel();
        public int[] getAktuellePosition() ;
        public Feld getFeld(int[] koordinaten);
        public int getFormularZaehler();
        public int getBlattZaehler();
        public ZielMap getStatischeZiele();
        public void reduziereBlattZaehler();
        public void vernebleKarte();
        public Feld[] getNachbarn(int[] koordinaten);
        public int[] getDynamischesZiel();
        public void vernebleUmgebung();
        public int[] getNorden(int[] aktuelleKoordinaten);
        public int[] getOsten(int[] aktuelleKoordinaten);
        public int[] getSueden(int[] aktuelleKoordinaten);
        public int[] getWesten(int[] aktuelleKoordinaten);
        
}