package com.project.backend.model;

import com.project.backend.model.DatabaseConnection;
import java.sql.Connection;
import java.util.*;
import java.sql.*;

public class Teacher {
    private String ID;
    private String Name;
    private String Password;
    private String Branch;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    public Teacher() {
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
    }

    public Teacher(String ID, String Name, String Password, String Branch) {
        this.ID = ID;
        this.Name = Name;
        this.Password = Password;
        this.Branch = Branch;
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
    }

    public void addTeacher() {
        try {
            String query = String.format("INSERT INTO Teacher (ID, Name, Password, Branch) VALUES ('%s', '%s', '%s', '%s');", ID, Name, Password, Branch);
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Successfully added record");
            } else {
                System.out.println("Failed to add record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String login(String ID) {
        String password = "";
        try {
            String query = String.format("SELECT Password FROM Teacher WHERE ID = '%s';", ID);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }
}
