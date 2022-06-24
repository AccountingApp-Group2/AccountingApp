package com.example.accountingapp.service.impl;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;
    private final UserRepository userRepository;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil, UserRepository userRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
        this.userRepository = userRepository;
    }

    @Override
    public List<InvoiceProductDTO> listAllByInvoiceType_Company(InvoiceType invoiceType) {

        User user = userRepository.findByEmail("admin@company2.com");

        List<InvoiceProductDTO> listDTO = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(invoiceType, user.getCompany()).stream().
                map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        // listDTO.forEach(System.out::println);
        return listDTO;
    }

    @Override

    public List<InvoiceProductDTO> listAll() {
        User user = userRepository.findByEmail("admin@company2.com");
        List<InvoiceProductDTO> listDTO = invoiceProductRepository.findAllByInvoice_Company(user.getCompany()).stream().
                map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        listDTO.forEach(System.out::println);
        return listDTO;
    }

}