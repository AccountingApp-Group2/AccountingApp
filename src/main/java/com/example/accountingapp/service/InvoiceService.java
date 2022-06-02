package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InvoiceService {


    List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType);
}
