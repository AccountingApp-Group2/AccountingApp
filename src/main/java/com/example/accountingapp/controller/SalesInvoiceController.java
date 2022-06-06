package com.example.accountingapp.controller;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/invoice")
public class SalesInvoiceController {

    private final InvoiceService invoiceService;
    private final CompanyService companyService;
    private final InvoiceProductService invoiceProductService;

    public SalesInvoiceController(InvoiceService invoiceService, CompanyService companyService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/salesInvoiceList")
    public String salesInvoiceList(Model model) {
        model.addAttribute("salesInvoice", new InvoiceDTO());
        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));

        return "/invoice/sales-invoice-list";
    }

    @GetMapping("/salesInvoiceCreate")
    public String salesInvoiceCreate(Model model) {



        return "/invoice/sales-invoice-create";

//        return "/invoice/sales-invoice-select-product";
    }

    @PostMapping("/approveDeleteToInvoice/{id}")
    public String approveDeleteToInvoice(@PathVariable("{id}") Long id, Model model){



        return "/invoice/sales-invoice-list";
    }


}
