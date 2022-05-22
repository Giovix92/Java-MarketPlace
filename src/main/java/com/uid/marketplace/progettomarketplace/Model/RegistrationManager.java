package com.uid.marketplace.progettomarketplace.Model;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import java.io.IOException;

public class RegistrationManager {

    private static RegistrationManager instance = new RegistrationManager();
    private RegistrationManager() {}
    public static RegistrationManager getInstance() { return instance; }

    public void registrationHandler() {
        while(true) {
            boolean choice = SceneHandler.getInstance().createRegistrationVerificationDialog(
                    AlertMessages.VERIFICATION_EMAIL_SENT,
                    AlertMessages.ALMOST_DONE);
            if(choice) {
                try {
                    if(Client.getInstance().isEmailVerified())
                        return;
                    else
                        SceneHandler.getInstance().createError(
                                AlertMessages.EMAIL_NOT_YET_VERIFIED,
                                AlertMessages.TITLE_REGISTRATION_ERROR);
                } catch (IOException | ConnectionException e) {
                    if(!SceneHandler.getInstance().createErrorWithContacts(
                            AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE)) {
                        SceneHandler.getInstance().createAlert("Contatti", "Contatti");
                    }
                }
            } else {
                try {
                    if(Client.getInstance().sendEmailVerification()) {
                        SceneHandler.getInstance().createAlert(
                                AlertMessages.VERIFICATION_EMAIL_SENT,
                                AlertMessages.ALMOST_DONE);
                    } else {
                        SceneHandler.getInstance().createError("Errore", "Errore");
                    }
                } catch (IOException | ConnectionException e) {
                    if(!SceneHandler.getInstance().createErrorWithContacts(
                            AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE)) {
                        SceneHandler.getInstance().createAlert("Contatti", "Contatti");
                    }
                }
            }
        }
    }
}
