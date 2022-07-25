package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.PaymentDTO;
import com.example.accountingapp.entity.Payment;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.PaymentRepository;
import com.example.accountingapp.service.PaymentService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;
  private final MapperUtil mapperUtil;
  private final UserService userService;

  public PaymentServiceImpl(PaymentRepository paymentRepository, MapperUtil mapperUtil, UserService userService) {
    this.paymentRepository = paymentRepository;
    this.mapperUtil = mapperUtil;
    this.userService = userService;
  }

  @Override
  public List<PaymentDTO> listAllPayments() {
    return paymentRepository.findAllBy().stream()
        .map(p -> mapperUtil.convert(p, new PaymentDTO()))
        .collect(Collectors.toList());
  }

  @Override
  public List<PaymentDTO> listByYearAndCompany(String year) {

      return paymentRepository.findPaymentByYearOrderByMonth(year).stream()
              .filter(payment -> payment.getCompany().equals(userService.findCompanyByLoggedInUser()))
              .map(payment -> mapperUtil.convert(payment, new PaymentDTO()))
              .collect(Collectors.toList());
  }

  @Override
  public PaymentDTO findById(Long id) {
    return mapperUtil.convert(paymentRepository.findById(id), new PaymentDTO());
  }

  @Override
  public void delete(Long id) {}

  @Override
  public PaymentDTO findPaymentById(Long id) {
    return mapperUtil.convert(paymentRepository.findPaymentById(id), new PaymentDTO());
  }

  @Override
  public void chargePaymentById(Long id) {
    Payment payment = paymentRepository.findPaymentById(id);
    payment.setIsPaid(true);
    paymentRepository.save(payment);
  }


}
