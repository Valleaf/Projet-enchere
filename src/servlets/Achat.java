package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Achat
 */
@WebServlet("/Achat")
public class Achat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Achat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateFinEnchere = request.getParameter(dateFinEnchere);
		Date dateaujourdhui = request.getDateHead;
		// Comparer les dates de fin d'enchere avec la date d'aujourd'hui
		// pour afficher les encheres ouvertes
		if (dateFinEnchere>dateaujoudhui) {
			//afficher les articles ou l'utilsateur peut faire une offre
		}
		
		boolean aEncherit = false; 
		//Afficher les articles pour lequels l'utilisateur à mis une offre
		// Mes encheres
		if (aEncherit = true && dateFinEnchere>dateaujourdhui) {
			//Affciher les articles dont la vente n'est pas terminé et qu'une offre à deja ete faite par cette utilisateur
		}
		
		boolean articleRemporté = false;
		// Afficher les encheres terminer et remporter par cette utilisateur
		if (articleRemporté = true && dateFinEnchere>dateaujourdhui)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
