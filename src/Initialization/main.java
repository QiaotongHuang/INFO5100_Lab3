package Initialization;

import controller.Admin.AdminDir;
import model.Doctor;
import model.Encounter;
import model.Patient;
import model.VitalSigns;
import view.admin.MainView;
import view.doctor.LoginFram;

import java.util.*;

public class main {


    public static void main(String[] args) {
        HashMap<String,ArrayList<String>> ComToPatients = new HashMap<>();
        ArrayList<Patient>  patients = new ArrayList<>();
        //<hospitalName, DoctorList>
        HashMap<String, ArrayList<Doctor>> hospitals = new HashMap<>();
        //<communityName, hospitalName>
        HashMap<String,ArrayList<String>> communityDirectory = new HashMap<>();
        //<cityName,communityName>
        HashMap<String, ArrayList<String>> cityDirectory = new HashMap<>();
        ArrayList<String> testHospital1 = new ArrayList<>();
        testHospital1.add("h1");
        testHospital1.add("h2");
        ArrayList<String> testHospital2 = new ArrayList<>();
        communityDirectory.put("community1",testHospital1);
        communityDirectory.put("community2",testHospital2);
        ArrayList<Doctor> testdoctor1 = new ArrayList<>();
        ArrayList<Doctor> testdoctor2 = new ArrayList<>();
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        Encounter encounter1 = new Encounter(new VitalSigns(), "");
        LinkedList<Encounter> encounterHistroy1 = new LinkedList<>();
        encounterHistroy1.add(encounter1);
        Encounter encounter2 = new Encounter(new VitalSigns(), "");
        LinkedList<Encounter> encounterHistroy2 = new LinkedList<>();
        encounterHistroy2.add(encounter2);
        Encounter encounter3 = new Encounter(new VitalSigns(), "");
        LinkedList<Encounter> encounterHistroy3 = new LinkedList<>();
        encounterHistroy3.add(encounter3);
        Patient patient1 = new Patient("city1", "community1", "p1", encounterHistroy1, "p1", "p1");
        Patient patient2 = new Patient("city1", "community1", "p2", encounterHistroy2, "p2", "p2");
        Patient patient3 = new Patient("city2", "community2", "p3", encounterHistroy3, "p3", "p3");
        patientArrayList.add(patient1);
        patientArrayList.add(patient2);
        patientArrayList.add(patient3);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        testdoctor1.add(new Doctor("d1","h1","d1","d1", patientArrayList));
        testdoctor1.add(new Doctor("d2","h1","d2","d2", patientArrayList));
        testdoctor1.add(new Doctor("d3","h1","d3","d3", patientArrayList));

        Encounter encounter4 = new Encounter(new VitalSigns(), "");
        LinkedList<Encounter> encounterHistroy4 = new LinkedList<>();
        encounterHistroy4.add(encounter4);
        LinkedList<Encounter> encounterHistroy5 = new LinkedList<>();
        Encounter encounter5 = new Encounter(new VitalSigns(), "");
        encounterHistroy5.add(encounter5);
        LinkedList<Encounter> encounterHistroy6 = new LinkedList<>();
        Encounter encounter6 = new Encounter(new VitalSigns(), "");
        encounterHistroy6.add(encounter6);
        Patient patient4 = new Patient("city1", "community1", "p4", encounterHistroy4, "p4", "p4");
        Patient patient5 = new Patient("city1", "community1", "p5", encounterHistroy5, "p5", "p5");
        Patient patient6 = new Patient("city2", "community2", "p6", encounterHistroy6, "p6", "p6");
        patientArrayList.add(patient4);
        patientArrayList.add(patient5);
        patientArrayList.add(patient6);
        ArrayList<String> comtop1 = new ArrayList<>();
        comtop1.add("p4");
        comtop1.add("p5");
        comtop1.add("p1");
        comtop1.add("p2");
        ComToPatients.put("community1",comtop1);
        ArrayList<String> comtop2 = new ArrayList<>();
        comtop2.add("p6");
        comtop2.add("p3");
        ComToPatients.put("community2",comtop2);
        patients.add(patient4);
        patients.add(patient5);
        patients.add(patient6);
        testdoctor2.add(new Doctor("d4","h2","d4","d4", patientArrayList));
        testdoctor2.add(new Doctor("d5","h2","d5","d5", patientArrayList));
        testdoctor2.add(new Doctor("d6","h2","d6","d6", patientArrayList));

        hospitals.put("h1",testdoctor1);
        hospitals.put("h2",testdoctor2);
        //initialize all the three login UIs
        AdminDir adminDir = new AdminDir();
        MainView mainView = new MainView(adminDir, patients, hospitals, communityDirectory, cityDirectory, ComToPatients);
        mainView.setVisible(true);

        LoginFram loginFram = new LoginFram(patients, hospitals, communityDirectory, cityDirectory, ComToPatients);
        loginFram.setVisible(true);
    }
}