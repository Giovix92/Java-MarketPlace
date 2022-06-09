package com.uid.marketplace.progettomarketplace;

public interface AlertMessages {
    String REGISTRATION_ERROR_TITLE = "Errore durante la fase di registrazione";
    String INVALID_EMAIL_MSG = "La mail inserita non è valida! Verifica la correttezza dell'indirizzo.";
    String PASSWORD_TOO_SHORT_MSG = "La password deve essere superiore a 6 caratteri!";
    String PASSWORD_DOESNT_MATCH_MSG = "Le due password inserite non combaciano!";
    String EMAIL_ALREADY_USED_MSG = "Questa mail è già associata ad un altro account! Prova a cambiare email.";
    String VERIFICATION_EMAIL_SENT_MSG = "Ti è stata inviata una mail di verifica! Controlla la tua casella di posta elettronica.";
    String ALMOST_DONE_TITLE = "Ci siamo quasi!";
    String CONNECTION_ERROR_TITLE = "Errore di connessione";
    String CONNECTION_ERROR_MSG = "Impossibile raggiungere il server.";
    String EMAIL_NOT_YET_VERIFIED_MSG = "L'email non è stata ancora verificata! Controlla la tua casella di posta elettronica.";
    String REGISTRATION_COMPLETED_MSG = "Registrazione andata a buon fine! Sarai reindirizzato alla pagina di accesso.";
    String REGISTRATION_COMPLETED_TITLE = "Registrazione completata";
    String INVALID_LOGIN_MSG = "Credenziali non valide! Assicurati di aver inserito email e password correttamente.";
    String INVALID_LOGIN_TITLE = "Login fallito!";
    String CHANGE_RECOVERY_EMAIL_SENT_MSG = "Ti è stata inviata una mail al tuo indirizzo di posta elettronica." + System.lineSeparator() +
                                     "Segui le istruzioni al suo interno.";
    String RECOVERY_EMAIL_TITLE = "Recupero password";
    String CHANGE_PASSWORD_TITLE = "Cambio password";
    String CHANGE_RECOVERY_EMAIL_ERROR_MSG = "La mail inserita non risulta associata a nessun account.";
    String INSUFFICIENT_TIME_MSG = "Per poter richiedere una nuova mail, il tempo di attesa minimo è di 5 minuti! Riprova piu' tardi.";
    String INSUFFICIENT_TIME_TITLE = "Tempo insufficiente!";
    String CHANGE_PASSWORD_ERROR_MSG = "Impossibile cambiare la password! Riprova più tardi.";
    String CHANGE_PASSWORD_MSG = "Hai appena cambiato la password del tuo account! Sarai reinderizzato all'homepage.";
    String ACCOUNT_COMPLETED_MSG = "Hai completato il tuo account. Benvenuto in UID - MarketPlace!";
    String ACCOUNT_COMPLETED_TITLE = "Account ultimato";
    String COUPON_ADDED_MSG = "Il coupon è stato aggiunto correttamente!";
    String COUPON_ADDED_TITLE = "Coupon aggiunto!";
    String COUPON_EMPTY_ERROR_MSG = "Non hai inserito alcun coupon!";
    String COUPON_EMPTY_ERROR_TITLE = "Coupon invalido";
    String COUPON_EMPTY_VALUE_ERROR_MSG = "Non hai inserito alcun valore!";
    String COUPON_EMPTY_VALUE_ERROR_TITLE = "Coupon invalido";
    String SET_PRODUCT_IMAGE_ERROR_MSG = "Prima di poter caricare un prodotto bisogna scegliere la relativa immagine da visualizzare!";
    String SET_PRODUCT_IMAGE_ERROR_TITLE = "Caricamento obbligatorio";
    String PRODUCT_ADDED_MSG = "Il prodotto è stato aggiunto correttamente!";
    String PRODUCT_ADDED_TITLE = "Prodotto aggiunto!";

    String BALANCE_UPDATED_MSG = "Il saldo è stato aggiornato correttamente. Ora puoi procedere agli acquisti!";
    String BALANCE_UPDATED_TITLE = "Coupon aggiunto al tuo account";
    String INVALID_COUPON_MSG = "Il coupon che hai inserito non è valido! Riprova.";

}

