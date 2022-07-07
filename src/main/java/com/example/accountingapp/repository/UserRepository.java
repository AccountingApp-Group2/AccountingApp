package com.example.accountingapp.repository;

import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAllByCompany(Company company);

}
