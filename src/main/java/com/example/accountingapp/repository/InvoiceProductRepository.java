package com.example.accountingapp.repository;

import com.example.accountingapp.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
    List<InvoiceProduct> findAllByInvoiceId(Long id);

    List<InvoiceProduct> getTaxByInvoiceId (Long id);
}
