package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Objects;

public class HomepageController {

    @FXML
    private TextField SearchBar;

    @FXML
    private MenuButton AccountMenuButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button CartButton;

    @FXML
    private MenuItem LoginButton;

    @FXML
    private MenuItem ControlPanelButton;

    @FXML
    private MenuItem CompleteButton;

    @FXML
    private MenuItem RegisterButton;

    @FXML
    private MenuItem BalanceButton;

    @FXML
    private MenuItem ChangeMailButton;

    @FXML
    private MenuItem RefactorPasswordButton;

    @FXML
    private MenuItem ExitButton;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private MenuButton CategoriesButton;

    @FXML
    void AccessAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
    }

    @FXML
    void ControlPanelAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setControlPanelScene();
    }

    @FXML
    void CompleteAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setCompleteAccountScene();
    }

    @FXML
    void RegisterAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setRegistrationScene();
    }

    @FXML
    void BalanceAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setRechargeBalanceScene();
    }

    @FXML
    void ChangeMailAction(ActionEvent event) {
        SceneHandler.getInstance().showHelpAlert();
    }

    @FXML
    void ChangePasswordAction(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setChangePasswordScene();
    }

    @FXML
    void ExitAction(ActionEvent event) throws Exception {
        try {
            if(Client.getInstance().logout()) {
                Utente.getInstance().resetData();
                SceneHandler.getInstance().setHomePageScene();
            } else SceneHandler.getInstance().createError(
                    AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE);
        } catch (Exception e) {
            if(!SceneHandler.getInstance().createErrorWithContacts(
                    AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE))
                        SceneHandler.getInstance().showHelpAlert();
        }
    }

    @FXML
    void CartAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        //else aprire pagina carrello
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
    void OrderAction(ActionEvent event) throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        //else aprire pagina ordini
    }

    @FXML
    void PrivacyAction(ActionEvent event) throws IOException {
        SceneHandler.getInstance().showPrivacyPolicyAlert();
    }

    @FXML
    void SearchAction(ActionEvent event) {
        //LENTE DI INGRANDIMENTO PER LA BARRA DI RICERCA
    }

    @FXML
    void SocietyAction(ActionEvent event) {
        SceneHandler.getInstance().showSocietyAlert();
    }

    @FXML
    void BooksAction(ActionEvent event) {
        CategoriesButton.setText("Libri");
    }

    @FXML
    void ClothesAction(ActionEvent event) {
        CategoriesButton.setText("Abbigliamento");
    }

    @FXML
    void ElectronicsAction(ActionEvent event) {
        CategoriesButton.setText("Elettronica e Informatica");
    }

    @FXML
    void HealthAction(ActionEvent event) {
        CategoriesButton.setText("Salute e Benessere");
    }

    @FXML
    void HouseAction(ActionEvent event) {
        CategoriesButton.setText("Casa e Giardino");
    }

    @FXML
    void MusicAction(ActionEvent event) {
        CategoriesButton.setText("Musica, Film e TV");
    }

    @FXML
    void OurAction(ActionEvent event) {
        CategoriesButton.setText("Scelti da noi");
    }

    @FXML
    void ShoesAction(ActionEvent event) {
        CategoriesButton.setText("Scarpe");
    }

    @FXML
    void SportAction(ActionEvent event) {
        CategoriesButton.setText("Sport e Tempo Libero");
    }

    @FXML
    void VideogamesAction(ActionEvent event) {
        CategoriesButton.setText("Videogiochi e Console");
    }

    @FXML
    void kidsAction(ActionEvent event) {
        CategoriesButton.setText("Per bambini");
    }

    void notLoggedIn() {
        LoginButton.setVisible(true);
        RegisterButton.setVisible(true);
        ChangeMailButton.setVisible(false);
        RefactorPasswordButton.setVisible(false);
        CompleteButton.setVisible(false);
        ControlPanelButton.setVisible(false);
        BalanceButton.setVisible(false);
        ExitButton.setVisible(false);
    }

    void loggedIn() {
        LoginButton.setVisible(false);
        RegisterButton.setVisible(false);
        ChangeMailButton.setVisible(true);
        ControlPanelButton.setVisible(Client.getInstance().getAdminRole());
        RefactorPasswordButton.setVisible(true);
        ExitButton.setVisible(true);
    }

    public void showCompleteButton(boolean choice) { CompleteButton.setVisible(choice); }

    public void showBalanceButton(boolean choice) { BalanceButton.setVisible(choice); }

    public void obtainData() {
        showCompleteButton(true);
        showBalanceButton(false);
        Client.getInstance().get("clienti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("clienti");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("email").equals(Client.getInstance().getEmail())) {
                    if(!obj.isNull("nome")) {
                        showCompleteButton(false);
                        showBalanceButton(true);
                        Utente.getInstance().setData(obj.getString("email"), obj.getString("nome"), obj.getString("cognome"),
                                obj.getString("indirizzo"), obj.getString("saldo"));
                    }
                }
            }
        }, exc -> {});
    }

    void setIcons() {
        FontIcon userIcon = new FontIcon("fas-user-circle");
        userIcon.setIconColor(Color.WHITE);
        AccountMenuButton.setGraphic(userIcon);

        FontIcon searchIcon = new FontIcon("fas-search");
        SearchButton.setGraphic(searchIcon);

        FontIcon orderIcon = new FontIcon("fas-clipboard-list");
        orderIcon.setIconColor(Color.WHITE);
        OrderButton.setGraphic(orderIcon);

        FontIcon cartIcon = new FontIcon("fas-cart-arrow-down");
        cartIcon.setIconColor(Color.WHITE);
        CartButton.setGraphic(cartIcon);
    }

    @FXML
    void initialize() throws Exception {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);

        setIcons();

        if(Client.getInstance().getEmail() != null) {
            obtainData();
            loggedIn();
        } else notLoggedIn();
    }
}