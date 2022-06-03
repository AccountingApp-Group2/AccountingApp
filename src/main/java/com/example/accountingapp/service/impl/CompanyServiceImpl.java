package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.CompanyDTO;
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


}
