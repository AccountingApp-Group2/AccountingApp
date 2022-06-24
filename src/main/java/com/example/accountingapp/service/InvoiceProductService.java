package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProduct> listAll();

    InvoiceProductDTO getByInvoiceId(Long invoiceId);
}