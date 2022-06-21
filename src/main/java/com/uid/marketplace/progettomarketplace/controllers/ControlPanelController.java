package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import com.uid.marketplace.progettomarketplace.models.Coupon;
import com.uid.marketplace.progettomarketplace.models.Product;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class ControlPanelController {

    @FXML
    private TextField CouponBar, ValueBar, ProductDescriptionBar, ProductNameBar, ProductSellerBar, ProductPriceBar;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private MenuButton CategoriesProductButton;

    @FXML
    private Button UploadImageButton;

    @FXML
    private CheckBox CheckHome;

    @FXML
    void BooksAction() {
        CategoriesProductButton.setText("Libri");
    }

    @FXML
    void ClothesAction() {
        CategoriesProductButton.setText("Abbigliamento");
    }

    @FXML
    void ElectronicsAction() {
        CategoriesProductButton.setText("Elettronica e Informatica");
    }

    @FXML
    void HealthAction() {
        CategoriesProductButton.setText("Salute e Benessere");
    }

    @FXML
    void HouseAction() {
        CategoriesProductButton.setText("Casa e Giardino");
    }

    @FXML
    void MusicAction() {
        CategoriesProductButton.setText("Musica, Film e TV");
    }

    @FXML
    void ShoesAction() {
        CategoriesProductButton.setText("Scarpe");
    }

    @FXML
    void SportAction() {
        CategoriesProductButton.setText("Sport e Tempo Libero");
    }

    @FXML
    void VideogamesAction() {
        CategoriesProductButton.setText("Videogiochi e Console");
    }

    @FXML
    void kidsAction() {
        CategoriesProductButton.setText("Per bambini");
    }


    @FXML
    void AddCouponAction() throws Exception {
        if (CouponBar.getText().equals("")) {
            SceneHandler.getInstance().createError(AlertMessages.COUPON_EMPTY_ERROR_MSG, AlertMessages.COUPON_EMPTY_ERROR_TITLE);
            return;
        }
        if (ValueBar.getText().equals("")) {
            SceneHandler.getInstance().createError(AlertMessages.COUPON_EMPTY_VALUE_ERROR_MSG, AlertMessages.COUPON_EMPTY_VALUE_ERROR_TITLE);
            return;
        }
        Coupon coupon = new Coupon(CouponBar.getText(), ValueBar.getText());
        JSONObject obj = JSONUtil.toJSON(coupon);
        Client.getInstance().insert("coupon", obj,
                reference -> {
                    CouponBar.setText("");
                    ValueBar.setText("");
                    Platform.runLater(() -> SceneHandler.getInstance().createAlert(AlertMessages.COUPON_ADDED_MSG,
                            AlertMessages.COUPON_ADDED_TITLE));
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );
    }

    @FXML
    void AddProductAction() {
        if (Prodotto.getInstance().getImage_id() == null) {
            SceneHandler.getInstance().createAlert(AlertMessages.SET_PRODUCT_IMAGE_ERROR_MSG, AlertMessages.SET_PRODUCT_IMAGE_ERROR_TITLE);
            return;
        }

        File file = new File(String.valueOf(Path.of(Prodotto.getInstance().getImage_id())));

        Client.getInstance().uploadFile(file, file.getName().replaceAll(".*\\.", ""),
                ref -> {
                    String description = ProductDescriptionBar.getText().replace("\"", "");
                    Product product = new Product(ProductNameBar.getText(), description, ProductSellerBar.getText(), ProductPriceBar.getText(), ref.fileId(), CategoriesProductButton.getText());
                    JSONObject obj = JSONUtil.toJSON(product);
                    Client.getInstance().insert("prodotti", obj,
                            reference -> Platform.runLater(() -> {
                                SceneHandler.getInstance().createAlert(AlertMessages.PRODUCT_ADDED_MSG, AlertMessages.PRODUCT_ADDED_TITLE);
                                try {
                                    Prodotto.getInstance().resetData();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                ProductDescriptionBar.setText("");
                                ProductNameBar.setText("");
                                ProductPriceBar.setText("");
                                ProductSellerBar.setText("");
                                CategoriesProductButton.setText("Categorie");
                                UploadImageButton.setGraphic(null);

                                if (CheckHome.isSelected()) {
                                    try {
                                        SceneHandler.getInstance().setSplashScreenScene();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }), exc -> Platform.runLater(() -> {
                                if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                        AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                    SceneHandler.getInstance().showHelpAlert();
                            }));
                }, exc -> Platform.runLater(() -> {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                })
        );
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
    void PrivacyAction() {
        SceneHandler.getInstance().showPrivacyPolicyAlert();
    }

    @FXML
    void SocietyAction() {
        SceneHandler.getInstance().showSocietyAlert();
    }

    @FXML
    void UploadImageAction() {
        Prodotto.getInstance().setImage_id(SceneHandler.getInstance().showFileChooser());
        if (Prodotto.getInstance().getImage_id() != null) {
            FontIcon selectedButton = new FontIcon("fas-check-circle");
            selectedButton.setIconColor(Color.GREEN);
            UploadImageButton.setGraphic(selectedButton);
        }
    }

    @FXML
    void ThemeChange() {
        SceneHandler.getInstance().changeTheme();
    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
