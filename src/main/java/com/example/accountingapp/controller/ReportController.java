package com.example.accountingapp.controller;

import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.ReportService;
import com.example.accountingapp.service.UserService;
import com.example.accountingapp.service.impl.ReportServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {




    private final ReportService reportService;
    private final UserService userService;

    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
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

    @GetMapping("/export")
    public String exportPDFButton(Model model){
        model.addAttribute("profitLoss", reportService.profitLoss());
        model.addAttribute("productsTotal", reportService.calculateByProducts());
        model.addAttribute("company", userService.findCompanyByUserName());

        return "/report/export-pdf-button";
    }

}
