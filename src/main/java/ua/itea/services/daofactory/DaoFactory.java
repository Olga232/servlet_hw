package ua.itea.services.daofactory;

import ua.itea.services.AuthorizationDaoService;
import ua.itea.services.CartDaoService;
import ua.itea.services.ProductDaoService;
import ua.itea.services.UserDaoService;

public abstract class DaoFactory {
	
	public static DaoFactory getDaoFactory() {
		return new DaoServiceFactory();
	}
	
	public abstract UserDaoService getUserDaoService();
	public abstract AuthorizationDaoService getAuthorizationDaoService();
	public abstract ProductDaoService getProductDaoService();
	public abstract CartDaoService getCartDaoService();

}
