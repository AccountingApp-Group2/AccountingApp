package com.example.accountingapp.repository;

import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {
    List<ClientVendor> findAllBy();

    List<ClientVendor> findAllByType(CompanyType companyType);
}