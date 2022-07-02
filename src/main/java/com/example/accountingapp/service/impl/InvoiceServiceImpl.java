package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.RoleDTO;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
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

        //set cost
        listDTO.forEach(p -> p.setPrice((calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        //set tax todo Vitaly Bahrom - set tax at 10 for now - Cihat
        listDTO.forEach(p -> p.setTax((p.getPrice().multiply(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.CEILING)));

        //set total
        listDTO.forEach(p -> p.setTotal(((p.getPrice()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));
        return listDTO;
    }

    @Override
    public BigDecimal calculateCostByInvoiceID(Long id) {
        //Get list of all invoice-products by invoice ID
        List<InvoiceProductDTO> invoiceProductListById = invoiceProductRepository.findAllByInvoiceId(id).stream()
                .map(p -> mapperUtil.convert(p, new InvoiceProductDTO()))
                .collect(Collectors.toList());
        BigDecimal cost = BigDecimal.valueOf(0);

        //add cost of each invoice-product for each invoice and write it to invoice cost field
        for (InvoiceProductDTO each : invoiceProductListById) {
            BigDecimal currItemCost = each.getPrice().multiply(each.getQty());
            cost = cost.add(currItemCost);
        }


       return cost;    
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


    @Override
    public BigDecimal calculatePriceByInvoiceID(Long id) {
        BigDecimal totalPrice = invoiceProductRepository.findAllByInvoiceId(id).stream().
                map(p->p.getPrice())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        return totalPrice;
    }

    @Override
    public BigDecimal calculateTaxByInvoiceID(Long id) {
        BigDecimal totalTax = invoiceProductRepository.findAllByInvoiceId(id).stream().
                map(p->p.getTax())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        return totalTax;
    }

}
