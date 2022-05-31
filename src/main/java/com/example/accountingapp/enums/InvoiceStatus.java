package com.example.accountingapp.enums;

public enum InvoiceStatus {

     PENDING("Pending"), APPROVED("Approved");

    private final String value;

    InvoiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
