package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.Coupon;
import com.uid.marketplace.progettomarketplace.Model.User;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import com.uid.marketplace.progettomarketplace.util.UserUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class RechargeController {

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField CouponBar;

    @FXML
    void RechargeAction(ActionEvent event) throws Exception {
        //tasto per ricaricare il saldo
        Coupon coupon = new Coupon(CouponBar.getText());
        JSONObject obj = JSONUtil.toJSON(coupon);
        System.out.println(obj);
        Client.getInstance().update("clienti", Utente.getInstance().getId(), obj,
                reference -> {},
                exc -> {});
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
    void ServiceAction(ActionEvent event) {
        SceneHandler.getInstance().createAlert( "DA COMPLETARE", "Contatti");
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
    void initialize() {
        Image image = new Image(Objects.requireNonNull(MarketPlaceApplication.class.getResourceAsStream("images/logo.png")));
        HomePageButton.setImage(image);
    }
}
