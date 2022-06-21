package com.uid.marketplace.progettomarketplace.utils;

import javafx.scene.image.Image;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Prodotto {

    private static final Prodotto instance = new Prodotto();
    Map<String, String> label2ImagesIDs = new HashMap<>();
    Map<String, Image> ids2Images = new HashMap<>();
    Map<String, JSONObject> ids2Objs = new HashMap<>();
    String name = null;
    String descrizione = null;
    String venditore = null;
    String prezzo = "0";
    String image_id = null;
    Image image = null;

    private Prodotto() {
    }

    public static Prodotto getInstance() {
        return instance;
    }

    public Map<String, JSONObject> getIds2Objs() {
        return ids2Objs;
    }

    public Map<String, String> getLabel2ImagesIDs() {
        return label2ImagesIDs;
    }

    public Map<String, Image> getIds2Images() {
        return ids2Images;
    }

    public void addProduct(String imageId, String label) {
        label2ImagesIDs.put(label, imageId);
    }

    public void addImage(String imageId, Image img) {
        ids2Images.put(imageId, img);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getVenditore() {
        return venditore;
    }

    public void setVenditore(String venditore) {
        this.venditore = venditore;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public void setData(String name, String descrizione, String venditore, String prezzo, String image_id, Image image) {
        this.name = name;
        this.descrizione = descrizione;
        this.venditore = venditore;
        this.prezzo = prezzo;
        this.image_id = image_id;
        this.image = image;
    }

    public void resetData() {
        setName(null);
        setDescrizione(null);
        setVenditore(null);
        setPrezzo("0");
        setImage_id(null);
        setImage(null);
    }
}
