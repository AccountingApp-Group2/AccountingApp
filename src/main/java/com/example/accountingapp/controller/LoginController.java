package com.example.accountingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {


    @RequestMapping(value = {"/", "/login"})
    public String login() {

        return "login";
    }

    @RequestMapping("/main2")
    public String main() {
        return "main2";
    }
}
