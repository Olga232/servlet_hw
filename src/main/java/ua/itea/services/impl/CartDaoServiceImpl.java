package ua.itea.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import ua.itea.models.Product;
import ua.itea.services.CartDaoService;
import ua.itea.services.ProductDaoService;
import ua.itea.services.daofactory.DaoFactory;

public class CartDaoServiceImpl implements CartDaoService {
	
	private static final String START_ADDING_PRODUCT_TO_CART = "Starts adding product_id %s (qnt=%d) into cart";
	private static final String START_REMOVING_PRODUCT_TO_CART = "Starts removing product_id %s (qnt=%d) from cart";	
	private static final String NEW_CART_CREATED = "New cart created";
	private static final String PRODUCT_ADDED_SUCCESSFULLY = "Product_id %s (qnt=%d) added to cart successfully";
	private static final String PRODUCT_REMOVED_SUCCESSFULLY = "Product_id %s (qnt=%d) removed from cart successfully";
	private static final Logger LOG = Logger.getLogger(CartDaoServiceImpl.class.getName());
	
	private ProductDaoService productService;
	private Map<Product, Integer> cart;

	public CartDaoServiceImpl() {
		productService = DaoFactory.getDaoFactory().getProductDaoService();
	}

	@Override
	public void addProductIntoCart(HttpSession session, String productId, Integer productQnt) {
		try {
			LOG.info(String.format(START_ADDING_PRODUCT_TO_CART, productId, productQnt));
			if (session.getAttribute("productsInCart") == null) {
				cart = new HashMap<>();
				LOG.info(NEW_CART_CREATED);
			}
			Product product = productService.getProductById(productId);
			if (cart.containsKey(product)) {
				cart.put(product, (cart.get(product)+productQnt));
			} else {
				cart.put(product, productQnt);
			}
			session.setAttribute("productsInCart", cart);
			setTotalProductsQntAndPriceAttributesToSession(session);
			LOG.info(String.format(PRODUCT_ADDED_SUCCESSFULLY, productId, productQnt));
		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}
	
	@Override
	public void removeProductFromCart(HttpSession session, String productId, Integer productQnt) {
		try {
			LOG.info(String.format(START_REMOVING_PRODUCT_TO_CART, productId, productQnt));
			if (session.getAttribute("productsInCart") != null) { 
				Product product = productService.getProductById(productId);
				if (productQnt == cart.get(product)) {
					cart.remove(product);
				} else if (productQnt > 0 && productQnt < cart.get(product)){
					cart.put(product, (cart.get(product)-productQnt));
				}
				session.setAttribute("productsInCart", cart);
				setTotalProductsQntAndPriceAttributesToSession(session);
				LOG.info(String.format(PRODUCT_REMOVED_SUCCESSFULLY, productId, productQnt));
			}
		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}
	
	private void setTotalProductsQntAndPriceAttributesToSession (HttpSession session) {
		int totalProductsQntInCart = countTotalProductsQntInCart(cart);
		session.setAttribute("totalProductsInCartQnt", totalProductsQntInCart);
		int totalProductsCost = countTotalProductsCost(cart);
		session.setAttribute("totalProductsCost", totalProductsCost);
	}
	
	private int countTotalProductsQntInCart(Map<Product, Integer> cart) {
		int totalProductsCounter = 0;
		for (Entry<Product, Integer> entry : cart.entrySet()) {
			totalProductsCounter += entry.getValue();
		}
		return totalProductsCounter;
	}
	
	private int countTotalProductsCost(Map<Product, Integer> cart) {
		int totalProductsCost = 0;
		for (Entry<Product, Integer> entry : cart.entrySet()) {
			totalProductsCost += entry.getKey().getPrice() * entry.getValue();
		}
		return totalProductsCost;
	}
	

}
