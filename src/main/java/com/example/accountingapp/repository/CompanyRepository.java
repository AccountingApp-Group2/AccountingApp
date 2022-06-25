package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByTitle(String companyName);

}
