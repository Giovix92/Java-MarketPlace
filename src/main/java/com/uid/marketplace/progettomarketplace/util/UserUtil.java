package com.uid.marketplace.progettomarketplace.util;

import com.uid.marketplace.progettomarketplace.Model.User;
import com.uid.marketplace.progettomarketplace.client.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class UserUtil {

    public static String getUserInfos(String email) throws Exception{
        AtomicReference<String> nome = new AtomicReference<>("");
        Client.getInstance().get("clienti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("clienti");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("email").equals(email)) {
                    User n = new User(obj.getString("email"), obj.getString("nome"), obj.getString("cognome"), obj.getString("indirizzo"), obj.getInt("saldo"));
                    nome.set(obj.getString("nome"));
                    Client.getInstance().setName(nome.toString());
                }
            }
        }, exc -> exc.printStackTrace());
        return Client.getInstance().getName();
    }
}


