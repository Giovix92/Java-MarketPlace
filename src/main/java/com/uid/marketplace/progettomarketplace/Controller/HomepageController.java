package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.Prodotto;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class HomepageController {

    @FXML
    private TextField SearchBar;

    @FXML
    private MenuButton AccountMenuButton, CategoriesButton;

    @FXML
    private Button SearchButton, OrderButton, CartButton;

    @FXML
    private MenuItem LoginButton, ControlPanelButton, CompleteButton, RegisterButton, BalanceButton, ChangeMailButton, RefactorPasswordButton, ExitButton;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private ImageView imgview1, imgview10, imgview11, imgview12, imgview13, imgview14, imgview15, imgview16, imgview2, imgview3, imgview4, imgview5, imgview6, imgview7, imgview8, imgview9;

    @FXML
    private Label productLabel1, productLabel2, productLabel3, productLabel4, productLabel5, productLabel6, productLabel7, productLabel8, productLabel9, productLabel10, productLabel11, productLabel12, productLabel13, productLabel14, productLabel15, productLabel16;

    @FXML
    private AnchorPane subAnchorPane;

    ArrayList<Label> productLabel;
    ArrayList<ImageView> imageViews;

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
        System.out.println(Utente.getInstance().getCart());
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
    void ThemeChange(ActionEvent event) { SceneHandler.getInstance().changeTheme(); }

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

    void showCompleteButton(boolean choice) { CompleteButton.setVisible(choice); }

    void showBalanceButton(boolean choice) { BalanceButton.setVisible(choice); }

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

    void loadProducts() {
        System.out.println("");
        Client.getInstance().get("prodotti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("prodotti");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println("[DEBUG] A product was found! JSON: " + obj);
                String image_id = obj.getString("image_id");
                int finalI = i;
                Client.getInstance().retrieveFile(image_id,
                    ref2 -> {
                        Platform.runLater(() -> {
                            System.out.println("[DEBUG] Adding an element.");
                            Image productImage = new Image(new ByteArrayInputStream(ref2.fileContent()));
                            String productName = obj.getString("nome");
                            productLabel.get(finalI).setText(productName);
                            productLabel.get(finalI).setOnMouseClicked(event -> {
                                Prodotto.getInstance().setData(productName, obj.getString("descrizione"), obj.getString("venditore"), obj.getString("prezzo"), obj.getString("image_id"), productImage);
                                try {
                                    subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/product-view.fxml"))));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            imageViews.get(finalI).setImage(productImage);
                            imageViews.get(finalI).setOnMouseClicked(event -> {
                                Prodotto.getInstance().setData(productName, obj.getString("descrizione"), obj.getString("venditore"), obj.getString("prezzo"), obj.getString("image_id"), productImage);
                                try {
                                    subAnchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/product-view.fxml"))));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        });
                    }, exc -> {
                            System.out.println("[DEBUG] Exception happened, cannot retrieve the file. Login status: " + (Client.getInstance().getEmail() != null));
                            exc.printStackTrace();
                    });
            }
        }, Throwable::printStackTrace);
    }

    void initializeArrayLists() {
        productLabel = new ArrayList<>();
        productLabel.add(productLabel1);
        productLabel.add(productLabel2);
        productLabel.add(productLabel3);
        productLabel.add(productLabel4);
        productLabel.add(productLabel5);
        productLabel.add(productLabel6);
        productLabel.add(productLabel7);
        productLabel.add(productLabel8);
        productLabel.add(productLabel9);
        productLabel.add(productLabel10);
        productLabel.add(productLabel11);
        productLabel.add(productLabel12);
        productLabel.add(productLabel13);
        productLabel.add(productLabel14);
        productLabel.add(productLabel15);
        productLabel.add(productLabel16);

        imageViews = new ArrayList<>();
        imageViews.add(imgview1);
        imageViews.add(imgview2);
        imageViews.add(imgview3);
        imageViews.add(imgview4);
        imageViews.add(imgview5);
        imageViews.add(imgview6);
        imageViews.add(imgview7);
        imageViews.add(imgview8);
        imageViews.add(imgview9);
        imageViews.add(imgview10);
        imageViews.add(imgview11);
        imageViews.add(imgview12);
        imageViews.add(imgview13);
        imageViews.add(imgview14);
        imageViews.add(imgview15);
        imageViews.add(imgview16);
    }

    @FXML
    void initialize() throws Exception {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);

        setIcons();
        initializeArrayLists();

        if(Client.getInstance().getEmail() != null) {
            obtainData();
            loggedIn();

            Utente.getInstance().getCartData();
        } else notLoggedIn();
        loadProducts();
    }
}