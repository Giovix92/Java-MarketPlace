package com.uid.marketplace.progettomarketplace.util;

import com.uid.marketplace.progettomarketplace.Model.Utente;
import com.uid.marketplace.progettomarketplace.client.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class UserUtil {

    public static String getName(String email) throws Exception{
        AtomicReference<String> nome = new AtomicReference<>("");
        Client.getInstance().get("clienti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("clienti");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("email").equals(email)) {
                    nome.set(obj.getString("nome"));
                    Utente.getInstance().setName(nome.toString());
                }
            }
        }, exc -> exc.printStackTrace());
        return Utente.getInstance().getName();
    }

    public static String getID(String email) throws Exception{
        AtomicReference<String> id = new AtomicReference<>("");
        Client.getInstance().get("clienti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("clienti");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("email").equals(email)) {
                    id.set(obj.getString("element_id"));
                    Utente.getInstance().setId(id.toString());
                }
            }
        }, exc -> exc.printStackTrace());
        return Utente.getInstance().getId();
    }

    public static String getRole(String email) throws Exception{
        AtomicReference<String> role = new AtomicReference<>("");
        Client.getInstance().get("clienti", ref -> {
            JSONObject result = ref.result();
            JSONArray jsonArray = result.getJSONArray("clienti");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("email").equals(email)) {
                    role.set(obj.getString("admin"));
                    Utente.getInstance().setRole(role.toString());
                }
            }
        }, exc -> exc.printStackTrace());
        return Utente.getInstance().getRole();
    }

}


