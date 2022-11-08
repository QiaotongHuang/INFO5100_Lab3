/*
 * Created by JFormDesigner on Mon Nov 07 16:03:00 CST 2022
 */

package view.doctor;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Doctor;
import model.Encounter;
import model.Patient;
import model.VitalSigns;
import net.miginfocom.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author qiaotong huang
 */
public class DoctorJPanel extends JPanel {
    private Doctor doctor;
    private HashMap<String, ArrayList<Doctor>> hospitals;
    private HashMap<String,ArrayList<String>> communityDirectory;
    private HashMap<String, ArrayList<String>> cityDirectory;
    private LoginFram loginFram;
    /**
     * Creates new form PatientPanel
     */
    public DoctorJPanel() {
        initComponents();
        displayTable(doctor.getPatientWaitingList());
    }

    public DoctorJPanel(HashMap<String, ArrayList<Doctor>> hospitals, HashMap<String,ArrayList<String>> communityDirectory, HashMap<String, ArrayList<String>> cityDirectory, Doctor doctor){
        this.hospitals = hospitals;
        this.communityDirectory = communityDirectory;
        this.cityDirectory = cityDirectory;
        this.doctor = doctor;
        initComponents();
        initTable();
        displayTable(doctor.getPatientWaitingList());
    }

    public DoctorJPanel(HashMap<String, ArrayList<Doctor>> hospitals, HashMap<String,ArrayList<String>> communityDirectory, HashMap<String, ArrayList<String>> cityDirectory, Doctor doctor, LoginFram loginFram){
        this.hospitals = hospitals;
        this.communityDirectory = communityDirectory;
        this.cityDirectory = cityDirectory;
        this.doctor = doctor;
        this.loginFram = loginFram;
        initComponents();
        initTable();
        displayTable(doctor.getPatientWaitingList());
    }

    void displayTable(ArrayList<Patient> patientList){
        if(null == patientList || patientList.size() == 0){
            return;
        }
        DefaultTableModel model = (DefaultTableModel)patientTable.getModel();
        model.setRowCount(0);
        patientTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        for(Patient patient : patientList){
            String[] row = new String[3];
            row[0] = patient.getName();
            row[1] = patient.getCity();
            row[2] = patient.getCommunity();
            model.addRow(row);
        }
    }

    private void rowClick(){
        int selectedRow = patientTable.getSelectedRow();
        if(selectedRow >= 0){
            displayPatient(doctor.getPatientWaitingList().get(selectedRow));
        }
    }

    void displayPatient(Patient patient){
        Encounter last = patient.getEncounterHistory().getLast();
        bodyTemperatureText.setText(last.getVitalSigns().getBodyTemperature()+"");
        pulseRateText.setText(last.getVitalSigns().getPulseRate()+"");
        diastolicPressureText.setText(last.getVitalSigns().getDiastolicPressure()+"");
        systolicPressureText.setText(last.getVitalSigns().getSystolicPressure()+"");
        diagnoseText.setText(last.getDiagnose());
    }

    private void diagnose(ActionEvent e) {
        // TODO add your code here
        int selectedRow = patientTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this,"please select a patient to diagnose!");
            return;
        }
        String bodyTemperature = bodyTemperatureText.getText();
        String pulseRate = pulseRateText.getText();
        String diastolicPressure = diastolicPressureText.getText();
        String systolicPressure = systolicPressureText.getText();
        String diagnose = diagnoseText.getText();
        if(bodyTemperature.isEmpty() || pulseRate.isEmpty() || diastolicPressure.isEmpty() || systolicPressure.isEmpty() ||
        "".equals(diagnose)){
            JOptionPane.showMessageDialog(this,"input can not be empty!");
            return;
        }
        double bodyTemperatureDouble;
        double pulseRateDouble;
        double diastolicPressureIDouble;
        double systolicPressureDouble;
        try{
            bodyTemperatureDouble = Double.parseDouble(bodyTemperature);
            pulseRateDouble = Double.parseDouble(pulseRate);
            diastolicPressureIDouble = Double.parseDouble(diastolicPressure);
            systolicPressureDouble = Double.parseDouble(systolicPressure);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(this,"the format of input is invalidate!");
            return;
        }
        if(bodyTemperatureDouble <= 0 || pulseRateDouble <= 0 || diastolicPressureIDouble <= 0 || systolicPressureDouble <= 0 ){
            JOptionPane.showMessageDialog(this,"the format of input is invalidate!");
            return;
        }
        Encounter last = doctor.getPatientWaitingList().get(selectedRow).getEncounterHistory().getLast();
        VitalSigns vitalSigns = new VitalSigns(bodyTemperatureDouble,pulseRateDouble,diastolicPressureIDouble,systolicPressureDouble);
        last.setVitalSigns(vitalSigns);
        last.setDiagnose(diagnose);
        //clear
        bodyTemperatureText.setText("");
        pulseRateText.setText("");
        diastolicPressureText.setText("");
        systolicPressureText.setText("");
        diagnoseText.setText("");
        JOptionPane.showMessageDialog(this,"Successful Diagnosis!");
    }

    private void search(ActionEvent e) {
        // TODO add your code here
        String text = searchText.getText();
        String patientItem = this.searchItem.getSelectedItem().toString();
        ArrayList<Patient> result = new ArrayList<>();
        switch (patientItem){
            case "Name":
                for(Patient patient : doctor.getPatientWaitingList()){
                    if(patient.getName().contains(text)){
                        result.add(patient);
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Patient!");
                }else {
                    displayTable(result);
                }
                break;
            case "City":
                for(Patient patient : doctor.getPatientWaitingList()){
                    if(patient.getCity().contains(text)){
                        result.add(patient);
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Patient!");
                }else {
                    displayTable(result);
                }
                break;
            case "Community":
                for(Patient patient : doctor.getPatientWaitingList()){
                    if(patient.getCommunity().contains(text)){
                        result.add(patient);
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Patient!");
                }else {
                    displayTable(result);
                }
                break;
            default:
                break;
        }
    }

    private void quit(ActionEvent e) {
        // TODO add your code here
        loginFram.getjSplitPane1().setRightComponent(loginFram.getjPanel2());
    }

    private void initTable(){
        patientTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Patient Name", "City", "Community"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        searchItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "City", "Community" }));
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - qiaotong huang
        quitButton = new JButton();
        scrollPane1 = new JScrollPane();
        patientTable = new JTable();
        searchItem = new JComboBox();
        searchText = new JTextField();
        searchButton = new JButton();
        diagnoseButton = new JButton();
        label1 = new JLabel();
        bodyTemperatureText = new JTextField();
        label5 = new JLabel();
        diagnoseText = new JTextField();
        label2 = new JLabel();
        pulseRateText = new JTextField();
        label3 = new JLabel();
        diastolicPressureText = new JTextField();
        label4 = new JLabel();
        systolicPressureText = new JTextField();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- quitButton ----
        quitButton.setText("Quit");
        quitButton.addActionListener(e -> quit(e));
        add(quitButton, "cell 37 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(patientTable);
        }
        add(scrollPane1, "cell 0 2 39 23");
        add(searchItem, "cell 0 25");
        add(searchText, "cell 2 25 17 1");

        //---- searchButton ----
        searchButton.setText("Search");
        searchButton.addActionListener(e -> search(e));
        add(searchButton, "cell 22 25");

        //---- diagnoseButton ----
        diagnoseButton.setText("Save Diagnose");
        diagnoseButton.addActionListener(e -> diagnose(e));
        add(diagnoseButton, "cell 30 25 9 1");

        //---- label1 ----
        label1.setText("bodyTemperature");
        add(label1, "cell 0 26");
        add(bodyTemperatureText, "cell 1 26 17 1");

        //---- label5 ----
        label5.setText("diagnose");
        add(label5, "cell 22 26");
        add(diagnoseText, "cell 23 26 16 1");

        //---- label2 ----
        label2.setText("pulseRate");
        add(label2, "cell 0 27");
        add(pulseRateText, "cell 1 27 17 1");

        //---- label3 ----
        label3.setText("diastolicPressure");
        add(label3, "cell 0 28");
        add(diastolicPressureText, "cell 1 28 17 1");

        //---- label4 ----
        label4.setText("systolicPressure");
        add(label4, "cell 0 29");
        add(systolicPressureText, "cell 1 29 17 1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - qiaotong huang
    private JButton quitButton;
    private JScrollPane scrollPane1;
    private JTable patientTable;
    private JComboBox searchItem;
    private JTextField searchText;
    private JButton searchButton;
    private JButton diagnoseButton;
    private JLabel label1;
    private JTextField bodyTemperatureText;
    private JLabel label5;
    private JTextField diagnoseText;
    private JLabel label2;
    private JTextField pulseRateText;
    private JLabel label3;
    private JTextField diastolicPressureText;
    private JLabel label4;
    private JTextField systolicPressureText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
