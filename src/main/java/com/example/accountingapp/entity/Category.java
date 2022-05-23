package com.example.accountingapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Category {

    private String description;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Company company;
    private boolean enabled;
    private BigInteger created_by;
    private BigInteger updated_by;
//    @Column(columnDefinition = "DATE")
//    private LocalDate created_time;
//
//    @Column(columnDefinition = "DATE")
//    private LocalDate updated_time;
}
