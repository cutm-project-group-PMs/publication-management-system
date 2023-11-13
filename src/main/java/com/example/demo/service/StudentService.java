package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {

    Student signup(Student student);

    Student login(String email, String password);
}
