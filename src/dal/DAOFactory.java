package dal;

import dal.jdbc.ArticleDAOJdbcImpl;
import dal.jdbc.UserDAOJdbcImpl;

public class DAOFactory {
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static UserDAO getUserDAO() {
			return new UserDAOJdbcImpl();
	}
}
