package com.example.accountingapp.enums;

public enum SalePurchaseStatus {

    PURCHASE("PURCHASE"),
    SALE("SALE");

    private final String value;

    SalePurchaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


