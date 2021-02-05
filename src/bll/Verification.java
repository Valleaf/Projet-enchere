package bll;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.management.Query;
import javax.naming.directory.ModificationItem;
import javax.servlet.RequestDispatcher;

import org.graalvm.compiler.nodes.calc.IntegerLessThanNode.LessThanOp;

import dal.DAOFactory;
import exceptions.BusinessException;
import servlets.CodesResultatServlets;
import servlets.ModifierProfil;
import servlets.Register;

/**
 * Cette classe sert a verifier les donnees envoyees a la BDD
 * @author Val
 *
 */
public class Verification {
	
	final static String C_SPECIAUX = "!@#$%^&*()";
	
	/**
	 * Cette fonction verifie si la string est nulle, trop longue ou si elle contient des caracteres autre que des espaces, lettres de l'alphabet ou des chiffres 
	 * TODO On pourait ajouter les apostrophes si on les double ainsi que les lettres avec accents
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
				return false;
			}
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Cette fonction verifie que le mot de passe contienne au moins un lettre miniscule, une lettr majuscule, un chiffre, un caractere special, et que la taille soit valide.
	 * La liste des caracteres speciaux necessaires est definie a l'avance.
	 * @param pw Le mot de passe a verifier
	 * @return Vrai si les tests sont bons
	 */
	public static boolean verificationPW(String pw) {
		
		//L'encode rajoute quelques caracteres donc on garde une marge
		if(pw == null || pw.length()>20 || pw.length() < 8) {
			return false;
		} 
		if(!pw.matches("(.*)[a-z](.*)") ){
			return false;
		}
		if(!pw.matches("(.*)[A-Z](.*)") ){
			return false;
		}
		if(!pw.matches("(.*)\\d(.*)") ){
			return false;
		}
		
		if(!pw.matches("(.*)["+C_SPECIAUX+"](.*)") ){
			return false;
		}
		
		return true;
	}
	
	/**
	 * On verifie qu'un email est conforme
	 * @param em L'email a verifier
	 * @return Vrai si les tests sont bons
	 */
	public static boolean verificationEmail(String em) {
		
		if(em == null || em.length()>60 || em.length() < 6) {
			return false;
		} 
		 
		try {
		      InternetAddress emailAddr = new InternetAddress(em);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      return false;		   
		      }
		
		return true;
	}
	
	/**
	 * Cette fonction verifie que l'on utilise un telephone. Ne sont verifies que les numeros francais
	 * @param t Le numero de telephone a verifier
	 * @return Vrai si les tests sont bons
	 */
	public static boolean verificationTelephone(String t) {
		if(t == null || t.length()!=10 ) {
			return false;
		} 
		
		//Ce regex verifie que l'on commence la chaine par un 0, puis que les 9 autres caracteres sont des chiffres de 0 a 9
		if(!t.matches("^0[0-9]{9}$")) {
			return false;
		}
		return true;
	}
	
	public static boolean verificationCPO(String cpo) {
		
		if(cpo == null || cpo.length()!=5 ) {
			return false;
		} 
		//On verifie que le code postal est une chaine de 5 chiffres
		if(!cpo.matches("[0-9]{5}$")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Cette fonction sert a verifier que les champs soient correct lors de l'inscription ou de la modification d'un compte.
	 * Validation de chaque parametre !
	 *	On doit trim et faire une verification qu'il n'y ait pas d'injection SQL
	 *	Pseudo doit etre unique et moins de 30 characteres
	 *	Le mot de passe doit etre egal a la confirmation et moins de 30caracteres
	 *	minimum 8 avec caractere special, avec majuscule, minuscule
	 *	 TODO : Optionnellement on peut recommander un mdp a l'utilisateur
	 * Utilisé par {@link Register} et {@link ModifierProfil}
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param rue
	 * @param telephone
	 * @param cpo
	 * @param ville
	 * @param pw
	 * @return Vrai si tous les tests sont bons
	 */
	public static VerificationMsgEtBoolean verificationUtilisateur(String pseudo, String nom, String prenom, String email, String rue, String telephone, String cpo, String ville, String pw, List<String> errors) {
		boolean vrai = true;
		List<String> listeMsgError = new ArrayList<>();
		
		if(!Verification.string(pseudo)) {
			listeMsgError.add("Pseudo vide, trop long ou caracteres non conformes");
			vrai = false;
		}
		
		if(!Verification.string(nom)) {
			listeMsgError.add("Nom vide ou trop long");
			vrai = false;
		}
		
		if(!Verification.string(prenom)) {
			listeMsgError.add("Nom vide ou trop long");
			vrai = false;
		}


		if (!Verification.verificationPW(pw)) {
			listeMsgError.add("Le nom de passe n'est pas conforme");
			vrai = false;
		}
		if(!Verification.verificationEmail(email)) {
			listeMsgError.add("L'email n'est pas conforme");
			vrai = false;
		}
		
		if(!Verification.verificationTelephone(telephone)) {
			listeMsgError.add("Le telephone n'est pas conforme");
			vrai = false;
		}
		
		if(!Verification.string(rue)) {
			listeMsgError.add("rue trop long ou vide");
			vrai = false;
		}
		if(!Verification.string(ville)) {
			listeMsgError.add("ville troplong ou vide");
			vrai = false;
		}
		if(!Verification.verificationCPO(cpo)) {
			listeMsgError.add("Code Postal invalide");
			vrai = false;
		}
		
		
		return new VerificationMsgEtBoolean(vrai, listeMsgError);
	}
	
	
	

}
