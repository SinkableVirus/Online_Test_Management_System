package com.project.backend.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class PrevTests {
    DatabaseConnection database = null;

    public PrevTests() {
        this.database = DatabaseConnection.getConnection();
    }

    public List<Map<String, Object>> retrieveAllTests(String SRN) {
        List<Map<String, Object>> tests = new ArrayList<>();
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT t.testId, t.Subject, st.markssecured FROM student_test st JOIN test t ON st.testId = t.testId WHERE st.SRN = '" + SRN + "'");
            while (resultSet.next()) {
                Map<String, Object> test = new HashMap<>();
                test.put("testId", resultSet.getString("testId"));
                test.put("Subject", resultSet.getString("Subject"));
                test.put("markssecured", resultSet.getInt("markssecured"));
                tests.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }

    
}
