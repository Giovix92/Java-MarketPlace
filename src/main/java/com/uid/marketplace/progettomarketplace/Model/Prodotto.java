package com.uid.marketplace.progettomarketplace.Model;

import javafx.scene.image.Image;

public class Prodotto {

    private static Prodotto instance = new Prodotto();

    private Prodotto() {}
    public static Prodotto getInstance() { return instance; }

    String name = null;
    String descrizione = null;
    String venditore = null;
    String prezzo = "0";
    String image_id = null;
    Image image = null;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public String getVenditore() {return venditore;}

    public void setVenditore(String venditore) {this.venditore = venditore;}

    public String getPrezzo() {return prezzo;}

    public void setPrezzo(String prezzo) {this.prezzo = prezzo;}

    public String getImage_id() {return image_id;}

    public void setImage_id(String image_id) {this.image_id = image_id;}

    public void setData(String name, String descrizione, String venditore, String prezzo, String image_id, Image image) {
        this.name = name;
        this.descrizione = descrizione;
        this.venditore = venditore;
        this.prezzo = prezzo;
        this.image_id = image_id;
        this.image = image;
    }

    public void resetData() throws Exception {
        setName(null);
        setDescrizione(null);
        setVenditore(null);
        setPrezzo("0");
        setImage_id(null);
        setImage(null);
    }
}
