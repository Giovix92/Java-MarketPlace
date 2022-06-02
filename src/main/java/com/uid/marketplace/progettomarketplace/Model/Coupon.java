package com.uid.marketplace.progettomarketplace.Model;

public record Coupon(String key) {
    @Override
    public String toString() {
        return key;
    }
}
