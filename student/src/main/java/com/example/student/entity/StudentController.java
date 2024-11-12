package com.example.student.entity;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@Valid @RequestBody StudentEntity student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return the error message if validation fails
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);  // Return error message
        }

        studentService.registerStudent(student); // Save student in the database
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public Optional<StudentEntity> loginStudent(@RequestBody LoginRequest loginRequest) {
        return studentService.loginStudent(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
