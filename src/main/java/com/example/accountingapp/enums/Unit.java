package com.example.accountingapp.enums;

public enum Unit {
    PIECES("Pieces");

    private final String value;
    Unit(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
