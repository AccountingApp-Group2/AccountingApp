package com.example.accountingapp.service;

import com.example.accountingapp.dto.ProfitLossDTO;
import com.example.accountingapp.dto.StockDTO;

import java.util.List;

public interface StockService {

    List<StockDTO> listAllStocks();

}
