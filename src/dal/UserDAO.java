package dal;

import java.util.List;

import bo.User;
import exceptions.BusinessException;

public interface UserDAO {
	
	//Creer un nouvel utilisateur
	public void insert(User u,String pw,boolean isAdmin) throws BusinessException;
	
	//Recuperer tous les utilisateurs
	public List<User> select() throws BusinessException;
	
	//Selectionner un utilisateur selon son pseudo
	public User selectById(String pseudo) throws BusinessException;
	
	//Supprimer un utilisateur
	void delete(int id) throws BusinessException;

	//Selectionner un utilisateur selon son numero d'identifiant
	public User selectById(int id) throws BusinessException;

	//Mettre a jour un utilisateur
	public void update(User u,String pw) throws BusinessException;

	//Recupere le mot de passe d'un utilsateur
	public String getPW(String s) throws BusinessException;


	

}
