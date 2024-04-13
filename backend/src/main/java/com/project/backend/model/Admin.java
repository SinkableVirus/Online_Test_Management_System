package com.project.backend.model;
import com.project.backend.model.DatabaseConnection;
import java.sql.Connection;
import java.util.*;
import java.sql.*;

public class Admin {

    private String adminId;
    private String name;
    private String password;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;


    public Admin(){
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
        // System.out.println(connection);
    }

    public Admin (String adminId, String name, String password) {
        this.adminId = adminId;
        this.name = name;
        this.password = password;
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
        // System.out.println(connection);      
        // System.out.println("Nicee");
    }

    public String login(String adminId){
        String password="";
        try{
            String query = String.format("select password from admin where ID='%s';", adminId);
            
            System.out.println(connection);
            System.out.println(query);
            statement=connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("password");
                System.out.println("Password: " + password);
            }

            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return password;


    }

    public String getAdminId() {
        return this.adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
