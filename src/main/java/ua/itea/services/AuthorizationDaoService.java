package ua.itea.services;

public interface AuthorizationDaoService {
	
	String checkLoginAndPassword(String login, String password);
}
