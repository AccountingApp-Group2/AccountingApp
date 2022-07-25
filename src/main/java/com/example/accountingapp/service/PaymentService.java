package com.example.accountingapp.service;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.dto.PaymentDTO;
import com.example.accountingapp.entity.Payment;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface PaymentService {
  List<PaymentDTO> listAllPayments();

  List<PaymentDTO> listByYearAndCompany(String year);

  PaymentDTO findById(Long id);

  void delete(Long id);

  PaymentDTO findPaymentById(Long id);

  void chargePaymentById(Long id);

}
