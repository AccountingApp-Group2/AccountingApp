package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.entity.Company;
import com.example.accountingapp.enums.CompanyStatus;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil, UserService userService) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public List<CompanyDTO> listAllCompanies() {
        return companyRepository.findAll().stream().map(company -> mapperUtil.convert(company, new CompanyDTO())).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO findById(Long id) {
        return mapperUtil.convert(companyRepository.findById(id).get(), new CompanyDTO());
    }


    @Override
    public void save(CompanyDTO company) {

        company.setEnabled(true);
        company.setCompanyStatus(CompanyStatus.ENABLED);
        companyRepository.save(mapperUtil.convert(company, new Company()));


    }

    @Override
    public CompanyDTO update(CompanyDTO dto) {
        Company company = companyRepository.findByEmail(dto.getEmail());
        Company convertedCompany = mapperUtil.convert(dto, new Company());
        convertedCompany.setId(company.getId());
        convertedCompany.setCompanyStatus(company.getCompanyStatus());
        companyRepository.save(convertedCompany);

        return findByEmail(dto.getEmail());
    }

    @Override
    public CompanyDTO findByEmail(String email) {
        Company company = companyRepository.findByEmail(email);
        return mapperUtil.convert(company, new CompanyDTO());
    }

    @Override
    public void reopen(Long id) {
        Company company = companyRepository.findById(id).get();
        company.setCompanyStatus(CompanyStatus.ENABLED);
        companyRepository.save(company);

    }

    @Override
    public void close(Long id) {
        Company company = companyRepository.findById(id).get();
        company.setCompanyStatus(CompanyStatus.DISABLED);
        companyRepository.save(company);

    }

    @Override
    public BigDecimal findTaxByCompany() {

        BigDecimal tax = userService.findCompanyByLoggedInUser().getState().getState_tax();
        return tax;
    }

    @Override
    public List<CompanyDTO> findCompanyByLoggedInUser() {
        List<CompanyDTO> listCompanies = new ArrayList<>();

        if (userService.findLoggedInUser().getRole().equals("Root")){
            return listAllCompanies();
        }
        listAllCompanies().forEach(System.out::println);
        listCompanies.add(mapperUtil.convert(userService.findCompanyByLoggedInUser(),new CompanyDTO()));
        return listCompanies;
    }

}
