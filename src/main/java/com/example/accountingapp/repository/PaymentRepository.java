package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {


    List<Payment> findAllBy();
    List<Payment> findAllByYear(String year);

}
