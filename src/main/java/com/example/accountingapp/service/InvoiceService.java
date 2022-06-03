package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.enums.InvoiceType;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType);

//    Long findByCompanyId();
}
