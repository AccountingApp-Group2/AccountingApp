package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Company;
import com.example.accountingapp.enums.InvoiceType;
import java.util.List;
import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct>  findAllByInvoiceId(Long id);
    List<InvoiceProduct> findAllByInvoice_Company(Company Company);
    List<InvoiceProduct> findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType invoiceType, Company company);


}
