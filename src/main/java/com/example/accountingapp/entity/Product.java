package com.example.accountingapp.entity;

import com.example.accountingapp.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    private String name;
    private String description;

    //TODO  @Abbos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
   private Category category;


    private BigInteger qty;
    private String unit;
    private BigInteger lowLimitAlert;
    private BigInteger tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private BigInteger newColumn;

}