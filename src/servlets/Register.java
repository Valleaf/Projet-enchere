package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		//UtilisateurManager um = UserDAO.getManager();
		
		
		List<String> listeMsgError = new ArrayList<>();
		
		//Validation de chaque parametre !
		//Pseudo doit etre unique et moins de 30 characteres
		if(pseudo.length() > 30) {
			listeMsgError.add("PSEUDOTROPLONG");
		}
		//User u = um.selectByPseudo(pseudo);
		//if (u != null){
		//listeMsgError.add("pseudodejaexistant");
		//}
		
		if(nom.length()>30) {
			listeMsgError.add("nomTROPLONG");
		}
		if(prenom.length()>30) {
			listeMsgError.add("prenomTROPLONG");
		}
		//Le mot de passe doit etre egal a la confrimation et moins de 30caracteres
		//minimum 8 avec caractere special, avec majuscule, minuscule
		//Optionnellement on peut recommander un mdp a l'utilisateur
		if (pw != pw2) {
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
		if(rue.length()>30) {
			listeMsgError.add("ruetroplong");
		}
		if(ville.length()>30) {
			listeMsgError.add("villetroplong");
		}
		if(!verificationCPO(cpo)) {
			listeMsgError.add("cpoinvalide");
		}
		
		if(listeMsgError.size()>0) {
			request.setAttribute("listeErreurs", listeMsgError);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
			rd.forward(request, response);
		}
		User u = new User(pseudo,nom,prenom,email,telephone,rue,ville,cpo);
			
		//um.insert(u,pw);
		//Il faut que l'utilisateur se connecte et retourne a l'accueil
		HttpSession session = request.getSession();
		boolean isLoggedIn = true;
		session.setAttribute("status", isLoggedIn);		
		session.setAttribute("id", pseudo);
		RequestDispatcher rd = request.getRequestDispatcher("/accueil.html");
		rd.forward(request, response);
		
	}

	private boolean verificationCPO(String cpo) {
		// TODO Auto-generated method stub
		//Possible? verification de la ville
		//cpo doit etre entre 00999 et 99999
		return false;
	}

	private boolean verificationTelephone(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean verificationEMail(String email) {
		// TODO Auto-generated method stub
		//Fonctions a voir :
		//verifier que l'email est valide selon un regex
		//Verifier que l'email n;est pas jetable
		//Verifier que l'email existe vraiment
		return false;
	}

	private boolean verificationPW(String pw) {
		// TODO Auto-generated method stub
		return false;
	}

}
