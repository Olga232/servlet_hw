package ua.itea.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnector {
	
	private static DbConnector instance;
    private static final Logger LOG = Logger.getLogger(DbConnector.class.getName());
    
    private DbConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            LOG.info("com.mysql.jdbc.Driver connected");
        } catch (Exception ex) {
            LOG.severe("com.mysql.jdbc.Driver not connected. " + ex.getMessage());
        }
    }

    public static DbConnector getInstance() {
        if (instance == null) {
            instance = new DbConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
        } catch (SQLException ex) {
        	LOG.severe("SQLException: " + ex.getMessage());
        	LOG.severe("SQLState: " + ex.getSQLState());
        	LOG.severe("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
