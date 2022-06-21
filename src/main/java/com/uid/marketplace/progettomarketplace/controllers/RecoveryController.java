package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;


public class RecoveryController {

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField MailBar;


    @FXML
    void ConditionAction() {
        SceneHandler.getInstance().showTOSAlert();
    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void PrivacyAction() {
        SceneHandler.getInstance().showPrivacyPolicyAlert();

    }

    @FXML
    void RecoveryPasswordAction() throws Exception {
        if (!MailBar.getText().contains("@")) {
            SceneHandler.getInstance().createError(AlertMessages.INVALID_EMAIL_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }
        try {
            if (Client.getInstance().resetPassword(MailBar.getText())) {
                SceneHandler.getInstance().createAlert(AlertMessages.CHANGE_RECOVERY_EMAIL_SENT_MSG, AlertMessages.RECOVERY_EMAIL_TITLE);
                SceneHandler.getInstance().setAccessScene();
            } else {
                SceneHandler.getInstance().createError(AlertMessages.CHANGE_RECOVERY_EMAIL_ERROR_MSG, AlertMessages.RECOVERY_EMAIL_TITLE);
            }
        } catch (IOException | ConnectionException e) {
            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                SceneHandler.getInstance().showHelpAlert();
        }
    }

    @FXML
    void ServiceAction() {
        SceneHandler.getInstance().showHelpAlert();
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
    }
}
