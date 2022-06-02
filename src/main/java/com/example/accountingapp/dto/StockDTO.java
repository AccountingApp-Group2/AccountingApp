package com.example.accountingapp.dto;

import com.example.accountingapp.entity.Product;
import com.example.accountingapp.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private LocalDateTime dateTime;
    private String productName;
    private String quantity;
    private InvoiceType invoiceType;
    private int basePrice;

}
