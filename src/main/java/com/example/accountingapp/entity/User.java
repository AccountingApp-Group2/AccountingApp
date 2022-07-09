package com.example.accountingapp.entity;
import com.example.accountingapp.enums.UserStatus;
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
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Role role;


}