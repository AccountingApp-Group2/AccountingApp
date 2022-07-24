package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.CompanyDTO;
import com.example.accountingapp.dto.UserDTO;
import com.example.accountingapp.entity.Company;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.entity.common.UserPrincipal;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUsers() {
       List<User> list = userRepository.findAllByCompany(findCompanyByLoggedInUser());

        return list.stream().map(user -> mapperUtil.convert(user, new UserDTO())).collect(Collectors.toList());
    }


    @Override
    public void save(UserDTO dto) {
        dto.setPassWord(passwordEncoder.encode(dto.getPassWord()));
        dto.setEnabled(true);
        userRepository.save(mapperUtil.convert(dto, new User()));
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail());
        User convertedUser = mapperUtil.convert(dto, new User());
        //set id to converted object which we found in DB by Email
        convertedUser.setId(user.getId());
        convertedUser.setEnabled(user.getEnabled());
        convertedUser.setPassword(user.getPassword());
        userRepository.save(convertedUser);

        return findByEmail(dto.getEmail());
    }

    @Override
    public void delete(String email) {
        User user = userRepository.findByEmail(email);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserDTO findById(Long id) {
        return mapperUtil.convert(userRepository.findById(id).get(), new UserDTO());
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapperUtil.convert(user, new UserDTO());
    }



    @Override
    public Company findCompanyByLoggedInUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = ((UserPrincipal) principal).getCompany();
        return company;
    }

    @Override
    public CompanyDTO findCompanyDTOByLoggedInUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = ((UserPrincipal) principal).getCompany();
        return mapperUtil.convert(company, new CompanyDTO());
    }

}


