package com.example.accountingapp.service.impl;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDTO> listAllByInvoiceType(InvoiceType invoiceType) {
        List<InvoiceProductDTO> listDTO = invoiceProductRepository.findAllByInvoice_InvoiceType(invoiceType).stream().
                map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        // listDTO.forEach(System.out::println);
        return listDTO;
    }

    @Override
    public List<InvoiceProductDTO> listAll() {
        List<InvoiceProductDTO> listDTO = invoiceProductRepository.findAll().stream().
                map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        //listDTO.forEach(System.out::println);
        return listDTO;
    }
}