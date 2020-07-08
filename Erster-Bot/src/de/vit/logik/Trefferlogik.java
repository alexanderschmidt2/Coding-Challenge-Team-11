package de.vit.logik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Alex
 * @Klasse, in der in einem String Quelle exakte Pattern auf vorhandensein geprüft werden
 */
public abstract class Trefferlogik {
	/**
	 * @Methode Diese Methode untersucht, ob ein Exakter char Teil in einer String quelle vorhanden ist
	 */
	public static boolean beeinhaltet(String Quelle, String gesuchterTeil){
		
        String pattern = "\\b"+gesuchterTeil+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(Quelle);
        return m.find();
   }
}
