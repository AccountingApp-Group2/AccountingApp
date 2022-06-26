package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  List<Payment> findAllBy();

  Payment findPaymentById(Long id);

  @Query(value = "SELECT * FROM payment ORDER BY to_date(month,'Month')",nativeQuery = true)
  List<Payment> findPaymentByYearOrderByMonth(String year);



}
