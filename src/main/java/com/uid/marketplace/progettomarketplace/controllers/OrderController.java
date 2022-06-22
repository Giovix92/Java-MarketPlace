package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.utils.Utente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class OrderController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    void setOnMouseClickedMethod(JSONObject obj, Image img) {
        Prodotto.getInstance().setData(obj.getString("nome"), obj.getString("descrizione"), obj.getString("venditore"), obj.getString("prezzo"), obj.getString("image_id"), img);
        try {
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/product-view.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showOrders() {
        for (int i = 0; i < Utente.getInstance().getOrders().length(); i++) {
            JSONObject order = Utente.getInstance().getOrders().getJSONObject(i);

            VBox orderVBox = new VBox();
            HBox headerHBox = new HBox();

            headerHBox.setStyle("-fx-background-color: LIGHTGREY;" +
                    "-fx-border-color: DARKGREY;");
            orderVBox.setStyle("-fx-border-color: DARKGREY;" +
                    "-fx-border-radius: 5px;");
            String fullAddress = "Inviato a: " + order.getString("nome") + " " + order.getString("cognome") + " " +
                    order.getString("indirizzo") + ", " + order.getString("date") + ".";
            String total = "Totale: " + order.getString("total") + "€";
            Label infos = new Label(fullAddress);
            Label amount = new Label(total);
            infos.setStyle("-fx-font-size: 18px");
            amount.setStyle("-fx-font-size: 18px");

            HBox.setMargin(amount, new Insets(0, 0, 0, 10));
            VBox.setMargin(headerHBox, new Insets(0, 0, 10, 0));

            headerHBox.getChildren().addAll(infos, amount);
            VBox.setVgrow(headerHBox, Priority.ALWAYS);
            orderVBox.getChildren().addAll(headerHBox);

            JSONArray cart = order.getJSONArray("cartArray");
            for (int j = 0; j < cart.length(); j++) {
                JSONObject product = cart.getJSONObject(j);
                Image img = Prodotto.getInstance().getIds2Images().get(product.getString("id"));
                ImageView view = new ImageView(img);
                view.setFitWidth(75);
                view.setFitHeight(75);

                String label = product.getString("nome");
                if (label.length() > 80) label = label.substring(0, 80) + "...";
                label += " *" + product.getInt("qty");
                Label productName = new Label(label);
                productName.setStyle("-fx-font-size: 16px");

                JSONObject productObj = Prodotto.getInstance().getIds2Objs().get(product.getString("id"));
                productName.setOnMouseClicked(event -> setOnMouseClickedMethod(productObj, img));
                view.setOnMouseClicked(event -> setOnMouseClickedMethod(productObj, img));

                view.setStyle("-fx-cursor: hand");
                productName.setStyle("-fx-cursor: hand;" +
                        "-fx-font-size: 16px;" +
                        "-fx-text-fill: totalLabelColor;");

                HBox productHBox = new HBox();
                HBox.setMargin(productName, new Insets(0, 0, 0, 10));
                VBox.setMargin(productHBox, new Insets(0, 0, 10, 0));
                productHBox.getChildren().addAll(view, productName);
                VBox.setVgrow(productHBox, Priority.ALWAYS);
                orderVBox.getChildren().add(productHBox);
            }
            VBox.setMargin(orderVBox, new Insets(15, 0, 15, 10));
            vBox.getChildren().addAll(orderVBox);
        }
    }

    void setAnchorPaneAnchors() {
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);

        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
    }

    @FXML
    void initialize() {
        setAnchorPaneAnchors();

        showOrders();

    }
}
