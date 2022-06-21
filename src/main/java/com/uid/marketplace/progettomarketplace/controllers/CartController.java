package com.uid.marketplace.progettomarketplace.controllers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.utils.Cart;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.utils.Utente;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

import java.text.DecimalFormat;

public class CartController {

    double finalAmount = 0;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label insufficientBalanceLabel, purchaseAddressLabel, actualBalanceLabel, purchaseInfoLabel, purchaseYourInfosLabel, purchaseTotalLabel;
    @FXML
    private Button purchaseButton;
    @FXML
    private Hyperlink rechargeHyperLink;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;

    @FXML
    void purchaseAction() throws Exception {
        if (Client.getInstance().getEmail() == null) {
            SceneHandler.getInstance().setAccessScene();
        } else if(Cart.getInstance().getCartArray().length() == 0) {
            SceneHandler.getInstance().createAlert(AlertMessages.NO_PRODUCTS_IN_CART_MSG, AlertMessages.NO_ORDERS_TITLE);
        } else {
            double balance = Double.parseDouble(Utente.getInstance().getSaldo());
            if (balance < finalAmount) {
                if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.INSUFFICIENT_BALANCE_MSG,
                        AlertMessages.INSUFFICIENT_BALANCE_TITLE, "Chiudi", "Ricarica saldo", Alert.AlertType.INFORMATION))
                    SceneHandler.getInstance().setRechargeBalanceScene();
            } else {
                balance -= finalAmount;
                Utente.getInstance().setSaldo(String.valueOf(balance));
                Utente.getInstance().updateBalance();
                Utente.getInstance().addPurchaseToDB(String.valueOf(new DecimalFormat("0.00").format(finalAmount)));

            }
        }
    }

    @FXML
    void rechargeAction() throws Exception {
        if (Client.getInstance().getEmail() != null) SceneHandler.getInstance().setRechargeBalanceScene();
        else SceneHandler.getInstance().setAccessScene();
    }

    void showProducts() {
        JSONArray cart = Cart.getInstance().getCartArray();
        for (int i = 0; i < cart.length(); i++) {
            JSONObject obj = cart.getJSONObject(i);
            finalAmount += Double.parseDouble(obj.getString("prezzo").replace(",", ".")) * obj.getInt("qty");

            String label = obj.getString("nome");
            if (label.length() > 30) label = label.substring(0, 30) + "...";
            Label productLabel = new Label(label);
            ChoiceBox<Integer> qtyEditor = new ChoiceBox<>();
            for (int j = 1; j <= 10; j++) qtyEditor.getItems().add(j);
            qtyEditor.setValue(obj.getInt("qty"));

            HBox hbox = new HBox();
            Image image = Prodotto.getInstance().getIds2Images().get(obj.getString("id"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            qtyEditor.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                Cart.getInstance().changeObjQty(obj.getString("id"), newValue);
                finalAmount -= Double.parseDouble(obj.getString("prezzo").replace(",", ".")) * oldValue;
                finalAmount += Double.parseDouble(obj.getString("prezzo").replace(",", ".")) * newValue;
                if (!purchaseTotalLabel.getText().equals(""))
                    purchaseTotalLabel.setText("Totale: " + new DecimalFormat("0.00").format(finalAmount) + "€");
            });

            FontIcon trashIcon = new FontIcon("fas-trash-alt");
            trashIcon.setIconColor(Color.RED);
            Button removeButton = new Button();
            removeButton.setText("Rimuovi");
            removeButton.setGraphic(trashIcon);

            removeButton.setStyle("-fx-cursor: hand");
            qtyEditor.setStyle("-fx-cursor: hand");
            productLabel.setStyle("-fx-text-fill: totalLabelColor;");

            Region region = new Region();
            region.setPrefWidth(140);

            removeButton.setOnMouseClicked(event -> {
                Cart.getInstance().removeFromCart(obj.getString("id"));
                vBox.getChildren().remove(hbox);
                finalAmount -= Double.parseDouble(obj.getString("prezzo").replace(",", ".")) * obj.getInt("qty");
                purchaseTotalLabel.setText("Totale: " + new DecimalFormat("0.00").format(finalAmount) + "€");
            });

            HBox.setHgrow(region, Priority.ALWAYS);
            HBox.setHgrow(imageView, Priority.NEVER);
            HBox.setHgrow(productLabel, Priority.ALWAYS);
            HBox.setHgrow(removeButton, Priority.NEVER);
            HBox.setHgrow(qtyEditor, Priority.NEVER);
            VBox.setVgrow(vBox, Priority.ALWAYS);

            VBox.setMargin(vBox, new Insets(0, 0, 20, 10));
            HBox.setMargin(removeButton, new Insets(0, 0, 0, 5));

            hbox.getChildren().addAll(imageView, productLabel, region, qtyEditor, removeButton);
            vBox.getChildren().add(hbox);
        }
    }

    void setInfos() {
        if (Client.getInstance().getEmail() != null) {
            purchaseInfoLabel.setText("Informazioni sull'acquisto:");
            purchaseYourInfosLabel.setFont(new Font(20));
            purchaseYourInfosLabel.setText("I tuoi dati di spedizione/fatturazione: ");
            purchaseAddressLabel.setText(Utente.getInstance().getName() + " " +
                    Utente.getInstance().getSurname() + ", " +
                    Utente.getInstance().getAddress());
            actualBalanceLabel.setText("Saldo attuale: " + new DecimalFormat("0.00").format(Double.parseDouble(Utente.getInstance().getSaldo())) + "€");
            rechargeHyperLink.setText("Ricarica il tuo saldo!");
            purchaseButton.setFont(new Font("Procedi all'acquisto!", 13));
            purchaseTotalLabel.setText("Totale: " + new DecimalFormat("0.00").format(finalAmount) + "€");
        } else {
            purchaseInfoLabel.setText("Prima di poter continuare con il tuo ordine, ");
            purchaseYourInfosLabel.setText("devi prima effettuare l'accesso!");
            purchaseYourInfosLabel.setTranslateY(-15.0);
            purchaseYourInfosLabel.setFont(new Font(25));
            purchaseButton.setText("Accedi");
            purchaseButton.setTranslateY(-80.0);
            insufficientBalanceLabel.setText("");
            rechargeHyperLink.setText("");
            purchaseAddressLabel.setText("");
            actualBalanceLabel.setText("");
            purchaseTotalLabel.setText("");
        }
    }

    void setAnchorPaneConstraints() {
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
    }

    @FXML
    void initialize() {
        setAnchorPaneConstraints();

        showProducts();

        setInfos();
    }

}