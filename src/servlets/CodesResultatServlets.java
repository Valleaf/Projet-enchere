package servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	/**
	 * Format pseudo incorrect
	 */
	public static final int LOGIN_PSEUDO_ERREUR=30000;
	/**
	 * Format mot de passe incorrect
	 */
	public static final int LOGIN_PW_ERREUR=30001;
	public static final int LOGIN_VIDE_ERREUR = 30002;
	/**
	 * Format pseudo deja existant
	 */
	public static final int PSEUDO_EXISTANT=30003;	
}
