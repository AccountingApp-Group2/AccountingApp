package com.example.accountingapp.service.Impl;

import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImplemetation implements InvoiceService {
    @Override
    public List<InvoiceDto> listAllByInvoiceType(InvoiceType invoiceType) {
        return null;
    }
}
