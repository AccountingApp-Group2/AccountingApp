package com.example.accountingapp.enums;

import java.math.BigDecimal;

public enum State {
    ALABAMA("Alabama", "AL", BigDecimal.valueOf(7)),
    ALASKA("Alaska", "AK", BigDecimal.valueOf(6.5)),
    AMERICAN_SAMOA("American Samoa", "AS", BigDecimal.valueOf(6.5)),
    ARIZONA("Arizona", "AZ", BigDecimal.valueOf(3)),
    ARKANSAS("Arkansas", "AR", BigDecimal.valueOf(8)),
    CALIFORNIA("California", "CA", BigDecimal.valueOf(12)),
    COLORADO("Colorado", "CO", BigDecimal.valueOf(8.5)),
    CONNECTICUT("Connecticut", "CT", BigDecimal.valueOf(9)),
    DELAWARE("Delaware", "DE", BigDecimal.valueOf(6.5)),
    DISTRICT_OF_COLUMBIA("District of Columbia", "DC", BigDecimal.valueOf(6.5)),
    FEDERATED_STATES_OF_MICRONESIA("Federated States of Micronesia", "FM", BigDecimal.valueOf(7)),
    FLORIDA("Florida", "FL", BigDecimal.valueOf(6.5)),
    GEORGIA("Georgia", "GA", BigDecimal.valueOf(9)),
    GUAM("Guam", "GU", BigDecimal.valueOf(6.5)),
    HAWAII("Hawaii", "HI", BigDecimal.valueOf(5)),
    IDAHO("Idaho", "ID", BigDecimal.valueOf(8)),
    ILLINOIS("Illinois", "IL", BigDecimal.valueOf(7)),
    INDIANA("Indiana", "IN", BigDecimal.valueOf(6.5)),
    IOWA("Iowa", "IA", BigDecimal.valueOf(7)),
    KANSAS("Kansas", "KS", BigDecimal.valueOf(6.5)),
    KENTUCKY("Kentucky", "KY", BigDecimal.valueOf(8)),
    LOUISIANA("Louisiana", "LA", BigDecimal.valueOf(6.5)),
    MAINE("Maine", "ME", BigDecimal.valueOf(6)),
    MARYLAND("Maryland", "MD", BigDecimal.valueOf(4)),
    MARSHALL_ISLANDS("Marshall Islands", "MH", BigDecimal.valueOf(6.5)),
    MASSACHUSETTS("Massachusetts", "MA", BigDecimal.valueOf(6.5)),
    MICHIGAN("Michigan", "MI", BigDecimal.valueOf(8)),
    MINNESOTA("Minnesota", "MN", BigDecimal.valueOf(8.5)),
    MISSISSIPPI("Mississippi", "MS", BigDecimal.valueOf(6.5)),
    MISSOURI("Missouri", "MO", BigDecimal.valueOf(8)),
    MONTANA("Montana", "MT", BigDecimal.valueOf(6.5)),
    NEBRASKA("Nebraska", "NE", BigDecimal.valueOf(6)),
    NEVADA("Nevada","NV", BigDecimal.valueOf(7)),
    NEW_HAMPSHIRE("New Hampshire", "NH", BigDecimal.valueOf(6.5)),
    NEW_JERSEY("New Jersey", "NJ", BigDecimal.valueOf(6.5)),
    NEW_MEXICO("New Mexico", "NM", BigDecimal.valueOf(8)),
    NEW_YORK("New York", "NY", BigDecimal.valueOf(4.5)),
    NORTH_CAROLINA("North Carolina", "NC", BigDecimal.valueOf(6)),
    NORTH_DAKOTA("North Dakota", "ND", BigDecimal.valueOf(6.5)),
    NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands", "MP", BigDecimal.valueOf(6.5)),
    OHIO("Ohio", "OH", BigDecimal.valueOf(6.5)),
    OKLAHOMA("Oklahoma", "OK", BigDecimal.valueOf(6.5)),
    OREGON("Oregon", "OR", BigDecimal.valueOf(6.5)),
    PALAU("Palau","PW", BigDecimal.valueOf(8.5)),
    PENNSYLVANIA("Pennsylvania", "PA", BigDecimal.valueOf(5)),
    PUERTO_RICO("Puerto Rico", "PR", BigDecimal.valueOf(5)),
    RHODE_ISLAND("Rhode Island", "RI", BigDecimal.valueOf(6.5)),
    SOUTH_CAROLINA("South Carolina", "SC", BigDecimal.valueOf(6.5)),
    SOUTH_DAKOTA("South Dakota", "SD", BigDecimal.valueOf(6.5)),
    TENNESSEE("Tennessee", "TN", BigDecimal.valueOf(6.5)),
    TEXAS("Texas", "TX", BigDecimal.valueOf(7)),
    UTAH("Utah", "UT", BigDecimal.valueOf(6.5)),
    VERMONT("Vermont", "VT", BigDecimal.valueOf(7)),
    VIRGIN_ISLANDS("Virgin Islands", "VI", BigDecimal.valueOf(6.5)),
    VIRGINIA("Virginia", "VA", BigDecimal.valueOf(8)),
    WASHINGTON("Washington", "WA", BigDecimal.valueOf(6.5)),
    WEST_VIRGINIA("West Virginia", "WV", BigDecimal.valueOf(9.5)),
    WISCONSIN("Wisconsin", "WI", BigDecimal.valueOf(8)),
    WYOMING("Wyoming", "WY", BigDecimal.valueOf(7));


    private String state_name;
    private String state_code;
    private BigDecimal state_tax;

    State(String state_name, String state_code, BigDecimal state_tax) {
        this.state_name = state_name;
        this.state_code = state_code;
        this.state_tax = state_tax;
    }

    public String getStateName() {
        return state_name;
    }

    public String getStateCode() {
        return state_code;
    }

    public BigDecimal getState_tax() {
        return state_tax;
    }
}