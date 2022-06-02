package com.example.accountingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {


    @GetMapping(value = {"/"})
    public String loginM() {
        return "main2";
    }


}
