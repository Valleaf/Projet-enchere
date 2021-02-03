package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UserManager;
import bo.User;
import exceptions.BusinessException;
import bll.UserManager;

/**
 * Servlet implementation class TestSelectUser
 */
@WebServlet("/TestSelectUser")
public class TestSelectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserManager um = new UserManager();
	User user = null;
	try {
		user = um.selectionnerUnUtilisateur(5);
	}
	catch (BusinessException e) {
		e.printStackTrace();
	}
	
		System.out.println(user.toString());
	
	response.getWriter().append("Served at : ").append("Liste des users");
	
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
