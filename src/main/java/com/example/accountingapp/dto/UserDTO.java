package com.example.accountingapp.dto;

import com.example.accountingapp.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
    private String phone;
    private Boolean enabled;
    private UserStatus userStatus;


    private CompanyDTO company;
    private RoleDTO role;


}