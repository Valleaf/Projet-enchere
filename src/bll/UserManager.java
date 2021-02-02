package bll;

import java.sql.SQLException;

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
	

}
