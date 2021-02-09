package listeners;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bll.ArticleManager;
import bo.Article;

/**
 * Application Lifecycle Listener implementation class ArticleListener
 *
 */
@WebListener
public class ArticleListener implements ServletContextListener {
	
	private final static long TEMPS_ACTUALISATION_MS = 60000; //60 secondes
	
	private Thread asyncTask = null;
	private boolean start = true;


	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @SuppressWarnings("deprecation")
	public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("L'application Livecoding5 s'est arretée !");
    	start = false;
    	asyncTask.stop();
    	System.out.println("Le traitement asychrone s'arrete !");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("L'application Livecoding5 a démarré !");
  	  	//description du traitement associé à la tâche asynchrone
    	asyncTask =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(start){
						ArticleManager am = new ArticleManager();
						List<Article> list = new ArrayList<Article>();
						Timestamp aujourdhui = new Timestamp(System.currentTimeMillis());
						try {
							list = am.selectionnerTousLesArticles();
							for (Article article : list) {
								switch (article.getEtatVente()) {
									case "CR": 
										if(article.getDateDebut().before(aujourdhui)) {
								    		article.setEtatVente("EC");
								    		am.modifierUnArticles(article);
								    	} break;
									case "EC": if(article.getDateFin().before(aujourdhui)) {
							    		article.setEtatVente("VD");
							    		am.modifierUnArticles(article);
							    	} break;
							    	default: break;
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
	                    Thread.sleep(TEMPS_ACTUALISATION_MS);
	                }	
	            } catch (InterruptedException e) {
	            	e.printStackTrace();
	            }	
			}
		});
    	asyncTask.start(); //lancement d'une tâche asynchrone... processus indépendant du processus lié à l'application
    }
	
}
