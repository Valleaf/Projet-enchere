package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleManager;
import bo.Article;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomFiltre = request.getParameter("nomArticleFiltre");
		String categorieFiltre = request.getParameter("categorieArticleFiltre");
		List<Article> listeA = new ArrayList<>(); 
		
		//ArticleManager am = ArticleManager.getArticleDAO();
		//listeA = am.getArticles();
		
		
		if (nomFiltre !=null) {
			/*
			 * On fait un foreach sur chaque element e de listeA
			 * On verifie que e.getNom contient nomFiltre
			 * Si le nom ne le contient pas, on supprime l'article
			 * */
		}
		
		if (categorieFiltre != null) {
			/*
			 * on fait un foreach sur chaque element e de listeA
			 * Si e.getCategorie n'est pas egal a categorieFiltre, on supprime l'article
			 */
		}
		
		
		request.setAttribute("listeArticles", listeA);
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
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
