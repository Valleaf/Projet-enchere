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
	 * Categorie non choisie
	 */
	public static final int WRONG_CATEGORIE=30004;
	/**
	 * Description trop longue ou vide
	 */
	public static final int ERROR_DESCRIPTION=30005;
	/**
	 * Prix trop bas
	 */
	public static final int WRONG_PRICE=30006;
	/**
	 * URL Incorrecte
	 */
	public static final int WRONG_URL=30007;

}
