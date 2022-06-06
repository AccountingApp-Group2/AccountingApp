package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.enums.CompanyType;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.ClientVendorRepository;
import com.example.accountingapp.service.ClientVendorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ClientVendorDTO> listAllClients() {
        return clientVendorRepository.findAllBy().stream().map(p -> mapperUtil.convert(p, new ClientVendorDTO())).collect(Collectors.toList());
    }

    @Override
    public ClientVendorDTO findById(Long id) {
        return mapperUtil.convert(clientVendorRepository.findById(id), new ClientVendorDTO());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ClientVendorDTO> findAllByCompanyType(CompanyType companyType) {
        return clientVendorRepository.findAllByType (companyType)
                .stream()
                .map(p -> mapperUtil.convert(p, new ClientVendorDTO()))
                .collect(Collectors.toList());

    }
}