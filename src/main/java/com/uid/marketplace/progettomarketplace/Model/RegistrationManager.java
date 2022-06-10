package com.uid.marketplace.progettomarketplace.Model;

import com.uid.marketplace.progettomarketplace.AlertMessages;
import com.uid.marketplace.progettomarketplace.View.SceneHandler;
import com.uid.marketplace.progettomarketplace.client.Client;
import com.uid.marketplace.progettomarketplace.client.ConnectionException;
import com.uid.marketplace.progettomarketplace.client.util.JSONUtil;
import org.json.JSONObject;

import java.io.IOException;

public class RegistrationManager {

    private static RegistrationManager instance = new RegistrationManager();

    private RegistrationManager() {}

    public static RegistrationManager getInstance() { return instance; }

    public void registrationHandler() {
        while(true) {
            boolean choice = SceneHandler.getInstance().createRegistrationVerificationDialog(
                    AlertMessages.VERIFICATION_EMAIL_SENT_MSG,
                    AlertMessages.ALMOST_DONE_TITLE);
            if(choice) {
                try {
                    if(Client.getInstance().isEmailVerified())
                        return;
                    else
                        SceneHandler.getInstance().createError(
                                AlertMessages.EMAIL_NOT_YET_VERIFIED_MSG,
                                AlertMessages.REGISTRATION_ERROR_TITLE);
                } catch (IOException | ConnectionException e) {
                    if(!SceneHandler.getInstance().createErrorWithContacts(
                            AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE)) {
                        SceneHandler.getInstance().showHelpAlert();
                    }
                }
            } else {
                try {
                    if(Client.getInstance().sendEmailVerification()) {
                        SceneHandler.getInstance().createAlert(
                                AlertMessages.VERIFICATION_EMAIL_SENT_MSG,
                                AlertMessages.ALMOST_DONE_TITLE);
                    } else {
                        SceneHandler.getInstance().createAlert(
                                AlertMessages.INSUFFICIENT_TIME_MSG,
                                AlertMessages.INSUFFICIENT_TIME_TITLE);
                    }
                } catch (IOException | ConnectionException e) {
                    if(!SceneHandler.getInstance().createErrorWithContacts(
                            AlertMessages.CONNECTION_ERROR_MSG,
                            AlertMessages.CONNECTION_ERROR_TITLE)) {
                        SceneHandler.getInstance().showHelpAlert();
                    }
                }
            }
        }
    }
}
