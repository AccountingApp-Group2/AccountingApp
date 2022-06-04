package com.example.accountingapp.controller;


import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class PurchaseInvoiceController {


    final private InvoiceService invoiceService;
    final private CompanyService companyService;

    public PurchaseInvoiceController(InvoiceService invoiceService, CompanyService companyService) {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
    }

    @GetMapping("/purchaseInvoiceList")
    public String purchaseInvoiceList(Model model) {

        model.addAttribute("purchaseInvoice", new InvoiceDTO());
        model.addAttribute("purchaseInvoices", invoiceService.listAllByInvoiceType(InvoiceType.PURCHASE));

        return "invoice/purchase-invoice-list";
    }

    @GetMapping("/purchaseInvoiceCreate")
    public String purchaseInvoiceCreate() {

        return "invoice/purchase-invoice-create";
    }
}
