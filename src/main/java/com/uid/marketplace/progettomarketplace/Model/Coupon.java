package com.uid.marketplace.progettomarketplace.Model;

public record Coupon(String key, String value) {
    @Override
    public String toString() {
        return key + ";" + value;
    }
}
