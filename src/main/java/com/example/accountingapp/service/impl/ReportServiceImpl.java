package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.PaymentDTO;
import com.example.accountingapp.dto.ReportDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.entity.Payment;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.repository.PaymentRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.InvoiceService;
import com.example.accountingapp.service.ReportService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceService invoiceService;
    private final UserService userService;
    private final PaymentRepository paymentRepository;
    private final CompanyService companyService;

    public ReportServiceImpl(InvoiceProductRepository invoiceProductRepository, UserRepository userRepository, InvoiceRepository invoiceRepository, MapperUtil mapperUtil, InvoiceService invoiceService, UserService userService, PaymentRepository paymentRepository, CompanyService companyService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceService = invoiceService;
        this.userService = userService;
        this.paymentRepository = paymentRepository;
        this.companyService = companyService;
    }

    @Override
    public Map<String, BigDecimal> profitLoss() {



        Map<String, BigDecimal> profitLoss = new HashMap<>();


        BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE, userService.findCompanyByLoggedInUser()).stream().
                map(p->p.getPrice().multiply(BigDecimal.valueOf(p.getQty()))).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalSale = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, userService.findCompanyByLoggedInUser()).stream().
                map(p->p.getPrice().multiply(BigDecimal.valueOf(p.getQty()))).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalTax = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, userService.findCompanyByLoggedInUser()).stream().
                map(p->p.getTax()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        profitLoss.put("totalCost", totalCost);
        profitLoss.put("totalSale", totalSale);
        profitLoss.put("totalTax", totalTax);

        return profitLoss;
    }

    @Override
    public Set<ReportDTO> calculateByProducts() {
        Set<ReportDTO> list= new HashSet<>();
        invoiceProductRepository.findAllByInvoice_Company(userService.findCompanyByLoggedInUser()).stream().forEach(p->{
            BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->(product.getPrice()).multiply(BigDecimal.valueOf(product.getQty()))).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            Integer purchasedQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(0, (a, b) -> a + b);

            BigDecimal totalIncome = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->(product.getPrice()).multiply(BigDecimal.valueOf(product.getQty()))).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            Integer soldQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(0, (a, b) -> a + b);

            list.add(new ReportDTO(p.getName(),purchasedQTY,soldQTY,totalCost,totalIncome));
        });
        return list;
    }

    @Override
    public List<InvoiceDTO> findLast3ByCompany() {
        List<InvoiceDTO> listInvoiceDTO = invoiceRepository.findLast3InvoiceByDate(userService.findCompanyByLoggedInUser().getTitle())
                        .stream().map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO())).collect(Collectors.toList());
        listInvoiceDTO.forEach(p -> p.setCost((invoiceService.calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        for (InvoiceDTO eachDTO : listInvoiceDTO) {
            if (eachDTO.getInvoiceType().equals("Purchase")) {
                BigDecimal tax = eachDTO.getClientVendor().getStateId().getState_tax().divide(BigDecimal.valueOf(100));
                listInvoiceDTO.forEach(p -> p.setTax((p.getCost().multiply(tax)).setScale(2, RoundingMode.CEILING)));
            } else {
                BigDecimal tax = companyService.findTaxByCompany().divide(BigDecimal.valueOf(100));

                listInvoiceDTO.forEach(p -> p.setTax((p.getCost().multiply(tax)).setScale(2, RoundingMode.CEILING)));
            }
        }
        listInvoiceDTO.forEach(p -> p.setTotal(((p.getCost()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));
        return listInvoiceDTO;
    }

    @Override
    public List<InvoiceProduct> findAllByCompany() {

        return invoiceProductRepository.findAllByInvoice_Company(userService.findCompanyByLoggedInUser());
    }

    @Override
    public List<Payment> listAllByYearAndCompany(String year) {
        return paymentRepository.findPaymentByYearOrderByMonth(year).stream()
                .collect(Collectors.toList());
    }


}
