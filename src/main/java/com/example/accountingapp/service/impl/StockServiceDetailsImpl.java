package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.StockDTO;
import com.example.accountingapp.entity.User;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.StockDetailsRepository;
import com.example.accountingapp.repository.UserRepository;
import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceDetailsImpl implements StockDetailsService {

    private final StockDetailsRepository stockDetailsRepository;
    private final MapperUtil mapperUtil;
    private final UserRepository userRepository;

    public StockServiceDetailsImpl(StockDetailsRepository stockDetailsRepository, MapperUtil mapperUtil, UserRepository userRepository) {
        this.stockDetailsRepository = stockDetailsRepository;
        this.mapperUtil = mapperUtil;
        this.userRepository = userRepository;
    }

    @Override
    public List<StockDTO> listAllStocks() {

        User user = userRepository.findByEmail("admin@company2.com");

        return stockDetailsRepository.findAllByProduct_Company(user.getCompany()).stream().map(stock -> mapperUtil.convert(stock, new StockDTO())).collect(Collectors.toList());
    }
}

