package com.example.accountingapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

    @GetMapping("/stock")
    public String clickStock(){

        return "/report/stock-report";
    }

    @GetMapping("/profit")
    public String clickProfit(){

        return "/report/profit-loss-report";
    }
}


