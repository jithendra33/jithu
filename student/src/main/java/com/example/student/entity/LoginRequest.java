package com.example.student.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}