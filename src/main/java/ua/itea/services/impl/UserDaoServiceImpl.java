package ua.itea.services.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import ua.itea.models.User;
import ua.itea.services.DbConnector;
import ua.itea.services.UserDaoService;


public class UserDaoServiceImpl implements UserDaoService{

    public static final String INSERT_USER = "INSERT INTO user (login, name, password, gender, region, comment) VALUES(?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET name=?, password=?, gender=?, region=?, comment=? WHERE login=?";
	private static final String START_ADDING_NEW_USER = "Starts adding new user with login (%s) to DB";
	private static final String USER_ADDED_SUCCESSFULLY = "New user with login (%s) added successfully";
	private static final String START_UPDATING_USER = "Starts updating user info with login (%s)";
	private static final String USER_UPDATED_SUCCESSFULLY = "User info with login (%s) updated successfully";
	private static final Logger LOG = Logger.getLogger(UserDaoServiceImpl.class.getName());

	@Override
    public void addUser(User user) {
    	LOG.info(String.format(START_ADDING_NEW_USER, user.getLogin()));
        try (Connection conn = DbConnector.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(INSERT_USER);) {
        	conn.setAutoCommit(false);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, hashPasswordWithSalt(user.getPassword()));
            ps.setString(4, user.getGender());
            ps.setString(5, user.getRegion());
            ps.setString(6, user.getComment());
            ps.execute();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
        	LOG.severe(e.getMessage());
        }
        LOG.info(String.format(USER_ADDED_SUCCESSFULLY, user.getLogin()));
    }
	
	@Override
	public void updateUser(User user) {
		LOG.info(String.format(START_UPDATING_USER, user.getLogin()));
        try (Connection conn = DbConnector.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(UPDATE_USER);) {
        	conn.setAutoCommit(false);
            ps.setString(1, user.getName());
            ps.setString(2, hashPasswordWithSalt(user.getPassword()));
            ps.setString(3, user.getGender());
            ps.setString(4, user.getRegion());
            ps.setString(5, user.getComment());
            ps.setString(6, user.getLogin());
            ps.execute();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
        	LOG.severe(e.getMessage());
        }
        LOG.info(String.format(USER_UPDATED_SUCCESSFULLY, user.getLogin()));
	}
	
	private String hashPasswordWithSalt(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            LOG.severe(e.getMessage());
        }
        String passWithSalt = password + "secretsalt";
        md.update(passWithSalt.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, md.digest()));
    }


}
