package controller.Admin;

import model.Interface.Admin;

import java.util.ArrayList;
import java.util.HashSet;

public class AdminImp implements Admin {
    private String login;
    private String password;

    public AdminImp(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ArrayList<String> checkCommunityByCity(String city) {
        return null;
    }

    @Override
    public ArrayList<String> checkCommunityByCity() {
        return null;
    }

    @Override
    public ArrayList<String> checkHospitalByCommunity(String community) {
        return null;
    }

    @Override
    public ArrayList<String> checkHospitalByCommunity() {
        return null;
    }

    @Override
    public HashSet<String> checkDoctorByHospital(String hospital) {
        return null;
    }
}
