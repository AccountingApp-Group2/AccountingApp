package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.RoleDTO;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final MapperUtil mapperUtil;
    private final InvoiceRepository invoiceRepository;

    private final InvoiceProductRepository invoiceProductRepository;

    public InvoiceServiceImpl(MapperUtil mapperUtil, InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType) {
        List<InvoiceDTO> listDTO = invoiceRepository.findAllByInvoiceType(invoiceType).stream()
                .map(p -> mapperUtil.convert(p, new InvoiceDTO()))
                .collect(Collectors.toList());

        listDTO.forEach(p -> p.setCost((calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        //TODO: Vitaly, Baha to change Tax% based on State - set tax at 10 for now per Cihat
        listDTO.forEach(p -> p.setTax((p.getCost().multiply(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.CEILING)));

        listDTO.forEach(p -> p.setTotal(((p.getCost()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));

        return listDTO;
    }

    @Override
    public BigDecimal calculateCostByInvoiceID(Long id) {
        List<InvoiceProductDTO> invoiceProductListById = invoiceProductRepository.findAllByInvoiceId(id).stream()
                .map(p -> mapperUtil.convert(p, new InvoiceProductDTO()))
                .collect(Collectors.toList());
        BigDecimal cost = BigDecimal.valueOf(0);

        for (InvoiceProductDTO each : invoiceProductListById) {
            BigDecimal currItemCost = each.getPrice().multiply(BigDecimal.valueOf(each.getQty()));
            cost = cost.add(currItemCost);
        }

        return cost.setScale(2);
    }

    @Override
    public List<InvoiceDTO> newInvoiceNumberCreate(InvoiceDTO dto) {
        dto.setInvoiceNumber(dto.getInvoiceNumber());


        return null;
    }

    @Override
    public void save(InvoiceDTO dto) {

    }

    @Override
    public void update(InvoiceDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }


}
