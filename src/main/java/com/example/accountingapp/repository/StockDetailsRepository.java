package com.example.accountingapp.repository;

import com.example.accountingapp.entity.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailsRepository extends JpaRepository <StockDetails, Long> {
}
