package com.example.student.entity;

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
        // Validate email: Only Gmail accounts are allowed
        if (student.getEmail() == null || !student.getEmail().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Only Gmail accounts are allowed.");
        }

        // Check if the email is already registered
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }

        // Manually validate password if annotation-based validation is not working
        if (!student.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@, $, !, %, *, ?, &).");
        }

        // Save student to the database
        return studentRepository.save(student);
    }

    public Optional<StudentEntity> loginStudent(String email, String password) {
        // Find student by email
        Optional<StudentEntity> student = studentRepository.findByEmail(email);

        // Validate password for login
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return student; // Successful login
        }

        return Optional.empty(); // Login failed
    }
}
