package dal.jdbc;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bll.Verification;
import bo.User;
import dal.CodesResultatDAL;
import dal.ConnectionProvider;
import dal.UserDAO;
import exceptions.BusinessException;

import java.util.ArrayList;
import java.util.Base64;

public class UserDAOJdbcImpl implements UserDAO{
	
	private static final String INSERT = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECTALL = "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,rue ,code_postal ,ville ,mot_de_passe ,credit ,administrateur FROM UTILISATEURS";
	private static final String SELECTBYPSEUDO = SELECTALL + " WHERE pseudo LIKE ?";
	private static final String SELECTBYID = SELECTALL + " WHERE no_utilisateur = ?";
	private static final String DELETEBYID = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?,	 ville = ?, mot_de_passe = ? WHERE no_utilisateur=?";
	
	
	/**
	 * Cette fonction insere un utilisateur dans la BDD.
	 * Les verifications sont faites dans la servlet {@link servlets.Register}}
	 */
	@Override
	public void insert(User u,String pw, boolean isAdmin) throws BusinessException {
		if(u==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement ps = cnx.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, u.getPseudo());
				ps.setString(2, u.getNom());
				ps.setString(3, u.getPrenom());
				ps.setString(4, u.getEmail());
				ps.setString(5, u.getTelephone());
				ps.setString(6, u.getRue());
				ps.setString(7, u.getCpo());
				ps.setString(8, u.getVille());
				ps.setString(9, Verification.encrypt(pw));
				ps.setInt(10,u.getCredit());
				ps.setInt(11, isAdmin? 1 : 0);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					u.setNumero(rs.getInt(1));
				}
				rs.close();
				ps.close();
				cnx.commit();
			}
			catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}

	

	/**
	 * Cette fonction permet de selectionner tous les utilisateurs de la BDD
	 */
	@Override
	public List<User> select() throws BusinessException {
	List<User> listeUsers = new ArrayList<>();
	try(Connection cnx = ConnectionProvider.getConnection()){
		PreparedStatement ps = cnx.prepareStatement(SELECTALL);
		ResultSet rs = ps.executeQuery();
		User userCourant = new User();
		while(rs.next()) {
			if(rs.getInt("no_utilisateur")!=userCourant.getNumero()) {
				userCourant = userBuilder(rs);
				listeUsers.add(userCourant);
			}
		}
		rs.close();
		ps.close();
		cnx.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.LECTURE_USER_ECHEC);
		throw businessException;
	}
		return listeUsers;
	}


	
	@Override
	public User selectById(String pseudo) throws BusinessException {
		User u = new User();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement ps = cnx.prepareStatement(SELECTBYPSEUDO);
			ps.setString(1, pseudo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = userBuilder(rs);
			}
			rs.close();
			ps.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_USER_ECHEC);
			throw businessException;
		}
			return u;
	}
	
	@Override
	public User selectById(int id) throws BusinessException {
		User u = new User();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement ps = cnx.prepareStatement(SELECTBYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = userBuilder(rs);
			}
			rs.close();
			ps.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_USER_ECHEC);
			throw businessException;
		}
			return u;
	}

	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement ps = cnx.prepareStatement(DELETEBYID);
			ps.setInt(1,id);
			ps.executeQuery();
			ps.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private User userBuilder(ResultSet rs) throws SQLException {
		User userCourant = new User();
		userCourant.setNumero(rs.getInt("no_utilisateur"));
		userCourant.setPseudo(rs.getString("pseudo"));
		userCourant.setNom(rs.getString("nom"));
		userCourant.setPrenom(rs.getString("prenom"));
		userCourant.setEmail(rs.getString("email"));
		userCourant.setTelephone(rs.getString("telephone"));
		userCourant.setRue(rs.getString("rue"));
		userCourant.setCpo(rs.getString("code_postal"));
		userCourant.setVille(rs.getString("ville"));
		return userCourant;
	}

	@Override
	public void update(User u,String pw) throws BusinessException {
		if(u==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				PreparedStatement ps = cnx.prepareStatement(UPDATE);
				ps.setString(1, u.getPseudo());
				ps.setString(2, u.getNom());
				ps.setString(3, u.getPrenom());
				ps.setString(4, u.getEmail());
				ps.setString(5, u.getTelephone());
				ps.setString(6, u.getRue());
				ps.setString(7, u.getCpo());
				ps.setString(8, u.getVille());
				ps.setString(9, Verification.encrypt(pw));
				ps.setInt(10,u.getNumero());
				ps.executeUpdate();

				ps.close();
				cnx.commit();
			}
			catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.MODIFIER_OBJET_ECHEC);
				throw businessException;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	
	

}
