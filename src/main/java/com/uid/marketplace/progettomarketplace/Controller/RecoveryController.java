package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void ConditionAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Condizioni generali di vendita");
    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void PrivacyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Informativa sulla privacy");

    }

    @FXML
    void RecoveryPasswordAction(ActionEvent event) throws Exception {
        if(!MailBar.getText().contains("@")) {
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
            if (!SceneHandler.getInstance().createErrorWithContacts(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE)) {
                SceneHandler.getInstance().createAlert("Contatti", "Contatti");
            }
        }
    }

    @FXML
    void ServiceAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Contatti");

    }

    @FXML
    void SocietyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "La nostra società");

    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }
}
