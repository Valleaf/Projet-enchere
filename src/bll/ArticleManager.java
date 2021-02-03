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
}
