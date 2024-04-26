package com.project.backend.controller;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import com.project.backend.model.leaderboard;
import com.project.backend.model.test_list;
import com.project.backend.model.Test;
import com.project.backend.model.Scoredetails;


import com.project.backend.model.*;
import jakarta.servlet.http.HttpSession;



@Controller
public class TeacherController {

    TestDatabaseModel testDatabaseModel = null;
    TeacherDatabaseModel teacherDatabaseModel = null;

    TeacherController() {
        this.testDatabaseModel = new TestDatabaseModel();
        this.teacherDatabaseModel = new TeacherDatabaseModel();
    }
    
    @GetMapping(path = "/teacherDashboard")
    String viewTeacherDashboard(@RequestParam String teacherId, Model model, HttpSession session) {
        if(!teacherId.equals(session.getAttribute("teacherId")) || !this.testDatabaseModel.checkTeacherId(teacherId)) {
            return "invalidSession";
        }
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("testIds", this.teacherDatabaseModel.getTeacherTests(teacherId));
        return "teacherDashboard";
    }

    @GetMapping(path = "/uploadTest")
    String uploadTest(@RequestParam String teacherId, Model model, HttpSession session) {
        if(!teacherId.equals(session.getAttribute("teacherId")) || !this.testDatabaseModel.checkTeacherId(teacherId)) {
            return "invalidSession";
        }
        return "uploadTest";
    }

    @SuppressWarnings("unchecked")
    @PostMapping(path = "/submitTest")
    String submitTest(@RequestBody Map<String, Object> body, Model model) {
        System.out.println(body);

        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<String> question = (ArrayList<String>)body.get("questions");
        ArrayList<String> answers = (ArrayList<String>)body.get("answers");
        ArrayList<ArrayList<String>> options = (ArrayList<ArrayList<String>>)body.get("options");
        ArrayList<String> marks = (ArrayList<String>)body.get("marks");
        
        for(int i = 0; i < question.size(); i++) {
            questions.add(new Question(question.get(i), answers.get(i), Integer.parseInt(marks.get(i)), options.get(i)));
        }
        
        Test test = new Test(
            (String)body.get("teacherId"),
            (String)body.get("subject"),
            (String)body.get("difficulty"),
            (String)body.get("startDateTime"),
            (String)body.get("endDateTime"),
            (String)body.get("description"),
            true,
            questions
        );

        int oldQuestionCount = testDatabaseModel.storeQuestions(questions);
        testDatabaseModel.storeTest(oldQuestionCount, test);

        return "redirect:" + "/teacherDashboard";
    }

    @GetMapping(path="/student_score")
    public ModelAndView getleaderboard(@RequestParam("testId") String testId)
    {
        leaderboard leader=new leaderboard();
        List<Scoredetails> leaderboard=leader.jointable(testId);
        double mean = leaderboard.stream()
                                 .mapToInt(Scoredetails::getScore)
                                 .average()
                                 .orElse(0.0);
        double median = new Median().evaluate(leaderboard.stream()
                                 .mapToInt(Scoredetails::getScore)
                                 .asDoubleStream()
                                 .toArray());
        ModelAndView modelAndView = new ModelAndView("Studentscore");
        modelAndView.addObject("testId", testId);
        modelAndView.addObject("topScores", leaderboard.stream().collect(Collectors.toList()));
        modelAndView.addObject("mean", mean);
        modelAndView.addObject("median", median);
        return modelAndView;   
    }
    
    @GetMapping(path="/leaderboard")
    public ModelAndView leaderboard()
    {
        ModelAndView modelAndView=new ModelAndView("leaderboard");
        test_list tests=new test_list();
        List<Test> t=tests.get_tests();
        modelAndView.addObject("tests", t);
        return modelAndView;
    }

    @GetMapping(path = "/seeTestReview")
    String seeTestReview(@RequestParam String teacherId, @RequestParam String testId, Model model, HttpSession session) {
        if(!teacherId.equals(session.getAttribute("teacherId")) || !this.testDatabaseModel.checkTeacherId(teacherId)) {
            return "invalidSession";
        }
        Test test = this.testDatabaseModel.getTestDetails(testId);
        ArrayList<StudentReview> studentReviews = this.testDatabaseModel.getStudentReviews(test);
        model.addAttribute("test", test);
        model.addAttribute("studentReviews", studentReviews);
        System.out.println(test.getQuestionList());
        return "seeTestReview";
    }
}