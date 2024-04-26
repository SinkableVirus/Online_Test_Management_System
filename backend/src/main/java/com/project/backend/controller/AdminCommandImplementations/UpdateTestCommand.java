package com.project.backend.controller.AdminCommandImplementations;

import java.util.Stack;

import com.project.backend.controller.Command;
import com.project.backend.model.Admin;
import com.project.backend.model.Test;

public class UpdateTestCommand implements Command {

    Admin admin;
    String testId;
    String difficulty;
    String startDateTime;
    String endDateTime;

    //undo stack
    private static Stack<Test> undoStack = new Stack<Test>();
    //the required members for undo command
    String prevTestId;
    String prevDifficulty;
    String prevStartDateTime;
    String prevEndDateTime;


    public UpdateTestCommand (Admin admin) { //for undoing update
        this.admin = admin;
    }

    public UpdateTestCommand(Admin admin, String testId, String difficulty, String startDateDate, String startEndTime) { //for update
        this.admin = admin;
        this.testId = testId;
        this.difficulty = difficulty;
        this.startDateTime = startDateDate;
        this.endDateTime = startEndTime;
    }
    public void execute() {
        Test prevTest = admin.getSpecificTest(testId);
        if (prevTest == null) {
          System.out.println("Test not found!");
          return;
        }
        System.out.println("HWYY " + prevTest.getStartDateTime());

        // Push the current state onto the undoStack
        undoStack.push(prevTest);
        admin.updateTest(testId, difficulty, startDateTime, endDateTime);
      }
      
    public void undo() {
        if (!(undoStack.empty())) {
            Test prevTest = undoStack.pop();
            System.out.println("CMON MNA " + prevTest.getStartDateTime());
            System.out.println(prevTest.getTestId() + prevTest.getStartDateTime());
            admin.updateTest(prevTest.getTestId(), prevTest.getDifficulty(), prevTest.getStartDateTime(), prevTest.getEndDateTime());
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}