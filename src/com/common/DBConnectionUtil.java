package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnectionUtil class provides utility methods for managing database connections.
 * Implements the Singleton design pattern to ensure only one instance of the database connection is used.
 */
public class DBConnectionUtil extends CommonUtil {
    
    // Holds the single instance of the database connection
    public static Connection connection;
    
    // Private constructor to prevent instantiation
    private DBConnectionUtil() {}
    
    /**
     * Retrieves a database connection. If no connection exists or the existing connection is closed,
     * a new connection is established using the provided connection properties.
     * 
     * @return The database Connection object
     * @throws ClassNotFoundException if the database driver class is not found
     * @throws SQLException if a database access error occurs or the URL is invalid
     */
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        
        // Check if the connection is null or closed, then create a new one
        if(connection == null || connection.isClosed()) {
            // Load the database driver class
            Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));
            
            // Establish a new connection using properties
            connection = DriverManager.getConnection(
                properties.getProperty(CommonConstants.URL), 
                properties.getProperty(CommonConstants.USERNAME), 
                properties.getProperty(CommonConstants.PASSWORD)
            );
        }
        
        // Return the existing or newly created connection
        return connection;
    }
}
