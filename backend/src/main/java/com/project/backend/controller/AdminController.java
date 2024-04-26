package com.project.backend.controller;

import com.project.backend.model.Admin;

import com.project.backend.controller.AdminCommandImplementations.AcceptTestCommand;
import com.project.backend.controller.AdminCommandImplementations.UpdateTestCommand;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Objects;
import java.util.Stack;


@Controller
public class AdminController {

    private Command command;
    private Stack<Command> commandStack;

    public void setCommand(Command command) {
        this.command = command;
    }

    @GetMapping("/adminDashboard")
    public String viewTests(Model model, @RequestParam String adminId, HttpSession session) { // Use @PathVariable for path variables
        model.addAttribute("adminId", adminId);

        Admin admin = (Admin) session.getAttribute("admin");

        ArrayList<String[]> pendingTestData = admin.getPendingTestData();
        ArrayList<String[]> acceptedTestData = admin.getAcceptedTestData();

        model.addAttribute("pendingTestList", pendingTestData.subList(1, pendingTestData.size()));
        model.addAttribute("acceptedTestList", acceptedTestData.subList(1, acceptedTestData.size()));
        return "adminDashboard";
    }

    @GetMapping("/updateTestForm")
    public String updateForm(@RequestParam String testId, Model model) {
        model.addAttribute("testId", testId);
        return "updateTestForm";
    }

    @PostMapping("/updateTest")
    public String updateTest(@RequestParam String testId, @RequestParam String difficulty,
    @RequestParam String startDateTime, @RequestParam String endDateTime, HttpSession session) {


        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println("\n\n\n\nGONE\n\n" + startDateTime);
        // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        // LocalDateTime localStartDateTime = LocalDateTime.parse(startDateTime, inputFormatter);
        // LocalDateTime localEndDateTime = LocalDateTime.parse(endDateTime, inputFormatter);
    
        // DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String formattedStartDateTime = localStartDateTime.format(outputFormatter);
        // String formattedEndDateTime = localEndDateTime.format(outputFormatter);
    
        // System.out.println("BROOOOO\n\n\n\n\n" + formattedStartDateTime);

        setCommand(new UpdateTestCommand(admin, testId, difficulty, startDateTime, endDateTime)); //setting command for updation
        command.execute();
     

        String redirectUrl = UriComponentsBuilder.fromPath("/adminDashboard")
                .queryParam("adminId", admin.getAdminId())
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/undoUpdateTest")
    public String undoLastUpdate(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");    
        setCommand(new UpdateTestCommand(admin));
        command.undo();
        String redirectUrl = UriComponentsBuilder.fromPath("/adminDashboard")
                .queryParam("adminId", admin.getAdminId())
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/acceptTest")
    public String acceptTest(@RequestParam String testId, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        setCommand(new AcceptTestCommand(admin, testId)); //setting new command to accept the test
        this.command.execute();

        String redirectUrl = UriComponentsBuilder.fromPath("/adminDashboard")
                .queryParam("adminId", admin.getAdminId())
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/undoAcceptTest")
    public String undoAcceptTest(@RequestParam String testId, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        setCommand(new AcceptTestCommand(admin, testId)); //setting new command to accept the test
        this.command.undo();

        String redirectUrl = UriComponentsBuilder.fromPath("/adminDashboard")
                .queryParam("adminId", admin.getAdminId())
                .toUriString();

        return "redirect:" + redirectUrl;
    }
}
