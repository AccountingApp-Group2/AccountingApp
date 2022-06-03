package com.example.accountingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {
    private Long id;
    private String name;
    private Boolean enabled;
}
