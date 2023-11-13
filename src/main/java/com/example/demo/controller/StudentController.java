package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //==============================   signup     ================================================
    @PostMapping("/signup")
    public ResponseEntity<Student> signup(@RequestBody Student student) {
        Student createdStudent = studentService.signup(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    
    //==============================login=========================================================
    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestParam String email, @RequestParam String password) {
        Student student = studentService.login(email, password);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
