package com.example.accountingapp.controller;

import com.example.accountingapp.dto.UserDTO;
import com.example.accountingapp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/login"})
    public String login() {

        return "login";
    }

    @RequestMapping("/main2")
    public String main(Model model) {
        return "main2";
    }

}
