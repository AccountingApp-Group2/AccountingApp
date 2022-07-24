package com.example.accountingapp.entity;

import com.example.accountingapp.enums.CompanyType;
import com.example.accountingapp.enums.State;
import lombok.*;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause= "is_deleted=false")
public class ClientVendor extends BaseEntity {

    private String companyName;
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    private String zipCode;
    private String address;

    @Enumerated(EnumType.STRING)
    private State stateId;

    private Boolean enabled;

}
