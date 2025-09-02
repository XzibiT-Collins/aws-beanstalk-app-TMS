package org.example.taskmanagementsystem.db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            // Employee table
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS employee (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL
                );
            """);

            // Task table
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS task (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    title VARCHAR(200) NOT NULL,
                    description VARCHAR(500),
                    status VARCHAR(50),
                    due_date DATE,
                    FOREIGN KEY (user_id) REFERENCES Employee(id)
                );
            """);

            System.out.println("✅ tables created/verified successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DB schema", e);
        }
    }
}