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
    String ACCOUNT_UPDATED_MSG = "Hai aggiornato con successo le informazioni dell'account!";
    String ACCOUNT_UPDATED_TITLE = "Informazioni aggiornate";
    String MISSING_INFOS_MSG = "Assicurati di aver compilato tutti i campi prima di continuare!";
    String MISSING_INFOS_TITLE = "Impossibile continuare";
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
    String INSUFFICIENT_BALANCE_TITLE = "Saldo insufficiente";
    String INSUFFICIENT_BALANCE_MSG = "Il tuo saldo non è sufficiente per completare l'acquisto!" + System.lineSeparator() +
            "Vuoi ricaricare il tuo saldo?";
    String ORDER_DONE_TITLE = "Ordine effettuato";
    String ORDER_DONE_MSG = "Ordine completato con successo!";

    String NO_ORDERS_TITLE = "Attenzione;";
    String NO_ORDERS_MSG = "Nessun ordine presente nel tuo account!";
    String INVALID_COUPON_MSG = "Il coupon che hai inserito non è valido! Riprova.";
    String ADDED_TO_CART_MSG = "Il prodotto è stato aggiunto al carrello correttamente!";
    String ADDED_TO_CART_TITLE = "Prodotto aggiunto";
    String PRIVACY_POLICY_MSG = """
            0. Informativa sulla privacy
            UID - Marketplace prende sul serio la vostra privacy.
            Per proteggere al meglio la vostra privacy, forniamo la presente informativa sulla privacy
            che spiega il modo in cui le informazioni personali vengono raccolte e utilizzate.
                      
            1. Link a siti web di terze parti
            Abbiamo incluso dei link in questa applicazione per il vostro uso e riferimento.
            Non siamo responsabili delle politiche sulla privacy di questi siti web.
            L'utente deve essere consapevole che le politiche sulla privacy di questi siti web
            possono differire dalle nostre.

            2. Sicurezza
            La sicurezza dei vostri dati personali è importante per noi,
            ma ricordate che nessun metodo di trasmissione su Internet,
            o metodo di archiviazione elettronica è sicuro al 100%.
            Sebbene ci sforziamo di utilizzare mezzi commercialmente accettabili per proteggere i vostri
            i vostri dati personali, non possiamo garantirne l'assoluta sicurezza.
                       
            3. Modifiche alla presente Informativa sulla privacy
            La presente Informativa sulla privacy è in vigore a partire dal 9/06/2022
            e rimarrà in vigore tranne che per quanto riguarda a eventuali modifiche delle sue disposizioni in futuro,
            che entreranno in vigore immediatamente dopo essere state pubblicate su questa pagina.
            Ci riserviamo il diritto di aggiornare o modificare la nostra Informativa sulla privacy in qualsiasi momento.
            e l'utente dovrebbe controllare periodicamente la presente Informativa sulla privacy.
            Qualora dovessimo apportare modifiche sostanziali alla presente Informativa sulla privacy,
            vi informeremo attraverso l'indirizzo e-mail che ci avete fornito,
            o inserendo un avviso in evidenza sulla nostra app.
            """;
    String PRIVACY_POLICY_TITLE = "Informativa sulla privacy";
    String SOCIETY_MSG = """
            UID - MarketPlace è un marketplace scritto in Java
            sviluppato e gestito da un team
            di 3 studenti dell'Università della Calabria.
            Per maggiori informazioni, guarda la repository su GitHub:
            https://www.github.com/Giovix92/Java-MarketPlace,
            oppure scrivici una mail a:
            Giovanni Gualtieri: giovix92@giovix92.com
            Gaetano Masci: mascigaetano@gmail.com
            Jacopo Garofalo: jacopogarofalo02@gmail.com
            """;
    String SOCIETY_TITLE = "La nostra società";
    String HELP_MSG = """
            Per assistenza, cambio mail, informazioni sull'utilizzo etc.
            puoi contattarci qui:
                        
            Giovanni Gualtieri: giovix92@giovix92.com
            Gaetano Masci: mascigaetano@gmail.com
            Jacopo Garofalo: jacopogarofalo02@gmail.com
            """;
    String HELP_TITLE = "Contatti";
    String TOS_MSG = """
            Condizioni Generali di Vendita
                        
            Art. 1 - Disposizioni generali
            1. L'utente navigando in quest'area accede
            a UID - MarketPlace mediante l'utilizzo di un applicativo.
            La navigazione e la trasmissione di un ordine di acquisto sul sito
            comportano l'accettazione delle Condizioni e delle Politiche di
            Protezione dei Dati adottate dal sito stesso ivi indicate.
            2. Le presenti Condizioni Generali di Vendita si applicano alla vendita\040
            di prodotti con esclusivo riferimento agli acquisti effettuati sul sito\040
            conformemente alle disposizioni della Parte III, Titolo III, Capo I,\040
            Codice del Consumo (D.lgs. n. 206/05 modificato dal D.lgs. n. 21/14 e D.lgs. 70/03)\040
            da parte di UID - MarketPlace.
            3. L'utente è tenuto, prima di accedere ai prodotti forniti dal sito,\040
            a leggere le presenti Condizioni Generali di Vendita che si intendono generalmente\040
            ed inequivocabilmente accettate al momento dell'acquisto.
            4. Si invita l'utente a scaricare e stampare una copia del modulo d'acquisto e\040
            delle presenti Condizioni Generali di Vendita i cui termini
            si riserva di modificare unilateralmente e senza alcun preavviso.
                        
            Art. 2 - Oggetto
            1. Le presenti Condizioni Generali di Vendita disciplinano l'offerta, l'inoltro e\040
            l'accettazione di ordini d'acquisto di prodotti su UID - MarketPlace, e non disciplinano,\040
            invece, la fornitura di servizi o la vendita di prodotti da parte di soggetti diversi dal venditore\040
            che siano presenti sul medesimo sito tramite link, banner o altri collegamenti ipertestuali.
            2. Prima di inoltrare ordini ed acquistare prodotti e servizi da soggetti diversi,\040
            suggeriamo di verificare le loro condizioni di vendita.
            """;
    String TOS_TITLE = "Condizioni generali di vendita";
}

