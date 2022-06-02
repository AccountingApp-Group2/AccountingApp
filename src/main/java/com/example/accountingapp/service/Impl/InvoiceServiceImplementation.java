package com.example.accountingapp.service.Impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImplementation implements InvoiceService {
    private final MapperUtil mapperUtil;
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImplementation(MapperUtil mapperUtil, InvoiceRepository invoiceRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType) {
        return invoiceRepository.findAllByInvoiceType(invoiceType).stream()
                .map(p -> mapperUtil.convert(p, new InvoiceDTO()))
                .collect(Collectors.toList());
    }
}
