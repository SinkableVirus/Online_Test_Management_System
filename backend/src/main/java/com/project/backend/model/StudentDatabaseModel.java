package com.project.backend.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class StudentDatabaseModel {

    DatabaseConnection database = null;

    public StudentDatabaseModel() {
        this.database = DatabaseConnection.getConnection();
    }

    public void storeStudentResponses(Result result) {
        try {

            Statement statement = this.database.connection.createStatement();

            for (Map.Entry<String, String> entry : result.getMarkedOptions().entrySet()) {
                statement.executeUpdate(
                        String.format("INSERT INTO Student_Answers VALUES (\"%s\", \"%s\", \"%s\", \"%s\", NULL);",
                                result.getSRN(),
                                result.getTestId(),
                                entry.getKey(),
                                entry.getValue()));
            }

            statement.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public int calculateStudentTotal(Result result) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = null;
            Question question;
            int totalScore = 0;

            for (Map.Entry<String, String> entry : result.getMarkedOptions().entrySet()) {
                resultSet = statement.executeQuery(
                        String.format("SELECT * FROM Questions WHERE questionId = \"%s\"", entry.getKey()));
                while (resultSet.next()) {
                    question = new Question(
                            resultSet.getString("questionId"),
                            resultSet.getString("question"),
                            resultSet.getString("correct_option"),
                            resultSet.getInt("marks"),
                            new ArrayList<String>(Arrays.asList(
                                    resultSet.getString("option1"),
                                    resultSet.getString("option2"),
                                    resultSet.getString("option3"),
                                    resultSet.getString("option4"))));
                    totalScore += question.evaluateQuestion(entry.getValue());
                }
            }

            result.setTotalScore(totalScore);
            resultSet.close();
            statement.close();

            return totalScore;

        } catch (Exception exception) {
            System.out.println(exception);
            return -1;
        }
    }

    public void storeTestResult(Result result) {
        try {

            Statement statement = this.database.connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO Student_Test VALUES (\"%s\", \"%s\", %d)",
                    result.SRN,
                    result.testId,
                    result.totalScore));

            statement.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public int getTestResult(String SRN, String testId) {
        try {

            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT MarksSecured FROM Student_Test WHERE SRN = \"%s\" AND TestID = \"%s\";",
                            SRN,
                            testId));
            int totalMarks = -1;

            while (resultSet.next()) {
                totalMarks = resultSet.getInt("MarksSecured");
            }

            resultSet.close();
            statement.close();

            return totalMarks;

        } catch (Exception exception) {
            System.out.println(exception);
            return -1;
        }
    }

    public List<Map<String, Object>> getTestDetails(String SRN, String testId) {
        List<Map<String, Object>> testDetails = new ArrayList<>();
        try {
            Statement statement = database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT q.questionid, q.question, q.option1, q.option2, q.option3, q.option4, q.correct_option, sa.markedAnswer,  sa.reviewComments "
                            +
                            "FROM questions q " +
                            "JOIN student_answers sa ON q.questionid = sa.questionid " +
                            "WHERE sa.SRN = (SELECT SRN FROM student WHERE SRN = '" + SRN + "') " +
                            "AND sa.testid = '" + testId + "'");

            while (resultSet.next()) {
                Map<String, Object> questionDetails = new HashMap<>();
                questionDetails.put("questionId", resultSet.getString("questionid"));
                questionDetails.put("question", resultSet.getString("question"));
                questionDetails.put("option1", resultSet.getString("option1"));
                questionDetails.put("option2", resultSet.getString("option2"));
                questionDetails.put("option3", resultSet.getString("option3"));
                questionDetails.put("option4", resultSet.getString("option4"));
                questionDetails.put("correct_option", resultSet.getString("correct_option"));
                questionDetails.put("markedAnswer", resultSet.getString("markedAnswer"));
                questionDetails.put("reviewComments", resultSet.getString("reviewComments"));

                testDetails.add(questionDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testDetails;
    }

    public void storeReviewComments(String SRN, String testId, Map<String, String> reviewComments) {
        try {
            String studentid = getStudentId(SRN);
            System.out.println("HEREE" + SRN + testId + reviewComments);
            PreparedStatement statement = database.connection.prepareStatement(
                    "UPDATE student_answers SET reviewComments = ? WHERE SRN = ? AND testId = ? AND questionId = ?");
            for (Map.Entry<String, String> entry : reviewComments.entrySet()) {
                String questionId = entry.getKey();
                String comment = entry.getValue();
                statement.setString(1, comment);
                statement.setString(2, studentid);
                statement.setString(3, testId);
                statement.setString(4, questionId);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStudentId(String SRN) {
        try {
            Statement statement = database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SRN FROM student WHERE SRN = '" + SRN + "'");
            if (resultSet.next()) {

                return resultSet.getString("SRN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    public boolean checkStudentSRN(String SRN) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("SELECT SRN FROM Student WHERE SRN = \"%s\"", SRN));

            while (resultSet.next()) {
                if (resultSet.getString("SRN").equals(SRN)) {
                    resultSet.close();
                    statement.close();
                    return true;
                }
            }

            resultSet.close();
            statement.close();

            return false;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

}
