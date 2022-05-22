package com.uid.marketplace.progettomarketplace;

public interface AlertMessages {
    String TITLE_REGISTRATION_ERROR = "Errore durante la fase di registrazione";
    String INVALID_EMAIL = "La mail inserita non è valida! Verifica la correttezza dell'indirizzo.";
    String PASSWORD_TOO_SHORT = "La password deve essere superiore a 6 caratteri!";
    String PASSWORD_DOESNT_MATCH = "Le due password inserite non combaciano!";
    String EMAIL_ALREADY_USED = "Questa mail è già associata ad un altro account! Prova a cambiare email.";
    String VERIFICATION_EMAIL_SENT = "Ti è stata inviata una mail di verifica! Controlla la tua casella di posta elettronica.";
    String ALMOST_DONE = "Ci siamo quasi!";
    String CONNECTION_ERROR_TITLE = "Errore di connessione";
    String CONNECTION_ERROR_MSG = "Impossibile raggiungere il server.";
    String EMAIL_NOT_YET_VERIFIED = "L'email non è stata ancora verificata! Controlla la tua casella di posta elettronica.";
    String REGISTRATION_COMPLETED_MSG = "Registrazione andata a buon fine! Sarai reindirizzato alla pagina di accesso.";
    String REGISTRATION_COMPLETED_TITLE = "Registrazione completata";
    String INVALID_LOGIN_MSG = "Credenziali non valide! Assicurati di aver inserito email e password correttamente.";
    String INVALID_LOGIN_TITLE = "Login fallito!";
}
