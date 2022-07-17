package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.*;
import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.enums.State;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.*;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.InvoiceService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final MapperUtil mapperUtil;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final StockDetailsRepository stockDetailsRepository;
    private final ClientVendorRepository clientVendorRepository;
    private final InvoiceProductService invoiceProductService;
    private final CompanyService companyService;
    private final UserService userService;

    public InvoiceServiceImpl(MapperUtil mapperUtil, InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository, CompanyRepository companyRepository, ProductRepository productRepository, StockDetailsRepository stockDetailsRepository, ClientVendorRepository clientVendorRepository, InvoiceProductService invoiceProductService, CompanyService companyService, UserService userService) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.stockDetailsRepository = stockDetailsRepository;
        this.clientVendorRepository = clientVendorRepository;
        this.invoiceProductService = invoiceProductService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public String getNextInvoiceIdSale() {
        long nextMax = invoiceRepository.selectMaxInvoiceId() + 1;
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        formatter.applyPattern("000");
        String tempMax = formatter.format(nextMax);
        return "S-INV" + tempMax;
    }

    @Override
    public String getNextInvoiceIdPurchase() {
        long nextMax = invoiceRepository.selectMaxInvoiceId() + 1;
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        formatter.applyPattern("000");
        String tempMax = formatter.format(nextMax);
        return "P-INV" + tempMax;
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
        List<InvoiceDTO> listInvoiceDTO = invoiceRepository.findAllByInvoiceTypeAndCompany(invoiceType,userService.findCompanyByLoggedInUser())
                .stream().filter(Invoice::isEnabled).map(p -> mapperUtil.convert(p, new InvoiceDTO())).collect(Collectors.toList());


        //map all invoice products from invoice to invoiceProduct DTO
        for (InvoiceDTO each : listInvoiceDTO) {
            List<InvoiceProductDTO> invoiceProductDTOList = invoiceProductRepository.findAllByInvoiceId(each.getId())
                    .stream()
                    .filter(InvoiceProduct::isEnabled)
                    .map(p -> mapperUtil.convert(p, new InvoiceProductDTO()))
                    .collect(Collectors.toList());
            each.setInvoiceProductList(invoiceProductDTOList);
        }

        //set cost
        listInvoiceDTO.forEach(p -> p.setCost((calculateCostByInvoiceID(p.getId())).setScale(2, RoundingMode.CEILING)));

        //set tax
        if (invoiceType == InvoiceType.PURCHASE) {
            for (InvoiceDTO eachInvoiceDTO : listInvoiceDTO) {
                BigDecimal totalTax = BigDecimal.valueOf(0);
                for (InvoiceProductDTO each : eachInvoiceDTO.getInvoiceProductList()) {
                    each.setTax(eachInvoiceDTO.getClientVendor().getStateId().getState_tax());
                    totalTax = totalTax.add(each.getPrice().multiply(BigDecimal.valueOf(each.getQty())).multiply(each.getTax()).divide(BigDecimal.valueOf(100)));
                }
                eachInvoiceDTO.setTax(totalTax.setScale(2, RoundingMode.CEILING));
            }
        } else {   //todo Vitaly Bahrom - set tax
            BigDecimal tax = companyService.findTaxByCompany().divide(BigDecimal.valueOf(100));

            listInvoiceDTO.forEach(p -> p.setTax((p.getCost().multiply(tax)).setScale(2, RoundingMode.CEILING)));
        }

        //set total
        listInvoiceDTO.forEach(p -> p.setTotal(((p.getCost()).add(p.getTax())).setScale(2, RoundingMode.CEILING)));

        return listInvoiceDTO;
    }


    @Override
    public BigDecimal calculateCostByInvoiceID(Long id) {
        List<InvoiceProductDTO> invoiceProductListById = invoiceProductRepository.findAllByInvoiceId(id)
                .stream().filter(p -> p.isEnabled())
                .map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        BigDecimal cost = BigDecimal.valueOf(0);
        for (InvoiceProductDTO each : invoiceProductListById) {
            BigDecimal currItemCost = each.getPrice().multiply(BigDecimal.valueOf(each.getQty()));
            cost = cost.add(currItemCost);
        }
        return cost;
    }

    @Override
    public Long saveAndReturnId(InvoiceDTO invoiceDTO) {
        return save(invoiceDTO).getId();
    }


    //Method for default invoice settings upon creation
    @Override
    public InvoiceDTO save(InvoiceDTO invoiceDTO) {


        Invoice invoice = mapperUtil.convert(invoiceDTO, new Invoice());
        String invoiceNumber = "";
        if (invoiceDTO.getInvoiceType().equals(InvoiceType.SALE)) {
            invoiceNumber = getNextInvoiceIdSale();
        } else {
            invoiceNumber = getNextInvoiceIdPurchase();
        }
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setCompany(userService.findCompanyByLoggedInUser());
        invoice.setInvoiceStatus(InvoiceStatus.PENDING);

        invoice = invoiceRepository.save(invoice);
        return mapperUtil.convert(invoice, new InvoiceDTO());
    }

    @Override
    public void updateInvoiceCompany(InvoiceDTO dto) {
        Optional<Invoice> invoice = invoiceRepository.findById(dto.getId());
        if (invoice.isPresent()) {
            ClientVendor clientVendor = clientVendorRepository.findByCompanyName(dto.getCompanyName()).get();
            invoice.get().setClientVendor(clientVendor);
            invoiceRepository.save(invoice.get());
        }
    }

    @Override
    public InvoiceDTO getInvoiceDTOById(Long id) {
        return mapperUtil.convert(invoiceRepository.findById(id), new InvoiceDTO());
    }

    @Override
    public void enableInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        // set status enabled for all product invoices in the list
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(id);
        for (InvoiceProduct eachInvoiceProduct : invoiceProductList) {
            eachInvoiceProduct.setEnabled(true);
        }
        invoice.setEnabled(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public void approvePurchaseInvoice(Long id) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(id);
        for (InvoiceProduct eachInvoiceProduct : invoiceProductList) {
            //update stock
            Long productId = eachInvoiceProduct.getProduct().getId();
            Integer additionalQty = eachInvoiceProduct.getQty();
            Product product = productRepository.findProductById(productId).get();
            product.setQty(product.getQty().add(BigInteger.valueOf(additionalQty)));
            productRepository.save(product);
        }
        //change status of invoice -> approved
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoiceRepository.save(invoice);

    }

    @Override
    public void addProductToStockByInvoice(Long id) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(id);

        for (InvoiceProduct eachInvoiceProduct : invoiceProductList) {
            StockDetails stockDetails = new StockDetails();
            stockDetails.setProduct(eachInvoiceProduct.getProduct());
            eachInvoiceProduct.setTax(invoiceProductService.getTaxByInvoiceId(id)); //new
            stockDetails.setPrice(eachInvoiceProduct.getTax().add(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100)).multiply(eachInvoiceProduct.getPrice()));
            stockDetails.setQuantity(BigInteger.valueOf(eachInvoiceProduct.getQty()));
            stockDetails.setRemainingQuantity(BigInteger.valueOf(eachInvoiceProduct.getQty()));
            stockDetails.setIDate(LocalDateTime.now());
            stockDetailsRepository.save(stockDetails);


        }
    }
}