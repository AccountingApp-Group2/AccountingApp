package com.example.accountingapp.dto;

import com.example.accountingapp.entity.Category;
import com.example.accountingapp.entity.Company;
import com.example.accountingapp.enums.ProductStatus;
import com.example.accountingapp.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private CategoryDTO category;

    @NotBlank
    private BigInteger qty;

    @NotNull
    private Unit unit;

    @NotBlank
    private BigInteger lowLimitAlert;

    @NotBlank
    private BigInteger tax;

    @NotNull
    private CompanyDTO company;

    @NotBlank
    private Boolean enabled;

    @NotNull
    private ProductStatus productStatus;

    @NotBlank
    private BigInteger newColumn;
}
