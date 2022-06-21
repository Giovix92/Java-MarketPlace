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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class InformationController {

    @FXML
    private TextField AddressBar, NameBar, SurnameBar;

    @FXML
    private Label completeLabel;

    @FXML
    private Button CompleteButton;

    @FXML
    private ImageView HomePageButton;

    @FXML
    void ChargeAction() throws Exception {
        SceneHandler.getInstance().setRechargeBalanceScene();
    }

    @FXML
    void CompleteAction() throws Exception {
        if (Utente.getInstance().getName() == null && Utente.getInstance().getSurname() == null && Utente.getInstance().getAddress() == null) {
            if (NameBar.getText().equals("") || SurnameBar.getText().equals("") || AddressBar.getText().equals("")) {
                SceneHandler.getInstance().createError(AlertMessages.MISSING_INFOS_MSG, AlertMessages.MISSING_INFOS_TITLE);
                return;
            }
            User user = new User(Client.getInstance().getEmail(), NameBar.getText(), SurnameBar.getText(), AddressBar.getText(), "0");
            JSONObject obj = JSONUtil.toJSON(user);
            Client.getInstance().insert("clienti", obj,
                    reference -> Platform.runLater(() -> {
                        SceneHandler.getInstance().createAlert(AlertMessages.ACCOUNT_COMPLETED_MSG, AlertMessages.ACCOUNT_COMPLETED_TITLE);
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
        } else {
            if (NameBar.getText().equals("") || SurnameBar.getText().equals("") || AddressBar.getText().equals("")) {
                SceneHandler.getInstance().createError(AlertMessages.MISSING_INFOS_MSG, AlertMessages.MISSING_INFOS_TITLE);
                return;
            }
            User user = new User(Client.getInstance().getEmail(), NameBar.getText(), SurnameBar.getText(), AddressBar.getText(), Utente.getInstance().getSaldo());
            JSONObject newUser = JSONUtil.toJSON(user);
            Client.getInstance().get("clienti", ref -> {
                        JSONObject result = ref.result();
                        JSONArray jsonArray = result.getJSONArray("clienti");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            if (obj.getString("email").equals(Client.getInstance().getEmail())) {
                                String elementID = obj.getString("element_id");
                                Client.getInstance().update("clienti", elementID, newUser,
                                        ref2 -> Platform.runLater(() -> {
                                            SceneHandler.getInstance().createAlert(AlertMessages.ACCOUNT_UPDATED_MSG, AlertMessages.ACCOUNT_UPDATED_TITLE);
                                            try {
                                                SceneHandler.getInstance().setHomePageScene();
                                            } catch (Exception e) {
                                                throw new RuntimeException(e);
                                            }
                                        }),
                                        exc -> Platform.runLater(() -> {
                                            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                SceneHandler.getInstance().showHelpAlert();
                                        })
                                );
                                return;
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

    @FXML
    void ConditionAction() {
        SceneHandler.getInstance().showTOSAlert();
    }

    @FXML
    void HomeAction() throws Exception {
        SceneHandler.getInstance().setHomePageScene();
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

    void setInformations() {
        if (Utente.getInstance().getName() != null && Utente.getInstance().getSurname() != null && Utente.getInstance().getAddress() != null) {
            completeLabel.setText("Aggiorna il tuo account");
            NameBar.setText(Utente.getInstance().getName());
            SurnameBar.setText(Utente.getInstance().getSurname());
            AddressBar.setText(Utente.getInstance().getAddress());
            CompleteButton.setText("Aggiorna");
        } else {
            completeLabel.setText("Completa il tuo account");
            NameBar.setText("");
            SurnameBar.setText("");
            AddressBar.setText("");
            CompleteButton.setText("Completa");
        }
    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);

        setInformations();
    }

}
