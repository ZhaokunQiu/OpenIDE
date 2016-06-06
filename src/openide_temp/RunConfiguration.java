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
public class RunConfiguration {

    private ArrayList<ArrayList> projectExes;
    private ArrayList<String> allExes;
    private ExecuteShellComand comand;
    private String selectedPath;
    private int selectedExeIndex;
    
    public RunConfiguration(ArrayList<ArrayList> projectExes, ArrayList<String> allExes, ExecuteShellComand comand) {
        this.projectExes = projectExes;
        this.allExes = allExes;
        this.comand = comand;
        runConfiguration();
    }

    private void runConfiguration() {
        System.out.println("Run configuration started");
        JDialog runConfigDialog = new JDialog();
        Dimension dim = new Dimension(400, 380);
        Dimension dimPre = new Dimension(700, 700);
        runConfigDialog.setMinimumSize(dim);
        runConfigDialog.setPreferredSize(dimPre);
        runConfigDialog.setTitle("Run Configuration");
        JPanel runConfigPanel = new JPanel();
        JTextField args = new JTextField();
        JScrollPane scrolBar = new JScrollPane();
        runConfigPanel.setLayout(new GridBagLayout());
        runConfigDialog.add(runConfigPanel);
        JLabel project = new JLabel("Project Location: ");
        JLabel arg = new JLabel("Arguments: ");
        JComboBox projectLoc = new JComboBox();
        projectLoc.addItem("All local files");
        for (int i = 0; i < projectExes.size(); i++) {
            projectLoc.addItem(projectExes.get(i).get(0));
        }
        DefaultListModel allLocs = new DefaultListModel();
        for (int i = 0; i < allExes.size(); i++) {
            allLocs.addElement(allExes.get(i));
        }
        projectLoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String path = (String) e.getItem();
                    selectedPath = path;
                    System.out.println("Path selected ["+selectedPath+"]");
                    if (path.compareTo("All local files") == 0) {
                        allLocs.removeAllElements();
                        for (int i = 0; i < allExes.size(); i++) {
                            allLocs.addElement(allExes.get(i));
                        }

                    } else {
                        for (int i = 0; i < projectExes.size(); i++) {
                            if (path.equals(projectExes.get(i).get(0))) {
                                // System.out.println("Found equal: " + projectExes.get(i).get(0));
                                allLocs.removeAllElements();
                                ArrayList<String> temp = projectExes.get(i);
                                for (int j = 1; j < temp.size(); j++) {
                                    allLocs.addElement(temp.get(j).substring(temp.get(j).lastIndexOf('/') + 1, temp.get(j).length()));
                                }
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
        JButton submit = new JButton("Run");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Add here code...");
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String exceFile = "";
                        String parameters = args.getText();
                        StringTokenizer st1 = new StringTokenizer(parameters);
                        int count = st1.countTokens();
                        count += 4;
                        String[] ar = new String[count];
                        ar[0] = "xterm";
                        ar[1] = "-hold";
                        ar[2] = "-e";
                        //wron
                        for (int i = 0; i < projectExes.size(); i++) {
                            if (projectExes.get(i).get(0).equals(selectedPath)) {
                                exceFile = (String) projectExes.get(i).get(selectedExeIndex + 1);
                            }
                        }
                        System.out.println("Running file [" + exceFile + "]");
                        ar[3] = exceFile;
                        int i = 4;
                        while (st1.hasMoreTokens()) {
                            ar[i] = st1.nextToken();
                            i++;
                        }
                        comand.Execute(ar);

                    }
                });
                t1.start();
                runConfigDialog.setVisible(false);

            }

        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        runConfigPanel.add(project, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        runConfigPanel.add(projectLoc, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        runConfigPanel.add(scrolBar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        runConfigPanel.add(arg, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.weightx = 1;
        //gbc.weighty = 1;
        runConfigPanel.add(args, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        runConfigPanel.add(submit, gbc);
        runConfigDialog.setVisible(true);

    }
}
