package com.example.accountingapp.enums;

public enum ClientStatus {

    ACTIVE("Active", true),
    PASSIVE("Passive", false);

    private String value;
    private Boolean status;

    ClientStatus(String value, Boolean status) {
        this.value = value;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public Boolean getStatus() {
        return status;
    }
}