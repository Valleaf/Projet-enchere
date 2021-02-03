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
import bo.User;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String telephone = request.getParameter("telephone");
		String cpo = request.getParameter("cpo");
		String ville = request.getParameter("ville");
		UserManager um = new UserManager();
		String pw = request.getParameter("pw");

		String password = request.getParameter("password");
		// Il faut faire laverification QUE l'ancien mot de passe et différent du nouveau
		// il faut aussi faire la modification avec le nouveau mot de passe 
		
		List<String> listeMsgError = new ArrayList<>();

		
		//Validation de chaque parametre !
		//Pseudo doit etre unique et moins de 30 characteres
		if(pseudo == null || pseudo.length() > 30) {
			listeMsgError.add("Pseudo vide ou trop long");
		}
		
		//User u = um.selectByPseudo(pseudo);
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
	
	//	if (!pw.equals(pw2)) {
	//		listeMsgError.add("Le mdp et la confrimation ne sontpas egaux");
	//	}
	//	if (!verificationPW(pw)) {
			listeMsgError.add("Le nom de passe n'est pas conforme");
	//	}
	//	if(!verificationEMail(email)) {
	//		listeMsgError.add("L'email n'est pas conforme");
	//	}
		
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
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp");
			rd.forward(request, response);
		} else {
		User u = new User(pseudo,nom,prenom,email,telephone,rue,ville,cpo);
			
		try {
			um.creerUnCompte(u, pw, false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Il faut que l'utilisateur se connecte et retourne a l'accueil
		HttpSession session = request.getSession();
		boolean isLoggedIn = true;
		session.setAttribute("status", isLoggedIn);		
		session.setAttribute("id", pseudo);
		RequestDispatcher rd = request.getRequestDispatcher("/Achat.html");
		rd.forward(request, response);
		}
	}

	private boolean verificationCPO(String cpo) {
		// TODO Auto-generated method stub
		//Possible? verification de la ville
		//cpo doit etre entre 00999 et 99999
		return true;
	}

	private boolean verificationTelephone(String telephone) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean verificationEMail(String email) {
		// TODO Auto-generated method stub
		//Fonctions a voir :
		//verifier que l'email est valide selon un regex
		//Verifier que l'email n;est pas jetable
		//Verifier que l'email existe vraiment
		return true;
	}

	private boolean verificationPW(String pw) {
		// TODO Auto-generated method stub
		return true;
	}



	}


