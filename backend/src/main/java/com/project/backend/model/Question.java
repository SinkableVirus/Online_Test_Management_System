package com.project.backend.model;

import java.util.*;

public class Question {
    String questionId;
    String question;
    String answer;
    int marks;
    ArrayList<String> options;

    public Question(String questionId, String question, String answer, int marks, ArrayList<String> options) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.marks = marks;
        this.options = options;
    }

    public Question(String question, String answer, int marks, ArrayList<String> options) {
        this.question = question;
        this.answer = answer;
        this.marks = marks;
        this.options = options;
    }

    public String getOptionName(String option) {
        String optionName;
        int index = this.options.indexOf(option);
        if(index == 0) {
            optionName = "a";
        } else if(index == 1) {
            optionName = "b";
        } else if(index == 2) {
            optionName = "c";
        } else if(index == 3) {
            optionName = "d";
        } else {
            optionName = null;
        }
        return optionName;
    }

    public int evaluateQuestion(String markedAnswer) {
        if(markedAnswer.equals(this.answer)) {
            return marks;
        }
        return 0;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int getMarks() {
        return this.marks;
    }

    public ArrayList<String> getOptions() {
        return this.options;
    }
}
