package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import com.uid.marketplace.progettomarketplace.models.CartElement;
import com.uid.marketplace.progettomarketplace.utils.Cart;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.json.JSONObject;

public class ProductController {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea productDescription;

    @FXML
    private ImageView productImg;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private ChoiceBox<Integer> qtyBox;

    @FXML
    void addToCartAction() throws Exception {
        CartElement elem = new CartElement(Prodotto.getInstance().getImage_id(), Prodotto.getInstance().getName(), Prodotto.getInstance().getPrezzo(), qtyBox.getValue());
        JSONObject jsonElem = JSONUtil.toJSON(elem);
        Cart.getInstance().addToCart(jsonElem);
        SceneHandler.getInstance().createAlert(AlertMessages.ADDED_TO_CART_MSG, AlertMessages.ADDED_TO_CART_TITLE);
    }

    @FXML
    void initialize() {
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);

        productImg.setImage(Prodotto.getInstance().getImage());

        productName.setText(Prodotto.getInstance().getVenditore() + " " + Prodotto.getInstance().getName());

        productDescription.setText(Prodotto.getInstance().getDescrizione());
        productDescription.setFont(new Font(13));
        productDescription.setWrapText(true);

        productPrice.setText(Prodotto.getInstance().getPrezzo() + "€");

        for (int i = 1; i <= 10; i++) qtyBox.getItems().add(i);
        qtyBox.setValue(1);
    }

}
