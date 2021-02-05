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

import bll.UserManager;
import bll.Verification;
import bll.VerificationMsgEtBoolean;
import bo.User;
import dal.DAOFactory;
import dal.UserDAO;
import exceptions.BusinessException;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String pw = request.getParameter("password");
		String prenom = request.getParameter("prenom");
		String pw2 = request.getParameter("confirmation");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String telephone = request.getParameter("telephone");
		String cpo = request.getParameter("cpo");
		String ville = request.getParameter("ville");
		UserManager um = new UserManager();
		List<String> listeMsgError = new ArrayList<>();
		//TODO Faire des codes erreurs constantes au lieu de chaines
		//TODO Pas forcement besoin de boolean et d'une classe supplementaire
		
		VerificationMsgEtBoolean v = Verification.verificationUtilisateur(pseudo, nom, prenom, email, rue, telephone, cpo, ville, pw, listeMsgError);
		listeMsgError = v.getListeMsgErreurs();
		
		
		pseudo = Verification.verifString(pseudo);
		if(Verification.isAlreadyTakenPseudo(pseudo)) {
			listeMsgError.add("Ce pseudo existe deja");
		}
		
		if(Verification.isAlreadyTakenEmail(email)) {
			listeMsgError.add("Cet adresse email est deja prise");
		}
		
		
		if (!pw.equals(pw2)) {
			listeMsgError.add("Le mdp et la confrimation ne sontpas egaux");
		}
		
		if(listeMsgError.size()>0) {
			request.setAttribute("listeErreurs", listeMsgError);
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
			rd.forward(request, response);
		} else {
		User u = new User(pseudo,nom,prenom,email,telephone,rue,ville,cpo);
		u.setCredit(100);
		try {
			um.creerUnCompte(u, pw, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Il faut que l'utilisateur se connecte et retourne a l'accueil
		HttpSession session = request.getSession();
		String isLoggedIn = "Connecté";
		session.setAttribute("status", isLoggedIn);		
		try {
			session.setAttribute("user", um.selectionnerUnUtilisateur(pseudo));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
		}
	}

}
