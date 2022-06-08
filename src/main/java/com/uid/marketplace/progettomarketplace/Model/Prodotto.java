package com.uid.marketplace.progettomarketplace.Model;

public class Prodotto {

    private static Prodotto instance = new Prodotto();

    private Prodotto() {}
    public static Prodotto getInstance() { return instance; }

    String id = null;
    String name = null;
    String descrizione = null;
    String venditore = null;
    String prezzo = "0";
    String image_id = null;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

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


    public void resetData() throws Exception {

        setId(null);
        setName(null);
        setDescrizione(null);
        setVenditore(null);
        setPrezzo("0");
        setImage_id(null);
    }
}
