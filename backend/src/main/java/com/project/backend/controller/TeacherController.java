package com.project.backend.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TeacherController {
    @GetMapping(path = "/teacherDashboard")
    String viewTeacherDashboard(Model model) {
        // System.out.println(SRN);
        return "teacherDashboard";
    }

    @GetMapping(path = "/uploadTest")
    String uploadTest(Model model) {
        return "uploadTest";
    }

    @PostMapping(path = "/submitTest")
    String submitTest(@RequestBody Map<String, Object> body, Model model) {
        System.out.println(body);
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        //     Map<String, Object> requestBody = mapper.readValue("{" + body + "}", Map.class);
        //     System.out.println(requestBody);
        // } catch(Exception excpetion) {
        //     System.out.println(excpetion);
        // }
        return "redirect:" + "/teacherDashboard";
    }
}