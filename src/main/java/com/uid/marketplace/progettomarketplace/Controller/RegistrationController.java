package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.RegistrationManager;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    void AccessAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setAccessScene();
    }

    @FXML
    void RegisterAction(ActionEvent event) throws Exception {
        if(!MailBar.getText().contains("@")) {
            SceneHandler.getInstance().createError(AlertMessages.INVALID_EMAIL_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        if(PasswordBar1.getText().length() < 6) {
            SceneHandler.getInstance().createError(AlertMessages.PASSWORD_TOO_SHORT_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        if(!PasswordBar1.getText().equals(PasswordBar2.getText())) {
            SceneHandler.getInstance().createError(AlertMessages.PASSWORD_DOESNT_MATCH_MSG,
                    AlertMessages.REGISTRATION_ERROR_TITLE);
            return;
        }

        try {
            if(Client.getInstance().register(MailBar.getText(), PasswordBar1.getText()) == null) {
                SceneHandler.getInstance().createError(AlertMessages.EMAIL_ALREADY_USED_MSG,
                        AlertMessages.REGISTRATION_ERROR_TITLE);
            } else {
                if(Client.getInstance().sendEmailVerification()) {
                    RegistrationManager.getInstance().registrationHandler();
                    SceneHandler.getInstance().createAlert(AlertMessages.REGISTRATION_COMPLETED_MSG,
                            AlertMessages.REGISTRATION_COMPLETED_TITLE);
                    SceneHandler.getInstance().setAccessScene();
                }
            }
        } catch (IOException | ConnectionException e) {
            if(!SceneHandler.getInstance().createErrorWithContacts(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE)) {
                SceneHandler.getInstance().showHelpAlert();
            }
        }
    }

    @FXML
    void ConditionAction(ActionEvent event) {
        SceneHandler.getInstance().showTOSAlert();

    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void PrivacyAction(ActionEvent event) {
        SceneHandler.getInstance().showPrivacyPolicyAlert();
    }

    @FXML
    void SocietyAction(ActionEvent event) {
        SceneHandler.getInstance().showSocietyAlert();
    }

    @FXML
    void ThemeChange(ActionEvent event) { SceneHandler.getInstance().changeTheme(); }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
