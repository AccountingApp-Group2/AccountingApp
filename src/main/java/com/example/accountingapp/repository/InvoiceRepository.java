package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByInvoiceType(InvoiceType invoiceType);

}
