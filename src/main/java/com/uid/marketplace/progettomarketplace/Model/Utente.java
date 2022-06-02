package com.uid.marketplace.progettomarketplace.Model;

import com.uid.marketplace.progettomarketplace.util.UserUtil;

public class Utente {

    private static Utente instance = new Utente();

    private Utente() {}
    public static Utente getInstance() { return instance; }

    String id = null;
    String name = null;
    String surname = null;
    String address = null;
    Integer saldo = 0;
    String role = null;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Integer getSaldo() {return saldo;}

    public void setSaldo(Integer saldo) {this.saldo = saldo;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public void getData(String email) throws Exception {
        this.name = UserUtil.getName(email);
        this.id = UserUtil.getID(email);
        this.role = UserUtil.getRole(email);
    }

    public void resetData() throws Exception {

        setId(null);
        setName(null);
        setSurname(null);
        setAddress(null);
        setSaldo(0);
        setRole(null);

    }



}
