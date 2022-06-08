package com.example.accountingapp.controller;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/report")
public class ReportController {


    private final StockDetailsService stockService;
    private final InvoiceProductService invoiceProductService;

    public ReportController(StockDetailsService stockService, InvoiceProductService invoiceProductService) {
        this.stockService = stockService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/stock")
    public String stockReport(Model model){
        model.addAttribute("invoiceProduct", invoiceProductService.listAll());
        return "/report/stock-report";
    }


    @GetMapping("/profit")
    public String profitLossReport(){
        return "/report/profit-loss-report";
    }
}



