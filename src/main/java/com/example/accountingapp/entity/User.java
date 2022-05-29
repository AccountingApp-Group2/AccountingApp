package com.example.accountingapp.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Where(clause= "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;
    private boolean enabled;

    // ToDo Gulmira
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    // ToDo Muhabbat
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;


}


