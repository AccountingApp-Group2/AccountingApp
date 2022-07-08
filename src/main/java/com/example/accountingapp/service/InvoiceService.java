package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.enums.InvoiceType;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType);

    BigDecimal calculateCostByInvoiceID(Long id);

    void delete(Long id);
    String getNextInvoiceIdSale();
    String getNextInvoiceIdPurchase();
    String getLocalDate();
    Long getInvoiceNo(String id);
    void approveInvoice(String invoiceId);

    String findInvoiceName(String invoiceId);
    Long saveAndReturnId(InvoiceDTO invoiceDTO);

    InvoiceDTO save(InvoiceDTO invoiceDTO);

    void updateInvoiceCompany(InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoiceDTOById(Long id);

    void enableInvoice(Long id);

    void approvePurchaseInvoice(Long id);

    void addProductToStockByInvoice(Long id);
}
