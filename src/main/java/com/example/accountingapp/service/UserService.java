package com.example.accountingapp.service;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.dto.UserDTO;
import com.example.accountingapp.entity.Company;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    void save(UserDTO dto);

    UserDTO update(UserDTO dto);

    void delete(String username);

    UserDTO findById(Long id);

    UserDTO findByEmail(String email);

    Company findCompanyByLoggedInUser();
}
