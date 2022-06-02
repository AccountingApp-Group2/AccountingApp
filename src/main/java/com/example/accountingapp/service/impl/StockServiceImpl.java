package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.ProfitLossDTO;
import com.example.accountingapp.dto.StockDTO;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.StockRepository;
import com.example.accountingapp.service.StockService;

import java.util.List;
import java.util.stream.Collectors;

public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final MapperUtil mapperUtil;

    public StockServiceImpl(StockRepository stockRepository, MapperUtil mapperUtil) {
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<StockDTO> listAllStocks() {
        return stockRepository.findAll().stream().map(stock->mapperUtil.convert(stock, new StockDTO())).collect(Collectors.toList());
    }
}
