package com.example.accountingapp.repository;

import com.example.accountingapp.entity.ClientVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor,Long> {
    List<ClientVendor> findAllBy();
}