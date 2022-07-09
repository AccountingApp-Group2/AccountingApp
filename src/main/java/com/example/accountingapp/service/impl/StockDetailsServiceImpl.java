package com.example.accountingapp.service.impl;

import com.example.accountingapp.dto.InvoiceProductDTO;
import com.example.accountingapp.dto.ProductDTO;
import com.example.accountingapp.dto.StockDetailsDTO;
import com.example.accountingapp.entity.Product;
import com.example.accountingapp.entity.StockDetails;
import com.example.accountingapp.mapper.MapperUtil;
import com.example.accountingapp.repository.StockDetailsRepository;
import com.example.accountingapp.service.StockDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StockDetailsServiceImpl implements StockDetailsService {

    private final MapperUtil mapperUtil;
    private final StockDetailsRepository stockDetailsRepository;

    public StockDetailsServiceImpl(MapperUtil mapperUtil, StockDetailsRepository stockDetailsRepository) {
        this.mapperUtil = mapperUtil;
        this.stockDetailsRepository = stockDetailsRepository;
    }

    @Override
    public StockDetailsDTO findById(Long id) {
        return mapperUtil.convert(stockDetailsRepository.findAllByProductId(id), new StockDetailsDTO());
    }

    @Override
    public List<StockDetailsDTO> getByProductId(Long productId) {
        List<StockDetailsDTO> stockDetailsDTOS = stockDetailsRepository.findAllByInvoiceId(productId)
                .stream()
                .filter(st -> st.getRemainingQuantity().intValue()!=0)
                .map(p -> mapperUtil.convert(p, new StockDetailsDTO())).collect(Collectors.toList());
        return stockDetailsDTOS;
    }

    @Override
    public void updateStockDetail(StockDetailsDTO stockDetailsDTO) {
        StockDetails s = mapperUtil.convert(stockDetailsDTO,new StockDetails());
        stockDetailsRepository.save(s);
    }


}
