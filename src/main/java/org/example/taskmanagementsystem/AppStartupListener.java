package org.example.taskmanagementsystem;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.taskmanagementsystem.db.DatabaseInitializer;
import java.util.logging.Logger;

@WebListener
public class AppStartupListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(AppStartupListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Starting database initialization...");
            DatabaseInitializer.init();  // create tables when app starts
            logger.info("Database initialization completed successfully");
        } catch (Exception e) {
            logger.severe("Failed to initialize database: " + e.getMessage());
            // Don't throw exception here - let the app start even if DB fails
            // This allows you to see error pages instead of complete failure
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application context destroyed");
    }
}