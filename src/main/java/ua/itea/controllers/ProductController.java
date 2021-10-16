package ua.itea.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.models.Product;
import ua.itea.services.ProductDaoService;
import ua.itea.services.daofactory.DaoFactory;

public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String GET_REQUEST_ALL_PRODUCTS = "Get request to select products.";
	private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
	
	private List<Product> listOfProducts;
	private ProductDaoService productService;
    
	public void init() {
		productService = DaoFactory.getDaoFactory().getProductDaoService();		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info(GET_REQUEST_ALL_PRODUCTS);
		String categoryId = request.getParameter("category");
		String productId = request.getParameter("id");
		RequestDispatcher rd;
		if (productId != null && !productId.isEmpty()) {
			Product product = productService.getProductById(productId);
			request.setAttribute("product", product);
			rd = request.getRequestDispatcher("WEB-INF/views/product.jsp");
		} else {
			listOfProducts = productService.getAllProductsByCategoryId(categoryId);
			request.setAttribute("listOfProducts", listOfProducts);
			rd = request.getRequestDispatcher("WEB-INF/views/products.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
