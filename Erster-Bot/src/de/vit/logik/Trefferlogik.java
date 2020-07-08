package de.vit.logik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Trefferlogik {
	/*
	 * @author Alex
	 * @Klasse, in der Treffer untersucht werden
	 */
	public static boolean beeinhaltet(String Quelle, String gesuchterTeil){
		/*
		 * Diese Methode untersucht, ob ein Exakter char Teil in einer String quelle vorhanden ist
		 */
        String pattern = "\\b"+gesuchterTeil+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(Quelle);
        return m.find();
   }
}
