package com.uid.marketplace.progettomarketplace.models;

public record User(String email, String nome, String cognome, String indirizzo, String saldo) {

    @Override
    public String toString() {
        return email + ";" + nome + ";" + cognome + ";" + indirizzo + ";" + saldo;
    }
}

