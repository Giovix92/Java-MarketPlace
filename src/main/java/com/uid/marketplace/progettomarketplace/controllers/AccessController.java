package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class AccessController {

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField MailBar;

    @FXML
    private PasswordField PasswordBar;

    @FXML
    void AccessAction() throws Exception {
        if (!MailBar.getText().contains("@")) {
            SceneHandler.getInstance().createError(AlertMessages.INVALID_EMAIL_MSG, AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }
        try {
            if (Client.getInstance().login(MailBar.getText(), PasswordBar.getText()) == null)
                SceneHandler.getInstance().createError(AlertMessages.INVALID_LOGIN_MSG, AlertMessages.INVALID_LOGIN_TITLE);
            else
                SceneHandler.getInstance().setHomePageScene();
        } catch (IOException | ConnectionException e) {
            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                SceneHandler.getInstance().showHelpAlert();
        }
    }

    @FXML
    void ConditionAction() { SceneHandler.getInstance().showTOSAlert(); }

    @FXML
    void HomeAction() throws Exception { SceneHandler.getInstance().setHomePageScene(); }

    @FXML
    void LostPasswordAction() throws Exception { SceneHandler.getInstance().setRecoveryPasswordScene(); }

    @FXML
    void PrivacyAction() { SceneHandler.getInstance().showPrivacyPolicyAlert(); }

    @FXML
    void RegistrationAction() throws Exception { SceneHandler.getInstance().setRegistrationScene(); }

    @FXML
    void SocietyAction() { SceneHandler.getInstance().showSocietyAlert(); }

    @FXML
    void ThemeChange() { SceneHandler.getInstance().changeTheme(); }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
