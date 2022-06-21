package com.uid.marketplace.progettomarketplace.utils.handlers;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import com.uid.marketplace.progettomarketplace.view.SceneHandler;
import javafx.scene.control.Alert;

import java.io.IOException;

public class RegistrationManager {

    private static final RegistrationManager instance = new RegistrationManager();

    private RegistrationManager() {
    }

    public static RegistrationManager getInstance() {
        return instance;
    }

    public void registrationHandler() {
        while (true) {
            boolean choice = SceneHandler.getInstance().createRegistrationVerificationDialog(
                    AlertMessages.VERIFICATION_EMAIL_SENT_MSG,
                    AlertMessages.ALMOST_DONE_TITLE);
            if (choice) {
                try {
                    if (Client.getInstance().isEmailVerified())
                        return;
                    else
                        SceneHandler.getInstance().createError(
                                AlertMessages.EMAIL_NOT_YET_VERIFIED_MSG,
                                AlertMessages.REGISTRATION_ERROR_TITLE);
                } catch (IOException | ConnectionException e) {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                }
            } else {
                try {
                    if (Client.getInstance().sendEmailVerification()) {
                        SceneHandler.getInstance().createAlert(
                                AlertMessages.VERIFICATION_EMAIL_SENT_MSG,
                                AlertMessages.ALMOST_DONE_TITLE);
                    } else {
                        SceneHandler.getInstance().createAlert(
                                AlertMessages.INSUFFICIENT_TIME_MSG,
                                AlertMessages.INSUFFICIENT_TIME_TITLE);
                    }
                } catch (IOException | ConnectionException e) {
                    if (SceneHandler.getInstance().createAlertWithButtons(AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE, "OK", "Segnala l'errore", Alert.AlertType.ERROR))
                        SceneHandler.getInstance().showHelpAlert();
                }
            }
        }
    }
}
