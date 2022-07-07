package com.example.accountingapp;

import com.stripe.Stripe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AccountingAppApplication {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public static void main(String[] args) {
        SpringApplication.run(AccountingAppApplication.class, args);
    }
    
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
    @PostConstruct
    public void setup() {
        Stripe.apiKey = stripeApiKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
