package com.example.accountingapp.mapper;

import com.example.accountingapp.dto.CategoryDTO;
import com.example.accountingapp.entity.Category;
import org.modelmapper.ModelMapper;

public class CategoryMapper extends ModelMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO convertToDTO(Category entity) {
        return modelMapper.map(entity, CategoryDTO.class);
    }
}
