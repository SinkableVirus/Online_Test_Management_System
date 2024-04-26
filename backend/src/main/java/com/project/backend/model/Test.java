package com.project.backend.model;

import java.util.*;

public class Test {
    String testId;
    String teacherId;
    String subject;
    String difficulty;
    String startDateTime;
    String endDateTime;
    String description;
    Boolean accepted;
    ArrayList<Question> questionList;

    public Test(String teacherId, String subject, String difficulty, String startDateTime, String endDateTime,
            String description, boolean accepted, ArrayList<Question> questionList) {
        this.testId = null;
        this.teacherId = teacherId;
        this.subject = subject;
        this.difficulty = difficulty;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.accepted = accepted;
        this.questionList = questionList;
    }

    public Test(String testId, String teacherId, String subject, String difficulty, String startDateTime,
            String endDateTime, String description) {
        this.testId = testId;
        this.teacherId = teacherId;
        this.subject = subject;
        this.difficulty = difficulty;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.questionList = null;
    }

    public Test(String testId, String teacherId, String subject, String difficulty, String startDateTime,
            String endDateTime, String description, ArrayList<Question> questionList) {
        this.testId = testId;
        this.teacherId = teacherId;
        this.subject = subject;
        this.difficulty = difficulty;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.questionList = questionList;
    }

    public Test(String testId, String difficulty, String startDateTime, String endDateTime) { // for undo functionality
        this.testId = testId;
        this.difficulty = difficulty;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Test(String testId, String subject) { //for leaderboard functionality
        this.testId = testId;
        this.subject = subject;
    }

    public String getTestId() {
        return this.testId;
    }

    public String getTeacherId() {
        return this.teacherId;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public String getStartDateTime() {
        return this.startDateTime;
    }

    public String getEndDateTime() {
        return this.endDateTime;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getAccepted() {
        return this.accepted;
    }

    public ArrayList<Question> getQuestionList() {
        return this.questionList;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
}
