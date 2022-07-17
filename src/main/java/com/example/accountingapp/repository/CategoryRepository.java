package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Category;
import com.example.accountingapp.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCompany(Company companyByLoggedInUser);

//    List<Category> findAllBy();

}
