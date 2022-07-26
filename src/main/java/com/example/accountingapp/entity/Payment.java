package com.example.accountingapp.entity;

import com.example.accountingapp.enums.PaymentMonth;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
  private Company company;
}
