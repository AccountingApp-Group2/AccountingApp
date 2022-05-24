package com.example.accountingapp.entity;

import com.example.accountingapp.enums.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Category extends BaseEntity{

    private String description;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    private boolean enabled;


}
