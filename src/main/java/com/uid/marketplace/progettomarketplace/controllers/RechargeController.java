package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import com.uid.marketplace.progettomarketplace.models.User;
import com.uid.marketplace.progettomarketplace.utils.Utente;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class RechargeController {

    @FXML
    private Label BalanceField;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField CouponBar;

    @FXML
    void RechargeAction() {
        AtomicBoolean foundCoupon = new AtomicBoolean(false);
        Client.getInstance().get("coupon", ref -> {
                    JSONObject result = ref.result();
                    JSONArray jsonArray = result.getJSONArray("coupon");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        if (obj.getString("key").equals(CouponBar.getText()) && Double.parseDouble(obj.getString("value")) > 0.00) {
                            foundCoupon.set(true);
                            String couponID = obj.getString("element_id");
                            double toBeAdded = Double.parseDouble(obj.getString("value"));
                            Client.getInstance().get("clienti", ref2 -> {
                                        JSONObject result2 = ref2.result();
                                        JSONArray jsonArray2 = result2.getJSONArray("clienti");
                                        for (int j = 0; j < jsonArray2.length(); j++) {
                                            JSONObject obj2 = jsonArray2.getJSONObject(j);
                                            if (obj2.getString("email").equals(Client.getInstance().getEmail())) {
                                                String idToBeUpdated = obj2.getString("element_id");
                                                double userBalance = Double.parseDouble(obj2.getString("saldo"));
                                                userBalance += toBeAdded;
                                                User user = new User(Client.getInstance().getEmail(), Utente.getInstance().getName(),
                                                        Utente.getInstance().getSurname(), Utente.getInstance().getAddress(), String.valueOf(userBalance));
                                                JSONObject newJson = JSONUtil.toJSON(user);
                                                Utente.getInstance().setSaldo(String.valueOf(userBalance));
                                                Client.getInstance().update("clienti", idToBeUpdated, newJson,
                                                        ref3 -> Client.getInstance().remove("coupon", couponID,
                                                                ref4 -> Platform.runLater(() -> {
                                                                    BalanceField.setText("Saldo attuale: " + new DecimalFormat("0.00").format(Double.parseDouble(Utente.getInstance().getSaldo())) + "€");
                                                                    CouponBar.setText("");
                                                                    SceneHandler.getInstance().createAlert(AlertMessages.BALANCE_UPDATED_MSG, AlertMessages.BALANCE_UPDATED_TITLE);
                                                                }),
                                                                exc -> Platform.runLater(() -> {
                                                                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                                        SceneHandler.getInstance().showHelpAlert();
                                                                })
                                                        ),
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
                            break;
                        }
                    }
                    if (!foundCoupon.get()) Platform.runLater(() ->
                            SceneHandler.getInstance().createError(AlertMessages.INVALID_COUPON_MSG, AlertMessages.COUPON_EMPTY_ERROR_TITLE));
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );

    }


    @FXML
    void ConditionAction() {
        SceneHandler.getInstance().showTOSAlert();
    }

    @FXML
    void HomeAction() throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }


    @FXML
    void ServiceAction() {
        SceneHandler.getInstance().showHelpAlert();
    }

    @FXML
    void PrivacyAction() {
        SceneHandler.getInstance().showPrivacyPolicyAlert();
    }

    @FXML
    void SocietyAction() {
        SceneHandler.getInstance().showSocietyAlert();
    }

    @FXML
    void ThemeChange() {
        SceneHandler.getInstance().changeTheme();
    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);

        BalanceField.setText("Saldo attuale: " + new DecimalFormat("0.00").format(Double.parseDouble(Utente.getInstance().getSaldo())) + "€");
    }
}
