package com.project.backend.model;
import com.project.backend.model.DatabaseConnection;
import java.sql.Connection;
import java.util.*;
import java.sql.*;

public class Student {
    private String Name;
    private String SRN;
    private String Branch;
    private String Password;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    public Student() {
        // default constructor
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
        // System.out.println(connection);
    }
    public Student(String Name,String SRN,String Branch,String Password){
        // System.out.println("Entering constructor");
        this.Branch=Branch;
        this.SRN=SRN;
        this.Name=Name;
        this.Password=Password;
        DatabaseConnection dbc = DatabaseConnection.getConnection();
        connection = dbc.connection;
        // System.out.println(connection);
    }

    public void addStudent(){
        try{
            // System.out.println(connection); 
            // String query=String.format("insert into Student values (%s,%s,%s,%s);",SRN,Branch,Name,Password);
            String query = String.format("INSERT INTO Student (SRN, Branch, Name, Password) VALUES ('%s', '%s', '%s', '%s');", SRN, Branch, Name, Password);

            // System.out.println(query);
            statement=connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Successfully added record");
            } else {
                System.out.println("Failed to add record");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String login(String SRN){
        String password="";
        try{
            String query = String.format("select Password from student where SRN='%s';", SRN);
            
            // System.out.println(connection);
            // System.out.println(query);
            statement=connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("Password");
                // System.out.println("Password: " + password);
            }
            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return password;


    }

    public String getName(){
        return Name;
    }
    public String getSRN(){
        return SRN;
    }
    public String getPassword(){
        return Password;
    }
    public String getBranch(){
        return Branch;
    }
    public void setName(String Name){
        this.Name=Name;
    }
    public void setPassword(String Password){
        this.Password=Password;
    }
    public void setBranch(String Branch){
        this.Branch=Branch;
    }
    public void setSRN(String SRN){
        this.SRN=SRN;
    }


}
