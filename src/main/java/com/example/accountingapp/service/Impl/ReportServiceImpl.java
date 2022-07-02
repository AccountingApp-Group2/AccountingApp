package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.ReportDTO;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.InvoiceService;
import com.example.accountingapp.service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceService invoiceService;

    public ReportServiceImpl(InvoiceProductRepository invoiceProductRepository, UserRepository userRepository, InvoiceRepository invoiceRepository, MapperUtil mapperUtil, InvoiceService invoiceService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceService = invoiceService;
    }

    @Override
    public Map<String, BigDecimal> profitLoss() {

        User user = userRepository.findByEmail("admin@company2.com");

        Map<String, BigDecimal> profitLoss = new HashMap<>();


        BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE, user.getCompany()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalSale = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, user.getCompany()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalTax = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, user.getCompany()).stream().
                map(p->p.getTax()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        profitLoss.put("totalCost", totalCost);
        profitLoss.put("totalSale", totalSale);
        profitLoss.put("totalTax", totalTax);

        return profitLoss;
    }

    @Override
    public Set<ReportDTO> calculateByProducts() {
        Set<ReportDTO> list= new HashSet<>();
        User user = userRepository.findByEmail("admin@company2.com");
        invoiceProductRepository.findAllByInvoice_Company(user.getCompany()).stream().forEach(p->{
            BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,user.getCompany())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getPrice()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            BigDecimal purchasedQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,user.getCompany())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            BigDecimal totalIncome = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,user.getCompany())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getPrice()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            BigDecimal soldQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,user.getCompany())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            list.add(new ReportDTO(p.getName(),purchasedQTY,soldQTY,totalCost,totalIncome));
        });
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public List<InvoiceDTO> findLast3ByCompany() {
        User user = userRepository.findByEmail("admin@company2.com");
        List<InvoiceDTO> listDTO = invoiceRepository.findInvoice(user.getCompany().getTitle())
                        .stream().map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO())).collect(Collectors.toList());
        listDTO.forEach(p -> p.setPrice((invoiceService.calculatePriceByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));
        listDTO.forEach(p -> p.setTax((invoiceService.calculateTaxByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        return listDTO;
    }


}
