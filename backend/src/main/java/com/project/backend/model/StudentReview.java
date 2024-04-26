package com.project.backend.model;

import java.util.ArrayList;

public class StudentReview {
    
    String testId;
    String questionId;
    ArrayList<String> SRNs;
    ArrayList<String> reviews;

    public StudentReview(String testId, String questionId, ArrayList<String> SRNs, ArrayList<String> reviews) {
        this.testId = testId;
        this.questionId = questionId;
        this.SRNs = SRNs;
        this.reviews = reviews;
    }

    public String getTestId() {
        return this.testId;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public ArrayList<String> getSRNs() {
        return this.SRNs;
    }

    public ArrayList<String> getReviews() {
        return this.reviews;
    }

    public void addSRN(String SRN) {
        this.SRNs.add(SRN);
    }

    public void addReview(String review) {
        this.reviews.add(review);
    }

}
