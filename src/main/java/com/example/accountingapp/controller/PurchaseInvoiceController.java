package com.example.accountingapp.controller;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.enums.CompanyType;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.service.ClientVendorService;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.InvoiceService;
import com.example.accountingapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class PurchaseInvoiceController {

    final private InvoiceService invoiceService;
    final private ClientVendorService clientVendorService;
    final private InvoiceProductService invoiceProductService;
    final private ProductService productService;

    public PurchaseInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService,
                                     InvoiceProductService invoiceProductService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.invoiceProductService = invoiceProductService;
        this.productService = productService;
    }

    @GetMapping("/purchaseInvoiceList")
    public String purchaseInvoiceList(Model model) {
        model.addAttribute("purchaseInvoices", invoiceService.listAllByInvoiceType(InvoiceType.PURCHASE));
        return "invoice/purchase-invoice-list";
    }



    @PostMapping("/setInvoiceStatusEnabled/{id}")
    public String setInvoiceStatusEnabled(@PathVariable("id") Long id){
        invoiceService.enableInvoice(id);
        return "redirect:/invoice/purchaseInvoiceList";
    }


    @PostMapping("/purchaseInvoiceList/{id}")
    public String saveInvoice(@PathVariable("id") Long id) {

        invoiceService.enableInvoice(id);
        return "redirect:/invoice/purchaseInvoiceList";
    }

    @GetMapping("/purchaseInvoiceCreate")
    public String purchaseInvoiceCreate(Model model) {
        // TODO Vitaly Bahrom for new ID
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceType(InvoiceType.PURCHASE);
        Long id = invoiceService.saveAndReturnId(invoiceDTO);
        invoiceDTO.setId(id);
        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("vendors", clientVendorService.findAllByCompanyType(CompanyType.VENDOR));
        return "invoice/purchase-invoice-create";
    }

    @PostMapping("/purchaseInvoiceCreate/{id}")
    public String postPurchaseInvoiceCreate(@PathVariable("id") Long id, @ModelAttribute("invoiceDTO") InvoiceDTO invoiceDTO) {
        invoiceService.updateInvoiceCompany(invoiceDTO);
        return "redirect:/invoice/purchaseInvoiceSelectProduct/" + id;
    }

    @GetMapping("/purchaseInvoiceSelectProduct/{id}")
    public String getProductDetailsForInvoiceProduct(@PathVariable("id") Long id, Model model) {
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceDTOById(id);
        model.addAttribute("id", id);
        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("companyName", invoiceDTO.getCompany().getTitle());
        model.addAttribute("date", invoiceService.getLocalDate());
        model.addAttribute("invoiceId", invoiceService.getNextInvoiceIdPurchase());
        model.addAttribute("invoiceProductDTO", new InvoiceProductDTO());
        model.addAttribute("products", invoiceProductService.findAllProductsByCompanyName(invoiceDTO.getCompany().getTitle()));
        model.addAttribute("invoiceProducts", invoiceProductService.findAllInvoiceProductsByInvoiceId(id));
        return "invoice/purchase-invoice-select-product";
    }

    @PostMapping("/purchaseInvoiceSelectProduct/{id}")
    public String postProductDetailsForInvoiceProduct(@PathVariable("id") Long id, @ModelAttribute("invoiceProductDTO") InvoiceProductDTO invoiceProductDTO) {
        invoiceProductService.addInvoiceProductByInvoiceId(id, invoiceProductDTO);
        return "redirect:/invoice/purchaseInvoiceSelectProduct/" + id;
    }

    @PostMapping("/removeItemFromInvoice/{ipid}")
    public String deleteInvoiceProductFromInvoice(@PathVariable("ipid") Long ipid) {
        Long id = invoiceProductService.findInvoiceIdByInvoiceProductId(ipid);
        invoiceProductService.deleteInvoiceProductById(ipid);
        return "redirect:/invoice/purchaseInvoiceSelectProduct/" + id;
    }


    @PostMapping("/deletePurchaseInvoice/{id}")
    public String approveDeleteToInvoice(@PathVariable("id") String id, Model model) {
        invoiceService.delete(invoiceService.getInvoiceNo(id));
        return "redirect:/invoice/purchaseInvoiceList";
    }

    @PostMapping("/toProductInvoice/{id}")
    public String toInvoice(@PathVariable("id") String id, Model model) {

        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        Long invoiceId = invoiceService.getInvoiceNo(id);
        return "/invoice/toInvoice";
    }


    @PostMapping ("/approvePurchaseInvoice/{id}")
    public String approvePurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.approvePurchaseInvoice(id);
        return "redirect:/invoice/purchaseInvoiceList";
    }


}
