package com.example.accountingapp.service;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.enums.CompanyType;
import java.util.List;

public interface ClientVendorService {
    List<ClientVendorDTO> listAllClients();
    ClientVendorDTO findById(Long id);
    void delete(Long id);
    void save(ClientVendorDTO dto);
    ClientVendorDTO update(ClientVendorDTO dto);
    ClientVendorDTO findByEmail(String email);

    List<ClientVendorDTO> findAllByCompanyType(CompanyType companyType);
}