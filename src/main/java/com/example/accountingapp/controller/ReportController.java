package com.example.accountingapp.controller;

import com.example.accountingapp.service.ExportPDFService;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.ReportService;
import com.example.accountingapp.service.UserService;
import com.example.accountingapp.service.impl.ReportServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {


    private final ReportService reportService;
    private final UserService userService;
    private final ExportPDFService exportPDFService;

    public ReportController(ReportService reportService, UserService userService, ExportPDFService exportPDFService) {
        this.reportService = reportService;
        this.userService = userService;
        this.exportPDFService = exportPDFService;
    }

    @GetMapping("/stock")
    public String stockReport(Model model){
        model.addAttribute("invoiceProduct", reportService.findAllByCompany());
        return "/report/stock-report";
    }

    @GetMapping("/payment")
    public String showAllPayments(Model model, @RequestParam(defaultValue = "2022") String year) {
        String[] month = new DateFormatSymbols().getMonths();
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("year", year);
        model.addAttribute("payments", reportService.listAllByYearAndCompany(year));
        return "/report/payment-report";
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
        model.addAttribute("company", userService.findCompanyByLoggedInUser());

        return "/report/export-pdf-button";
    }

    @GetMapping("/downloadPDF")
    public void downloadReceipt(HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("profitLoss", reportService.profitLoss());
        data.put("productsList", reportService.calculateByProducts());
        data.put("company", userService.findCompanyByLoggedInUser());


        ByteArrayInputStream exportedData = exportPDFService.exportReceiptPdf("report", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
    }

}
