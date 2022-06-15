package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.Model.CartElement;
import com.uid.marketplace.progettomarketplace.Model.Prodotto;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

public class ProductController {

    @FXML
    private Button addToCartButton;

    @FXML
    private Label productDescription;

    @FXML
    private ImageView productImg;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private ChoiceBox<Integer> qtyBox;

    @FXML
    private Label qtyLabel;

    @FXML
    void addToCartAction(ActionEvent event) throws Exception {
        CartElement elem = new CartElement(Prodotto.getInstance().getImage_id(), Prodotto.getInstance().getName(), Prodotto.getInstance().getPrezzo(), qtyBox.getValue());
        JSONObject jsonElem = JSONUtil.toJSON(elem);
        Utente.getInstance().addToCart(jsonElem);
        SceneHandler.getInstance().createAlert(AlertMessages.ADDED_TO_CART_MSG, AlertMessages.ADDED_TO_CART_TITLE);
    }

    @FXML
    void initialize() {
        productImg.setImage(Prodotto.getInstance().getImage());
        productName.setText(Prodotto.getInstance().getVenditore() + " " + Prodotto.getInstance().getName());
        productDescription.setText(Prodotto.getInstance().getDescrizione());
        productPrice.setText(Prodotto.getInstance().getPrezzo());

        for(int i=1; i<=10; i++) qtyBox.getItems().add(i);
        qtyBox.setValue(1);
    }

}
