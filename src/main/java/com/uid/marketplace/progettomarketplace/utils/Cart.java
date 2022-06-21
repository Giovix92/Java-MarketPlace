package com.uid.marketplace.progettomarketplace.utils;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONObject;

public class Cart {

    private static final Cart instance = new Cart();
    JSONObject cart = null;

    private Cart() {
    }

    public static Cart getInstance() {
        return instance;
    }

    public JSONObject getCart() {
        return cart;
    }

    public void setCart(JSONObject cart) {
        this.cart = cart;
    }

    public JSONArray getCartArray() {
        if (cart == null) prepCart();
        return getCart().getJSONArray("cartArray");
    }

    public void prepCart() {
        if (this.cart == null) cart = new JSONObject();
        if (!cart.has("email")) cart.put("email", Client.getInstance().getEmail());
        if (!cart.has("cartArray")) {
            JSONArray cartArray = new JSONArray();
            cart.put("cartArray", cartArray);
        }
    }

    public void addToCart(JSONObject element) {
        prepCart();

        for (int i = 0; i < getCartArray().length(); i++) {
            if (element.has("id")) {
                if (element.getString("id").equals(getCartArray().getJSONObject(i).getString("id"))) {
                    int newQty = getCartArray().getJSONObject(i).getInt("qty") + element.getInt("qty");
                    getCartArray().getJSONObject(i).remove("qty");
                    getCartArray().getJSONObject(i).put("qty", newQty);
                    if(Client.getInstance().getEmail() != null) insertIntoDatabase();
                    return;
                }
            }
        }
        getCartArray().put(element);
        if(Client.getInstance().getEmail() != null) insertIntoDatabase();
    }

    public void removeFromCart(String id) {
        for (int i = 0; i < getCartArray().length(); i++) {
            if (id.equals(getCartArray().getJSONObject(i).getString("id"))) {
                getCartArray().remove(i);
                if (Client.getInstance().getEmail() != null) insertIntoDatabase();
                return;
            }
        }
    }

    public void emptyCart() {
        cart = null;
        prepCart();
        if (Client.getInstance().getEmail() != null) insertIntoDatabase();
    }

    public void changeObjQty(String id, int newQty) {
        for (int i = 0; i < getCartArray().length(); i++) {
            if (getCartArray().getJSONObject(i).has("id") && getCartArray().getJSONObject(i).getString("id").equals(id)) {
                getCartArray().getJSONObject(i).remove("qty");
                getCartArray().getJSONObject(i).put("qty", newQty);
                if (Client.getInstance().getEmail() != null) insertIntoDatabase();
                return;
            }
        }
    }

    public void insertIntoDatabase() {
        if (Client.getInstance().getEmail() != null) {
            Client.getInstance().get("cart",
                    ref -> {
                        JSONObject result = ref.result();
                        JSONArray jsonArray = result.getJSONArray("cart");
                        if (jsonArray.length() == 0) {
                            Client.getInstance().insert("cart", cart,
                                    ref2 -> {
                                    },
                                    exc -> Platform.runLater(() -> {
                                        if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                            SceneHandler.getInstance().showHelpAlert();
                                    }));
                        } else {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                if (obj.getString("email").equals(Client.getInstance().getEmail())) {
                                    Client.getInstance().update("cart", obj.getString("element_id"), cart,
                                            ref3 -> {
                                            },
                                            exc -> Platform.runLater(() -> {
                                                if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                        AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                    SceneHandler.getInstance().showHelpAlert();
                                            })
                                    );
                                }
                            }
                        }
                    }, exc -> Platform.runLater(() -> {
                        if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                            SceneHandler.getInstance().showHelpAlert();
                    })
            );
        }
    }

    public void getCartData() {
        prepCart();
        Client.getInstance().get("cart",
                ref -> {
                    JSONObject result = ref.result();
                    JSONArray jsonArray = result.getJSONArray("cart");
                    if(jsonArray.length() == 0) {
                        insertIntoDatabase();
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String elementID = obj.getString("element_id");
                        if (obj.getString("email").equals(Client.getInstance().getEmail())) {
                            JSONArray databaseArray = obj.getJSONArray("cartArray");
                            if (!cart.has("cartArray")) {
                                cart.put("cartArray", databaseArray);
                            } else {
                                JSONArray cartArray = cart.getJSONArray("cartArray");
                                for(int j = 0; j < databaseArray.length(); j++) {
                                    JSONObject objDBArray = databaseArray.getJSONObject(j);
                                    boolean foundSameID = false;
                                    for (int k = 0; k < cartArray.length(); k++) {
                                        JSONObject objCartArray = cartArray.getJSONObject(k);
                                        if(objDBArray.getString("id").equals(objCartArray.getString("id"))) {
                                            foundSameID = true;
                                            if (objCartArray.getInt("qty") != objDBArray.getInt("qty")) {
                                                int newQty = objCartArray.getInt("qty") + objDBArray.getInt("qty");
                                                objCartArray.put("qty", newQty);
                                                obj.getJSONArray("cartArray").getJSONObject(j).remove("qty");
                                                obj.getJSONArray("cartArray").getJSONObject(j).put("qty", newQty);
                                            }
                                            break;
                                        }
                                    }
                                    if(foundSameID) break;
                                    cartArray.put(databaseArray.getJSONObject(j));
                                }
                                JSONObject newJson = new JSONObject();
                                newJson.put("email", Client.getInstance().getEmail());
                                newJson.put("cartArray", cartArray);
                                Client.getInstance().update("cart", elementID, newJson,
                                        ref4 -> {},
                                        exc -> Platform.runLater(() -> {
                                            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                SceneHandler.getInstance().showHelpAlert();
                                        })
                                );
                            }
                        }
                    }
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                }));
    }
}
