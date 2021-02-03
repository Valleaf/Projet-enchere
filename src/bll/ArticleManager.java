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
	
	public List<Article> selectionnerTousLesArticles() throws SQLException{
		
		return this.articleDAO.selectAll();
		
	}
	
	public Article selectionnerUnArticle(int id) throws SQLException{
		
		return this.articleDAO.selectById(id);
		
	}
	
	public void ajouterUnArticles(Article data) throws SQLException{
		
		this.articleDAO.insert(data);
		
	}
	
	public void modifierUnArticles(Article data) throws SQLException{
		
		this.articleDAO.update(data);
		
	}
	
	public void supprimerUnArticle(int id) throws SQLException{
		
		this.articleDAO.delete(id);
		
	}
}
