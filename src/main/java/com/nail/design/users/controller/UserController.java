package com.nail.design.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "Loggining";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Registering";
    }
}
