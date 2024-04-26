package com.project.backend.model;

import java.util.Map;

public class Result {
    String SRN;
    String testId;
    int totalScore;
    Map<String, String> markedOptions;

    public Result(String SRN, String testId, Map<String, String> markedOptions) {
        this.SRN = SRN;
        this.testId = testId;
        this.totalScore = -1;
        this.markedOptions = markedOptions;
    }

    public String getSRN() {
        return this.SRN;
    }

    public String getTestId() {
        return this.testId;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public Map<String, String> getMarkedOptions() {
        return this.markedOptions;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
