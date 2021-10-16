package ua.itea.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ua.itea.models.Product;
import ua.itea.services.DbConnector;
import ua.itea.services.ProductDaoService;

public class ProductDaoServiceImpl implements ProductDaoService {
	
	public static final String SELECT_ALL_PRODUCTS = "SELECT p.id, p.name, p.description, p.price FROM product p";
	public static final String SELECT_ALL_PRODUCTS_BY = SELECT_ALL_PRODUCTS
			+ " JOIN product_category pc on pc.product_id = p.id "
			+ "JOIN category c on c.id = pc.category_id ";
	public static final String SELECT_ALL_PRODUCTS_BY_CATEGORY_ID = SELECT_ALL_PRODUCTS_BY + "WHERE c.id = ";
	public static final String SELECT_PRODUCT_BY_ID = SELECT_ALL_PRODUCTS + " WHERE p.id = ";
	private static final String START_SELECTING_PRODUCTS = "Starts selecting all products from DB";
	private static final String START_SELECTING_PRODUCTS_BY_CATEGORY = "Starts selecting products by category_id %s from DB";
	private static final String START_SELECTING_PRODUCT_BY_ID = "Starts selecting product by product_id %s from DB";
	private static final String PRODUCTS_SELECTED_SUCCESSFULLY = "Products selected from DB successfully";
	private static final String PRODUCT_SELECTED_BY_ID_SUCCESSFULLY = "Product_id %s selected from DB successfully";
	private static final String ERROR_SELECTING_PRODUCTS = "Error occurred during products selecting from DB: ";
	private static final Logger LOG = Logger.getLogger(ProductDaoServiceImpl.class.getName());

	@Override
	public List<Product> getAllProductsByCategoryId(String categoryId) {
		List<Product> listOfProducts = new ArrayList<>();
		String sqlQuery;
		if (categoryId == null || categoryId.isEmpty()) {
			LOG.info(START_SELECTING_PRODUCTS);
			sqlQuery = SELECT_ALL_PRODUCTS;
		} else {
			LOG.info(String.format(START_SELECTING_PRODUCTS_BY_CATEGORY, categoryId));
			sqlQuery = SELECT_ALL_PRODUCTS_BY_CATEGORY_ID + categoryId;
		}
		
        try (Connection conn = DbConnector.getInstance().getConnection();
        		PreparedStatement pstatement = conn.prepareStatement(sqlQuery)) {
        	ResultSet rs = pstatement.executeQuery();
        	while (rs.next()) {
        		Product product = new Product();
        		product.setId(rs.getInt(1));
        		product.setName(rs.getString(2));
        		product.setDescription(rs.getString(3).substring(0, (rs.getString(3).indexOf((" "), 20))) + "...");
        		product.setPrice(rs.getInt(4));
        		listOfProducts.add(product);
        	}
        	LOG.info(PRODUCTS_SELECTED_SUCCESSFULLY);
		} catch (SQLException e) {
        	LOG.severe(ERROR_SELECTING_PRODUCTS + e.getMessage());
        } catch (Exception e) {
        	LOG.severe(e.getMessage());
        }
        return listOfProducts;
	}

	@Override
	public Product getProductById(String productId) {
		LOG.info(String.format(START_SELECTING_PRODUCT_BY_ID, productId));
		Product product = new Product();
		try (Connection conn = DbConnector.getInstance().getConnection();
        		PreparedStatement pstatement = conn.prepareStatement(SELECT_PRODUCT_BY_ID + productId)) {
        	ResultSet rs = pstatement.executeQuery();
        	while (rs.next()) {
        		product.setId(rs.getInt(1));
        		product.setName(rs.getString(2));
        		product.setDescription(rs.getString(3));
        		product.setPrice(rs.getInt(4));
        	}
        	LOG.info(String.format(PRODUCT_SELECTED_BY_ID_SUCCESSFULLY, productId));
        } catch (SQLException e) {
        	LOG.severe(ERROR_SELECTING_PRODUCTS + e.getMessage());
        }
		return product;
	}
	
	

}
