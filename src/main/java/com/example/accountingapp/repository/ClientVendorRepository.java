package com.example.accountingapp.repository;

import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {
    List<ClientVendor> findAllBy();
  
    List<ClientVendor> findAllByType(CompanyType companyType);

    @Query(value = "SELECT company_name FROM Client_Vendor WHERE client_vendor.company_id = ?1",nativeQuery = true)
    String findClientNameById(@Param("id") Long id);
}