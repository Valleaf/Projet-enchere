package dal.jdbc;

import java.util.List;
import java.sql.*;

import bo.Article;
import dal.ArticleDAO;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SQLINSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQLUPDATE = "update ARTICLES_VENDUS set nom_article=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, etat_vente=?, image=?";
	private static final String SQLDELETE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String SQLSELECTALL = "select nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS";
	private static final String SQLSELECTBYID = "select nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where no_article=?";
	
	@Override
	public Article insert(Article data) {
		return null;
	}

	@Override
	public void update(Article data) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
