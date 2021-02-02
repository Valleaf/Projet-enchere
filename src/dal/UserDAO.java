package dal;

import java.util.List;

import bo.User;
import exceptions.BusinessException;

public interface UserDAO {
	
	//Creer un nouvel utilisateur
	public void insert(User u,String pw,boolean isAdmin) throws BusinessException;
	
	//Recuperer tous les utilisateurs
	public List<User> select() throws BusinessException;
	
	//Selectionner un utilisateur selon son numero d'identifiant
	public User selectById(int id) throws BusinessException;
	
	//Supprimer un utilisateur
	public void delete() throws BusinessException;
	

}
