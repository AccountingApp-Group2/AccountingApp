package com.example.accountingapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingAppApplication.class, args);
    }
    
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
