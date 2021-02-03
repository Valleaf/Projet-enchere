package bll;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.management.Query;

/**
 * Cette classe sert a verifier les donnees envoyees a la BDD
 * @author Val
 *
 */
public class Verification {
	
	/**
	 * Cette fonction verifie si la string est nulle, trop longue ou si elle contient des caracteres autre que des espaces, lettres de l'alphabet ou des chiffres 
	 * @param s la string a verifier
	 * @return Vrai si les tests sont bons, false sinon
	 */
	public static boolean string(String s) {
		if(s == null || s.length()>30) {
			return false;
		} 
		/*TODO On ne veut pas de caracteres speciaux, a tester
		if(s.matches("[^a-zA-Z\\d\\s:]") ){
			return false;
		}
		*/
		return true;
	}
	
	public static String verifString(String s) {
		return s.trim();
	}
	
	
	/**
	 * Cette fonction encrypte le mot de passe en {@link Base64} avant de l'inserer dans la BDD
	 * @param pw Mot de passe en clair
	 * @return Mot de passe encrypte
	 */
	public static String encrypt(String pw) {
		
	   final byte[] authBytes = pw.getBytes(StandardCharsets.UTF_8);
	   final String encoded = Base64.getEncoder().encodeToString(authBytes);
	   return encoded;
	}
	
	/**
	 * Cette fonction decrypte une chaine de caractere en {@link Base64}
	 * @param pw Chaine de caracteres en {@link Base64}
	 * @return Retourne le mot de passe en clair
	 */
	public static String decrypt(String pw) {

		final byte[] decodedBytes = Base64.getDecoder().decode(pw);
		final String decoded = new String(decodedBytes,StandardCharsets.UTF_8);
		return decoded;
	}
	
	
	
	

}
