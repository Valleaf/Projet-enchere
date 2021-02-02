package dal;

import java.util.List;

import bo.Article;

public interface ArticleDAO {

	public Article selectById(int id);
	
	//Sélectionner tous les articles 
	public List<Article> selectAll() ;
	
	//Modifier les attributs d'un article connu en BD
	public void update(Article data);
	
	//Insérer un nouvel article
	public Article insert(Article data);
	
	//Supprimer un article
	public void delete(int id);

}
