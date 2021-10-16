package ua.itea.services.daofactory;

import ua.itea.services.AuthorizationDaoService;
import ua.itea.services.CartDaoService;
import ua.itea.services.ProductDaoService;
import ua.itea.services.UserDaoService;
import ua.itea.services.impl.AuthorizationDaoServiceImpl;
import ua.itea.services.impl.CartDaoServiceImpl;
import ua.itea.services.impl.ProductDaoServiceImpl;
import ua.itea.services.impl.UserDaoServiceImpl;

public class DaoServiceFactory extends DaoFactory {

	@Override
	public UserDaoService getUserDaoService() {
		return new UserDaoServiceImpl();
	}

	@Override
	public AuthorizationDaoService getAuthorizationDaoService() {
		return new AuthorizationDaoServiceImpl();
	}

	@Override
	public ProductDaoService getProductDaoService() {
		return new ProductDaoServiceImpl();
	}

	@Override
	public CartDaoService getCartDaoService() {
		return new CartDaoServiceImpl();
	}

	
}
