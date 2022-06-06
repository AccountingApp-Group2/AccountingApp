package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final MapperUtil mapperUtil;

    private final InvoiceProductRepository invoiceProductRepository;

    public InvoiceProductServiceImpl(MapperUtil mapperUtil, InvoiceProductRepository invoiceProductRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceProductRepository = invoiceProductRepository;
    }


    @Override
    public Long calculateSaleCostByInvoiceId(Long id) {
        return null;
    }

    @Override
    public List<InvoiceProduct> invoiceProductListById(Long id) {
        return invoiceProductRepository.findAllByInvoiceId(id);
    }


}
