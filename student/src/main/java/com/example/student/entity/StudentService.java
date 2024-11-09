package com.example.student.entity;


import com.example.student.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity registerStudent(StudentEntity student) {
        // Check if email already exists
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }
        return studentRepository.save(student); // Save the student entity to the database
    }

    public Optional<StudentEntity> loginStudent(String email, String password) {
        Optional<StudentEntity> student = studentRepository.findByEmail(email);
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return student; // Successful login
        }
        return Optional.empty(); // Login failed
    }

    // Existing methods...
}
