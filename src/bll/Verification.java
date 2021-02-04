package bll;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.management.Query;
import javax.servlet.RequestDispatcher;

import dal.DAOFactory;
import exceptions.BusinessException;
import servlets.CodesResultatServlets;

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
		if(s.matches("(.*)[^a-zA-Z\\d\\s:](.*)") ){
			return false;
		}
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
	
	/**
	 * Cette fonction regarde dans la base de donnes si compte avec ce pseudo donnee en parametre existe deja
	 * @param pseudo Le compte a verifier
	 * @return Vrai si le compte existe deja, faux sinon
	 */
	public static boolean isAlreadyTakenPseudo(String pseudo) {
		
		UserManager um = new UserManager();
		try {
			if(um.selectionnerUnUtilisateur(pseudo).getPseudo() == null) {
				return true;
			}
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}
		return false;
	}
	
	
	
	

}
