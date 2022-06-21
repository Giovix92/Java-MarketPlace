package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.utils.Cart;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.utils.Utente;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class HomepageController {

    ArrayList<Label> productLabel;
    ArrayList<ImageView> imageViews;
    @FXML
    private TextField SearchBar;
    @FXML
    private MenuButton AccountMenuButton, CategoriesButton;
    @FXML
    private Button SearchButton, OrderButton, CartButton;
    @FXML
    private MenuItem LoginButton;
    @FXML
    private MenuItem ControlPanelButton;
    @FXML
    private MenuItem CompleteAccountButton;
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
    private MenuItem updateAccountInfosButton;
    @FXML
    private ImageView HomePageButton;
    @FXML
    private AnchorPane subAnchorPane;
    @FXML
    private ImageView imgview1, imgview10, imgview11, imgview12, imgview13, imgview14, imgview15, imgview16, imgview2, imgview3, imgview4, imgview5, imgview6, imgview7, imgview8, imgview9;
    @FXML
    private Label productLabel1, productLabel2, productLabel3, productLabel4, productLabel5, productLabel6, productLabel7, productLabel8, productLabel9, productLabel10, productLabel11, productLabel12, productLabel13, productLabel14, productLabel15, productLabel16;

    @FXML
    void AccessAction() throws Exception {
        if (Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
    }

    @FXML
    void ControlPanelAction() throws Exception {
        SceneHandler.getInstance().setControlPanelScene();
    }

    @FXML
    void CompleteAction() throws Exception {
        SceneHandler.getInstance().setCompleteAccountScene();
    }

    @FXML
    void RegisterAction() throws Exception {
        SceneHandler.getInstance().setRegistrationScene();
    }

    @FXML
    void BalanceAction() throws Exception {
        SceneHandler.getInstance().setRechargeBalanceScene();
    }

    @FXML
    void ChangeMailAction() {
        SceneHandler.getInstance().showHelpAlert();
    }

    @FXML
    void ChangePasswordAction() throws Exception {
        SceneHandler.getInstance().setChangePasswordScene();
    }

    @FXML
    void ExitAction() {
        try {
            if (Client.getInstance().logout()) {
                Utente.getInstance().resetData();
                SceneHandler.getInstance().setHomePageScene();
            } else SceneHandler.getInstance().createError(
                    AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE);
        } catch (Exception e) {
            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                SceneHandler.getInstance().showHelpAlert();
        }
    }

    @FXML
    void CartAction() throws Exception {
        if(Client.getInstance().getEmail() != null && (Utente.getInstance().getName() == null || Utente.getInstance().getSurname() == null || Utente.getInstance().getAddress() == null)) {
            SceneHandler.getInstance().createAlert(AlertMessages.COMPLETE_ACCOUNT_FIRST_MSG, AlertMessages.NO_ORDERS_TITLE);
            return;
        }
        subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/cart-view.fxml"))));
    }

    @FXML
    void ConditionAction() {
        SceneHandler.getInstance().showTOSAlert();
    }

    @FXML
    void HomeAction() throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void OrderAction() throws Exception {
        if(Client.getInstance().getEmail() == null) SceneHandler.getInstance().setAccessScene();
        else if(Client.getInstance().getEmail() != null && (Utente.getInstance().getName() == null || Utente.getInstance().getSurname() == null || Utente.getInstance().getAddress() == null)) {
            SceneHandler.getInstance().createAlert(AlertMessages.COMPLETE_ACCOUNT_FIRST_MSG, AlertMessages.NO_ORDERS_TITLE);
        } else {
            if (Utente.getInstance().getOrders() == null || Utente.getInstance().getOrders().length() == 0) {
                SceneHandler.getInstance().createAlert(AlertMessages.NO_ORDERS_MSG, AlertMessages.NO_ORDERS_TITLE);
                return;
            }
            subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/order-view.fxml"))));
        }
    }

    @FXML
    void PrivacyAction() {
        SceneHandler.getInstance().showPrivacyPolicyAlert();
    }

    @FXML
    void SearchAction() {
        SearchController.setSearchWord(SearchBar.getText());
        SearchController.setCategory(CategoriesButton.getText());
        try {
            subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/search-view.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ThemeChange() {
        SceneHandler.getInstance().changeTheme();
    }

    @FXML
    void SocietyAction() {
        SceneHandler.getInstance().showSocietyAlert();
    }

    @FXML
    void BooksAction() {
        CategoriesButton.setText("Libri");
    }

    @FXML
    void ClothesAction() {
        CategoriesButton.setText("Abbigliamento");
    }

    @FXML
    void ElectronicsAction() {
        CategoriesButton.setText("Elettronica e Informatica");
    }

    @FXML
    void HealthAction() {
        CategoriesButton.setText("Salute e Benessere");
    }

    @FXML
    void HouseAction() {
        CategoriesButton.setText("Casa e Giardino");
    }

    @FXML
    void MusicAction() {
        CategoriesButton.setText("Musica, Film e TV");
    }

    @FXML
    void ShoesAction() {
        CategoriesButton.setText("Scarpe");
    }

    @FXML
    void SportAction() {
        CategoriesButton.setText("Sport e Tempo Libero");
    }

    @FXML
    void VideogamesAction() {
        CategoriesButton.setText("Videogiochi e Console");
    }

    @FXML
    void kidsAction() {
        CategoriesButton.setText("Per bambini");
    }

    void notLoggedIn() {
        LoginButton.setVisible(true);
        RegisterButton.setVisible(true);
        ChangeMailButton.setVisible(false);
        RefactorPasswordButton.setVisible(false);
        CompleteAccountButton.setVisible(false);
        updateAccountInfosButton.setVisible(false);
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

    void showCompleteAccountButton(boolean choice) {
        CompleteAccountButton.setVisible(choice);
    }

    void showBalanceButton(boolean choice) {
        BalanceButton.setVisible(choice);
    }

    void showUpdateAccountButton(boolean choice) {
        updateAccountInfosButton.setVisible(choice);
    }

    public void obtainData() {
        showCompleteAccountButton(true);
        showBalanceButton(false);
        showUpdateAccountButton(false);

        Client.getInstance().get("clienti", ref -> {
                    JSONObject result = ref.result();
                    JSONArray jsonArray = result.getJSONArray("clienti");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        if (obj.getString("email").equals(Client.getInstance().getEmail())) {
                            if (!obj.isNull("nome")) {
                                showCompleteAccountButton(false);
                                showBalanceButton(true);
                                showUpdateAccountButton(true);
                                Utente.getInstance().setData(obj.getString("email"), obj.getString("nome"), obj.getString("cognome"),
                                        obj.getString("indirizzo"), obj.getString("saldo"));
                                Client.getInstance().get("orders",
                                        ref2 -> {
                                            JSONObject result2 = ref2.result();
                                            JSONArray jsonArray2 = result2.getJSONArray("orders");
                                            if (Utente.getInstance().getOrders() != null) {
                                                Utente.getInstance().setOrders(null);
                                                Utente.getInstance().setOrders(new JSONArray());
                                            }
                                            for (int j = 0; j < jsonArray2.length(); j++) {
                                                JSONObject obj2 = jsonArray2.getJSONObject(j);
                                                if (obj2.getString("email").equals(Client.getInstance().getEmail())) {
                                                    if (Utente.getInstance().getOrders() == null)
                                                        Utente.getInstance().setOrders(new JSONArray());
                                                    if (obj2.has("element_id")) obj2.remove("element_id");
                                                    if (obj2.has("email")) obj2.remove("email");
                                                    if (obj2.has("id")) obj2.remove("id");
                                                    Utente.getInstance().getOrders().put(obj2);
                                                }
                                            }
                                        }, exc -> Platform.runLater(() -> {
                                            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                SceneHandler.getInstance().showHelpAlert();
                                        })
                                );
                            }
                        }
                    }
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );
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

    void initializeArrayLists() {
        productLabel = new ArrayList<>();
        imageViews = new ArrayList<>();
        Collections.addAll(productLabel, productLabel1, productLabel2, productLabel3, productLabel4,
                productLabel5, productLabel6, productLabel7, productLabel8, productLabel9, productLabel10,
                productLabel11, productLabel12, productLabel13, productLabel14, productLabel15, productLabel16);

        Collections.addAll(imageViews, imgview1, imgview2, imgview3, imgview4, imgview5, imgview6, imgview7,
                imgview8, imgview9, imgview10, imgview11, imgview12, imgview13, imgview14, imgview15, imgview16);
    }

    void setOnMouseClickedMethod(JSONObject obj, Image img) {
        Prodotto.getInstance().setData(obj.getString("nome"), obj.getString("descrizione"), obj.getString("venditore"), obj.getString("prezzo"), obj.getString("image_id"), img);
        try {
            subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/product-view.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadProductsLoop() {
        int i = 0;
        for (String label : Prodotto.getInstance().getLabel2ImagesIDs().keySet()) {
            productLabel.get(i).setText(label);

            String image_id = Prodotto.getInstance().getLabel2ImagesIDs().get(label);
            Image img = Prodotto.getInstance().getIds2Images().get(image_id);
            imageViews.get(i).setImage(img);

            JSONObject obj = Prodotto.getInstance().getIds2Objs().get(image_id);
            productLabel.get(i).setOnMouseClicked(event -> setOnMouseClickedMethod(obj, img));
            imageViews.get(i).setOnMouseClicked(event -> setOnMouseClickedMethod(obj, img));
            i++;

            if (i > 15) break;
        }
    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);

        setIcons();
        initializeArrayLists();

        if (Client.getInstance().getEmail() != null) {
            obtainData();
            loggedIn();
            Cart.getInstance().getCartData();
        } else notLoggedIn();

        loadProductsLoop();
    }
}

