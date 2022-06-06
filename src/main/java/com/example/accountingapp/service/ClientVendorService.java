package com.example.accountingapp.service;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.enums.CompanyType;

import java.util.List;

public interface ClientVendorService {
    List<ClientVendorDTO> listAllClients();
    ClientVendorDTO findById(Long id);
    void delete(Long id);

    List<ClientVendorDTO> findAllByCompanyType(CompanyType vendor);
}