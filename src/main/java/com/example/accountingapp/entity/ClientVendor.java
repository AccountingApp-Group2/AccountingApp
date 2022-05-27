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
@Table(name = "vendors")
@Where(clause = "is_deleted=false")
public class ClientVendor extends BaseEntity{

    private String name;
    private String phone;
    private String email;
    private String address;
    private String state;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

//    TODO Rumiya
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

}
