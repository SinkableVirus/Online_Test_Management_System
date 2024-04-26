package com.project.backend.model;

public class StudentFactory extends UserFactory {

    private String SRN;
    private String Name;
    private String Password;
    private String Branch;

    public StudentFactory(String Name, String SRN, String Branch, String Password) {
        this.SRN = SRN;
        this.Name = Name;
        this.Password = Password;
        this.Branch = Branch;
    }

    public User createUser() {
        return new Student(Name, SRN, Branch, Password);
    }

}
