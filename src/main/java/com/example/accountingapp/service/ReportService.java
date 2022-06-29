package com.example.accountingapp.service;

import com.example.accountingapp.dto.ReportDTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;


public interface ReportService {

//    BigDecimal totalCost();
//    BigDecimal totalSale();
//    BigDecimal totalTax();
//    BigDecimal totalProfitLoss();


    Map<String, BigDecimal> profitLoss();


    Set <ReportDTO> calculateByProducts();
}