package com.project.backend.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class DatabaseConnection {

    private static DatabaseConnection instance; 
    public Connection connection;

    private DatabaseConnection() {

        String dbUrl = AppConfig.getProperty("db.url");
        String dbUsername = AppConfig.getProperty("db.username");
        String dbPassword = AppConfig.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            // System.out.println("Peace");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}


class AppConfig {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
