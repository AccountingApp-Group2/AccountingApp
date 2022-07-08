package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.entity.Product;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.ProductRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(email);
        List<Product> list = productRepository.findAllByCompany(loggedInUser.getCompany());
        return list.stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        return mapperUtil.convert(productRepository.findById(id).get(), new ProductDTO());
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product convertedProduct = mapperUtil.convert(productDTO, new Product());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(email);
        convertedProduct.setCompany(loggedInUser.getCompany());
        convertedProduct.setEnabled(true);
        productRepository.save(convertedProduct);
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        Product product = productRepository.findById(dto.getId()).get();
        Product convertedProduct = mapperUtil.convert(dto,new Product());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(email);
        convertedProduct.setCompany(loggedInUser.getCompany());
        convertedProduct.setEnabled(product.getEnabled());
        productRepository.save(convertedProduct);
        return findById(convertedProduct.getId());
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        product.setIsDeleted(true);
        productRepository.save(product);
    }

}
