package com.uid.marketplace.progettomarketplace;

import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;


public class MarketPlaceApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().setSplashScreenScene();


    }
}