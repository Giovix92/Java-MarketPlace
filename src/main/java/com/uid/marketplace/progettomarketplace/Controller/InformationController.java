package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.User;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.util.Objects;

public class InformationController {

    @FXML
    private TextField AddressBar;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField NameBar;

    @FXML
    private TextField SurnameBar;

    @FXML
    void ChargeAction(ActionEvent event) {
        //hyperlink per la ricarica del saldo istantaneo

    }

    @FXML
    void CompleteAction(ActionEvent event) throws Exception {
        //tasto di completamento del profilo
        User user = new User(Client.getInstance().getEmail(), NameBar.getText(), SurnameBar.getText(), AddressBar.getText(), "0");
        JSONObject obj = JSONUtil.toJSON(user);
        Client.getInstance().insert("clienti", obj,
            reference -> {},
            exc -> {});
        SceneHandler.getInstance().createAlert(AlertMessages.ACCOUNT_COMPLETED_MSG, AlertMessages.ACCOUNT_COMPLETED_TITLE);
        SceneHandler.getInstance().setHomePageScene();
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
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
