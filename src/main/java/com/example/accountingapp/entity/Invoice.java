package com.example.accountingapp.entity;


import com.example.accountingapp.enums.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity{
    private String invoiceNumber;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    //@Enumerated(EnumType.STRING)
    //private InvoiceType invoiceType;

    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="sptable_id")
    //private ClientVendor clientVendor;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="company_id")
   //private Company company;

    private boolean enabled;




}
