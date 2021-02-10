package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bll.EnchereManager;
import bo.Article;
import bo.Enchere;
import bo.User;

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
		
			String idArticle = request.getParameter("id");
			ArticleManager am = new ArticleManager();
			EnchereManager em = new EnchereManager();
			Article art = null;
			Enchere e = null;
			boolean isOver = true;
			boolean isStarted = true;
			Timestamp t = new Timestamp(System.currentTimeMillis());
			
			try {
				art = am.selectionnerUnArticle(Integer.parseInt(idArticle));
				e = em.selectionnerEnchereLaPlusHaute(art.getNoArticle());
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
			
			request.setAttribute("dataArticle", art);
		
			
			if (e.getPrixEnchere()==null) {
				e = new Enchere();
				e.setPrixEnchere(art.getPrixInitial());
			}
			request.setAttribute("enchereActive", e);
			if( t.before(art.getDateFin()) ) 
				isOver = false;
			if(t.before(art.getDateDebut()))
				isStarted=false;
			
			request.setAttribute("over",isOver);
			request.setAttribute("started",isStarted);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailsVente.jsp");
			rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
