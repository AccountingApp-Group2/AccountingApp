package com.example.accountingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Dashboard {

    @GetMapping
    public String dashboard() {
        return "fragments";
    }
}
