package com.infoera.sportshub_inventory.enums;

public enum Country {

    INDIA("IN", "India"),
    UNITED_STATES("US", "United States"),
    UNITED_KINGDOM("GB", "United Kingdom"),
    CANADA("CA", "Canada"),
    AUSTRALIA("AU", "Australia"),
    GERMANY("DE", "Germany"),
    FRANCE("FR", "France"),
    UNITED_ARAB_EMIRATES("AE", "United Arab Emirates"),
    SAUDI_ARABIA("SA", "Saudi Arabia"),
    SINGAPORE("SG", "Singapore"),
    JAPAN("JP", "Japan");

    private final String code;   // ISO-2 country code
    private final String name;   // UI-friendly name

    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
