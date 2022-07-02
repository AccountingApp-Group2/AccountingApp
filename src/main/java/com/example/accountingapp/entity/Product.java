package com.example.accountingapp.entity;

import com.example.accountingapp.enums.ProductStatus;
import com.example.accountingapp.enums.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    private String name;
    private String description;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
   private Category category;


    private BigDecimal qty;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private BigDecimal lowLimitAlert;
    private BigDecimal tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private BigInteger newColumn;

}