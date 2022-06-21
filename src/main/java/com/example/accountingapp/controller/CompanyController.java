package com.example.accountingapp.controller;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.enums.CompanyStatus;
import com.example.accountingapp.enums.State;
import com.example.accountingapp.enums.UserStatus;
import com.example.accountingapp.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String listCompany(Model model) {
        model.addAttribute("companies",companyService.listAllCompanies());
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());

        return "/company/company-list";
    }


    @GetMapping("/add")
    public String createCompany(Model model) {

        model.addAttribute("company", new CompanyDTO());
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());
        return "/company/company-add";
    }


    @PostMapping("/add")
    public String insertCompany(@ModelAttribute("company") CompanyDTO company, Model model) {

        companyService.save(company);
        model.addAttribute("company", new CompanyDTO());
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());

        return "/company/company-list";

    }
    @GetMapping("/edit/{id}") //
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());
        model.addAttribute("company", companyService.findById(id));
        return "/company/company-edit";
    }

    @PostMapping("/edit")
    public String editCompany(@ModelAttribute("company") CompanyDTO company, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/company/company-edit";
        }
        System.out.println("what about here");
        companyService.update(company);
        return "redirect:/company/list";
    }

    @GetMapping("/close/{id}")
    public String closeCompany(@PathVariable("id") Long id) {
        companyService.close(id);
        return "redirect:/company/list";
    }

    @GetMapping("/reopen/{id}")
    public String reopenCompany(@PathVariable("id") Long id) {
        companyService.reopen(id);
        return "redirect:/company/list";
    }


}
