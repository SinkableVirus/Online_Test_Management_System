package com.project.backend.model;

public abstract class UserFactory {
    public abstract User createUser();

    public void returnUser() {
        User user = createUser();
        user.Add();
    }
}
