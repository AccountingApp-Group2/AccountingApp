package com.example.accountingapp.service;

import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.entity.InvoiceProduct;
import com.example.accountingapp.enums.InvoiceType;
import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProduct> listAll();
    List<ProductDTO> findAllProductsByCompanyName(String companyName);


    void addInvoiceProductByInvoiceId(Long id, InvoiceProductDTO invoiceProductDTO);

    List<InvoiceProductDTO> findAllInvoiceProductsByInvoiceId(Long id);

    Long findInvoiceIdByInvoiceProductId(Long ipid);

    void deleteInvoiceProductById(Long ipid);


    List<InvoiceProductDTO> getByInvoiceId(Long invoiceId);

    List<InvoiceProductDTO> listAllAddedItems();

    List<InvoiceProductDTO> findAllByInvoiceId(Long id);
}