package com.example.accountingapp.service.impl;

import com.example.accountingapp.entity.User;
import com.example.accountingapp.entity.common.UserPrincipal;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user==null) {
            throw new UsernameNotFoundException("This user does not exist");
        }

        return new UserPrincipal(user);
    }
}
