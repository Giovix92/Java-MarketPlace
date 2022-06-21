package com.uid.marketplace.progettomarketplace.utils.handlers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.utils.Prodotto;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;

import static java.lang.Thread.sleep;

public class LoadProducts extends Service<Boolean> {

    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() {
                Client.getInstance().get("prodotti",
                        ref -> {
                            JSONObject result = ref.result();
                            JSONArray jsonArray = result.getJSONArray("prodotti");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                String image_id = obj.getString("image_id");
                                String label = obj.getString("nome");


                                Prodotto.getInstance().addProduct(image_id, label);
                                Prodotto.getInstance().getIds2Objs().put(image_id, obj);
                            }
                            for (String id : Prodotto.getInstance().getLabel2ImagesIDs().values()) {
                                Client.getInstance().retrieveFile(id,
                                        ref2 -> Prodotto.getInstance().addImage(id, new Image(new ByteArrayInputStream(ref2.fileContent()))),
                                        exc -> Platform.runLater(() -> {
                                            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                                SceneHandler.getInstance().showHelpAlert();
                                        })
                                );
                                sleep(150);
                            }
                            while (true) {
                                if (Prodotto.getInstance().getIds2Images().size() == jsonArray.length() &&
                                        Prodotto.getInstance().getLabel2ImagesIDs().size() == jsonArray.length()) {

                                    break;
                                }
                            }
                        },
                        exc -> Platform.runLater(() -> {
                            if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                                    AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                                SceneHandler.getInstance().showHelpAlert();
                        })
                );
                return true;
            }
        };
    }
}
