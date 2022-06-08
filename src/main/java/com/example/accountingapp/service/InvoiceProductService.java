package com.example.accountingapp.service;



import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InvoiceProductService {
    List<InvoiceProductDTO> listAllByInvoiceType(InvoiceType invoiceType);
    List<InvoiceProductDTO> listAll();

}