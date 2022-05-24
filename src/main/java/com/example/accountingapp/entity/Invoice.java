package com.example.accountingapp.entity;


import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity {

    private String invoiceNumber;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;

    //TODO @Darien
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="sptable_id")
    //private ClientVendor clientVendor;

    //TODO Gulmira
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="company_id")
    //private Company company;

    private boolean enabled;

}
