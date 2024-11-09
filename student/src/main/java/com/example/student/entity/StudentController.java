package com.example.student.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody StudentEntity student) {
        studentService.registerStudent(student); // Save student in database
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public Optional<StudentEntity> loginStudent(@RequestBody LoginRequest loginRequest) {
        return studentService.loginStudent(loginRequest.getEmail(), loginRequest.getPassword());
    }


    // Existing endpoints...
}