package com.example.accountingapp.enums;

import lombok.Getter;

@Getter
public enum Unit {
    LBS("Libre"),
    GALLON("Gallon"),
    PCS("Pieces"),
    KG("Kilogram"),
    METER("Meter"),
    INCH("inch"),
    FEET("Feet");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

}
