package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bll.UserManager;
import bo.Article;
import bo.User;
import exceptions.BusinessException;

/**
 * Servlet implementation class ProfilsAutre
 */
@WebServlet("/Profils")
public class ProfilsAutre extends HttpServlet {
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
			String idUser = request.getParameter("id");
			UserManager um = new UserManager();
			User u= null;
			try {
				u = um.selectionnerUnUtilisateur(Integer.parseInt(idUser));
			} catch (NumberFormatException | BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.setAttribute("dataUser", u);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profilAutre.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
