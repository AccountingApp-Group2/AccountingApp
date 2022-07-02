package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByInvoiceType(InvoiceType invoiceType);
    List<Invoice> findAllByInvoiceTypeAndCompany(InvoiceType invoiceType, Company company);

    @Query(value = "SELECT * FROM invoice i INNER JOIN company c ON c.id = i.company_id WHERE c.title = ?1 ORDER BY i.invoice_date DESC LIMIT 3 ", nativeQuery = true)
    List<Invoice> findInvoice(@Param("companyTitle") String companyTitle);
}
