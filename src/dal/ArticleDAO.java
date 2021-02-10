package dal;

import java.sql.SQLException;
import java.util.List;

import bo.Article;

public interface ArticleDAO {

	//Séléctionner un article par son ID
	public Article selectById(int id) throws SQLException;
	
	//Séléctionner un article par son ID Utilisateur
	public List<Article> selectByUser(int id) throws SQLException;
	
	//Séléctionner un article par son ID de Categorie
	public List<Article> selectByCategory(int id) throws SQLException;

	//Séléctionner un article par son Nom
	public List<Article> selectByName(String name) throws SQLException;
	
	//Séléctionner un article par son Nom et par categorie
	public List<Article> selectByNameAndCategory(String name, int id) throws SQLException;
		
	//Sélectionner tous les articles 
	public List<Article> selectAll() throws SQLException ;
	
	//Modifier les attributs d'un article connu en BD
	public void update(Article data) throws SQLException;
	
	//Insérer un nouvel article
	public void insert(Article data) throws SQLException;
	
	//Supprimer un article
	public void delete(int id) throws SQLException;

	public List<Article> selectByPage(int offset) throws SQLException;

}
