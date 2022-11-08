/*
 * Created by JFormDesigner on Mon Oct 24 17:21:48 EDT 2022
 */

package view.admin;

import java.awt.event.*;
import java.util.*;
import model.Patient;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author unknown
 */
public class displayInfo extends JFrame {
    //ArrayList<String> patients;
    private String[] colunms;
    private String[][] data;
    private String curCom;
    Pattern digitp = Pattern.compile("^[-\\+]?[\\d]*$");
    List<Patient> patientList;
    public displayInfo( String curCom, List<Patient> patientList) {
//        this.patients =patients;
        this.curCom = curCom;
        this.patientList = patientList;
        initComponents();
        preparetable();
    }

    private void preparetable(){
        colunms = new String[]{"Community","patient name"};
        data = new String[patientList.size()][colunms.length];
        int index = 0;
        for (Patient patient:
                patientList) {
                    data[index][0] = curCom;
                    data[index][1] = patient.getName();
                    index++;
        }
        DefaultTableModel model = new DefaultTableModel(data,colunms);
        table1 = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane1.setViewportView(table1);
    }

    private void delete(ActionEvent e) {
        // TODO add your code here
        int selected_row = table1.getSelectedRow();
        if (selected_row < 0) {
            JOptionPane.showMessageDialog(new JDialog(), ":please select one row to delete");
            return;
        }
        patientList.remove(selected_row);
        preparetable();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("form");
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        delete = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        //---- delete ----
        delete.setText(bundle.getString("displayInfo.delete.text"));
        delete.addActionListener(e -> delete(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 787, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(delete)))
                    .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(delete)
                    .addGap(0, 8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
