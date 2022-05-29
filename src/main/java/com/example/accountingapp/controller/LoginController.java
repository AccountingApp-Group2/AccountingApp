package com.example.accountingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {


    @GetMapping(value = {"/", "/login"})
    public String loginM() {

        return "login";
    }


}
