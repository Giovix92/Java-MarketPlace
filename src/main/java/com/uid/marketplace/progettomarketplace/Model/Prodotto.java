package com.uid.marketplace.progettomarketplace.Model;

import java.util.Arrays;

public record Prodotto(Integer id, String nome, String descrizione, String venditore, Integer prezzo, String[] recensioni) {
    @Override
    public String toString() {
        return id + ";" + nome + ";" + descrizione + ";" + venditore + ";" + prezzo + ";" + Arrays.toString(recensioni);
    }
}