package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Article;
import dal.ArticleDAO;
import dal.DAOFactory;

public class ArticleManager {
	
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	//Sélectionner tous les articles 
	public List<Article> selectionnerTousLesArticles() throws SQLException{
		
		return this.articleDAO.selectAll();
		
	}
	//Séléctionner un article par son ID Utilisateur
	public List<Article> selectionnerParUtilisateurs(int id) throws SQLException{
		
		return this.articleDAO.selectByUser(id);
	}
	
	//Séléctionner un article par son ID de Categorie
	public List<Article> selectionnerParCategorie(int id) throws SQLException{
		
		return this.articleDAO.selectByCategory(id);
	}

	//Séléctionner un article par son Nom
	public List<Article> selectionnerParNom(String name) throws SQLException{
		
		return this.articleDAO.selectByName(name);
	}
	
	//Séléctionner un article par Nom et par Categorie
	public List<Article> selectionnerParNomEtCategorie(String name, int id) throws SQLException{
		
		return this.articleDAO.selectByNameAndCategory(name, id);
	}
	
	public Article selectionnerUnArticle(int id) throws SQLException{
		
		return this.articleDAO.selectById(id);
		
	}
	
	//Insérer un nouvel article
	public void ajouterUnArticles(Article data) throws SQLException{
		
		this.articleDAO.insert(data);
		
	}
	
	//Modifier les attributs d'un article connu en BD
	public void modifierUnArticles(Article data) throws SQLException{
		
		this.articleDAO.update(data);
		
	}
	
	//Supprimer un article
	public void supprimerUnArticle(int id) throws SQLException{
		
		this.articleDAO.delete(id);
		
	}
}
