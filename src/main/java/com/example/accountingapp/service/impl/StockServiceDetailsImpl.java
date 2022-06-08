package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.StockDTO;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.StockDetailsRepository;
import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceDetailsImpl implements StockDetailsService {

    private final StockDetailsRepository stockDetailsRepository;
    private final MapperUtil mapperUtil;

    public StockServiceDetailsImpl(StockDetailsRepository stockRepository, MapperUtil mapperUtil) {
        this.stockDetailsRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<StockDTO> listAllStocks() {
        return stockDetailsRepository.findAll().stream().map(stock -> mapperUtil.convert(stock, new StockDTO())).collect(Collectors.toList());
    }
}

