package com.example.accountingapp.enums;

public enum InvoiceType {

    PURCHASE("PURCHASE"), SALE("SALE");

    private final String value;

    InvoiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
