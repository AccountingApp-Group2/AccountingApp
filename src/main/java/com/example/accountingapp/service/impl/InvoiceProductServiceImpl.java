package com.example.accountingapp.service.impl;


import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.entity.Invoice;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.entity.Product;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.enums.State;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.repository.InvoiceProductRepository;
import com.example.accountingapp.repository.InvoiceRepository;
import com.example.accountingapp.repository.ProductRepository;
import com.example.accountingapp.service.InvoiceProductService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    final private InvoiceProductRepository invoiceProductRepository;
    final private CompanyRepository companyRepository;
    final private ProductRepository productRepository;
    final private MapperUtil mapperUtil;
    final private InvoiceRepository invoiceRepository;
    final private UserService userService;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, CompanyRepository companyRepository, ProductRepository productRepository, MapperUtil mapperUtil, InvoiceRepository invoiceRepository, UserService userService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
    }

    @Override
    public List<InvoiceProduct> listAll() {
        return invoiceProductRepository.findAll();
    }

    @Override
    public List<ProductDTO> findAllProductsByCompanyName(String companyName) {
        List<Product> productList = productRepository.findAllProductsByCompanyName(companyName);
        List<ProductDTO> productDTOList= productList.stream()
                .map(p->mapperUtil.convert(p,new ProductDTO()))
                .collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public void addInvoiceProductByInvoiceId(Long id, InvoiceProductDTO invoiceProductDTO) {

        Invoice invoice = invoiceRepository.findById(id).get();
        InvoiceProduct invoiceProduct = mapperUtil.convert(invoiceProductDTO, new InvoiceProduct());
        invoiceProduct.setInvoice(invoice);
        //get last id in invoiceProduct Table +1
        Long lastId = invoiceProductRepository.findHighestId().get()+1;
        invoiceProduct.setId(lastId);

        if(invoice.getInvoiceType()== InvoiceType.PURCHASE) invoiceProduct.setProfit(BigDecimal.ZERO);
        Product product = productRepository.getProductByName(invoiceProductDTO.getName()).get();
        invoiceProduct.setProduct(product);
        invoiceProductRepository.save (invoiceProduct);
    }

    @Override
    public List<InvoiceProductDTO>  findAllInvoiceProductsByInvoiceId(Long id) {
        List<InvoiceProductDTO> invoiceProductDTOList = invoiceProductRepository.findAllByInvoiceId(id)
                .stream()
                .filter(p -> !p.getIsDeleted())
                .map(p -> mapperUtil.convert(p, new InvoiceProductDTO()))
                .collect(Collectors.toList());

        for (InvoiceProductDTO each : invoiceProductDTOList) {
            each.setTax(getTaxByInvoiceId(id));
            each.setTotal((BigDecimal.valueOf(each.getQty()).multiply(each.getPrice()).multiply(each.getTax().add(BigDecimal.valueOf(100)))).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING));
        }
        return invoiceProductDTOList;
    }

    @Override
    public Long findInvoiceIdByInvoiceProductId(Long id) {
        return invoiceProductRepository.findInvoiceByInvoiceProductId(id);
    }

    @Override
    public void deleteInvoiceProductById(Long ipid) {
        InvoiceProduct invoiceProduct =  invoiceProductRepository.findById(ipid).get();
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);

    }


    @Override
    public List<InvoiceProductDTO> getByInvoiceId(Long invoiceId) {

        List<InvoiceProductDTO> invoiceProductDTO = invoiceProductRepository.findAllByInvoiceId(invoiceId)
                .stream()
//                .filter(Invoice::isEnabled)
                .map(p -> mapperUtil.convert(p, new InvoiceProductDTO())).collect(Collectors.toList());
        return invoiceProductDTO;
    }

    @Override
    public List<InvoiceProductDTO> listAllAddedItems() {
        return null;
    }

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceId(Long id) {
        return null;
    }

    @Override
    public void disableInvoiceProductsByInvoiceId(Long id) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(id);
        for (InvoiceProduct each : invoiceProductList) {
            each.setEnabled(false);
            invoiceProductRepository.save(each);
        }
    }

    @Override
    public BigDecimal getTaxByInvoiceId(Long id) {
        BigDecimal tax = invoiceRepository.findById(id).get().getClientVendor().getStateId().getState_tax();
        return tax;
    }


}


