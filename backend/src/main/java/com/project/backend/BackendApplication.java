package com.project.backend;

import com.project.backend.controller.*;
import com.project.backend.model.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		// Admin a = new Admin (123, "testing"); //this should print "peace" if connection is established.
		// Admin a2 = new Admin (123, "testing"); //this should print "peace" if connection is established.
		// Student a3 = new Student("a","b","c","d");
	}
}
