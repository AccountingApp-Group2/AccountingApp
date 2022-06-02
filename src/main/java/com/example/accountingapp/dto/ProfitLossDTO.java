package com.example.accountingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfitLossDTO {

    private int totalCost;
    private int totalSale;
    private int totalTax;
    private int totalProfitLoss;
    private String productName;
    private int quantityOfSoldProducts;
    private int profitLoss;
}
