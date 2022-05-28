package com.example.accountingapp.entity;

import com.example.accountingapp.enums.CompanyType;
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
public class ClientVendor extends BaseEntity {

    private String companyName;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    private String zipCode;
    private String address;

//    TODO Rumiya
//    @Enumerated(EnumType.STRING)
//    private State stateId;

    private boolean enabled;

}
