package ua.itea.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.services.CartDaoService;
import ua.itea.services.daofactory.DaoFactory;

public class CartController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CartDaoService cartService;
	
	public void init() {
		cartService = DaoFactory.getDaoFactory().getCartDaoService();		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/cart.jsp");
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("id");
		Integer productQnt = Integer.parseInt(request.getParameter("qnt"));
		HttpSession session = request.getSession();
		if (productId !=null && !productId.isEmpty()) {
			String remove = request.getParameter("remove");
			if (remove != null) {
				cartService.removeProductFromCart(session, productId, productQnt);
				response.sendRedirect("cart");
			} else {
				cartService.addProductIntoCart(session, productId, productQnt);
				response.getWriter().write(session.getAttribute("totalProductsInCartQnt").toString());
			}
		} else {
			doGet(request, response);
		}
	}

}
