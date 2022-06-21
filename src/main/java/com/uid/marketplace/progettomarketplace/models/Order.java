package com.uid.marketplace.progettomarketplace.models;

public record Order(String email, String nome, String cognome, String indirizzo, String date, String total) {
    @Override
    public String toString() {
        return email + ";" + nome + ";" + cognome + ";" + indirizzo + ";" + date + ";" + total;
    }
}