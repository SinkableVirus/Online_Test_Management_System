package com.project.backend.model;
import java.sql.Connection;
import java.sql.*;

public abstract class User {

    protected String Name;
    protected String Password;
    protected String Branch;
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    abstract public void Add();
    
}

