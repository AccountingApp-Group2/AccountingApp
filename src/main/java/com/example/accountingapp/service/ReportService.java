package com.example.accountingapp.service;

import java.math.BigDecimal;


public interface ReportService {

    BigDecimal totalCost();
    BigDecimal totalSale();
    BigDecimal totalTax();
    BigDecimal totalProfitLoss();




}