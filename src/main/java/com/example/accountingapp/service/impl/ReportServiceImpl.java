package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.ReportDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.InvoiceService;
import com.example.accountingapp.service.ReportService;
import com.example.accountingapp.service.UserService;
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
    private final UserService userService;

    public ReportServiceImpl(InvoiceProductRepository invoiceProductRepository, UserRepository userRepository, InvoiceRepository invoiceRepository, MapperUtil mapperUtil, InvoiceService invoiceService, UserService userService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @Override
    public Map<String, BigDecimal> profitLoss() {



        Map<String, BigDecimal> profitLoss = new HashMap<>();


        BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE, userService.findCompanyByLoggedInUser()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalSale = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, userService.findCompanyByLoggedInUser()).stream().
                map(p->p.getPrice()).
                reduce(BigDecimal.ZERO, (a, b) -> a.add(b));


        BigDecimal totalTax = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, userService.findCompanyByLoggedInUser()).stream().
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
        invoiceProductRepository.findAllByInvoice_Company(userService.findCompanyByLoggedInUser()).stream().forEach(p->{
            BigDecimal totalCost = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getPrice()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            Integer purchasedQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.PURCHASE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(0, (a, b) -> a + b);

            BigDecimal totalIncome = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getPrice()).
                    reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

            Integer soldQTY = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE,userService.findCompanyByLoggedInUser())
                    .stream()
                    .filter(product->product.getName().equals(p.getName()))
                    .map(product->product.getQty()).
                    reduce(0, (a, b) -> a + b);

            list.add(new ReportDTO(p.getName(),purchasedQTY,soldQTY,totalCost,totalIncome));
        });
        return list;
    }

    @Override
    public List<InvoiceDTO> findLast3ByCompany() {
        List<InvoiceDTO> listInvoiceDTO = invoiceRepository.findLast3InvoiceByDate(userService.findCompanyByLoggedInUser().getTitle())
                        .stream().map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO())).collect(Collectors.toList());
        listInvoiceDTO.forEach(p -> p.setCost((invoiceService.calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));
        listInvoiceDTO.forEach(p -> p.setTax((p.getCost().multiply(BigDecimal.valueOf(0.07))).setScale(2, RoundingMode.CEILING)));
        listInvoiceDTO.forEach(p -> p.setTotal(((p.getCost()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));
        return listInvoiceDTO;
    }

    @Override
    public List<InvoiceProduct> findAllByCompany() {

        return invoiceProductRepository.findAllByInvoice_Company(userService.findCompanyByLoggedInUser());
    }


}
