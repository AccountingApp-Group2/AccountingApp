package com.example.accountingapp.entity;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted=false")
public class Payment extends BaseEntity {

    @Column(columnDefinition = "DATE")
    private LocalDate year;
    private BigInteger amount;
    private Boolean isPaid;
    private String institutionId;
    @Enumerated(EnumType.STRING)
    private Month month;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;
}
