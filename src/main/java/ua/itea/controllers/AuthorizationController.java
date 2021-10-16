package ua.itea.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.services.AuthorizationDaoService;
import ua.itea.services.daofactory.DaoFactory;


public class AuthorizationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String GET_LOGIN_PASSWORD_FROM_AUTH_FORM = "Get login (%s) and password from authorization form.";
	private static final String USER_NOT_FOUND = "User %s is not found. Check your login, password or register.";
	private static final Logger LOG = Logger.getLogger(AuthorizationController.class.getName());
	
	private String result;
	private AuthorizationDaoService authorizationService;
    
	public void init() {
		authorizationService = DaoFactory.getDaoFactory().getAuthorizationDaoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("logout") != null) {  
			unauthorizeSession(request);
	    }
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/authorization.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        result = "";
		if (login != null && !login.isEmpty()) {
			LOG.info(String.format(GET_LOGIN_PASSWORD_FROM_AUTH_FORM, login));
			String userNameFromDb = authorizationService.checkLoginAndPassword(login, password);
			if (userNameFromDb != null) {
				authorizeSessionForRegisteredUser(request, userNameFromDb, login);
			} else {
				result = String.format(USER_NOT_FOUND, login);
			}
		}
		request.setAttribute("result", result);
		doGet(request, response);
	}
	
	private void unauthorizeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("authorized", null);
		session.setAttribute("authorizedUserLogin", null);
        result = "";
        request.setAttribute("result", result);
	}
	
	private void authorizeSessionForRegisteredUser(HttpServletRequest request, String userNameFromDb, String login) {
		HttpSession session = request.getSession();
		session.setAttribute("authorized", userNameFromDb);
		session.setAttribute("authorizedUserLogin", login);
	}

}
