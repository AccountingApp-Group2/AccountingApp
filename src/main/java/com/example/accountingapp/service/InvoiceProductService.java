package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.InvoiceProduct;

import java.util.List;

public interface InvoiceProductService {

    Long calculateSaleCostByInvoiceId(Long id);

    List<InvoiceProduct> invoiceProductListById(Long id);


}
