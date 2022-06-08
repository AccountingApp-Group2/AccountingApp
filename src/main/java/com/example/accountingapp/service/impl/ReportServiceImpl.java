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
        List<BigDecimal> bigInteger = listOfCost.stream().map(p->p.getPrice()).collect(Collectors.toList());
        BigDecimal totalCost = bigInteger.stream()
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        return totalCost;
    }

    // tax
}
