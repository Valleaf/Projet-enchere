package dal.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import bo.Article;
import dal.ArticleDAO;
import dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SQLINSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQLUPDATE = "update ARTICLES_VENDUS set nom_article=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, etat_vente=?, image=? where no_article=?";
	private static final String SQLDELETE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String SQLSELECTALL = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS";
	private static final String SQLSELECTBYID = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where no_article=?";
	private static final String SQLSELECTBYUSER = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where no_utilisateur=?";
	private static final String SQLSELECTBYCATEGORIE = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where no_categorie=?";
	private static final String SQLSELECTBYNAME = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where nom_article LIKE ?";
	private static final String SQLSELECTBYNAMEANDCATEGORIE = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image from ARTICLES_VENDUS where no_categorie=? and nom_article LIKE ?";
	
	@Override
	public void insert(Article data) throws SQLException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(SQLINSERT);
				pstmt.setString(1, data.getNomArticle());
				pstmt.setString(2, data.getDescription());
				pstmt.setTimestamp(3, data.getDateDebut());
				pstmt.setTimestamp(4, data.getDateFin());
				pstmt.setInt(5, data.getPrixInitial().intValue());
				pstmt.setNull(6, Types.NULL);
				pstmt.setInt(7, data.getNoUtilisateur().intValue());
				pstmt.setInt(8, data.getNoCategorie().intValue());
				pstmt.setString(9, data.getEtatVente());
				pstmt.setString(10, data.getImage());
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw new SQLException("Insert failed - " , e);
			}
			
		}
	}

	@Override
	public void update(Article data) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(SQLUPDATE);
				pstmt.setString(1, data.getNomArticle());
				pstmt.setString(2, data.getDescription());
				pstmt.setTimestamp(3, data.getDateDebut());
				pstmt.setTimestamp(4, data.getDateFin());
				pstmt.setInt(5, data.getPrixInitial().intValue());
				pstmt.setInt(6, data.getPrixVente().intValue());
				pstmt.setInt(7, data.getNoUtilisateur().intValue());
				pstmt.setInt(8, data.getNoCategorie().intValue());
				pstmt.setString(9, data.getEtatVente());
				pstmt.setString(10, data.getImage());
				pstmt.setInt(11, data.getNoArticle().intValue());
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw new SQLException("update failed - " , e);
			}
			
		}
		
	}

	@Override
	public void delete(int id) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(SQLDELETE);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw new SQLException("delete failed - " , e);
			}
			
		}
	}

	@Override
	public Article selectById(int id) throws SQLException {
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTBYID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					art = new Article(
						rs.getInt("no_article"),
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
				}
				rs.close();
				pstmt.close();
				cnx.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new SQLException("selectById failed - " , e);
			}
			
		}
		
		return art;
	}

	@Override
	public List<Article> selectByUser(int id) throws SQLException {
		List<Article> liste = new ArrayList<Article>();
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTBYUSER);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					art = new Article(
						rs.getInt("no_article"),
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
				rs.close();
				pstmt.close();
				cnx.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new SQLException("selectByUser failed - " , e);
			}
			
		}
		
		return liste;
	}

	@Override
	public List<Article> selectByCategory(int id) throws SQLException {
		List<Article> liste = new ArrayList<Article>();
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTBYCATEGORIE);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					art = new Article(
						rs.getInt("no_article"),
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
				rs.close();
				pstmt.close();
				cnx.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new SQLException("selectByCategorie failed - " , e);
			}
			
		}
		
		return liste;
	}

	@Override
	public List<Article> selectByName(String name) throws SQLException {
		List<Article> liste = new ArrayList<Article>();
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTBYNAME);
				pstmt.setString(1, "%" + name + "%");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					art = new Article(
						rs.getInt("no_article"),
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
				rs.close();
				pstmt.close();
				cnx.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new SQLException("selectByName failed - " , e);
			}	
		}
		return liste;
	}

	@Override
	public List<Article> selectByNameAndCategory(String name, int id) throws SQLException {
		List<Article> liste = new ArrayList<Article>();
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTBYNAMEANDCATEGORIE);
				pstmt.setInt(1, id);
				pstmt.setString(2, "%" + name + "%");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					art = new Article(
						rs.getInt("no_article"),
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
				rs.close();
				pstmt.close();
				cnx.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new SQLException("selectByName failed - " , e);
			}	
		}
		return liste;
	}
	
	@Override
	public List<Article> selectAll() throws SQLException {
		List<Article> liste = new ArrayList<Article>();
		Article art = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SQLSELECTALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				art = new Article(
					rs.getInt("no_article"),
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
			rs.close();
			pstmt.close();
			cnx.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new SQLException("selectByAll failed - " , e);
		}
		return liste;
	}
}
