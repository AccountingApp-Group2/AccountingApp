package com.example.accountingapp.controller;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.dto.InvoiceDTO;

import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.enums.CompanyType;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.enums.ProductStatus;
import com.example.accountingapp.service.*;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;


@Controller
@RequestMapping("/invoice")
public class SalesInvoiceController {

    private final InvoiceService invoiceService;
    private final CompanyService companyService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;

    public SalesInvoiceController(InvoiceService invoiceService, CompanyService companyService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }

    @GetMapping("/salesInvoiceList")
    public String salesInvoiceList(Model model) {
        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        return "/invoice/sales-invoice-list";
    }

    @PostMapping("/delete/{id}")
    public String approveDeleteToInvoice(@PathVariable("id") String id, Model model) {

        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        Long invoiceId = invoiceService.getInvoiceNo(id);
        invoiceService.delete(invoiceId);
        return "redirect:/invoice/salesInvoiceList";
    }

    @PostMapping("/toInvoice/{id}")
    public String toInvoice(@PathVariable("id") String id, Model model) {

        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        Long invoiceId = invoiceService.getInvoiceNo(id);
        return "/invoice/toInvoice";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable("id") String id, Errors errors, Model model) {

        String qtyNotEnough = "Qty not enough to be approved";
        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        Long invoiceId = invoiceService.getInvoiceNo(id);
        InvoiceProductDTO invoiceProduct = invoiceProductService.getByInvoiceId(invoiceId);
        ProductDTO product = productService.findById(invoiceProduct.getProductId());

        if(product.getProductStatus()== ProductStatus.ACTIVE && product.getQty().intValue()>=invoiceProduct.getQty()){
            BigInteger leftOverQty = BigInteger.valueOf(product.getQty().intValue() - invoiceProduct.getQty());
            product.setQty(leftOverQty);
            productService.updateProduct(product);
            invoiceService.approveInvoice(id);
        }else{
            errors.addMessage("Couldnt process");
            model.addAttribute("errorMessage","We couldnt process the request");
        };
        return "redirect:/invoice/salesInvoiceList";
    }
    @PostMapping("/addInvoiceItem")
    public String addItem (@ModelAttribute("saleProductList")InvoiceDTO invoiceDTO) {

        return "redirect:/invoice/sales-invoice-set-product-numbers";

    }

    @GetMapping("/salesInvoiceCreate")
    public String salesInvoiceCreate(Model model) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        return "/invoice/sales-invoice-create";
    }

    @GetMapping("/salesInvoiceSelectProduct")
    public String salesInvoiceSelectProduct(@ModelAttribute("client") ClientVendorDTO clientDto, Model model) {

        model.addAttribute("clientName", clientDto.getCompanyName());
        model.addAttribute("date", invoiceService.getLocalDate());
        model.addAttribute("invoiceId", invoiceService.getNextInvoiceId());
        model.addAttribute("products", productService.listAllProducts());
        return "/invoice/sales-invoice-set-product-numbers";
    }

    @GetMapping("/addInvoiceCancel")
    public String cancelAddItem() {

        return "redirect:/invoice/salesInvoiceCreate";
    }

}