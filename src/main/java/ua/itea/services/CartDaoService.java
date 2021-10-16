package ua.itea.services;

import javax.servlet.http.HttpSession;

public interface CartDaoService {
	
	void addProductIntoCart(HttpSession session, String productId, Integer productQnt);
	void removeProductFromCart(HttpSession session, String productId, Integer productQnt);
	
}
