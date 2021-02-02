package servlets;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import bll.ArticleManager;
import bo.Article;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		List<Article> article = null;
		article = articleManager.selectionnerTousLesArticles();
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(article);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpRequest request, HttpResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
