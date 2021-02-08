package bll;

import java.sql.SQLException;

import bo.Enchere;
import dal.DAOFactory;
import dal.EnchereDAO;
import exceptions.BusinessException;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public void creerUneEnchere(Enchere e) throws SQLException{
		try {
			this.enchereDAO.insert(e);
		} catch (BusinessException e2) {
			// TODO: handle exception
		}
	}
	
	public void mettreAJourUneEnchere(Enchere e) throws SQLException{
			try {
				this.enchereDAO.modifier(e);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	/**
	 * On recupere une enchere grace aux id de l'article et de l'utilisateur
	 * @param idArticle
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	public Enchere selectionnerUneEnchereAvecIds(int idArticle, int idUser) throws SQLException{
		
		try {
			return this.enchereDAO.selectByID(idArticle,idUser);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
