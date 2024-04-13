package com.project.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.backend.model.Admin;
import com.project.backend.model.Student;
import com.project.backend.model.Teacher;

@Controller
public class HomeController {
    @GetMapping("/")
    public String studentLogin(){
        return "studentLogin";
    }
    @GetMapping("/studentRegister")
    public String studentRegister(){
        return "studentRegister";
    }
    @GetMapping("/teacherLogin")
    public String teacherLogin(){
        return "teacherLogin";
    }
    @GetMapping("/teacherRegister")
    public String teacherRegister(){
        return "teacherRegister";
    }
    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "adminLogin";
    }
    @PostMapping("/studentRegister")
    public String registerStudent(@ModelAttribute Student student){
        // System.out.println(student.getName()+student.getSRN()+student.getBranch()+student.getPassword());
        student.addStudent();
        // return "register";
        return "redirect:/";
    }
    @PostMapping("/studentLogin")
    public String loginStudent(@RequestParam String SRN, @RequestParam String Password,RedirectAttributes redirectAttributes,Model model){
        // System.out.println(SRN+Password);
        Student stu = new Student();
        String passFromDB = stu.login(SRN);
        // System.out.println(passFromDB);
        // stu.login(SRN);
        if (passFromDB.equals(Password)) {
            redirectAttributes.addAttribute("SRN", SRN);
            return "redirect:/teacherDashboard";
        } else {
            model.addAttribute("error", "Incorrect SRN or Password");
            return "studentLogin";
        }
    }

    @PostMapping("/teacherRegister")
    public String registerTeacher(@ModelAttribute Teacher teacher){
        // System.out.println(student.getName()+student.getSRN()+student.getBranch()+student.getPassword());
        teacher.addTeacher();
        // return "register";
        return "redirect:/";
    }
    @PostMapping("/teacherLogin")
    public String loginTeacher(@RequestParam String ID, @RequestParam String Password,RedirectAttributes redirectAttributes,Model model){
        // System.out.println(SRN+Password);
        Teacher teac = new Teacher();
        String passFromDB = teac.login(ID);
        // System.out.println(passFromDB);
        // stu.login(SRN);
        if (passFromDB.equals(Password)) {
            redirectAttributes.addAttribute("ID", ID);
            return "redirect:/teacherDashboard";
        } else {
            model.addAttribute("error", "Incorrect TeacherID or Password");
            return "teacherLogin";
        }
    }


    @PostMapping("/adminLogin")
    public String loginAdmin(@RequestParam String adminId, @RequestParam String password,RedirectAttributes redirectAttributes,Model model){
        // System.out.println(SRN+Password);
        Admin adm = new Admin();
        String passFromDB = adm.login(adminId);
        System.out.println(passFromDB);
        // stu.login(SRN);
        if (passFromDB.equals(password)) {
            redirectAttributes.addAttribute("adminId", adminId);
            return "redirect:/teacherDashboard";
        } else {
            model.addAttribute("error", "Incorrect AdminID or Password");
            return "adminLogin";
        }
    }
}
