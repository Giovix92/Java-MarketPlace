package com.uid.marketplace.progettomarketplace.Model;


public record Product(String nome, String descrizione, String venditore, String prezzo, String image_id) {
    @Override
    public String toString() {
        return nome + ";" + descrizione + ";" + venditore + ";" + prezzo + ";" + image_id;
    }
}