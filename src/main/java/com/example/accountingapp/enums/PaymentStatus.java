package com.example.accountingapp.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    INVOICE("Invoice"),PAY("Pay");
    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

}
