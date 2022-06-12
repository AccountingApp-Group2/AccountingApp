package com.example.accountingapp.service;

import com.example.accountingapp.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listAllCategories();
    CategoryDTO findById(Long id);
    void delete(Long id);
    void save(CategoryDTO dto);
    CategoryDTO update(CategoryDTO dto);
}
