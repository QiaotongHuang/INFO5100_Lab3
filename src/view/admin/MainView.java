/*
 * Created by JFormDesigner on Mon Oct 24 00:14:55 EDT 2022
 */

package view.admin;

import java.awt.event.*;
import controller.Admin.AdminDir;
import controller.Admin.AdminImp;
import controller.Admin.CommunityAdminImp;
import model.Doctor;
import model.Patient;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class MainView extends JFrame {
    AdminDir adminDir;
    List<Patient> patientList;
    HashMap<String, ArrayList<Doctor>> hospitals;
    HashMap<String,ArrayList<String>> communityDirectory;
    HashMap<String, ArrayList<String>> cityDirectory;
    HashMap<String,ArrayList<String>> ComToPatients;
    public MainView(AdminDir adminDir, List<Patient> patientList,
                    HashMap<String, ArrayList<Doctor>> hospitals, HashMap<String,ArrayList<String>> communityDirectory,
                    HashMap<String, ArrayList<String>> cityDirectory,HashMap<String,ArrayList<String>> ComToPatients) {
        initComponents();
        this.adminDir = adminDir;
        this.patientList = patientList;
        this.communityDirectory = communityDirectory;
        this.hospitals = hospitals;
        this.cityDirectory = cityDirectory;
        this.ComToPatients = ComToPatients;
    }
    private boolean check(){
        if (password.getText().length()==0||
                login.getText().length()==0)
            return false;
        return true;
    }
    private boolean checkRegister(){
        return adminDir.getAdmins().containsKey(login.getText())||adminDir.getCommunityAdmins().containsKey(login.getText());
    }

    private boolean checkLogin(){
        return adminDir.getAdmins().containsKey(login.getText())||adminDir.getCommunityAdmins().containsKey(login.getText());
    }

    private void claer(){
        login.setText("");
        password.setText("");
        ComAdmin.setSelected(false);
        ResAdmin.setSelected(false);
    }
    private void login(ActionEvent e) {
        if (!check()){
            JOptionPane.showMessageDialog(new JDialog(), ":should enter login and password");
            return;
        }
        if (!checkLogin()){
            JOptionPane.showMessageDialog(new JDialog(), ":admin not existed");
            return;
        }
        if (!ComAdmin.isSelected()&&!ResAdmin.isSelected()){
            JOptionPane.showMessageDialog(new JDialog(), ":should select a role");
            return;
        }
        if (ComAdmin.isSelected()){
            if (adminDir.getCommunityAdmins().get(login.getText()).getPassword().equals(password.getText())){
                //load community admin page
                CommunityAdminImp curAdmin = adminDir.getCommunityAdmins().get(login.getText());
                new ComAdminFrame(curAdmin,communityDirectory.get(curAdmin.getCurCom()),ComToPatients.get(curAdmin.getCurCom()),hospitals,patientList).setVisible(true);
            }else {
                JOptionPane.showMessageDialog(new JDialog(), ":password wrong");
                return;
            }
        }else {
            if (adminDir.getAdmins().get(login.getText()).getPassword().equals(password.getText())){
                //load resource admin page
                AdminImp curAdmin = adminDir.getAdmins().get(login.getText());
                new ResAdminView(curAdmin,communityDirectory,ComToPatients,hospitals,adminDir,patientList).setVisible(true);
            }else {
                JOptionPane.showMessageDialog(new JDialog(), ":password wrong");
                return;
            }
        }
    }

    private void register(ActionEvent e) {
        if (!check()){
            JOptionPane.showMessageDialog(new JDialog(), ":should enter login and password");
            return;
        }
        // TODO add your code here
        if (checkRegister()){
            JOptionPane.showMessageDialog(new JDialog(), ":admin existed");
            return;
        }
        if (!ComAdmin.isSelected()&&!ResAdmin.isSelected()){
            JOptionPane.showMessageDialog(new JDialog(), ":should select a role");
            return;
        }
        if (ComAdmin.isSelected()){
            adminDir.addCommunityAdmin(login.getText(),password.getText(),community.getText());
            JOptionPane.showMessageDialog(new JDialog(), ":register successful");
            claer();
        }else {
            adminDir.addAdmin(login.getText(),password.getText());
            JOptionPane.showMessageDialog(new JDialog(), ":register successful");
            claer();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("form");
        label1 = new JLabel();
        label2 = new JLabel();
        login = new JTextField();
        password = new JPasswordField();
        ResAdmin = new JRadioButton();
        ComAdmin = new JRadioButton();
        label3 = new JLabel();
        loginButton = new JButton();
        registerButton = new JButton();
        label4 = new JLabel();
        community = new JTextField();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText(bundle.getString("MainView.label1.text"));

        //---- label2 ----
        label2.setText(bundle.getString("MainView.label2.text"));

        //---- ResAdmin ----
        ResAdmin.setText(bundle.getString("MainView.ResAdmin.text"));

        //---- ComAdmin ----
        ComAdmin.setText(bundle.getString("MainView.ComAdmin.text"));

        //---- label3 ----
        label3.setText(bundle.getString("MainView.label3.text"));

        //---- loginButton ----
        loginButton.setText(bundle.getString("MainView.loginButton.text"));
        loginButton.addActionListener(e -> login(e));

        //---- registerButton ----
        registerButton.setText(bundle.getString("MainView.registerButton.text"));
        registerButton.addActionListener(e -> register(e));

        //---- label4 ----
        label4.setText(bundle.getString("MainView.label4.text"));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(192, 192, 192)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(community, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 84, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(label2))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                .addComponent(password))
                            .addContainerGap(316, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(ResAdmin)
                                .addComponent(loginButton)
                                .addComponent(label3))
                            .addGap(43, 43, 43)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(registerButton)
                                    .addGap(0, 426, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(ComAdmin)
                                    .addContainerGap(383, Short.MAX_VALUE))))))
        );
        contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {label1, label2});
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(58, 58, 58)
                    .addComponent(label3)
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ResAdmin)
                        .addComponent(ComAdmin))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(community, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginButton)
                        .addComponent(registerButton))
                    .addContainerGap(110, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- roleSelection ----
        var roleSelection = new ButtonGroup();
        roleSelection.add(ResAdmin);
        roleSelection.add(ComAdmin);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField login;
    private JPasswordField password;
    private JRadioButton ResAdmin;
    private JRadioButton ComAdmin;
    private JLabel label3;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel label4;
    private JTextField community;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
