package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType);

    BigDecimal calculateCostByInvoiceID(Long id);

    void save(InvoiceDTO dto);
    void update(InvoiceDTO dto);
    void delete(Long id);
    String getNextInvoiceId();
    String getLocalDate();
    Long getInvoiceNo(String id);

    void approveInvoice(String invoiceId);

}
