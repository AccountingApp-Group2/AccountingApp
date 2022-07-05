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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


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

    @GetMapping("/updateInvoice/{id}")
    public String editInvoice(@PathVariable("id") String invoiceId, Model model) {

        Long id = invoiceService.getInvoiceNo(invoiceId);
        model.addAttribute("clientName", clientVendorService.findClientNameById(id));
        model.addAttribute("date", invoiceService.getLocalDate());
        model.addAttribute("invoiceId", invoiceService.findInvoiceName(invoiceId));
        model.addAttribute("products", productService.listAllProducts());
        model.addAttribute("invoiceProductList", invoiceProductService.findAllByInvoiceId(id));
        return "/invoice/sales-invoice-set-product-numbers";
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
    public String toInvoice(@PathVariable("id") Long id, Model model) {

        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        model.addAttribute("invoiceProducts", invoiceProductService.findAllInvoiceProductsByInvoiceId(id));
        return "/invoice/toInvoice";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable("id") String id, Model model) {

        model.addAttribute("salesInvoices", invoiceService.listAllByInvoiceType(InvoiceType.SALE));
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        Long invoiceId = invoiceService.getInvoiceNo(id);
        List<InvoiceProductDTO> invoiceProducts = invoiceProductService.getByInvoiceId(invoiceId);

        for (InvoiceProductDTO invoiceProduct : invoiceProducts) {

            ProductDTO product = productService.findById(invoiceProduct.getProductId());

            if (product.getProductStatus() == ProductStatus.ACTIVE && product.getQty().intValue() >= invoiceProduct.getQty()) {
                BigInteger leftOverQty = BigInteger.valueOf(product.getQty().intValue() - invoiceProduct.getQty());
                product.setQty(leftOverQty);
                productService.updateProduct(product);
                invoiceService.approveInvoice(id);
            }

        }
        return "redirect:/invoice/salesInvoiceList";
    }

    @GetMapping("/salesInvoiceCreate")
    public String salesInvoiceCreate(Model model) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setInvoiceType(InvoiceType.SALE);
        Long id = invoiceService.saveAndReturnId(invoiceDTO);
        invoiceDTO.setId(id);

        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("clients", clientVendorService.findAllByCompanyType(CompanyType.CLIENT));
        return "/invoice/sales-invoice-create";
    }


    @PostMapping("/salesInvoiceCreate/{id}")
    public String postSalesInvoiceCreate(@PathVariable("id") Long id, @ModelAttribute("invoiceDTO") InvoiceDTO invoiceDTO) {
        invoiceService.updateInvoiceCompany(invoiceDTO);
        return "redirect:/invoice/salesInvoiceSelectProduct/" + id;
    }

    @GetMapping("/salesInvoiceSelectProduct/{id}")
    public String salesInvoiceSelectProduct(@PathVariable("id") Long id, Model model) {

        InvoiceDTO invoiceDTO = invoiceService.getInvoiceDTOById(id);
        model.addAttribute("id", id);
        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("companyName", invoiceDTO.getClientVendor().getCompanyName());
        model.addAttribute("date", invoiceService.getLocalDate());
        model.addAttribute("invoiceId", invoiceService.getNextInvoiceIdSale());
        model.addAttribute("invoiceProductDTO", new InvoiceProductDTO());
        model.addAttribute("products", productService.listAllProducts());
        model.addAttribute("invoiceProducts", invoiceProductService.findAllInvoiceProductsByInvoiceId(id));
        return "invoice/sales-invoice-select-product";
    }
    @PostMapping("/salesInvoiceSelectProduct/{id}")
    public String postProductDetailsForInvoiceProduct(@PathVariable("id") Long id, @ModelAttribute("invoiceProductDTO") InvoiceProductDTO invoiceProductDTO) {
        invoiceProductService.addInvoiceProductByInvoiceId(id, invoiceProductDTO);
        return "redirect:/invoice/salesInvoiceSelectProduct/" + id;
    }

    @PostMapping("/setInvoiceStatusEnabledSales/{id}")
    public String setInvoiceStatusEnabled(@PathVariable("id") Long id){
        invoiceService.enableInvoice(id);
        return "redirect:/invoice/salesInvoiceList";
    }


    @GetMapping("/editSalesInvoiceSelectProduct/{id}")
    public String editSalesInvoiceSelectProduct (@PathVariable("id") Long id) {

        invoiceProductService.disableInvoiceProductsByInvoiceId(id);
        return "redirect:/invoice/salesInvoiceSelectProduct/" + id;
    }

    @PostMapping("/removeItemFromInvoiceSales/{ipid}")
    public String deleteInvoiceProductFromInvoice(@PathVariable("ipid") Long ipid) {
        Long id = invoiceProductService.findInvoiceIdByInvoiceProductId(ipid);
        invoiceProductService.deleteInvoiceProductById(ipid);
        return "redirect:/invoice/salesInvoiceSelectProduct/" + id;
    }


//
//    @PostMapping("/addInvoiceItem")
//    public String addItem (@ModelAttribute("invoiceProduct") InvoiceProductDTO invoiceProductDTO, Model model) {
//
//        model.addAttribute("invoiceProduct", invoiceProductService.listAllAddedItems());
//
//        return "redirect:/invoice/sales-invoice-set-product-numbers";
//    }
//
//    @GetMapping("/addInvoiceCancel")
//    public String cancelAddItem() {
//
//        return "redirect:/invoice/salesInvoiceCreate";
//    }

}