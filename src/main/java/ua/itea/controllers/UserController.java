package ua.itea.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.services.UserDaoService;
import ua.itea.services.daofactory.DaoFactory;
import ua.itea.services.mapper.UserMapper;



public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String REGEX_FOR_EMAIL = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
	private static final String REGEX_FOR_PASSWORD = "\\w*[A-Z]+[0-9]+\\w*";
	private static final String NEW_USER_DATA_IS_VALID = "Get valid user info from registration form.";
	private static final String REGISTERED_SUCCESSFULLY = "User registered successfully.";
	private static final String USER_DATA_IS_VALID_TO_UPDATE = "Get valid user info from form to update.";
	private static final String UPDATED_SUCCESSFULLY = "User info updated successfully.";
	private static final Logger LOG = Logger.getLogger(UserController.class.getName());
	
	private String login;
	private String name;
	private String password;
	private String passwordRepeat;
	private String gender;
	private String region;
	private String comment;
	private String browser;
	private String nameFromSession;
	private String result;
	private boolean isValid;
	private List<String> errorList;
	private UserDaoService userDaoService;
    
	public void init() {
		userDaoService = DaoFactory.getDaoFactory().getUserDaoService();	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		nameFromSession = (String) request.getSession().getAttribute("authorized");
		if (nameFromSession != null) {  
			rd = request.getRequestDispatcher("WEB-INF/views/updateUserInfo.jsp");
	    } else {
	    	rd = request.getRequestDispatcher("WEB-INF/views/registration.jsp");
	    }
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getParamsFromRequest(request);
	    if (checkRequestParams(login, name, password, passwordRepeat, gender, region, comment, browser)) {
	    	if (request.getParameter("update") == null) { 
	    		LOG.info(NEW_USER_DATA_IS_VALID);
	    		userDaoService.addUser(UserMapper.mapToUser(login, name, password, gender, region, comment));
	    		result = REGISTERED_SUCCESSFULLY;	    		
	    	} else {
	    		LOG.info(USER_DATA_IS_VALID_TO_UPDATE);
				userDaoService.updateUser(UserMapper.mapToUser(login, name, password, gender, region, comment));
				request.getSession().setAttribute("authorized", name);
				result = UPDATED_SUCCESSFULLY;
	    	}
	    }
		request.setAttribute("errorList", errorList);
		request.setAttribute("result", result);
		doGet(request, response);
	}
	
	private void getParamsFromRequest(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		login = request.getParameter("login");
		name = request.getParameter("name");
	    password = request.getParameter("password");
	    passwordRepeat = request.getParameter("passwordRepeat");
	    gender = request.getParameter("gender");
	    region = request.getParameter("region");
	    comment = request.getParameter("comment");
	    browser = request.getParameter("browser");
	}
	
	private boolean checkRequestParams(String login, String name, String password, String passwordRepeat, 
			String gender, String region, String comment, String browser) {
		result = "";
		isValid = true;
		errorList = new ArrayList<>();
		
		if (login != null) {
			if (login.isEmpty()) {
			isValid = false;
			errorList.add("Email is empty!");
			} else if (!Pattern.matches(REGEX_FOR_EMAIL, login)) {		
				isValid = false;
				errorList.add("Invalid email");
			}
			
			if (name.isEmpty()) {
				isValid = false;
				errorList.add("Name is empty!");
			}
		
			if (password.isEmpty()) {
				isValid = false;
				errorList.add("Password is empty!");
			} else {
				if (password.length() <= 8) {
					isValid = false;
					errorList.add("Password must be more then 8 symbols");
				}
				if (!Pattern.matches(REGEX_FOR_PASSWORD, password)) {
				isValid = false;
				errorList.add("Password must be with minimum 1 capital letter and 1 number");
				}
			}
		
			if (!Objects.equals(password, passwordRepeat)) {
				isValid = false;
				errorList.add("Password fields are not equal");
			}
		
			if (gender == null) {
				isValid = false;
				errorList.add("Gender is not checked!");
			}
		
			if (region.isEmpty()) {
				isValid = false;
				errorList.add("Region is empty!");
			}
		
			if (comment == null || comment.isEmpty()) {
				isValid = false;
				errorList.add("Comment is empty!");
			}
		
			if (!Objects.equals(browser, "on")) {
				isValid = false;
				errorList.add("Browser has to be checked");
			}
		}
		return isValid;
	}

}
