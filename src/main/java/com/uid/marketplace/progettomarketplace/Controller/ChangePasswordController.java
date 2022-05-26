package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class ChangePasswordController {

    @FXML
    private ImageView HomePageButton;

    @FXML
    private PasswordField PasswordBar1;

    @FXML
    private PasswordField PasswordBar2;

    @FXML
    void ChangePasswordAction(ActionEvent event) throws Exception {
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
            if(Client.getInstance().changePassword(PasswordBar1.getText()) == null) {
                SceneHandler.getInstance().createError("Impossibile cambiare la password! Riprova più tardi.", "Cambio Password");
            }
            else{
                SceneHandler.getInstance().createAlert("Hai appena cambiato la password del tuo account! Sarai reinderizzato all'homepage.", "Cambio password");
                SceneHandler.getInstance().setHomePageScene();
            }

        } catch (IOException | ConnectionException e) {
            if(!SceneHandler.getInstance().createErrorWithContacts(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE)) {
                SceneHandler.getInstance().createAlert("Contatti", "Contatti");
            }
        }
    }

    @FXML
    void ConditionAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "La nostra società");
    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void PrivacyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "La nostra società");
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
