package model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String hospital;
    private String account;
    private String password;
    private ArrayList<Patient> patientWaitingList;

    public Doctor() {
    }

    public Doctor(String name, String hospital, String account, String password) {
        this.name = name;
        this.hospital = hospital;
        this.account = account;
        this.password = password;
        patientWaitingList = new ArrayList<>();
    }

    public Doctor(String name, String hospital, String account, String password, ArrayList<Patient> patients) {
        this.name = name;
        this.hospital = hospital;
        this.account = account;
        this.password = password;
        this.patientWaitingList = patients;
    }

    public Doctor(String name, String hName) {
        this.name = name;
        this.hospital = hName;
        this.patientWaitingList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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

    public ArrayList<Patient> getPatientWaitingList() {
        return patientWaitingList;
    }

    public void setPatientWaitingList(ArrayList<Patient> patientWaitingList) {
        this.patientWaitingList = patientWaitingList;
    }
}
