package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.User;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class InformationController {


    Boolean valid = false;

    public Boolean getValid() {return valid;}
    public void setValid(Boolean valid) {this.valid = valid;}


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
        User user = new User(Client.getInstance().getEmail(), NameBar.getText(), SurnameBar.getText(), AddressBar.getText(), 0);
        System.out.println(user.toString());
        JSONObject obj = JSONUtil.toJSON(user);
        System.out.println(obj);
        Client.getInstance().insert("clienti", obj,
                reference -> {
                    System.out.println("sono nel reference");
                    setValid(true);
                },
                exc -> {
                    System.out.println("sono nel exc");
                    setValid(false);
                });
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
