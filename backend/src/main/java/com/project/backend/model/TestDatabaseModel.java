package com.project.backend.model;

import java.sql.*;
import java.util.*;

public class TestDatabaseModel {

    DatabaseConnection database = null;

    public TestDatabaseModel() {
        this.database = DatabaseConnection.getConnection();
    }

    public boolean checkTeacherId(String teacherId) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("SELECT ID FROM Teacher WHERE ID = \"%s\";", teacherId));
            String databaseTeacherId = "";

            while (resultSet.next()) {
                databaseTeacherId = resultSet.getString("ID");
            }
            resultSet.close();
            statement.close();

            if (teacherId.equals(databaseTeacherId)) {
                return true;
            }
            return false;

        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    public int storeQuestions(ArrayList<Question> questions) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("SELECT COUNT(*) AS question_count FROM Questions;"));
            int oldQuestionCount = -1;
            int count;

            while (resultSet.next()) {
                oldQuestionCount = resultSet.getInt("question_count");
            }
            count = oldQuestionCount + 1;
            resultSet.close();

            for (int i = 0; i < questions.size(); i++) {
                statement.executeUpdate(String.format(
                        "INSERT INTO Questions VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %d)",
                        "Q" + count,
                        questions.get(i).question,
                        questions.get(i).options.get(0),
                        questions.get(i).options.get(1),
                        questions.get(i).options.get(2),
                        questions.get(i).options.get(3),
                        questions.get(i).answer,
                        questions.get(i).marks));
                count++;
            }

            statement.close();

            return oldQuestionCount;

        } catch (Exception exception) {
            System.out.println(exception);
            return -1;
        }
    }

    public void storeTest(int oldQuestionCount, Test test) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS test_count FROM Test;");
            int oldTestCount = -1;
            int count;

            while (resultSet.next()) {
                oldTestCount = resultSet.getInt("test_count");
            }
            resultSet.close();
            count = oldTestCount + 1;

            statement.executeUpdate(String.format(
                    "INSERT INTO Test VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %s);",
                    "TEST" + count,
                    test.getTeacherId(),
                    test.getSubject(),
                    test.getDifficulty(),
                    test.getStartDateTime(),
                    test.getEndDateTime(),
                    test.getDescription(),
                    test.getAccepted()));

            for (int i = 0; i < test.getQuestionList().size(); i++) {
                oldQuestionCount++;
                statement.executeUpdate(String.format("INSERT INTO Test_Questions VALUES (\"%s\", \"%s\");",
                        "TEST" + count,
                        "Q" + oldQuestionCount));
            }

            statement.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public ArrayList<Test> retrieveAllValidTests(String SRN) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT TestID, TeacherID, Subject, Difficulty, StartDateTime, EndDateTime, Description from Test where Accepted = true AND TestID NOT IN (SELECT testId FROM Student_Test WHERE SRN = \"%s\");",
                    SRN));
            ArrayList<Test> test = new ArrayList<Test>();
            while (resultSet.next()) {
                test.add(new Test(
                        resultSet.getString("TestID"),
                        resultSet.getString("TeacherID"),
                        resultSet.getString("Subject"),
                        resultSet.getString("Difficulty"),
                        resultSet.getString("StartDateTime"),
                        resultSet.getString("EndDateTime"),
                        resultSet.getString("Description")));
            }
            return test;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public Test getTestDetails(String testId) {
        try {
            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT temp.questionId, question, option1, option2, option3, option4, correct_option, marks FROM (SELECT questionId FROM Test_Questions WHERE testId = \"%s\") AS temp JOIN Questions WHERE temp.questionId = Questions.questionID;",
                    testId));
            ArrayList<Question> questions = new ArrayList<Question>();
            Test test = null;

            while (resultSet.next()) {
                questions.add(new Question(
                        resultSet.getString("questionId"),
                        resultSet.getString("question"),
                        resultSet.getString("correct_option"),
                        resultSet.getInt("marks"),
                        new ArrayList<String>(Arrays.asList(
                                resultSet.getString("option1"),
                                resultSet.getString("option2"),
                                resultSet.getString("option3"),
                                resultSet.getString("option4")))));
            }

            resultSet = statement.executeQuery(String.format("SELECT * FROM Test WHERE TestID = \"%s\"", testId));

            while (resultSet.next()) {
                test = new Test(
                        resultSet.getString("TestID"),
                        resultSet.getString("TeacherID"),
                        resultSet.getString("Subject"),
                        resultSet.getString("Difficulty"),
                        resultSet.getString("StartDateTime"),
                        resultSet.getString("EndDateTime"),
                        resultSet.getString("Description"),
                        questions);
            }

            resultSet.close();
            statement.close();

            return test;

        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    public ArrayList<StudentReview> getStudentReviews(Test test) {
        try {

            Statement statement = this.database.connection.createStatement();
            ResultSet resultSet;
            ArrayList<String> SRNs;
            ArrayList<String> reviews;
            StudentReview studentReview;
            ArrayList<StudentReview> studentReviews = new ArrayList<StudentReview>();

            for(int i = 0; i < test.getQuestionList().size(); i++) {
                SRNs = new ArrayList<String>();
                reviews = new ArrayList<String>();
                studentReview = new StudentReview(test.getTestId(), test.getQuestionList().get(i).getQuestionId(), SRNs, reviews);

                resultSet = statement.executeQuery(String.format(
                "SELECT SRN, reviewComments FROM Student_Answers WHERE testId = \"%s\" AND questionId = \"%s\"",
                    test.getTestId(),
                    test.getQuestionList().get(i).getQuestionId()
                ));

                while(resultSet.next()) {
                    if(!resultSet.getString("reviewComments").equals("none")) {
                        studentReview.addSRN(resultSet.getString("SRN"));
                        studentReview.addReview(resultSet.getString("reviewComments"));
                    }
                }

                studentReviews.add(studentReview);
                resultSet.close();
            }


            statement.close();

            return studentReviews;
            

        } catch(Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

}