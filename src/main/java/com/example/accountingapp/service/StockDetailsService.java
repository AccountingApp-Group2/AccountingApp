package com.example.accountingapp.service;

import com.example.accountingapp.dto.StockDetailsDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StockDetailsService {
    StockDetailsDTO findById(Long id);


    List<StockDetailsDTO> getByProductId(Long productId);

    void updateStockDetail(StockDetailsDTO stockDetailsDTO);
}
