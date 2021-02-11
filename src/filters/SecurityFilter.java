package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.User;

import javax.servlet.DispatcherType;


/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
, urlPatterns = { "/DetailsVente","/Encherir/*","/MaPageProfil","/Profil*","/RegisterArticle/*","/Delete/*" })
public class SecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		//des infos sur l'utilisateur connecté sont elles présentes dans la session ?
		
		String isLoggedIn = (String) session.getAttribute("status");
		if((isLoggedIn != null && !isLoggedIn.equals("Connecté"))|| isLoggedIn == null )  {
			//intercepter la requete et établir une redirection
			httpRequest.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			//httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login");
		}
		else {
			//laisser passer la requete
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
