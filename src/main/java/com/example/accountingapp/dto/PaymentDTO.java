package com.example.accountingapp.dto;

import com.example.accountingapp.enums.PaymentMonth;
import com.example.accountingapp.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private String year;
    private PaymentMonth month;
    private PaymentStatus status;
    private Integer amount;
    private Boolean isPaid;
    private CompanyDTO companyDTO;
    private String preparedBy;
//    private UserDTO user;
}
