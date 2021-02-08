package bo;

import java.sql.Timestamp;

public class Enchere {
	
	private Integer noUser;
	private Integer noArticle;
	private Timestamp dateEnchere;
	private Integer prixEnchere;
	
	public Enchere() {};
	
	public Enchere(Integer noUser, Integer noArticle,Timestamp dateEnchere, Integer prixEnchere) {
		this.noUser=noUser;
		this.noArticle=noArticle;
		this.dateEnchere=dateEnchere;
		this.prixEnchere=prixEnchere;
	}
	
	
	
	public Integer getNoUser() {
		return noUser;
	}
	public void setNoUser(Integer noUser) {
		this.noUser = noUser;
	}
	public Integer getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	public Timestamp getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Integer getPrixEnchere() {
		return prixEnchere;
	}
	public void setPrixEnchere(Integer prixEnchere) {
		this.prixEnchere = prixEnchere;
	}
	
	

}
