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
import exceptions.BusinessException;

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
		
		HttpSession session = request.getSession();
		String isLoggedIn2 = (String) session.getAttribute("status");
		if(!(isLoggedIn2 != null && isLoggedIn2.equals("Connecté"))) {
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return;
		}else {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp");
		rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserManager um = new UserManager();
		User ancienpseudo = (User) session.getAttribute("user");
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String telephone = request.getParameter("telephone");
		String cpo = request.getParameter("cpo");
		String ville = request.getParameter("ville");
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		String currentPassword = request.getParameter("currentPassword");
		List<String> listeMsgError = new ArrayList<>();
		boolean conforme = false;
		
		//TODO Faire des codes erreurs constantes au lieu de chaines
		//TODO A l'heure actuelle, il faut que tous les champs soit valables. On pourrait placer en placeholder les champs actuels.
		
		VerificationMsgEtBoolean v = Verification.verificationUtilisateur(pseudo, nom, prenom, email, rue, telephone, cpo, ville, pw, listeMsgError);
		listeMsgError = v.getListeMsgErreurs();
		conforme = v.isTest();
		pseudo = Verification.verifString(pseudo);
		
		pseudo = Verification.verifString(pseudo);
		try {
			if(Verification.isAlreadyTakenPseudo(pseudo) && !(um.selectionnerUnUtilisateur(pseudo).getPseudo().equals(ancienpseudo.getPseudo()))) {
				listeMsgError.add("Ce pseudo existe deja");
			}
		} catch (BusinessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(Verification.isAlreadyTakenEmail(email) && !(ancienpseudo.getEmail().equals(email))) {
			listeMsgError.add("Cet adresse email est deja prise");
		}
		
		if (pw2 == null || !pw.equals(pw2)) {
			listeMsgError.add("Le mdp et la confrimation ne sontpas egaux");
		}
		// le mot de passe courant doit etre conforme
		try {
			if (!currentPassword.equals(Verification.decrypt(um.recupererMDP(ancienpseudo.getPseudo())))) {
				listeMsgError.add("Le mot de passe actuel ne correspond pas");
			}
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(listeMsgError.size()>0) {
			request.setAttribute("listeErreurs", listeMsgError);
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp");
			rd.forward(request, response);
		} else {
		User u = new User(pseudo,nom,prenom,email,telephone,rue,ville,cpo);
		u.setNumero(ancienpseudo.getNumero());
			
		try {
			um.mettreAJourUnUtilisateur(u,pw);
			System.out.println("test");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Il faut que l'utilisateur se connecte et retourne a l'accueil
		String isLoggedIn = "Connecté";
		session.setAttribute("status", isLoggedIn);		
		session.setAttribute("user", u);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
		}
	}




	}


