package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;


public class RecoveryController {

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField MailBar;
        //BARRA IN CUI INSERIRE LA MAIL DESTINATARIA

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
    void RecoveryPasswordAction(ActionEvent event) {
        //TASTO DI RECUPERO PW FINALE CHE FA INVIARE LA MAIL
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
