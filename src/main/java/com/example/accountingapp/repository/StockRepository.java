package com.example.accountingapp.repository;

import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.entity.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository <StockDetails, Long> {
}
