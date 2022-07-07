package com.example.accountingapp.entity;

import com.example.accountingapp.enums.PaymentMonth;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted=false")
public class Payment extends BaseEntity {

  // @Column(columnDefinition = "DATE")
  private String year;
  private Integer amount;
  private Boolean isPaid;
  private String institutionId;

  @Enumerated(EnumType.STRING)
  private PaymentMonth month;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;
}
