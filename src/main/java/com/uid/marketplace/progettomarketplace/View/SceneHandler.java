package com.uid.marketplace.progettomarketplace.View;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.client.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SceneHandler {

    private static SceneHandler instance = new SceneHandler();
    private Stage stage;
    private Scene scene;

    private String theme = "light";

    Alert alert = new Alert(null);

    private SceneHandler() {}
    public static SceneHandler getInstance() { return instance; }

    public void loadResources(Scene scene){
        for (String font : List.of("fonts/Roboto/Roboto-Regular.ttf", "fonts/Roboto/Roboto-Bold.ttf"))
            Font.loadFont(Objects.requireNonNull(MarketPlaceApplication.class.getResource(font)).toExternalForm(), 10);
        for (String style : List.of("css/"+theme+".css", "css/fonts.css", "css/style.css")) {
            String res = Objects.requireNonNull(MarketPlaceApplication.class.getResource(style)).toExternalForm();
            scene.getStylesheets().add(res);
            alert.getDialogPane().getStylesheets().add(res);

        }
    }

    public void init(Stage stage){
        if(this.stage == null) {
            this.stage = stage;
            this.stage.setMinWidth(1080);
            this.stage.setMinHeight(770);
            this.stage.setTitle("UID - MarketPlace");
            this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    try {
                        if (Client.getInstance() != null) {
                            Client.getInstance().close();
                        }
                        System.exit(0);
                    } catch (Exception ignored) {
                    }
                }
            });
        }
    }

    public void createAlert(String message, String title) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void createError(String message, String title) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public boolean createErrorWithContacts(String message, String title) {
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType report = new ButtonType("Segnala l'errore", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert = new Alert(Alert.AlertType.ERROR, "", ok, report);
        alert.setTitle(title);
        alert.setHeaderText(message);
        Optional<ButtonType> result_tmp = alert.showAndWait();
        return result_tmp.orElse(report) == ok;
    }

    public boolean createRegistrationVerificationDialog(String message, String title) {
        ButtonType confirm = new ButtonType("Conferma", ButtonBar.ButtonData.OK_DONE);
        ButtonType resend = new ButtonType("Invia di nuovo", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert = new Alert(Alert.AlertType.INFORMATION, message, confirm, resend);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText("");
        Optional<ButtonType> result_tmp = alert.showAndWait();
        return result_tmp.orElse(resend) == confirm;
    }

    public void showPrivacyPolicyAlert() { createAlert(AlertMessages.PRIVACY_POLICY_MSG, AlertMessages.PRIVACY_POLICY_TITLE); }
    public void showSocietyAlert() { createAlert(AlertMessages.SOCIETY_MSG, AlertMessages.SOCIETY_TITLE); }
    public void showTOSAlert() { createAlert(AlertMessages.TOS_MSG, AlertMessages.TOS_TITLE); }
    public void showHelpAlert() { createAlert(AlertMessages.HELP_MSG, AlertMessages.HELP_TITLE); }

    public void changeTheme() {
        if("dark".equals(theme))
            theme = "light";
        else
            theme = "dark";
        scene.getStylesheets().clear();
        for (String style : List.of("css/"+theme+".css", "css/fonts.css", "css/style.css"))
            scene.getStylesheets().add(Objects.requireNonNull(MarketPlaceApplication.class.getResource(style)).toExternalForm());

    }

    public void loadFXML(String FXMLPath) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource(FXMLPath));
        if(scene == null) scene = new Scene(fxmlLoader.load());
        else scene = new Scene(fxmlLoader.load(), scene.getWidth(), scene.getHeight());
        this.stage.setMinWidth(1080);
        this.stage.setMinHeight(770);
        loadResources(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void setHomePageScene() throws Exception { loadFXML("fxmls/homepage-view.fxml"); }

    public void setAccessScene() throws Exception { loadFXML("fxmls/access-view.fxml"); }

    public void setRegistrationScene() throws Exception { loadFXML("fxmls/registration-view.fxml"); }

    public void setRecoveryPasswordScene() throws Exception { loadFXML("fxmls/recovery-view.fxml"); }

    public void setChangePasswordScene() throws Exception { loadFXML("fxmls/changePassword-view.fxml"); }

    public void setCompleteAccountScene() throws Exception { loadFXML("fxmls/information-view.fxml"); }

    public void setRechargeBalanceScene() throws Exception { loadFXML("fxmls/recharge-view.fxml"); }

    public void setControlPanelScene() throws Exception { loadFXML("fxmls/control_panel-view.fxml"); }

    public String showFileChooser() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Scegli l'immagine del prodotto");
        return fileChooser.showOpenDialog(stage).getAbsolutePath();
    }

}
