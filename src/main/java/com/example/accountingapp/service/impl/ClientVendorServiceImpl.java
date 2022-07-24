package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.CompanyType;
import com.example.accountingapp.entity.ClientVendor;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.ClientVendorRepository;
import com.example.accountingapp.service.ClientVendorService;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, UserService userService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public List<ClientVendorDTO> listAllClients() {

        return clientVendorRepository.findAllByCompany(userService.findCompanyByLoggedInUser()).stream().map(p -> mapperUtil.convert(p, new ClientVendorDTO())).collect(Collectors.toList());
    }

    @Override
    public ClientVendorDTO findById(Long id) {
        return mapperUtil.convert(clientVendorRepository.findById(id), new ClientVendorDTO());
    }

    @Override
    public void delete(Long id) {
        ClientVendor clientVendor = clientVendorRepository.findById(id).get();
        clientVendor.setIsDeleted(true);
        clientVendorRepository.save(clientVendor);
    }

    @Override
    public void save(ClientVendorDTO dto) {
        dto.setEnabled(true);
        dto.setCompany(userService.findCompanyByLoggedInUser());
        clientVendorRepository.save(mapperUtil.convert(dto, new ClientVendor()));
    }

    @Override
    public ClientVendorDTO update(ClientVendorDTO dto) {


        ClientVendor client = clientVendorRepository.findByEmail(dto.getEmail());
        ClientVendor convertedClient = mapperUtil.convert(dto,new ClientVendor());
        convertedClient.setId(client.getId());
        convertedClient.setEnabled(client.getEnabled());
        convertedClient.setCompany(userService.findCompanyByLoggedInUser());
        clientVendorRepository.save(convertedClient);
        return findByEmail(dto.getEmail());
    }

    @Override
    public ClientVendorDTO findByEmail(String email) {
        ClientVendor clientVendor = clientVendorRepository.findByEmail(email);
        return mapperUtil.convert(clientVendor,new ClientVendorDTO());
    }

    @Override
    public List<ClientVendorDTO> findAllByCompanyType(CompanyType companyType) {
        List<ClientVendorDTO> clientVendorList = clientVendorRepository.findAllByTypeAndCompany (companyType,userService.findCompanyByLoggedInUser())
                .stream()
                .map(p -> mapperUtil.convert(p, new ClientVendorDTO()))
                .collect(Collectors.toList());

        return clientVendorList;

    }

    @Override
    public String findClientNameById(Long id) {
        return clientVendorRepository.findClientNameById(id);
    }


}