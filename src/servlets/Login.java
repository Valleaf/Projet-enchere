package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.UserManager;
import bll.Verification;
import exceptions.BusinessException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String isLoggedIn2 = (String) session.getAttribute("status");
		if(isLoggedIn2 != null && isLoggedIn2.equals("Connecté")) {
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
			return;
		} else {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
	
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");

			//On verifie que les champs soient remplis
			if(pseudo.isBlank()|| password.isBlank()) {
				request.setAttribute("messageerreur",CodesResultatServlets.LOGIN_VIDE_ERREUR);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}

			UserManager um = new UserManager();
			//On verifie que l'utilisateur avec ce pseudo existe
			
				try {
					if(um.selectionnerUnUtilisateur(pseudo).getPseudo() == null) {
						System.out.println("test");
						request.setAttribute("messageerreur",CodesResultatServlets.LOGIN_PSEUDO_ERREUR);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
						rd.forward(request, response);
						return;
					}
				} catch (BusinessException e2) {
					e2.printStackTrace();
				}
			
			//On verifie que le password entre est le bon
			try {
				if (password.equals(Verification.decrypt(um.recupererMDP(pseudo)))) {
					String isLoggedIn = "Connecté";
					session.setAttribute("status", isLoggedIn);
					session.setAttribute("user", um.selectionnerUnUtilisateur(pseudo));
					RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
					rd.forward(request, response);
					return;
				} else {
					request.setAttribute("messageerreur",CodesResultatServlets.LOGIN_PW_ERREUR);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
					rd.forward(request, response);
				return;
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}

