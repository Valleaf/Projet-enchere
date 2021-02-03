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
import bo.User;
import dal.DAOFactory;
import dal.UserDAO;

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

		
		//Validation de chaque parametre !
		//On doit trim et faire une verification qu'il n'y ait pas d'injection SQL
		//Pseudo doit etre unique et moins de 30 characteres
		if(!Verification.string(pseudo)) {
			listeMsgError.add("Pseudo vide ou trop long");
		}
		pseudo = Verification.verifString(pseudo);
		
		//TODO User u = um.selectByPseudo(pseudo);
		//if (u != null){
		//listeMsgError.add("pseudodejaexistant");
		//}
		
		if(nom== null || nom.length() > 30) {
			listeMsgError.add("Nom vide ou trop long");
		}
		
		if(prenom== null || prenom.length()>30) {
			listeMsgError.add("prenom vide ou trop long");
		}
		//Le mot de passe doit etre egal a la confrimation et moins de 30caracteres
		//minimum 8 avec caractere special, avec majuscule, minuscule
		//Optionnellement on peut recommander un mdp a l'utilisateur
		if (!pw.equals(pw2)) {
			listeMsgError.add("Le mdp et la confrimation ne sontpas egaux");
		}
		if (!verificationPW(pw)) {
			listeMsgError.add("Le nom de passe n'est pas conforme");
		}
		if(!verificationEMail(email)) {
			listeMsgError.add("L'email n'est pas conforme");
		}
		
		if(!verificationTelephone(telephone)) {
			listeMsgError.add("Le telephone n'est pas conforme");
		}
		
		//verification que l'adresse existe vraiment
		if(rue==null || rue.length()>30) {
			listeMsgError.add("ruetroplong ou vide");
		}
		if(ville==null || ville.length()>30) {
			listeMsgError.add("villetroplong ou vide");
		}
		if(!verificationCPO(cpo)) {
			listeMsgError.add("cpoinvalide");
		}
		
		if(listeMsgError.size()>0) {
			request.setAttribute("listeErreurs", listeMsgError);
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
			rd.forward(request, response);
		} else {
		User u = new User(pseudo,nom,prenom,email,telephone,rue,ville,cpo);
		
		try {
			um.creerUnCompte(u, pw, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Il faut que l'utilisateur se connecte et retourne a l'accueil
		HttpSession session = request.getSession();
		String isLoggedIn = "Connecté";
		session.setAttribute("status", isLoggedIn);		
		//TODO recuperer l'id au lieu du pseudo
		session.setAttribute("id", pseudo);
		RequestDispatcher rd = request.getRequestDispatcher("/Achat.html");
		rd.forward(request, response);
		}
	}

	private boolean verificationCPO(String cpo) {
		//Possible? verification de la ville
		//cpo doit etre entre 00999 et 99999
		return true;
	}

	private boolean verificationTelephone(String telephone) {
		return true;
	}

	private boolean verificationEMail(String email) {
		//Fonctions a voir :
		//verifier que l'email est valide selon un regex
		//Verifier que l'email n;est pas jetable
		//Verifier que l'email existe vraiment
		return true;
	}

	private boolean verificationPW(String pw) {
		return true;
	}

}
