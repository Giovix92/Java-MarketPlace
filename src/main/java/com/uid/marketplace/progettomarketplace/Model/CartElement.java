package com.uid.marketplace.progettomarketplace.Model;


public record CartElement(String id, String nome, String prezzo, Integer qty) {
    @Override
    public String toString() {
        return id + ";" + nome + ";" + prezzo + ";" + qty;
    }
}