package com.example.accountingapp.converter;

import com.example.accountingapp.dto.CategoryDTO;
import com.example.accountingapp.service.CategoryService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CategoryDTOConverter implements Converter<String, CategoryDTO> {

    private final CategoryService categoryService;

    public CategoryDTOConverter(@Lazy CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDTO convert(String source) {

        if (source == null || source.equals("")) {
            return null;
        }

        return categoryService.findById(Long.parseLong(source));

    }

}

