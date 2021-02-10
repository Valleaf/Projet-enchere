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
import bll.Verification;
import bo.Article;
import bo.Enchere;
import bo.User;
import exceptions.BusinessException;

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
		User uPrecedent = null;
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

		
		Enchere bid = new Enchere(u.getNumero(),id,t,prix);
		Enchere eOfUser = null;
		Enchere highest = null;
		//On regarde si l'utilisateur a deja encheri sur l'item
		try {
			eOfUser = em.selectionnerUneEnchereAvecIds(id,u.getNumero());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//On cherche l'enchere la plus haute
		try {
			highest = em.selectionnerEnchereLaPlusHaute(id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//On cree l'enchere et on debite l'acheteur
		try {
			if(eOfUser.getNoUser() != null) {
				eOfUser = bid;
				em.mettreAJourUneEnchere(eOfUser);
			} else {
				em.creerUneEnchere(bid);
			}
			u.setCredit(u.getCredit() - prix);
			um.mettreAJourUnUtilisateurCredit(u);
		} catch (SQLException | BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//On rembourse l'acheteur de l'enchere precedente
		if (highest.getNoUser() != null) {
			try {
				uPrecedent = um.selectionnerUnUtilisateur(highest.getNoUser());
				uPrecedent.setCredit(uPrecedent.getCredit() + highest.getPrixEnchere());
				um.mettreAJourUnUtilisateurCredit(uPrecedent);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//TODO: on pourrait utiliser l'etat de l'article a la place
		boolean isOver = true;
		if( t.before(a.getDateFin()) ) 
			isOver = false;
		
		boolean isStarted = true;
		if(t.after(a.getDateDebut()) ) 
			isStarted = false;
		
		
		
		
		request.setAttribute("over",isOver);
		request.setAttribute("started",isStarted);
		request.setAttribute("time",new Timestamp(System.currentTimeMillis()));
		request.setAttribute("enchereActive", bid);
		
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
