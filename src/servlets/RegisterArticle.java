package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bll.Verification;
import bll.VerificationMsgEtBoolean;
import bo.Article;
import bo.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterArticle")
public class RegisterArticle extends HttpServlet {
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
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/registerArticle.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = user.getNumero();
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorieArticle"));
		String heureDebutString = request.getParameter("heureDebut");
		String heureFinString = request.getParameter("heureFin");
		Timestamp heureDebut = null;
		Timestamp heureFin = null;
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		String image = request.getParameter("image");
		List<String> listeMsgError = new ArrayList<>();
		System.out.println(nomArticle);
		System.out.println(prixInitial);
		VerificationMsgEtBoolean v = Verification.verificationVente(nomArticle, description, categorie, prixInitial, image, listeMsgError);
		listeMsgError = v.getListeMsgErreurs();
		
		if(heureDebutString != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			try {
				Date dateDedutSDF = (Date) formatter.parse(heureDebutString);
				Long time = dateDedutSDF.getTime();
				heureDebut = new Timestamp(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if(heureFinString != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			try {
				Date dateFinSDF = (Date) formatter.parse(heureFinString);
				Long time = dateFinSDF.getTime();
				heureFin = new Timestamp(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if(listeMsgError.size()>0) {
			request.setAttribute("listeErreurs", listeMsgError);
			System.out.println("une erreur");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/registerArticle.jsp");
			rd.forward(request, response);
		} else {
			Article newArticle = new Article();
			newArticle.setNomArticle(nomArticle);
			newArticle.setDescription(description);
			newArticle.setDateDebut(heureDebut);
			newArticle.setDateFin(heureFin);
			newArticle.setPrixInitial(prixInitial);
			newArticle.setNoUtilisateur(id);
			newArticle.setNoCategorie(categorie);
			newArticle.setEtatVente("EC");
			newArticle.setImage(image);
			ArticleManager am = new ArticleManager();
			try {
				am.ajouterUnArticles(newArticle);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
		}
		
	}

}
