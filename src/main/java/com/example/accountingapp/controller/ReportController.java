package com.example.accountingapp.controller;

import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.ReportService;
import com.example.accountingapp.service.impl.ReportServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {




    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/stock")
    public String stockReport(Model model){
        model.addAttribute("invoiceProduct", reportService.findAllByCompany());
        return "/report/stock-report";
    }


    @GetMapping("/profit")
    public String profitLossReport(Model model){
        model.addAttribute("profitLoss", reportService.profitLoss());
        model.addAttribute("productsTotal", reportService.calculateByProducts());

        return "/report/profit-loss-report";
    }


}
