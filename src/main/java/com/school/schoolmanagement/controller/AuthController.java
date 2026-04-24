package com.school.schoolmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/access-denied")
    public String deniedPage() {
        return "auth/access-denied";
    }
}