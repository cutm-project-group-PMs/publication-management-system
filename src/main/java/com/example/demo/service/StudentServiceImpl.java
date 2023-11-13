package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
//====================================== signup  =============================================
    @Override

    public Student signup(Student student) {
        // Validate if email is unique
        if (isNullOrEmpty(student.getEmail()) || emailExists(student.getEmail())) {
            throw new IllegalArgumentException("Email is required and must be unique");
        }

        // Additional validation logic (e.g., password strength) can be added here

        // Validate other parameters (non-null and non-blank)
        if (isNullOrEmpty(student.getFirstName()) || isNullOrEmpty(student.getLastName()) ||
                isNullOrEmpty(student.getPassword())) {
            throw new IllegalArgumentException("First Name, Last Name, and Password are required");
        }

        // Save the student after validation
        return studentRepository.save(student);
    }

    private boolean emailExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }


	private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

//=================================     login      ================================================
    @Override
    public Student login(String email, String password) {
        // For simplicity, password hashing and proper authentication checks are omitted here
        return studentRepository.findByEmail(email)
                .filter(student -> student.getPassword().equals(password))
                .orElse(null);
    }
}
