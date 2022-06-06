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


        return "/company/company-list";
    }


    @GetMapping("/add")
    public String addCompany(Model model) {

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
    @GetMapping("/update/{email}")
    public String editUser(@PathVariable("email")String email,Model model){
        model.addAttribute("company",companyService.findByEmail(email));
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());
        return "/company/company-edit";
    }
    @PostMapping("/update")
    public String updateCompany(@ModelAttribute("company")CompanyDTO company,Model model){
        companyService.update(company);
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("states", State.values());
        model.addAttribute("status", CompanyStatus.values());
        return "/company/company-list";
    }
}
