package com.example.accountingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDetailsDTO {

    private Long id;
    private LocalDateTime iDate;
    private BigInteger quantity;
    private BigDecimal price;
    private BigInteger remainingQuantity;
    private BigInteger productId;
}
