/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author castor
 */
public class Debug {
    private String selectedPath;
    private int selectedExeIndex;
    Debug(ArrayList<ArrayList> projectExes, ArrayList allExes){
        System.out.println("Runing run config..");
        JDialog debugConfigDialog = new JDialog();
        Dimension dim = new Dimension(350, 380);
        Dimension dimPre = new Dimension(700, 700);
        //runConfigDialog.setMaximumSize(dim);
        debugConfigDialog.setMinimumSize(dim);
        debugConfigDialog.setPreferredSize(dimPre);
        debugConfigDialog.setTitle("Debug Configuration");
        JPanel runConfigPanel = new JPanel();
        JTextField lineNo = new JTextField();
        JScrollPane scrolBar = new JScrollPane();
        //BoxLayout bx = new BoxLayout(runConfigPanel,BoxLayout.Y_AXIS);
        runConfigPanel.setLayout(new GridBagLayout());
        debugConfigDialog.add(runConfigPanel);
        JLabel project = new JLabel("Project Location: ");
        JLabel arg = new JLabel("Line Number: ");
        JComboBox projectLoc = new JComboBox();
        projectLoc.addItem("select a project..");
        for (int i = 0; i < projectExes.size(); i++) {
            projectLoc.addItem(projectExes.get(i).get(0));
        }
        DefaultListModel allLocs = new DefaultListModel();
        allLocs.addElement("temp: ./a.out");
        allLocs.addElement("temp: mosquitto_passwrd.o");
        allLocs.addElement("temp: mosquitto_client_pub.o");
        for (int i = 0; i < allExes.size(); i++) {
            allLocs.addElement(allExes.get(i));
        }
        projectLoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String path = (String) e.getItem();
                    selectedPath = path;
                    System.out.println("state changed: " + path);
                    //get the arrayList
                    for (int i = 0; i < projectExes.size(); i++) {
                        if (path.equals(projectExes.get(i).get(0))) {
                            System.out.println("Found equal: " + projectExes.get(i).get(0));
                            allLocs.removeAllElements();
                            for (int j = 0; j < allExes.size(); j++) {
                                allLocs.addElement(allExes.get(j));
                            }
                            ArrayList<String> temp = projectExes.get(i);
                            for (int j = 1; j < temp.size(); j++) {
                                allLocs.addElement(temp.get(j).substring(temp.get(j).lastIndexOf('/') + 1, temp.get(j).length()));
                            }
                        }
                    }
                }
            }
        });
        JList listOfExe = new JList(allLocs);
        listOfExe.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();
                selectedExeIndex = list.getSelectedIndex();
            }
        });
        scrolBar.getViewport().add(listOfExe);
        JButton submit = new JButton("Debug");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When debug button clicked
            }

        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        runConfigPanel.add(project,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        runConfigPanel.add(projectLoc,gbc);
        gbc.gridx =0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        runConfigPanel.add(scrolBar,gbc);
        gbc.gridx =0;
        gbc.gridy = 2;
        runConfigPanel.add(arg,gbc);
        gbc.gridx =1;
        gbc.gridy = 2;
        //gbc.weightx = 1;
        //gbc.weighty = 1;
        runConfigPanel.add(lineNo,gbc);
        gbc.gridx =0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        runConfigPanel.add(submit,gbc);
        debugConfigDialog.setVisible(true);
    }
}
