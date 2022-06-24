package com.example.accountingapp.service.impl;

import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.service.ReportService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {

    private final InvoiceProductRepository invoiceProductRepository;

    public ReportServiceImpl(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public BigDecimal totalCost() {
        List<InvoiceProduct> listOfCost = invoiceProductRepository.findAllByInvoice_InvoiceType(InvoiceType.PURCHASE).stream().collect(Collectors.toList());
        List<BigDecimal> bigDecimal1 = listOfCost.stream().map(p->p.getPrice()).collect(Collectors.toList());
        BigDecimal totalCost = bigDecimal1.stream()
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        return totalCost;
    }
    // to get total Sale
    @Override
    public BigDecimal totalSale() {
        List<InvoiceProduct> listOfSale = invoiceProductRepository.findAllByInvoice_InvoiceType(InvoiceType.SALE).stream().collect(Collectors.toList());
        List<BigDecimal> bigDecimal2 = listOfSale.stream().map(p->p.getPrice()).collect(Collectors.toList());
        BigDecimal totalSale = bigDecimal2.stream().reduce(BigDecimal.ZERO, (a,b)-> a.add(b));
        return totalSale;
    }
    // to get total tax
    @Override
    public BigDecimal totalTax() {
        return null;
    }
//to get profit
    @Override
    public BigDecimal totalProfitLoss() {
        return null;
    }

    // tax

}
