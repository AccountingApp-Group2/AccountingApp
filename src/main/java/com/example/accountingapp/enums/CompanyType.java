package com.example.accountingapp.enums;

public enum CompanyType {

    CLIENT("Client"), VENDOR("Vendor");

    private final String value;

    CompanyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
