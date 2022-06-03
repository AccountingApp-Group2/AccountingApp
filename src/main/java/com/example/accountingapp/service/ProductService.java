package com.example.accountingapp.service;

import com.example.accountingapp.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> listAllProducts();
    ProductDTO findById(Long id);
}
