package ua.itea.services.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ua.itea.services.AuthorizationDaoService;
import ua.itea.services.DbConnector;


public class AuthorizationDaoServiceImpl implements AuthorizationDaoService {
	
	public static final String SELECT_USER_NAME_BY_LOGIN_AND_PASSWORD = "SELECT name FROM user WHERE login=? AND password=?";
	private static final String START_CHECKING_LOGIN = "Starts checking login (%s) in DB.";
	private static final String LOGIN_CHECKED_SUCCESSFULLY = "Login (%s) checked successfully";
	private static final String CHECKING_LOGIN_ERROR = "Error occurred during login checking (%s): ";
    private static final Logger LOG = Logger.getLogger(AuthorizationDaoServiceImpl.class.getName());
	
	@Override
    public String checkLoginAndPassword(String login, String password) {
		LOG.info(String.format(START_CHECKING_LOGIN, login));
        String name = null;
        try (Connection conn = DbConnector.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(SELECT_USER_NAME_BY_LOGIN_AND_PASSWORD);) {
        	ps.setString(1, login);
            ps.setString(2, hashPasswordWithSalt(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
        	LOG.severe(String.format(CHECKING_LOGIN_ERROR, login) + e.getMessage());
        }
        LOG.info(String.format(LOGIN_CHECKED_SUCCESSFULLY, login));
        return name;
    }
	
	private String hashPasswordWithSalt(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            String passWithSalt = password + ResourceBundle.getBundle("secretsalt").getString("secretSalt.value");
       	 	md.update(passWithSalt.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            LOG.severe(e.getMessage());
        } catch (MissingResourceException e) {
        	LOG.severe(e.getMessage());
        }
        return String.format("%064x", new BigInteger(1, md.digest()));
    }

}
