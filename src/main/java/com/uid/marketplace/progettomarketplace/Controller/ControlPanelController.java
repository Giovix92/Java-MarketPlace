package com.uid.marketplace.progettomarketplace.Controller;

import com.sun.jdi.Value;
import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.Coupon;
import com.uid.marketplace.progettomarketplace.Model.Prodotto;
import com.uid.marketplace.progettomarketplace.Model.Product;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ControlPanelController {

    @FXML
    private TextField CouponBar;

    @FXML
    private TextField ValueBar;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField ProductDescriptionBar;

    @FXML
    private TextField ProductNameBar;

    @FXML
    private TextField ProductPriceBar;

    @FXML
    private TextField ProductSellerBar;

    @FXML
    void AddCouponAction(ActionEvent event) throws Exception {
        //tasto di aggiunta di un coupon
        if(CouponBar.getText().equals("")){
            SceneHandler.getInstance().createError(AlertMessages.COUPON_EMPTY_ERROR_MSG, AlertMessages.COUPON_EMPTY_ERROR_TITLE);
            return;
        }
        if(ValueBar.getText().equals("")){
            SceneHandler.getInstance().createError(AlertMessages.COUPON_EMPTY_VALUE_ERROR_MSG, AlertMessages.COUPON_EMPTY_VALUE_ERROR_TITLE);
            return;
        }
        Coupon coupon = new Coupon(CouponBar.getText(),ValueBar.getText());
        JSONObject obj = JSONUtil.toJSON(coupon);
        Client.getInstance().insert("coupon", obj,
                reference -> {
                    CouponBar.setText("");
                    ValueBar.setText("");
                },
                exc -> {});
        SceneHandler.getInstance().createAlert(AlertMessages.COUPON_ADDED_MSG, AlertMessages.COUPON_ADDED_TITLE);
    }

    @FXML
    void AddProductAction(ActionEvent event) throws Exception {
        if(Prodotto.getInstance().getImage_id() == null) {
            SceneHandler.getInstance().createAlert(AlertMessages.SET_PRODUCT_IMAGE_ERROR_MSG, AlertMessages.SET_PRODUCT_IMAGE_ERROR_TITLE);
            return;
        }

        File file = new File(String.valueOf(Path.of(Prodotto.getInstance().getImage_id())));

        if(file != null) {
            Client.getInstance().uploadFile(file, file.getName().replaceAll(".*\\.",""),
                    ref -> {
                            Product product = new Product(ProductNameBar.getText(), ProductDescriptionBar.getText(), ProductSellerBar.getText(), ProductPriceBar.getText(), ref.fileId());
                            JSONObject obj = JSONUtil.toJSON(product);
                            Client.getInstance().insert("prodotti", obj,
                            reference -> {},
                            exc -> {});
                            /* Cartella dove vengono messi i files temporanei
                            System.out.println(System.getProperty("java.io.tmpdir"));  */
                            Path src = Paths.get(Prodotto.getInstance().getImage_id());
                            Path dest = Paths.get(Objects.requireNonNull(MarketPlaceApplication.class.getResource("ProductImageCache")).getFile() + "/" + ref.fileId());
                            Files.copy(src,dest);
                    },
                    Throwable::printStackTrace);
            SceneHandler.getInstance().createAlert(AlertMessages.PRODUCT_ADDED_MSG, AlertMessages.PRODUCT_ADDED_TITLE);
            Prodotto.getInstance().resetData();
            ProductDescriptionBar.setText("");
            ProductNameBar.setText("");
            ProductPriceBar.setText("");
            ProductSellerBar.setText("");
        }
    }

    @FXML
    void ConditionAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Condizioni generali di vendita");
    }

    @FXML
    void HomeAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setHomePageScene();
    }

    @FXML
    void PrivacyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Informativa sulla privacy");
    }

    @FXML
    void SocietyAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "La nostra società");
    }

    @FXML
    void UploadImageAction(ActionEvent event) throws Exception {
        Prodotto.getInstance().setImage_id(SceneHandler.getInstance().showFileChooser());
    }

    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }

}
