package dal.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import bo.Article;
import dal.ArticleDAO;
import dal.ConnectionProvider;

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
		
	}


	@Override
	public void delete(int id) {
		
	}

	@Override
	public Article selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAll() throws SQLException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(SQLSELECTALL);
			Article art = null;

			while (rs.next()) {
					art = new Article(
						1,
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getTimestamp("date_debut_enchere"),
						rs.getTimestamp("date_fin_enchere"),
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), 
						rs.getInt("no_utilisateur"), 
						rs.getInt("no_categorie"), 
						rs.getString("etat_vente"), 
						rs.getString("image")
					);
					liste.add(art);

				}
		} catch (SQLException e) {
			throw new SQLException("selectAll failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return liste;
	}

}
