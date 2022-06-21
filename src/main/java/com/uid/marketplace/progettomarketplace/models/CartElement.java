package com.uid.marketplace.progettomarketplace.models;


public record CartElement(String id, String nome, String prezzo, Integer qty) {
    @Override
    public String toString() {
        return id + ";" + nome + ";" + prezzo + ";" + qty;
    }
}