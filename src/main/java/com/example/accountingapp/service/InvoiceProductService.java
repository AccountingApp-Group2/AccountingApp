package com.example.accountingapp.service;

import com.example.accountingapp.entity.InvoiceProduct;

import java.util.List;

public interface InvoiceProductService {
    Long calculatePurchaseCostByInvoiceId(Long Id);

    List<InvoiceProduct> invoiceProductListById(Long Id);
}
