package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import com.uid.marketplace.progettomarketplace.utils.handlers.RegistrationManager;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField MailBar;

    @FXML
    private PasswordField PasswordBar1;

    @FXML
    private PasswordField PasswordBar2;

    @FXML
    void AccessAction() throws Exception {
        SceneHandler.getInstance().setAccessScene();
    }

    @FXML
    void RegisterAction() throws Exception {
        if (!MailBar.getText().contains("@")) {
            SceneHandler.getInstance().createError(AlertMessages.INVALID_EMAIL_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        if (PasswordBar1.getText().length() < 6) {
            SceneHandler.getInstance().createError(AlertMessages.PASSWORD_TOO_SHORT_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        if (!PasswordBar1.getText().equals(PasswordBar2.getText())) {
            SceneHandler.getInstance().createError(AlertMessages.PASSWORD_DOESNT_MATCH_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        try {
            if (Client.getInstance().register(MailBar.getText(), PasswordBar1.getText()) == null) {
                SceneHandler.getInstance().createError(AlertMessages.EMAIL_ALREADY_USED_MSG,
                        AlertMessages.REGISTRATION_ERROR_TITLE);
            } else {
                if (Client.getInstance().sendEmailVerification()) {
                    RegistrationManager.getInstance().registrationHandler();
                    SceneHandler.getInstance().createAlert(AlertMessages.REGISTRATION_COMPLETED_MSG,
                            AlertMessages.REGISTRATION_COMPLETED_TITLE);
                    SceneHandler.getInstance().setAccessScene();
                }
            }
        } catch (IOException | ConnectionException e) {
            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR)) {
                SceneHandler.getInstance().showHelpAlert();
            }
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

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
