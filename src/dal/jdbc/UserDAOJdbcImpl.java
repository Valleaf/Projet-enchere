package dal.jdbc;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.User;
import dal.CodesResultatDAL;
import dal.ConnectionProvider;
import dal.UserDAO;
import exceptions.BusinessException;
import java.util.Base64;

public class UserDAOJdbcImpl implements UserDAO{
	
	private static final String INSERT = "INSERT INTO UTILISATEURS "
										+ "( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
										+ "VALUES "
										+ "(  ? , 	 ? ,  ?,	  ?,	 ?,			 ?,  ?, 		  ?,     ?,			   ?,	   ?)";

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
				ps.setString(9, encrypt(pw));
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

	private String encrypt(String pw) {
		
	   final byte[] authBytes = pw.getBytes(StandardCharsets.UTF_8);
	   final String encoded = Base64.getEncoder().encodeToString(authBytes);
	   return encoded;
	}
	
	private String decrypt(String pw) {

		final byte[] decodedBytes = Base64.getDecoder().decode(pw);
		final String decoded = new String(decodedBytes,StandardCharsets.UTF_8);
		return decoded;
	}

	@Override
	public List<User> select() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	

}
