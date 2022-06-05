package com.example.accountingapp.dto;

import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.enums.SalePurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    
    private LocalDateTime dateTime;
    private String quantity;
//    private InvoiceType invoiceType;
    private int basePrice;
    private SalePurchaseStatus salePurchaseStatus;

    private String invoiceProductDTO;
}
