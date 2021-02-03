package dal;

import java.sql.SQLException;
import java.util.List;

import bo.Article;

public interface ArticleDAO {

	public Article selectById(int id) throws SQLException;
	
	//Sélectionner tous les articles 
	public List<Article> selectAll() throws SQLException ;
	
	//Modifier les attributs d'un article connu en BD
	public void update(Article data) throws SQLException;
	
	//Insérer un nouvel article
	public void insert(Article data) throws SQLException;
	
	//Supprimer un article
	public void delete(int id) throws SQLException;

}
