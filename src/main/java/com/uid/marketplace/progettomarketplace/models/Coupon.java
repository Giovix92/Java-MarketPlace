package com.uid.marketplace.progettomarketplace.models;

public record Coupon(String key, String value) {
    @Override
    public String toString() {
        return key + ";" + value;
    }
}
