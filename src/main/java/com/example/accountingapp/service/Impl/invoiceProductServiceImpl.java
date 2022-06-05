package com.example.accountingapp.service.impl;

import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.service.InvoiceProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class invoiceProductServiceImpl implements InvoiceProductService {

    final private InvoiceProductRepository invoiceProductRepository;

    public invoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public Long calculatePurchaseCostByInvoiceId(Long id) {


        return null;
    }

    @Override
    public List<InvoiceProduct> invoiceProductListById(Long id) {
        return invoiceProductRepository.findAllByInvoiceId(id);
    }

    @Override
    public List<InvoiceProduct> listAll() {
        return invoiceProductRepository.findAll();
    }
}
