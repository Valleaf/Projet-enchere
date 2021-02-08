package dal;

import bo.Enchere;
import exceptions.BusinessException;

public interface EnchereDAO {

	//Creer une enchere
	public void insert(Enchere e) throws BusinessException;

	public void modifier(Enchere e) throws BusinessException;

	public Enchere selectByID(int idArticle, int idUser) throws BusinessException;

}
