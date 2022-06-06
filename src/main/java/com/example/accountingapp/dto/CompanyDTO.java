package com.example.accountingapp.dto;

import com.example.accountingapp.enums.CompanyStatus;
import com.example.accountingapp.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDTO {
    private Long id;
    private String title;
    private String address1;
    private String address2;
    private String zip;
    private String representative;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishmentDate;
    private Boolean enabled;
    private String phone;
    private State state;
    private CompanyStatus companyStatus;
}
