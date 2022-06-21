package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.utils.handlers.LoadProducts;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class SplashScreenController {

    LoadProducts loadProducts;

    @FXML
    private ImageView splashScreenImg;

    @FXML
    void initialize() throws Exception {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        splashScreenImg.setImage(image);

        loadProducts = new LoadProducts();

        loadProducts.setOnSucceeded(event2 -> {
            PauseTransition pause = new PauseTransition(Duration.millis(5000));
            pause.setOnFinished(event -> {
                try {
                    SceneHandler.getInstance().setHomePageScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            pause.play();
        });

        loadProducts.restart();
    }

}
