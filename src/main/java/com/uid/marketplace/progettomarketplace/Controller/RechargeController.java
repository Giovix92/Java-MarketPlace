package com.uid.marketplace.progettomarketplace.Controller;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.MarketPlaceApplication;
import com.uid.marketplace.progettomarketplace.Model.User;
import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;
import static java.lang.Thread.sleep;

public class RechargeController {

    @FXML
    private Label BalanceField;

    @FXML
    private ImageView HomePageButton;

    @FXML
    private TextField CouponBar;

    @FXML
    void RechargeAction(ActionEvent event) throws Exception {
        double currentBalance = Double.parseDouble(Utente.getInstance().getSaldo());
        Client.getInstance().get("coupon", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("coupon");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("key").equals(CouponBar.getText()) && Double.parseDouble(obj.getString("value")) > 0.00) {
                    System.out.println("[DEBUG] Coupon found and value is greater than 0.00!");
                    String couponID = obj.getString("element_id");
                    System.out.println("[DEBUG] Coupon ID: " + couponID);
                    double toBeAdded = Double.parseDouble(obj.getString("value"));
                    Client.getInstance().get("clienti", ref2 -> {
                        System.out.println("[DEBUG] Getting user infos.");
                        JSONObject result2 = ref2.result();
                        JSONArray jsonArray2 = result2.getJSONArray("clienti");
                        for(int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject obj2 = jsonArray2.getJSONObject(j);
                            if(obj2.getString("email").equals(Client.getInstance().getEmail())) {
                                System.out.println("[DEBUG] Email found, now going to pick its id.");
                                String idToBeUpdated = obj2.getString("element_id");
                                System.out.println("[DEBUG] ID of the user: " + idToBeUpdated);
                                double oldBalance = Double.parseDouble(obj2.getString("saldo"));
                                oldBalance += toBeAdded;
                                System.out.println("[DEBUG] New balance: " + oldBalance);
                                User user = new User(Client.getInstance().getEmail(), Utente.getInstance().getName(),
                                        Utente.getInstance().getSurname(), Utente.getInstance().getAddress(), String.valueOf(oldBalance));
                                JSONObject newJson = JSONUtil.toJSON(user);
                                Utente.getInstance().setSaldo(String.valueOf(oldBalance));
                                System.out.println("[DEBUG] JSON: " + newJson);
                                Client.getInstance().update("clienti", idToBeUpdated, newJson,
                                        ref3 -> {
                                            System.out.println("[DEBUG] Updated!");
                                            System.out.println("[DEBUG] Proceeding to remove the used coupon.");
                                            Client.getInstance().remove("coupon", couponID,
                                                    ref4 -> System.out.println("[DEBUG] Removed coupon successfully!"),
                                                    Throwable::printStackTrace);
                                        },
                                        Throwable::printStackTrace);
                            }
                        }
                    }, exc -> {});
                }
            }
        }, exc -> {});
        sleep(150);
        if(currentBalance == Double.parseDouble(Utente.getInstance().getSaldo()))
            SceneHandler.getInstance().createError(AlertMessages.INVALID_COUPON_MSG, AlertMessages.COUPON_EMPTY_ERROR_TITLE);
        else {
            BalanceField.setText(Double.parseDouble(Utente.getInstance().getSaldo()) + "€");
            CouponBar.setText("");
            SceneHandler.getInstance().createAlert(AlertMessages.BALANCE_UPDATED_MSG, AlertMessages.BALANCE_UPDATED_TITLE);
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

        BalanceField.setText(Double.parseDouble(Utente.getInstance().getSaldo()) + "€");
    }
}
