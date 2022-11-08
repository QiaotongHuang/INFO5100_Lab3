package model.Interface;

import java.util.ArrayList;
import java.util.HashSet;

public interface Admin {
    public ArrayList<String> checkCommunityByCity(String city);
    public ArrayList<String> checkCommunityByCity();
    public ArrayList<String> checkHospitalByCommunity(String community);
    public ArrayList<String> checkHospitalByCommunity();
    public HashSet<String> checkDoctorByHospital(String hospital);

}
