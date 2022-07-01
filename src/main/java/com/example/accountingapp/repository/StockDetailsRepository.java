package com.example.accountingapp.repository;


import com.example.accountingapp.entity.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {

    List<StockDetails> findAllByProductId(Long Id);


    Optional<StockDetails> findFirstByProductId(Long id);
}
