package com.example.accountingapp.controller;


import com.example.accountingapp.service.ProductService;
import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class ReportController {


    private final StockDetailsService stockService;
    private final ProductService productService;

    public ReportController(StockDetailsService stockService, ProductService productService) {
        this.stockService = stockService;
        this.productService = productService;
    }

    @GetMapping("/stock")
    public String clickStock(Model model){

        model.addAttribute("stocks", stockService.listAllStocks());

        model.addAttribute("products", productService.findById(stockService.listAllStocks().stream().map(product -> product.getId())));

        return "/report/stock-report";
    }


    @GetMapping("/profit")
    public String clickProfit(){

        return "/report/profit-loss-report";
    }
}


