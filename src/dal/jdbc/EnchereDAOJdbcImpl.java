package dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bo.Enchere;
import bo.User;
import dal.CodesResultatDAL;
import dal.ConnectionProvider;
import dal.EnchereDAO;
import exceptions.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERT = "INSERT INTO encheres VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE encheres SET date_enchere = ?, montant_enchere = ? WHERE no_utilisateur= ? AND no_article = ?";
	private static final String SELECTBYIDANDID = "SELECT no_utilisateur, no_article ,date_enchere ,montant_enchere  FROM ENCHERES  WHERE no_utilisateur= ? AND no_article = ?";
	
	@Override
	public void insert(Enchere e) throws BusinessException {
		if(e==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}		
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement ps = cnx.prepareStatement(INSERT);
				ps.setInt(1, e.getNoUser());
				ps.setInt(2, e.getNoArticle());
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.setInt(4, e.getPrixEnchere());
				ps.execute();
			} catch (Exception e1) {
				e1.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void modifier(Enchere e) throws BusinessException {
		if(e==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}		
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				System.out.println("Dans la dao");
				PreparedStatement ps = cnx.prepareStatement(UPDATE);
				ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				ps.setInt(2, e.getPrixEnchere());
				ps.setInt(3, e.getNoUser());
				ps.setInt(4, e.getNoArticle());
				ps.executeUpdate();
				ps.close();
				cnx.commit();
			} catch (Exception e1) {
				e1.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public Enchere selectByID(int idArticle, int idUser) throws BusinessException {
		
		Enchere e = new Enchere();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement ps = cnx.prepareStatement(SELECTBYIDANDID);
			ps.setInt(1, idUser);
			ps.setInt(2, idArticle);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e = enchereBuilder(rs);
			}
			rs.close();
			ps.close();
			cnx.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_USER_ECHEC);
			throw businessException;
		}
			return e;
	}

	private Enchere enchereBuilder(ResultSet rs) throws SQLException {
		Enchere enchereCourant = new Enchere();
		enchereCourant.setNoUser(rs.getInt("no_utilisateur"));
		enchereCourant.setDateEnchere(rs.getTimestamp("date_enchere"));
		enchereCourant.setNoArticle(rs.getInt("no_article"));
		enchereCourant.setPrixEnchere(rs.getInt("montant_enchere"));
		return enchereCourant;
	}
	
	
	

}
