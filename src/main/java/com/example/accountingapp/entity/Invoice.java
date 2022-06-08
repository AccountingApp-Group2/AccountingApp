package com.example.accountingapp.entity;


import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    //todo Vitaly

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sptable_id")
    private ClientVendor clientVendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private boolean enabled;

    @OneToMany
    List<InvoiceProduct> invoiceProductList;

}
