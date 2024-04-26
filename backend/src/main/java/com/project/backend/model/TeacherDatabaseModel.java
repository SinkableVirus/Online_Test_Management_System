package com.project.backend.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherDatabaseModel {
    
    DatabaseConnection database = null;

    public TeacherDatabaseModel() {
        this.database = DatabaseConnection.getConnection();
    }

    public ArrayList<String> getTeacherTests(String teacherId) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT TestID FROM Test WHERE TeacherID = \"%s\"", teacherId));
            ArrayList<String> testIds = new ArrayList<String>();

            while(resultSet.next()) {
                testIds.add(resultSet.getString("TestID"));
            }

            resultSet.close();
            statement.close();

            return testIds;
        } catch(Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

}
