package com.uid.marketplace.progettomarketplace.View;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SceneHandler {

    private static SceneHandler instance = new SceneHandler();
    private Stage stage;
    private Scene scene;

    private SceneHandler() {}
    public static SceneHandler getInstance() { return instance; }

    public void loadResources(Scene scene){
        for (String font : Settings.fonts)
            Font.loadFont(Objects.requireNonNull(MarketPlaceApplication.class.getResource(font)).toExternalForm(), 10);
        for (String style : Settings.styles)
            scene.getStylesheets().add(Objects.requireNonNull(MarketPlaceApplication.class.getResource(style)).toExternalForm());
    }

    public void init(Stage stage){
        if(this.stage == null) {
            this.stage = stage;
            this.stage.setMinWidth(1080);
            this.stage.setMinHeight(500);
            this.stage.setTitle("UID - MarketPlace");
        }
    }

    public void setHomePageScene() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("homepage-view.fxml"));
        if (scene == null) scene = new Scene(fxmlLoader.load());
        else scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void createAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void createError(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public boolean createErrorWithContacts(String message, String title) {
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType report = new ButtonType("Segnala l'errore", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ok, report);
        alert.setTitle(title);
        alert.setHeaderText(message);
        Optional<ButtonType> result_tmp = alert.showAndWait();
        return result_tmp.orElse(report) == ok;
    }

    public boolean createRegistrationVerificationDialog(String message, String title) {
        ButtonType confirm = new ButtonType("Conferma", ButtonBar.ButtonData.OK_DONE);
        ButtonType resend = new ButtonType("Invia di nuovo", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, confirm, resend);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText("");
        Optional<ButtonType> result_tmp = alert.showAndWait();
        return result_tmp.orElse(resend) == confirm;
    }

    public void setAccessScene() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("access-view.fxml"));
        scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }


    public void setRegistrationScene() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("registration-view.fxml"));
        scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void setRecoveryPasswordScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("recovery-view.fxml"));
        scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void setChangePasswordScene() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("changePassword-view.fxml"));
        scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }

}
