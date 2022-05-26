package com.example.accountingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Where(clause = "is_deleted=false")
public class StockDetails extends BaseEntity {

    @Column(columnDefinition = "DATE")
    private LocalDate iDate;

    private BigInteger quantity;
    private BigDecimal price;
    private BigInteger remainingQuantity;

    @OneToOne
    private Product product;
}
