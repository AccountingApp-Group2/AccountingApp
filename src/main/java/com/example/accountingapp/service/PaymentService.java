package com.example.accountingapp.service;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.dto.PaymentDTO;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface PaymentService {
    List<PaymentDTO> listAllPayments();

    List<PaymentDTO> listAllByYear(String year);
    PaymentDTO findById(Long id);
    void delete(Long id);






}
