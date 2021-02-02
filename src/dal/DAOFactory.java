package dal;

import dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
