package com.uid.marketplace.progettomarketplace.Model;

import com.uid.marketplace.progettomarketplace.client.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

import static java.lang.Thread.sleep;

public class Utente {

    private static Utente instance = new Utente();

    private Utente() {}
    public static Utente getInstance() { return instance; }

    String id = null;
    String name = null;
    String surname = null;
    String address = null;
    String saldo = "0";
    JSONObject cart = null;

    public JSONObject getCart() {return cart;}

    public void setCart(JSONObject cart) {this.cart = cart;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getSaldo() {return saldo;}

    public void setSaldo(String saldo) {this.saldo = saldo;}

    public void setData(String id, String name, String surname, String address, String saldo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.saldo = saldo;
    }

    public void resetData() throws Exception {
        setId(null);
        setName(null);
        setSurname(null);
        setAddress(null);
        setSaldo("0");
        setCart(null);
    }

    public void addToCart(JSONObject element) throws InterruptedException {
        if(this.cart == null) cart = new JSONObject();
        if(!cart.has("email")) cart.put("email", Client.getInstance().getEmail());
        if(!cart.has("cartArray")) {
            JSONArray cartArray = new JSONArray();
            cart.put("cartArray", cartArray);
        }

        JSONArray cartArray = cart.getJSONArray("cartArray");
        for(int i=0; i<cartArray.length(); i++) {
            JSONObject obj = cartArray.getJSONObject(i);
            if(element.has("id")) {
                if(element.getString("id").equals(obj.getString("id"))) {
                    int newQty = obj.getInt("qty") + element.getInt("qty");
                    obj.remove("qty");
                    obj.put("qty", newQty);
                    insertIntoDatabase();
                    return;
                }
            }
        }
        cartArray.put(element);
        insertIntoDatabase();
        System.out.println(cart);
    }

    public void removeFromCart(JSONObject element) {
        JSONArray cartArray = cart.getJSONArray("cartArray");
        for(int i=0; i<cartArray.length(); i++) {
            JSONObject obj = cartArray.getJSONObject(i);
            if(element.getString("id").equals(obj.getString("id"))) {
                cartArray.remove(i);
                return;
            }
        }
    }

    public void insertIntoDatabase() throws InterruptedException {
        if(Client.getInstance().getEmail() != null) {
            Client.getInstance().get("cart",
                    ref -> {
                        JSONObject result = ref.result();
                        JSONArray jsonArray = result.getJSONArray("cart");
                        if (jsonArray.length() == 0) {
                            Client.getInstance().insert("cart", cart,
                                    ref2 -> {},
                                    Throwable::printStackTrace);
                        } else {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                if (obj.getString("email").equals(Client.getInstance().getEmail())) {
                                    Client.getInstance().update("cart", obj.getString("element_id"), cart,
                                            ref3 -> {}, Throwable::printStackTrace);
                                }
                            }
                        }
                    }, Throwable::printStackTrace);
        }
    }

    public void getCartData() {
        if(cart == null) cart = new JSONObject();
        if(!cart.has("email")) cart.put("email", Client.getInstance().getEmail());
        Client.getInstance().get("cart",
            ref -> {
                JSONObject result = ref.result();
                JSONArray jsonArray = result.getJSONArray("cart");
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String elementID = obj.getString("element_id");
                    if(obj.getString("email").equals(Client.getInstance().getEmail())) {
                        JSONArray databaseArray = obj.getJSONArray("cartArray");
                        if(!cart.has("cartArray")) {
                            cart.put("cartArray", databaseArray);
                        } else {
                            JSONArray cartArray = cart.getJSONArray("cartArray");
                            for(int j=0; j<databaseArray.length(); j++) {
                                JSONObject objDBArray = databaseArray.getJSONObject(j);
                                boolean found = false;
                                for(int k=0; k<cartArray.length(); k++) {
                                    JSONObject objCartArray = cartArray.getJSONObject(k);
                                    if(objDBArray.getString("id").equals(objCartArray.getString("id"))) {
                                        if(objCartArray.getInt("qty") != objDBArray.getInt("qty")) {
                                            int newQty = objCartArray.getInt("qty") + objDBArray.getInt("qty");
                                            objCartArray.put("qty", newQty);
                                            if(obj.has("element_id")) obj.remove("element_id");
                                            if(obj.has("id")) obj.remove("id");
                                            obj.getJSONArray("cartArray").getJSONObject(j).remove("qty");
                                            obj.getJSONArray("cartArray").getJSONObject(j).put("qty", newQty);
                                        }
                                        found = true;
                                        break;
                                    }
                                }
                                if(found) break;
                                cartArray.put(databaseArray.getJSONObject(j));
                            }
                            Client.getInstance().update("cart", elementID, obj,
                                    ref4 -> {},
                                    Throwable::printStackTrace);
                        }
                    }
                }
            }, Throwable::printStackTrace);
    }
}
