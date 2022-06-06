package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.CompanyRepository;
import com.example.accountingapp.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CompanyDTO> listAllCompanies() {
        return companyRepository.findAll().stream().map(company -> mapperUtil.convert(company,new CompanyDTO())).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO findById(Long id) {
        return mapperUtil.convert(companyRepository.findById(id).get(),new CompanyDTO());
    }

    @Override
    public void save(CompanyDTO company) {

        company.setEnabled(true);
        companyRepository.save(mapperUtil.convert(company,new Company()));
    }

    @Override
    public CompanyDTO findByEmail(String email) {
        Company company=companyRepository.findByEmail(email);
        return mapperUtil.convert(company,new CompanyDTO());
    }

    @Override
    public CompanyDTO update(CompanyDTO dto) {
        Company company=companyRepository.findByEmail(dto.getEmail());
        Company convertedCompany= mapperUtil.convert(dto,new Company());
        convertedCompany.setId(company.getId());
        convertedCompany.setEnabled(company.getEnabled());
        convertedCompany.setCompanyStatus(company.getCompanyStatus());
        convertedCompany.setState(company.getState());
        convertedCompany.setEstablishmentDate(company.getEstablishmentDate());
        companyRepository.save(convertedCompany);
        return findByEmail(dto.getEmail());
    }


}
