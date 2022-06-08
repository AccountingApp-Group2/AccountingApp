package com.example.accountingapp.repository;

import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;



public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoice_InvoiceType(InvoiceType invoiceType);

    List<InvoiceProduct> findAllByInvoice(Long id);
}