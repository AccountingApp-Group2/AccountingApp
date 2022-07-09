package com.example.accountingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportDTO {
    private String name;
    private Integer purchasedQty;
    private Integer soldQty;
    private BigDecimal totalCost;
    private BigDecimal totalIncome;

}
