package com.example.accountingapp.service.impl;
import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.repository.ProductRepository;
import com.example.accountingapp.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import com.example.accountingapp.entity.Company;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final MapperUtil mapperUtil;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;


    public InvoiceServiceImpl(MapperUtil mapperUtil, InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository, CompanyRepository companyRepository, ProductRepository productRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public String getNextInvoiceId() {
        long nextMax= invoiceRepository.selectMaxInvoiceId() + 1;
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        formatter.applyPattern("000");
        String tempMax = formatter.format(nextMax);
        return"S-INV" + tempMax;
    }

    @Override
    public String getLocalDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/YYYY");
        String localDate = date.format(formatters);
        return localDate;
    }

    @Override
    public Long getInvoiceNo(String id) {
        return invoiceRepository.getInvoiceId(id);
    }

    @Override
    public void approveInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceId);
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoiceRepository.save(invoice);

    }

    @Override
    public String findInvoiceName(String invoiceId) {
        return invoiceRepository.findInvoiceNameByInvoiceId(invoiceId);
    }


    //--------Vitaly methods-------


    @Override
    public List<InvoiceDTO> listAllByInvoiceType(InvoiceType invoiceType) {
        //map invoiceProduct of each Invoice -> DTO
        List<InvoiceDTO> listDTO = invoiceRepository.findAllByInvoiceType(invoiceType)
                .stream()
                .filter(Invoice::isEnabled)
                .map(p -> mapperUtil.convert(p, new InvoiceDTO())).collect(Collectors.toList());

        //map all invoice products from invoice to invoiceProduct DTO
        for (InvoiceDTO each: listDTO){
            List<InvoiceProductDTO> invoiceProductDTOList =  invoiceProductRepository.findAllByInvoiceId(each.getId())
                    .stream()
                    .map(p->mapperUtil.convert(p,new InvoiceProductDTO()))
                    .collect(Collectors.toList());
            each.setInvoiceProductList(invoiceProductDTOList);
        }

        //set cost
        listDTO.forEach(p -> p.setCost((calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        //set tax
        if (invoiceType == InvoiceType.PURCHASE) {
            for (InvoiceDTO eachInvoiceDTO : listDTO) {
                BigDecimal totalTax = BigDecimal.valueOf(0);
                for (InvoiceProductDTO each : eachInvoiceDTO.getInvoiceProductList()) {
                    totalTax = totalTax.add(each.getPrice().multiply(BigDecimal.valueOf(each.getQty())).multiply(each.getTax()).divide(BigDecimal.valueOf(100)));
                }
                eachInvoiceDTO.setTax(totalTax.setScale(2, RoundingMode.CEILING));
            }
        } else {   //todo Vitaly Bahrom - set tax
            listDTO.forEach(p -> p.setTax((p.getCost().multiply(BigDecimal.valueOf(0.07))).setScale(2, RoundingMode.CEILING)));
        }

        //set total
        listDTO.forEach(p -> p.setTotal(((p.getCost()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));
        return listDTO;
    }



    @Override
    public BigDecimal calculateCostByInvoiceID(Long id) {

        //Get list of all invoice-products by invoice ID
        List<InvoiceProductDTO> invoiceProductListById = invoiceProductRepository.findAllByInvoiceId(id).stream().map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        BigDecimal cost = BigDecimal.valueOf(0);

        //add cost of each invoice-product for each invoice and write it to invoice cost field
        for (InvoiceProductDTO each : invoiceProductListById) {
            BigDecimal currItemCost = each.getPrice().multiply(BigDecimal.valueOf(each.getQty()));
            cost = cost.add(currItemCost);
        }
        return cost;
    }

    @Override
    public Long saveAndReturnId(InvoiceDTO invoiceDTO) {
        //call method to save and also saved DTO
        return save(invoiceDTO).getId();
    }

    @Override
    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        //Map to entity
        Invoice invoice = mapperUtil.convert(invoiceDTO, new Invoice());
        //save in DB so can populate ID
        invoice = invoiceRepository.save(invoice);
        //create  Invoice Number based on Invoice ID
        invoice.setInvoiceNumber(invoice.getInvoiceType().toString().substring(0, 1) + "-INV-" + invoice.getId());
        invoice = invoiceRepository.save(invoice);
        return mapperUtil.convert(invoice, new InvoiceDTO());

    }


    @Override
    public void updateNewInvoice(InvoiceDTO dto) {
        Optional<Invoice> invoice = invoiceRepository.findById(dto.getId());
        if (invoice.isPresent()) {
            Company company = companyRepository.findByTitle(dto.getCompanyName()).get();
            invoice.get().setCompany(company);
            invoice.get().setInvoiceStatus(InvoiceStatus.PENDING);
            invoiceRepository.save(invoice.get());
        }
    }

    @Override
    public InvoiceDTO getInvoiceDTOById(Long id) {
        return mapperUtil.convert(invoiceRepository.findById(id), new InvoiceDTO());
    }

    @Override
    public void saveInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setEnabled(true);
        invoiceRepository.save(invoice);

    }



}