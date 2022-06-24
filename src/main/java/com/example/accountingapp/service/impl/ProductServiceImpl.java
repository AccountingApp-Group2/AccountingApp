package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.entity.Product;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.ProductRepository;
import com.example.accountingapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        return mapperUtil.convert(productRepository.findById(id).get(), new ProductDTO());
    }

    @Override
    public ProductDTO findByDescription(String id) {
        return productRepository.findByDescription(id);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        Product p = mapperUtil.convert(product,new Product());
        productRepository.save(p);
    }

}
