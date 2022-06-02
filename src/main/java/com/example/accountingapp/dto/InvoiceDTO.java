package com.example.accountingapp.dto;

import com.example.accountingapp.enums.InvoiceStatus;
import com.example.accountingapp.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {

    private Long id;

    @NotBlank
    private String invoiceNumber;

    @NotNull
    private InvoiceStatus invoiceStatus;

    @NotNull
    private InvoiceType invoiceType;

    private LocalDate invoiceDate;

    private boolean enabled;

}