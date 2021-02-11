package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bo.Article;
import bo.User;

/**
 * Servlet implementation class MaPageProfil
 */
@WebServlet("/MaPageProfil")
public class MaPageProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaPageProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String isLoggedIn2 = (String) session.getAttribute("status");
		if(!(isLoggedIn2 != null && isLoggedIn2.equals("Connecté"))) {
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return;
		} else {
			List<Article> listeA = new ArrayList<>(); 
			
			ArticleManager am = new ArticleManager();
			User user = new User();
			user = (User) session.getAttribute("user");
    		try {
    			listeA = am.selectionnerParUtilisateurs(user.getNumero());
    			request.setAttribute("listeArticles", listeA);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}        	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/monProfil.jsp");
			rd.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
