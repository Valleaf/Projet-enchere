package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bll.EnchereManager;
import bll.UserManager;
import bo.Article;
import bo.Enchere;
import bo.User;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User u = (User) session.getAttribute("user");
		Article a = null;
		
		ArticleManager am = new ArticleManager();
		UserManager um = new UserManager();
		EnchereManager em = new EnchereManager();
		Integer prix = null;
		Integer id = null;
		Timestamp t = new Timestamp(System.currentTimeMillis());
		prix = Integer.parseInt(request.getParameter("encherePrix"));
		id = Integer.parseInt(request.getParameter("articleID"));
		
		try {
			a = am.selectionnerUnArticle(id);
			a.setPrixVente(prix);
			am.modifierUnArticles(a);
			request.setAttribute("dataArticle", a);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(id);
		System.out.println(u.getNumero());
		System.out.println(t);
		System.out.println(prix);
		
		Enchere bid = new Enchere(u.getNumero(),id,t,prix);
		Enchere eux = null;
		try {
			eux = em.selectionnerUneEnchereAvecIds(id,u.getNumero());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(eux.getNoUser());
		System.out.println(eux.getDateEnchere());
		System.out.println(eux.getNoArticle());
		System.out.println(eux.getPrixEnchere());
		
		if (eux.getNoUser() == bid.getNoUser()) {
			try {
				em.mettreAJourUneEnchere(bid);
				System.out.println("test");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		
		try {
			em.creerUneEnchere(bid);
			System.out.println("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailsVente.jsp");
		rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
