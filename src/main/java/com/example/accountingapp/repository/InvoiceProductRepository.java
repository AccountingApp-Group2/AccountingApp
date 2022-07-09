package com.example.accountingapp.repository;

import java.util.List;

import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    @Query(value = "SELECT * FROM invoice_product i where i.invoice_id =?1",nativeQuery = true)
    List<InvoiceProduct>  findAllByInvoiceId(@Param("id") Long id);


    @Query(value = "SELECT MAX(id) FROM invoice_product",nativeQuery = true)
    Optional<Long> findHighestId();


    @Query("SELECT i.invoice.id from InvoiceProduct i where i.id =?1")
    Long findInvoiceByInvoiceProductId (@Param("id") Long id);


    List<InvoiceProduct> getByInvoiceId(Long id);


    List<InvoiceProduct> findAllByInvoice_Company(Company Company);
    List<InvoiceProduct> findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType invoiceType, Company company);




}
