package com.example.accountingapp.service;

import com.example.accountingapp.dto.CompanyDTO;

import java.util.Collection;
import java.util.List;

public interface CompanyService {
    List<CompanyDTO> listAllCompanies();

    CompanyDTO findById(Long Id);

}
