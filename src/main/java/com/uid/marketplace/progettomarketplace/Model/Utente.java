package com.uid.marketplace.progettomarketplace.Model;

public class Utente {

    private static Utente instance = new Utente();

    private Utente() {}
    public static Utente getInstance() { return instance; }

    String id = null;
    String name = null;
    String surname = null;
    String address = null;
    String saldo = "0";

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getSaldo() {return saldo;}

    public void setSaldo(String saldo) {this.saldo = saldo;}

    public void setData(String id, String name, String surname, String address, String saldo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.saldo = saldo;
    }

    public void resetData() throws Exception {
        setId(null);
        setName(null);
        setSurname(null);
        setAddress(null);
        setSaldo("0");
    }
}
