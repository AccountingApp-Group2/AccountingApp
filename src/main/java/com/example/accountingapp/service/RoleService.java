package com.example.accountingapp.service;

import com.example.accountingapp.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listAllRoles();
    RoleDTO findById(Long Id);
}