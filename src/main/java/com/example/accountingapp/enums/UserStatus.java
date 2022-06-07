package com.example.accountingapp.enums;

public enum UserStatus {

    ACTIVE("Active"), INACTIVE("Inactive");

    private final String value;

    UserStatus(String value) {

        this.value = value;

    }

    public String getValue() {

        return this.value;
    }
}