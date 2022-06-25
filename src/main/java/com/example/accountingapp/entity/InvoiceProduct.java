package com.example.accountingapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class InvoiceProduct extends BaseEntity {

    private String name;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal profit;

    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    Invoice invoice;

}
