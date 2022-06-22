package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.json.JSONObject;

import java.util.Objects;

public class SearchController {

    static String wordToSearch = null;
    static String categorySet = null;
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;

    public static void setCategory(String categorySet) {
        SearchController.categorySet = categorySet;
    }

    public static void setSearchWord(String word) {
        wordToSearch = word;
    }

    void setOnMouseClickedMethod(JSONObject obj, Image img) {
        Prodotto.getInstance().setData(obj.getString("nome"), obj.getString("descrizione"), obj.getString("venditore"), obj.getString("prezzo"), obj.getString("image_id"), img);
        try {
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MarketPlaceApplication.class.getResource("fxmls/product-view.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showProducts() throws Exception {
        Prodotto.getInstance().getIds2Objs().forEach((id, obj) -> {
            Image img = Prodotto.getInstance().getIds2Images().get(id);
            Label productLabel = new Label(obj.getString("venditore") + " " + obj.getString("nome"));
            productLabel.setFont(new Font(16));
            if (wordToSearch.length() > 0 && !productLabel.getText().toLowerCase().contains(wordToSearch.toLowerCase()))
                return;
            if (!categorySet.equals(obj.getString("category")) && !categorySet.equals("Categorie")) return;

            HBox hbox = new HBox();
            ImageView view = new ImageView(img);

            view.setFitHeight(100);
            view.setFitWidth(150);
            view.maxHeight(100);
            view.maxWidth(150);
            view.setPreserveRatio(true);

            productLabel.setOnMouseClicked(event -> setOnMouseClickedMethod(obj, img));
            view.setOnMouseClicked(event -> setOnMouseClickedMethod(obj, img));
            view.setStyle("-fx-cursor: hand");
            productLabel.setStyle("-fx-cursor: hand; " +
                    "-fx-text-fill: totalLabelColor;");

            HBox.setMargin(productLabel, new Insets(0, 0, 0, 15));
            hbox.setStyle("-fx-font-size: 15px");
            hbox.setOnMouseClicked(event -> hbox.setStyle("-fx-font-size: 15px"));
            hbox.getChildren().addAll(view, productLabel);
            VBox.setMargin(hbox, new Insets(0, 0, 20, 20));
            vBox.getChildren().add(hbox);
        });
        if(vBox.getChildren().size() == 0) {
            SceneHandler.getInstance().createAlert(AlertMessages.NO_PRODUCTS_FOUND_MSG, AlertMessages.NO_PRODUCTS_FOUND_TITLE);
            SceneHandler.getInstance().setHomePageScene();
        }
    }

    @FXML
    void initialize() throws Exception {
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        showProducts();
    }

}
