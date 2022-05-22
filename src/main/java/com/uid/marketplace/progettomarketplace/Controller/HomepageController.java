package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class HomepageController {

    @FXML
    private TextField SearchBar;
        //BARRA DI RICERCA DEI PRODOTTI

    @FXML
    void AccessPageAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        else SceneHandler.getInstance().createAlert("Ciao funziona", "Porcodio");
    }

    @FXML
    private ImageView HomePageButton;

    @FXML
    private Button accountButton;

    @FXML
    void CartAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        else SceneHandler.getInstance().createAlert("Ciao funziona", "Porcodio");
    }

    @FXML
    void ConditionAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert("DA COMPLETARE", "Condizioni generali di vendita");
    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void OrderAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        else SceneHandler.getInstance().createAlert("Ciao funziona", "Porcodio");
    }

    @FXML
    void PrivacyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Informativa sulla privacy");

    }

    @FXML
    void SearchAction(ActionEvent event) {
        //LENTE DI INGRANDIMENTO PER LA BARRA DI RICERCA
    }

    @FXML
    void SocietyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "La nostra società");

    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
        if(Client.getInstance().getEmail() != null) {
            accountButton.setText("Account");
        } else {
            accountButton.setText("Accedi");
        }
    }

}
