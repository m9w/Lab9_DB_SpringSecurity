package com.example.lab9.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassHash {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static PasswordEncoder getInstance(){
        return passwordEncoder;
    }
}
