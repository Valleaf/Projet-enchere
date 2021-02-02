package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean isLoggedIn2 = (boolean) session.getAttribute("status");
		//TODO A verifier si le status null peut faire un bug
		if(isLoggedIn2) {
			RequestDispatcher rd = request.getRequestDispatcher("/accueil.html");
			rd.forward(request, response);
		}
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		
		if(pseudo != null && password != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(request, response);
		}
		//Recuperer le mot de passe a partir de la BDD
		//User utilsateur = UserManager.selectbyPseudo(pseudo);
		//String pw = utilisateur.getPassword;
		//if (pw == password) {
			//Creer une session ou cookie
			boolean isLoggedIn = true;
			session.setAttribute("status", isLoggedIn);
			session.setAttribute("id", pseudo);
		
	//	} else {
			//Envoyer message erreur et tu renvoie sur la meme page
		//request.setAttribute("messagerrreur",error);
		//	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	//		rd.forward(request, response);
		//}
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/accueil.html");
		rd.forward(request, response);
	}

}
