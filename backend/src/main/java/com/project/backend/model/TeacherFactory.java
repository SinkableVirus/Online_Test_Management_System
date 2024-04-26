package com.project.backend.model;

public class TeacherFactory extends UserFactory {

    private String ID;
    private String Name;
    private String Password;
    private String Branch;

    public TeacherFactory(String ID, String Name, String Password, String Branch) {
        this.ID = ID;
        this.Name = Name;
        this.Password = Password;
        this.Branch = Branch;
    }

    public User createUser() {
        return new Teacher(ID, Name, Password, Branch);
    }
}
