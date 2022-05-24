package com.example.accountingapp.entity;

import com.example.accountingapp.enums.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@Data
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    private String name;
    private String description;

    //TODO  @Abbos
    /*
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Category category;
    */

    private BigInteger qty;
    private String unit;
    private BigInteger lowLimitAlert;
    private BigInteger tax;

    //TODO @Gulmira
    /*
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Company company;
    */

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private BigInteger newColumn;

}