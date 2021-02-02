package bo;

import java.sql.Timestamp;

public class Article {
	
	//attributs d'instance
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private Timestamp dateDebut;
	private Timestamp dateFin;
	private Integer prixInitial;
	private Integer prixVente;
	private Integer noUtilisateur;
	private Integer noCategorie;
	private String etatVente;
	private String Image;
	
	public Article() {
		
	}
	
	public Article(Integer noArticle, String nomArticle, String description, Timestamp dateDebut, Timestamp dateFin,
			Integer prixInitial, Integer prixVente, Integer noUtilisateur, Integer noCategorie, String etatVente,
			String image) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.etatVente = etatVente;
		Image = image;
	}

	/**
	 * @return the noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}
	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dateDebut
	 */
	public Timestamp getDateDebut() {
		return dateDebut;
	}
	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * @return the dateFin
	 */
	public Timestamp getDateFin() {
		return dateFin;
	}
	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * @return the prixInitial
	 */
	public Integer getPrixInitial() {
		return prixInitial;
	}
	/**
	 * @param prixInitial the prixInitial to set
	 */
	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}
	/**
	 * @return the prixVente
	 */
	public Integer getPrixVente() {
		return prixVente;
	}
	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}
	/**
	 * @return the noUtilisateur
	 */
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	/**
	 * @return the noCategorie
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}
	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * @return the etatVente
	 */
	public String getEtatVente() {
		return etatVente;
	}
	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return Image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		Image = image;
	}
	
}
