package model;

import java.util.LinkedList;

public class Patient {
    String city;
    String community;
    String name;
    String account;
    String password;
    LinkedList<Encounter> encounterHistory;

    public Patient() {}

    public Patient(String city, String community, String name, LinkedList<Encounter> encounterHistory) {
        this.city = city;
        this.community = community;
        this.name = name;
        this.encounterHistory = encounterHistory;
    }

    public Patient(String city, String community, String name, LinkedList<Encounter> encounterHistory, String account, String password){
        this.city = city;
        this.community = community;
        this.name = name;
        this.encounterHistory = encounterHistory;
        this.account = account;
        this.password = password;
    }

    public Patient(String city, String community, String name) {
        this.city = city;
        this.community = community;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<Encounter> getEncounterHistory() {
        return encounterHistory;
    }

    public void setEncounterHistory(LinkedList<Encounter> encounterHistory) {
        this.encounterHistory = encounterHistory;
    }
}
