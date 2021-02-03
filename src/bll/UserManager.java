package bll;

import java.sql.SQLException;
import java.util.List;

import bo.User;
import dal.DAOFactory;
import dal.UserDAO;
import exceptions.BusinessException;

public class UserManager {
	
	private UserDAO userDAO;
	
	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}
	
	public void creerUnCompte(User u,String pw, boolean isAdmin) throws SQLException{
		try {
			this.userDAO.insert(u, pw, isAdmin);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> selectionnerTousLesUsers() throws BusinessException{
			return this.userDAO.select();
	}
	
	public User selectionnerUnUtilisateur(String pseudo) throws BusinessException{
		return this.userDAO.selectById(pseudo);
	}
	
	public User selectionnerUnUtilisateur(int id) throws BusinessException{
		return this.userDAO.selectById(id);
	}	
	
	public void supprimerUnUtilisateur(int id) throws BusinessException{
		this.userDAO.delete(id);
	}
	
	public void mettreAJourUnUtilisateur(User u,String pw) throws BusinessException{
		this.userDAO.update(u,pw);
	}

}
