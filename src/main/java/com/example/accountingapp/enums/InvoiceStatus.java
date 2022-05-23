package com.example.accountingapp.enums;

public enum InvoiceStatus {

    OPEN("OPEN"), PENDING("Pending"), PAID("Paid");

    private final String value;

    InvoiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
