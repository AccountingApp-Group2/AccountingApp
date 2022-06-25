package com.example.accountingapp.repository;

import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

    ProductDTO findByDescription(String description);



    List<Product> findAllByCompanyId(Long companyId);



    Optional<Product> getProductByName(String name);
}
