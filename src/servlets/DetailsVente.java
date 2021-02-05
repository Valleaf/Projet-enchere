package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bo.Article;

/**
 * Servlet implementation class DetailsVente
 */
@WebServlet("/DetailsVente")
public class DetailsVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String isLoggedIn2 = (String) session.getAttribute("status");
		if(!(isLoggedIn2 != null && isLoggedIn2.equals("Connecté"))) {
			RequestDispatcher rd = request.getRequestDispatcher("/Login");
			rd.forward(request, response);
			return;
		} else {
			String idArticle = request.getParameter("id");
			ArticleManager am = new ArticleManager();
			Article art = null;
			try {
				art = am.selectionnerUnArticle(Integer.parseInt(idArticle));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("dataArticle", art);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailsVente.jsp");
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
