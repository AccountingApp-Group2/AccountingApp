package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.UserDTO;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.enums.UserStatus;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(user->mapperUtil.convert(user,new UserDTO())).collect(Collectors.toList());
    }



    @Override
    public void save(UserDTO dto) {



        userRepository.save(mapperUtil.convert(dto,new User()));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        return null;
    }



    @Override
    public void delete(String username) {

    }

    @Override
    public UserDTO findById(Long id) {
        return mapperUtil.convert(userRepository.findById(id).get(),new UserDTO());
    }


}
