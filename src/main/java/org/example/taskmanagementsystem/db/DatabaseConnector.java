package org.example.taskmanagementsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());

    // Use H2 in-memory DB. "DB_CLOSE_DELAY=-1" keeps the DB alive while the JVM runs.
    private static final String URL = "jdbc:h2:mem:taskdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            // Load H2 driver instead of MySQL
            Class.forName("org.h2.Driver");
            logger.info("H2 JDBC driver loaded successfully");
        } catch (ClassNotFoundException e) {
            logger.severe("H2 JDBC driver not found: " + e.getMessage());
            throw new RuntimeException("H2 JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connection established successfully");
            return conn;
        } catch (SQLException e) {
            logger.severe("Failed to establish database connection: " + e.getMessage());
            throw e;
        }
    }
}


