package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByInvoiceType(InvoiceType invoiceType);

    @Query("SELECT MAX(id) FROM Invoice")
    long selectMaxInvoiceId();

    @Query("SELECT id FROM Invoice WHERE invoiceNumber = ?1")
    Long getInvoiceId(@Param("id") String id);

    Invoice findByInvoiceNumber(String invoiceId);
}
