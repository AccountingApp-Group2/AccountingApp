package com.example.accountingapp.controller;

import com.example.accountingapp.dto.CategoryDTO;
import com.example.accountingapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category-add")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "/category/category-add";
    }

    @PostMapping("/category-add")
    public String insertCategory(@ModelAttribute("category") CategoryDTO category,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", new CategoryDTO());
            return "/category/category-add";
        }
        categoryService.save(category);
        return "redirect:/category/category-list";
    }

    @GetMapping("/category-edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "/category/category-edit";
    }

    @PostMapping("/category-edit/{id}")
    public String updateCategory(@ModelAttribute("category") CategoryDTO category,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/category/category-edit";
        }
        categoryService.update(category);
        return "redirect:/category/category-list";
    }

    @GetMapping("/category-list")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/category/category-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        categoryService.delete(id);

        return "redirect:/category/category-list";
    }
}
