package de.vit.karte;

import de.vit.karte.felder.Feld;
import de.vit.karte.typen.ZielMap;

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