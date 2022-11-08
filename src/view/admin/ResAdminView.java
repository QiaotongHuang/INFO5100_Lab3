/*
 * Created by JFormDesigner on Thu Oct 27 23:39:22 EDT 2022
 */

package view.admin;

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

import controller.Admin.AdminDir;
import controller.Admin.AdminImp;
import controller.Admin.CommunityAdminImp;
import model.Doctor;
import model.Patient;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author unknown
 */
public class ResAdminView extends JFrame {
    private String[] colunms;
    private String[][] data;
    AdminImp curAdmin;
    HashMap<String,ArrayList<String>> communityDirectory;
    HashMap<String,ArrayList<String>> ComToPatients;
    HashMap<String, ArrayList<Doctor>> hospitals;
    AdminDir adminDir;
    List<Patient> patientlist;
    public ResAdminView(AdminImp curAdmin, HashMap<String,ArrayList<String>> communityDirectory, HashMap<String,ArrayList<String>>
            ComToPatients, HashMap<String, ArrayList<Doctor>> hospitals, AdminDir adminDir,List<Patient> patientlist) {
        this.curAdmin = curAdmin;
        this.communityDirectory = communityDirectory;
        this.ComToPatients = ComToPatients;
        this.hospitals = hospitals;
        this.adminDir = adminDir;
        this.patientlist = patientlist;
        initComponents();
        comName.addItem("");
        for (String com :
                communityDirectory.keySet()) {
            comName.addItem(com);
        }
        comName.setSelectedItem("");
    }
    public ResAdminView() {
        initComponents();

    }

    private void check(ActionEvent e) {
        // TODO add your code here
        String coName = comName.getSelectedItem().toString();
        if (coName.equals("")){
            JOptionPane.showMessageDialog(new JDialog(), ":please select one community");
            return;
        }
        new ComAdminFrame(new CommunityAdminImp(coName) ,communityDirectory.get(coName),ComToPatients.get(coName),hospitals,patientlist).setVisible(true);
    }

    private void addCom(ActionEvent e) {
        // TODO add your code here
        if (com.getText().length()==0){
            JOptionPane.showMessageDialog(new JDialog(), ":please enter one community");
            return;
        }
        String newCom = com.getText();
        if (communityDirectory.containsKey(newCom)){
            JOptionPane.showMessageDialog(new JDialog(), ":community existed");
            return;
        }else {
            ArrayList<String> temp = new ArrayList<>();
            communityDirectory.put(newCom,temp);
            comName.addItem(newCom);
            comName.setSelectedItem("");
        }
    }

    private void removeCom(ActionEvent e) {
        // TODO add your code here
        if (deleteCom.getText().length()==0){
            JOptionPane.showMessageDialog(new JDialog(), ":please enter one community");
            return;
        }
        String remove = deleteCom.getText();
        if (!communityDirectory.containsKey(remove)){
            JOptionPane.showMessageDialog(new JDialog(), ":community not existed");
            return;
        }else {
            communityDirectory.remove(remove);
            comName.removeItem(remove);
            comName.setSelectedItem("");
        }
    }

    private void removeAdmin(ActionEvent e) {
        // TODO add your code here
        if (deleteAdmin.getText().length()==0){
            JOptionPane.showMessageDialog(new JDialog(), ":please enter one admin");
            return;
        }
        String remove = deleteAdmin.getText();
        if (!adminDir.getCommunityAdmins().containsKey(remove)){
            JOptionPane.showMessageDialog(new JDialog(), ":community admin not existed");
            return;
        }
        adminDir.getCommunityAdmins().remove(remove);
        JOptionPane.showMessageDialog(new JDialog(), ":delete successfully");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("form");
        comName = new JComboBox();
        button1 = new JButton();
        label1 = new JLabel();
        addCom = new JButton();
        removeCom = new JButton();
        com = new JTextField();
        deleteCom = new JTextField();
        removeAdmin = new JButton();
        deleteAdmin = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- button1 ----
        button1.setText(bundle.getString("ResAdminView.button1.text"));
        button1.addActionListener(e -> check(e));

        //---- label1 ----
        label1.setText(bundle.getString("ResAdminView.label1.text"));

        //---- addCom ----
        addCom.setText(bundle.getString("ResAdminView.addCom.text"));
        addCom.addActionListener(e -> addCom(e));

        //---- removeCom ----
        removeCom.setText(bundle.getString("ResAdminView.removeCom.text"));
        removeCom.addActionListener(e -> removeCom(e));

        //---- removeAdmin ----
        removeAdmin.setText(bundle.getString("ResAdminView.removeAdmin.text"));
        removeAdmin.addActionListener(e -> removeAdmin(e));

        //---- label4 ----
        label4.setText(bundle.getString("ResAdminView.label4.text"));

        //---- label5 ----
        label5.setText(bundle.getString("ResAdminView.label5.text"));

        //---- label6 ----
        label6.setText(bundle.getString("ResAdminView.label6.text"));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addGap(45, 45, 45)
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(comName, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                .addComponent(removeAdmin)
                                .addGap(47, 47, 47)
                                .addComponent(label4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteAdmin, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(addCom)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label6)
                                .addGap(18, 18, 18)
                                .addComponent(com, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                .addComponent(removeCom)
                                .addGap(12, 12, 12)
                                .addComponent(label5)
                                .addGap(22, 22, 22)
                                .addComponent(deleteCom, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(226, Short.MAX_VALUE))
        );
        contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {addCom, removeAdmin, removeCom});
        contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {com, deleteAdmin, deleteCom});
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(comName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(56, 56, 56)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addCom)
                        .addComponent(com, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6))
                    .addGap(45, 45, 45)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(removeCom)
                        .addComponent(deleteCom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5))
                    .addGap(36, 36, 36)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(removeAdmin)
                        .addComponent(label4)
                        .addComponent(deleteAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(130, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox comName;
    private JButton button1;
    private JLabel label1;
    private JButton addCom;
    private JButton removeCom;
    private JTextField com;
    private JTextField deleteCom;
    private JButton removeAdmin;
    private JTextField deleteAdmin;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
