package com.project.backend.controller;

public interface Command {

    public void execute();
    public void undo();
}
