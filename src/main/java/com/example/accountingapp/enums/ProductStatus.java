package com.example.accountingapp.enums;

public enum ProductStatus {
    ACTIVE("Active"), PENDING("Pending");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
