package com.uid.marketplace.progettomarketplace.models;


public record Product(String nome, String descrizione, String venditore, String prezzo, String image_id,
                      String category) {
    @Override
    public String toString() {
        return nome + ";" + descrizione + ";" + venditore + ";" + prezzo + ";" + image_id + ";" + category;
    }
}