package com.project.backend.model;

public class Scoredetails
{
    private String Name;
    private String SRN;
    private Integer MarksSecured;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    public String getSRN()
    {
        return SRN;
    }
    public void setSRN(String SRN)
    {
        this.SRN=SRN;
    }

    public Integer getScore() {
        return MarksSecured;
    }

    public void setScore(Integer score) {
        this.MarksSecured = score;
    }
}