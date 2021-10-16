package ua.itea.services;

import java.util.List;

import ua.itea.models.Product;



public interface ProductDaoService {
	
	List<Product> getAllProductsByCategoryId(String categoryId);
	Product getProductById(String productId);

}
