package com.example.accountingapp.service;

import com.example.accountingapp.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> listAllCompanies();

    CompanyDTO findById(Long Id);

    void save(CompanyDTO company);
   CompanyDTO findByEmail(String email);

    CompanyDTO update(CompanyDTO company);

}
