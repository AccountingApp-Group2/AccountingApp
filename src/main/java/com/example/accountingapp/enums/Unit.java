package com.example.accountingapp.enums;




public enum Unit {
    LBS("Pounds"),
    GL("Gallon"),
    PCS("Pieces"),
    KG("Kilogram"),
    M("Meter"),
    IN("Inch"),
    FT("Feet");

    private final String value;

    Unit(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
