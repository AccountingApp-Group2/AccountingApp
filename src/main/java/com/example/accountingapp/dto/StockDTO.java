package com.example.accountingapp.dto;

import com.example.accountingapp.entity.Product;
import com.example.accountingapp.enums.InvoiceType;
import com.example.accountingapp.enums.SalePurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long id;

    @NotBlank
    private LocalDateTime iDate;

    @NotBlank
    private BigInteger quantity;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private BigInteger remainingQuantity;


}
