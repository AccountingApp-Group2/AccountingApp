package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public interface StockDetailsRepository extends JpaRepository <StockDetails, Long> {

    ArrayList<StockDetails> findAllByProduct_Company(Company company);
}
