package com.example.accountingapp.service;

import com.example.accountingapp.dto.ClientVendorDTO;
import java.util.List;

public interface ClientVendorService {
    List<ClientVendorDTO> listAllClients();
    ClientVendorDTO findById(Long id);
    void delete(Long id);
}