package com.example.accountingapp.controller;

import com.example.accountingapp.client.CurrencyClient;
import com.example.accountingapp.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class DashboardController {

    private final ReportService reportService;
    private final CurrencyClient currencyClient;

    public DashboardController(ReportService reportService, CurrencyClient currencyClient) {
        this.reportService = reportService;
        this.currencyClient = currencyClient;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("profitLoss", reportService.profitLoss());
        model.addAttribute("last3Invoices", reportService.findLast3ByCompany());
        model.addAttribute("rates", currencyClient.getCurrency());


        return "dashboard";
    }
}
