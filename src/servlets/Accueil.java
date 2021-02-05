package servlets;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name="/Accueil",urlPatterns={"/index.html"})
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categorieFiltre = 0;
		String nomFiltre = null;

		// on test si un parametre de recherche existe et on le recupère si c'est le cas
		if(request.getParameterMap().containsKey("categorieArticleFiltre")) {
			categorieFiltre = Integer.parseInt(request.getParameter("categorieArticleFiltre"));
		}
		if(request.getParameterMap().containsKey("nomArticleFiltre")){
			nomFiltre = request.getParameter("nomArticleFiltre");
		}
		
		List<Article> listeA = new ArrayList<>(); 
		
		ArticleManager am = new ArticleManager();
        if(categorieFiltre != 0) {
        	if(nomFiltre != null) {
        		try {
        			listeA = am.selectionnerParNomEtCategorie(nomFiltre, categorieFiltre);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}        	
        		
        	}else {
        		try {
        			listeA = am.selectionnerParCategorie(categorieFiltre);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}        	
        		
        	}
        }else if(nomFiltre != null){
        	if(categorieFiltre != 0) {
        		try {
        			listeA = am.selectionnerParNomEtCategorie(nomFiltre, categorieFiltre);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}        	
        		
        	}else {
        		try {
        			listeA = am.selectionnerParNom(nomFiltre);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}        	
        		
        	}
        	
        }else {
    		try {
    			listeA = am.selectionnerTousLesArticles();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}        	
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
