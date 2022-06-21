package com.uid.marketplace.progettomarketplace.utils;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import com.uid.marketplace.progettomarketplace.models.Order;
import com.uid.marketplace.progettomarketplace.models.User;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utente {

    private static final Utente instance = new Utente();
    String id = null;
    String name = null;
    String surname = null;
    String address = null;
    String saldo = "0";
    JSONArray orders = null;
    private Utente() {
    }

    public static Utente getInstance() {
        return instance;
    }

    public JSONArray getOrders() {
        return orders;
    }

    public void setOrders(JSONArray orders) {
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void setData(String id, String name, String surname, String address, String saldo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.saldo = saldo;
    }

    public void resetData() {
        setId(null);
        setName(null);
        setSurname(null);
        setAddress(null);
        setSaldo("0");
        Cart.getInstance().setCart(null);
    }

    public void updateBalance() {
        Client.getInstance().get("clienti", ref2 -> {
                    JSONObject result2 = ref2.result();
                    JSONArray jsonArray2 = result2.getJSONArray("clienti");
                    for (int j = 0; j < jsonArray2.length(); j++) {
                        JSONObject obj2 = jsonArray2.getJSONObject(j);
                        if (obj2.getString("email").equals(Client.getInstance().getEmail())) {
                            String idToBeUpdated = obj2.getString("element_id");
                            User user = new User(Client.getInstance().getEmail(), Utente.getInstance().getName(),
                                    Utente.getInstance().getSurname(), Utente.getInstance().getAddress(), Utente.getInstance().getSaldo());
                            JSONObject newJson = JSONUtil.toJSON(user);
                            Client.getInstance().update("clienti", idToBeUpdated, newJson,
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
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );
    }

    public void addPurchaseToDB(String totalAmount) throws Exception {
        java.util.Date date = new java.util.Date();
        String data = date.toString();
        Order order = new Order(Client.getInstance().getEmail(), Utente.getInstance().getName(), Utente.getInstance().getSurname(), Utente.getInstance().getAddress(), data, totalAmount);
        JSONObject newJson = JSONUtil.toJSON(order);
        newJson.put("cartArray", Cart.getInstance().getCartArray());
        Client.getInstance().insert("orders", newJson,
                ref -> Platform.runLater(() -> {
                    Cart.getInstance().emptyCart();
                    SceneHandler.getInstance().createAlert(AlertMessages.ORDER_DONE_MSG, AlertMessages.ORDER_DONE_TITLE);
                    try {
                        SceneHandler.getInstance().setHomePageScene();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }), exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );
    }
}
