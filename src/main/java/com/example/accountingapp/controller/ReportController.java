package com.example.accountingapp.controller;


import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {


    private final StockDetailsService stockService;

    public ReportController( StockDetailsService stockService) {

        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public String clickStock(Model model){

        model.addAttribute("stocks", stockService.listAllStocks());

        return "/report/stock-report";
    }


    @GetMapping("/profit")
    public String clickProfit(){

        return "/report/profit-loss-report";
    }
}


