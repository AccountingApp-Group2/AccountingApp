package com.example.accountingapp.dto;

import com.example.accountingapp.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    @NotBlank
    @Size(max = 15, min = 2)
    private String firstName;

    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;
    private Boolean enabled;

    @NotNull
    private UserStatus userStatus;

    @NotNull
    private CompanyDTO company;

    @NotNull
    private RoleDTO role;


}