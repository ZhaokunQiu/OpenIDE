/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static openide_temp.TextEditor.texteditor;

/**
 *
 * @author castor
 */
public class Debugger {

    private ArrayList<ArrayList> projectSources;
    private ArrayList<String> allCFiles;
    private ProcessCom gdb;
    JPanel buttonRibbon;
    JTabbedPane tabb;
    private String selectedPath;
    private int selectedExeIndex;
    private JEditorPane gdbOutput;
    private Highlighter highlighter;
    private JButton stepUp;
    private JButton stop;
    private JEditorPane editor;
    private JTextField printValue;
    private JButton printBtn;
    private JScrollPane debuggerComponent;
    private JMenuItem  debugConfig;

    public Debugger(ArrayList<ArrayList> projectSources, ArrayList<String> allCFiles, JPanel buttonRibbon, JTabbedPane tabb, IDEOperation ideOperation, JMenuItem debugConfig) {
        this.projectSources = projectSources;
        this.allCFiles = allCFiles;
        this.buttonRibbon = buttonRibbon;
        this.tabb = tabb;
        this.ideOperation = ideOperation;
        this.debugConfig = debugConfig;
        highlighter = new Highlighter();
        stepUp = new JButton();
        stop = new JButton();
        printBtn = new JButton();
        printValue = new JTextField();
        gdb = new ProcessCom();
        //initializing 
        stepUp.setIcon(new ImageIcon(getClass().getResource("/resources/next.png")));
        stepUp.setBorderPainted(false);
        stepUp.setFocusPainted(false);
        stepUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adding gdb next code here
                debugNext();
            }

        });

        stop.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
        stop.setBorderPainted(false);
        stop.setFocusPainted(false);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Shutting down debugger..");
                //buttonRibbon.re
                buttonRibbon.remove(stepUp);
                buttonRibbon.remove(stop);
                buttonRibbon.remove(printValue);
                buttonRibbon.remove(printBtn);
                System.out.println("Debugger shut down successfully");
                editor.getHighlighter().removeAllHighlights();
                debugConfig.setEnabled(true);
                texteditor.repaint();
            }
        });

        printValue.setColumns(5);
        printValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = printValue.getText();
                gdb.executeCommand(cmd);
                String output = gdb.readOutput();
                //System.out.println(output);
                gdbOutput.setText(gdbOutput.getText() + "\n"
                        + cmd + " " + output);
                tabb.setSelectedComponent(debuggerComponent);
            }
        });
        Debug();
    }

    private IDEOperation ideOperation;
    private JDialog debugConfigDialog;

    private void Debug() {
        System.out.println("Starting Debug configuration..");
        debugConfigDialog = new JDialog();
        Dimension dim = new Dimension(400, 380);
        Dimension dimPre = new Dimension(700, 700);
        debugConfigDialog.setMinimumSize(dim);
        debugConfigDialog.setPreferredSize(dimPre);
        debugConfigDialog.setTitle("Debug Configuration");
        JPanel runConfigPanel = new JPanel();
        JTextField lineNo = new JTextField();
        JScrollPane scrolBar = new JScrollPane();
        runConfigPanel.setLayout(new GridBagLayout());
        debugConfigDialog.add(runConfigPanel);
        JLabel project = new JLabel("Project Location: ");
        JLabel arg = new JLabel("Line Number: ");
        JComboBox projectLoc = new JComboBox();
        projectLoc.addItem("Local source files");
        for (int i = 0; i < projectSources.size(); i++) {
            projectLoc.addItem(projectSources.get(i).get(0));
        }
        DefaultListModel allLocs = new DefaultListModel();
        for (int i = 0; i < allCFiles.size(); i++) {
            allLocs.addElement(allCFiles.get(i));
        }
        projectLoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String path = (String) e.getItem();
                    selectedPath = path;
                    if (path.compareTo("Local source files") == 0) {
                        allLocs.removeAllElements();
                        for (int i = 0; i < allCFiles.size(); i++) {
                            allLocs.addElement(allCFiles.get(i));
                        }
                    } else {
                        for (int i = 0; i < projectSources.size(); i++) {
                            if (path.equals(projectSources.get(i).get(0))) {
                                allLocs.removeAllElements();
                                ArrayList<String> temp = projectSources.get(i);
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
        JButton submit = new JButton("Debug");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debug(projectSources, lineNo);
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
        runConfigPanel.add(lineNo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        runConfigPanel.add(submit, gbc);
        debugConfigDialog.setVisible(true);
    }

    public void debugNext() {
        gdb.executeCommand("next");
        System.out.println(gdb.readOutput());
        gdb.executeCommand("where");
        String output = gdb.readOutput();
        gdbOutput.setText(gdbOutput.getText() + "\n" + output);
        output = output.substring(output.lastIndexOf(":") + 1, output.length() - 1);
        System.out.println("line next: " + output);
        highlighter.hightLightLine(editor, Integer.parseInt(output));
    }

    private void debug(ArrayList<ArrayList> projectSources, JTextField lineNo) {
        int lineNumber = 0;
        String pathOfSource = "";
        System.out.println("Selected path = " + selectedPath);
        for (int i = 0; i < projectSources.size(); i++) {
            if (projectSources.get(i).get(0).equals(selectedPath)) {
                pathOfSource = (String) projectSources.get(i).get(selectedExeIndex + 1);
            }
        }
        if(selectedPath == null){
            pathOfSource = allCFiles.get(selectedExeIndex);
        }else if(selectedPath.compareTo("Local source files") == 0) {
            pathOfSource = allCFiles.get(selectedExeIndex);
        }
        //System.out.println("Debugging file : [" + pathOfSource+"]");

        String exceFileTemp = pathOfSource;
        gdb = new ProcessCom();
        File f1 = new File(pathOfSource + ".c");
        System.out.println("Debugging file : [" + f1.getAbsolutePath() + "]");
        System.out.println("Name: " + f1.getName());
        editor = ideOperation.openFile(f1);
        gdbOutput = new JEditorPane();
        debuggerComponent = new JScrollPane(gdbOutput);
        gdbOutput.setText("gdb started...");
        tabb.add(debuggerComponent);
        JButton close = new JButton("x");
        close.setForeground(Color.RED);
        close.setBorderPainted(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        gdbOutput.setContentType("text/java");
        gdbOutput.setEditable(false);
        pnlTab.add(new JLabel("Debugger"), gbc);
        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(close, gbc);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                JPanel parent = (JPanel) b.getParent();
                int i = tabb.indexOfTabComponent(parent);
                if (i != -1) {
                    tabb.remove(i);
                }
            }

        });

        tabb.setTabComponentAt(tabb.getTabCount() - 1, pnlTab);

        gdb.executeCommand("gdb " + exceFileTemp);
        gdbOutput.setText(gdbOutput.getText() + "\n" + gdb.readOutput());
        debugConfigDialog.setVisible(false);
        //setting all options visible;
        stop.setAlignmentX(Component.LEFT_ALIGNMENT);
        printValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        printBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(stepUp);
        buttonRibbon.add(stop);
        buttonRibbon.add(printValue);
        buttonRibbon.add(printBtn);
        texteditor.repaint();
        if (lineNo.getText().equalsIgnoreCase("")) {
            System.out.println("No line number provide");
            gdb.executeCommand("b main");
            String output = gdb.readOutput();
            System.out.println("$" + output);
            gdbOutput.setText(gdbOutput.getText() + "\n" + output);
            lineNumber = Integer.parseInt(output.substring(output.lastIndexOf("line") + 5, output.length() - 2));
            System.out.println("The line number " + lineNumber);

        } else {
            System.out.println("Start on line number = " + lineNo.getText());
            lineNumber = Integer.parseInt(lineNo.getText());
            gdb.executeCommand("b main");
            String output = gdb.readOutput();
            System.out.println("$" + output);
            gdbOutput.setText(gdbOutput.getText() + "\n" + output);
            System.out.println("$" + output);
        }
        gdb.executeCommand("run");
        String output = gdb.readOutput();
        System.out.println("$" + output);
        gdbOutput.setText(gdbOutput.getText() + "\n" + output);
        System.out.println("$" + output);
        highlighter.hightLightLine(editor, lineNumber);
        debugConfig.setEnabled(false);
    }

    protected void removeOptions() {

    }
}
