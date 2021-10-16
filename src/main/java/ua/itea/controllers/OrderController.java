package ua.itea.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		if (session.getAttribute("productsInCart") != null) {
			// order processing
			session.setAttribute("productsInCart", null);
			session.setAttribute("totalProductsInCartQnt", null);
			rd = request.getRequestDispatcher("WEB-INF/views/order.jsp");
		} else {
			rd = request.getRequestDispatcher("WEB-INF/views/cart.jsp");
		}
		rd.forward(request, response);
	}


}
