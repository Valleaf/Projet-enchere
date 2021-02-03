package bo;


/**
 * La classe {@link User} contient les informations necessaires pour utilisateur voulant acheter ou vendre sur le site
 * @author Val
 *
 */
public class User {
	private int numero;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String cpo;
	private String ville;
	private int credit;
	private boolean admin;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCpo() {
		return cpo;
	}
	public void setCpo(String cpo) {
		this.cpo = cpo;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	public User() {
		this.credit=0;
		this.admin=false;
	}
	@Override
	public String toString() {
		return "User [numero=" + numero + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", rue=" + rue + ", cpo=" + cpo + ", ville=" + ville
				+ ", credit=" + credit + ", admin=" + admin + "]";
	}
	public User(String pseudo2, String nom2, String prenom2, String email2, String telephone2, String rue2,
			String ville2, String cpo2) {
		this();
		this.pseudo=pseudo2;
		this.nom=nom2;
		this.prenom=prenom2;
		this.email=email2;
		this.telephone=telephone2;
		this.rue=rue2;
		this.ville=ville2;
		this.cpo=cpo2;		
	}
	
	
	
	

}
