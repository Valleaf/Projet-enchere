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
import bo.User;
import exceptions.BusinessException;

/**
 * Servlet implementation class SupressionCompte
 */
@WebServlet("/Delete")
public class SupressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		HttpSession session = request.getSession();
		String isLoggedIn2 = (String) session.getAttribute("status");
		if(isLoggedIn2 != null && isLoggedIn2.equals("Connecté")) {
			String s = "Deconnecté";
			session.setAttribute("status", s);
			User u = (User) session.getAttribute("user");
			try {
				um.supprimerUnUtilisateur(u.getNumero());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
