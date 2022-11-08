package view.patient;

import java.awt.event.*;
import model.Doctor;
import model.Encounter;
import model.Patient;
import model.VitalSigns;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import net.miginfocom.swing.*;
import view.doctor.LoginFram;

/**
 *
 * @author qiaotong
 */
public class PatientPanel extends javax.swing.JPanel {

    private Doctor doctor;
    private Patient patient;
    private HashMap<String, ArrayList<Doctor>> hospitals;
    private HashMap<String,ArrayList<String>> communityDirectory;
    private HashMap<String, ArrayList<String>> cityDirectory;
    private LoginFram loginFram;

    /**
     * Creates new form PatientPanel
     */
    public PatientPanel() {
        initComponents();
    }

    public PatientPanel(Patient patient, HashMap<String, ArrayList<Doctor>> hospitals, HashMap<String,ArrayList<String>> communityDirectory, HashMap<String, ArrayList<String>> cityDirectory){
        this.patient = patient;
        this.hospitals = hospitals;
        this.communityDirectory = communityDirectory;
        this.cityDirectory = cityDirectory;
        initComponents();
        initTable();
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        hospitals.keySet().forEach(one->{
            doctorArrayList.addAll(this.hospitals.get(one));
        });
        displayTable(doctorArrayList);
    }

    public PatientPanel(Patient patient, HashMap<String, ArrayList<Doctor>> hospitals, HashMap<String,ArrayList<String>> communityDirectory, HashMap<String, ArrayList<String>> cityDirectory, LoginFram loginFram){
        this.patient = patient;
        this.hospitals = hospitals;
        this.communityDirectory = communityDirectory;
        this.cityDirectory = cityDirectory;
        this.loginFram = loginFram;
        initComponents();
        initTable();
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        hospitals.keySet().forEach(one->{
            doctorArrayList.addAll(this.hospitals.get(one));
        });
        displayTable(doctorArrayList);
    }

    void displayTable(ArrayList<Doctor> doctorList){
        if(null == doctorList || doctorList.size() == 0){
            return;
        }
        DefaultTableModel model = (DefaultTableModel)doctorTable.getModel();
        model.setRowCount(0);
        doctorTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectDoctor();
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
        for(Doctor doctor : doctorList){
            String[] row = new String[3];
            row[0] = doctor.getName();
            row[1] = doctor.getHospital();
            model.addRow(row);
        }
    }

    private void selectDoctor(){
        int selectedRow = doctorTable.getSelectedRow();
        if(selectedRow >= 0){
            String name = (String)doctorTable.getValueAt(selectedRow, 0);
            hospitals.keySet().forEach(one->{
                hospitals.get(one).forEach(t->{
                    if(name.equals(t.getName())){
                        doctor = t;
                    }
                });
            });
        }
    }

    private void appointment(ActionEvent e) {
        // TODO add your code here
        if(null == doctor){
            JOptionPane.showMessageDialog(this,"Please select a doctor!");
            return;
        }
        Encounter encounter = new Encounter(new VitalSigns(), "");
        patient.getEncounterHistory().add(encounter);
        doctor.getPatientWaitingList().add(patient);
        JOptionPane.showMessageDialog(this,"Successful Appointment!");
    }

    private void search(ActionEvent e) {
        // TODO add your code here
        String text = searchText.getText();
        String doctorItem = this.searchItem.getSelectedItem().toString();
        ArrayList<Doctor> doctorAllList = new ArrayList<>();
        hospitals.keySet().forEach(one->{
            doctorAllList.addAll(hospitals.get(one));
        });
        ArrayList<Doctor> result = new ArrayList<>();
        switch (doctorItem){
            case "Doctor":
                for(Doctor d : doctorAllList){
                    if(d.getName().contains(text)){
                        result.add(d);
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Doctor!");
                }else {
                    displayTable(result);
                }
                break;
            case "Hospital":
                for(String hospital : hospitals.keySet()){
                    if(hospital.contains(text)){
                        result.addAll(hospitals.get(hospital));
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Doctor!");
                }else {
                    displayTable(result);
                }
                break;
            case "Community":
                ArrayList<String> hospitalList = new ArrayList<>();
                for(String community : communityDirectory.keySet()){
                    if(community.contains(text)){
                        hospitalList.addAll(communityDirectory.get(community));
                    }
                }
                if(hospitalList.size() > 0){
                    for(String hospital : hospitalList){
                        result.addAll(hospitals.get(hospital));
                    }
                }
                if(result.size() == 0){
                    JOptionPane.showMessageDialog(this,"Can Not Find Such Doctor!");
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
        doctorTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Doctor Name", "Hospital"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        searchItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor", "Hospital", "Community" }));
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - qiaotong huang
        quitButton = new JButton();
        scrollPane1 = new JScrollPane();
        doctorTable = new JTable();
        searchItem = new JComboBox();
        searchText = new JTextField();
        searchButton = new JButton();
        appointmentButton = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
        swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border
        . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog"
        , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder
        () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
        . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException
        ( ) ;} } );
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
            "[center]",
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
            "[]"));

        //---- quitButton ----
        quitButton.setText("Quit");
        quitButton.addActionListener(e -> quit(e));
        add(quitButton, "cell 12 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(doctorTable);
        }
        add(scrollPane1, "cell 0 2 13 1");
        add(searchItem, "cell 0 4 3 1");
        add(searchText, "cell 3 4 7 1");

        //---- searchButton ----
        searchButton.setText("Search");
        searchButton.addActionListener(e -> search(e));
        add(searchButton, "cell 11 4");

        //---- appointmentButton ----
        appointmentButton.setText("Appointment");
        appointmentButton.addActionListener(e -> appointment(e));
        add(appointmentButton, "cell 12 4");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - qiaotong huang
    private JButton quitButton;
    private JScrollPane scrollPane1;
    private JTable doctorTable;
    private JComboBox searchItem;
    private JTextField searchText;
    private JButton searchButton;
    private JButton appointmentButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
