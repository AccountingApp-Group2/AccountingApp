package com.example.accountingapp.repository;

import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.entity.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository <StockDetails, Long> {

    List<StockDetails> findAllBy();
}
