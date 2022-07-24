package com.example.accountingapp.dto;

import com.example.accountingapp.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    private boolean enabled;

    private Company company;
}
