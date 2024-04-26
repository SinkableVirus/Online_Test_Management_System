package com.project.backend.controller.AdminCommandImplementations;

import com.project.backend.controller.Command;
import com.project.backend.model.Admin;

public class AcceptTestCommand implements Command {
    Admin admin;
    String testId;
    int prevState;

    public AcceptTestCommand (Admin admin, String testId) {
        this.admin = admin;
        this.testId = testId;
        this.prevState = 0;

    }

    public void execute() {
        this.prevState = 0;
        admin.acceptTest(this.testId, 1);
    }

    public void undo() {
        if (this.prevState == 0); 
            admin.acceptTest(this.testId, 0);
    }
}