package com.example.accountingapp.service.impl;

import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final UserRepository userRepository;

    public ReportServiceImpl(InvoiceProductRepository invoiceProductRepository, UserRepository userRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.userRepository = userRepository;
    }

    //    @Override
//    public BigDecimal totalCost() {
//        List<InvoiceProduct> listOfCost = invoiceProductRepository.findAllByInvoice_InvoiceType(InvoiceType.PURCHASE).stream().collect(Collectors.toList());
//        List<BigDecimal> bigDecimal1 = listOfCost.stream().map(p->p.getPrice()).collect(Collectors.toList());
//        BigDecimal totalCost = bigDecimal1.stream()
//                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
//        System.out.println(totalCost);
//        return totalCost;
//    }
//    // to get total Sale
//    @Override
//    public BigDecimal totalSale() {
//        List<InvoiceProduct> listOfSale = invoiceProductRepository.findAllByInvoice_InvoiceType(InvoiceType.SALE).stream().collect(Collectors.toList());
//        List<BigDecimal> bigDecimal2 = listOfSale.stream().map(p->p.getPrice()).collect(Collectors.toList());
//        BigDecimal totalSale = bigDecimal2.stream().reduce(BigDecimal.ZERO, (a,b)-> a.add(b));
//        return totalSale;
//    }
    // to get total tax



    @Override
    public Map<String, BigDecimal> profitLoss() {

        User user = userRepository.findByEmail("admin@company2.com");

        Map<String, BigDecimal> profitLoss = new HashMap<>();


        BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE, user.getCompany()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalSale = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, user.getCompany()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalTax = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, user.getCompany()).stream().
                map(p->p.getTax()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        profitLoss.put("totalCost", totalCost);
        System.out.println("this is total cost =  "+ totalCost);
        profitLoss.put("totalSale", totalSale);
        System.out.println("this is total sale =  " + totalSale);
        profitLoss.put("totalTax", totalTax);
        System.out.println("this is total for tax =  " + totalTax);

        return profitLoss;
    }



}
