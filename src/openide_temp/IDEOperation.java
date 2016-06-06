/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import static openide_temp.TextEditor.OutputPanel;

/**
 *
 * @author castor
 */
public class IDEOperation {

    private JTabbedPane tabb;
    private ArrayList<String> allCFiles, allExes;
    private ExecuteShellComand comand;
    private JLabel statusMsg;
    private JEditorPane editor;

    IDEOperation(JTabbedPane tab, ArrayList allC, ExecuteShellComand cmd, ArrayList allE, JLabel msg) {
        tabb = tab;
        allCFiles = allC;
        comand = cmd;
        allExes = allE;
        statusMsg = msg;
    }

    public void compile(String source, String outfile) {
        String exceFile = "";
        boolean compileFile = true;
        System.out.println("Compiling...");
        String out = "", filePath = "";
        OutputPanel.removeAll();
        JPanel pnl = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
        JLabel lbl = (JLabel) pnl.getComponent(2);
        if (lbl.getText().compareTo("false") == 0) {
            save();
        }
        if (source != null) {
            System.out.println("Compiling source file " + source);
            out = outfile;
            filePath = source;
            System.out.println("Excutable file stored at loc: " + out);
        } else if (!(tabb.getComponentCount() == 0 || tabb.getTitleAt(tabb.getSelectedIndex()).equals("Output"))) {
            System.out.println("Compiling tab file");
            JPanel p = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
            JLabel path = (JLabel) p.getComponent(1);
            filePath = path.getText();
            out = filePath.substring(0, filePath.length() - 2).replace(" ", "\\ ");
            String exceFileTemp = filePath.substring(0, filePath.lastIndexOf('/') + 1) + "./" + filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length() - 2);
            exceFileTemp = exceFileTemp.replace(" ", "\\");
            exceFile = exceFileTemp.replace(" ", "\\ ");
            System.out.println("Compiling source file " + filePath);
            System.out.println("Excutable file stored at loc: " + exceFile);
        } else {
            compileFile = false;
        }
        final String exceFileTemp = exceFile;
        if (compileFile) {
            final String outtemp = out;
            final String filePathTemp = filePath;
            System.out.println("Ready for compiling...");
            System.out.println("Compilation output: ");
            changeStatus("Compiling", true);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String arr[] = {"cc", filePathTemp, "-o", outtemp, "-g", "-g3"};
                    String output = comand.Execute(arr);
                    if (output.equals("")) {
                        System.out.println("File compiled without errors.");
                        statusMsg.setText("Status: Code compile without any errors.");
                        if (!allCFiles.contains(outtemp.replace(" ", "\\ "))) {
                            allCFiles.add(outtemp.replace(" ", "\\ "));
                        }
                        if (!allExes.contains(outtemp.replace(" ", "\\ "))) {
                            allExes.add(outtemp.replace(" ", "\\ "));
                        }
                        JPanel pnl = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
                        JLabel lbl = (JLabel) pnl.getComponent(3);
                        lbl.setText("true");
                        lbl = (JLabel) pnl.getComponent(4);
                        lbl.setText(outtemp);
                        lbl = (JLabel) pnl.getComponent(5);
                        lbl.setText(exceFileTemp);

                    } else {
                        //Adding output panel
                        System.out.println("Error: file compiled with one or more errors");
                        JButton close = new JButton("x");
                        close.setForeground(Color.RED);
                        close.setBorderPainted(false);
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.weightx = 1;
                        JPanel pnlTab = new JPanel(new GridBagLayout());
                        pnlTab.setOpaque(false);
                        JLabel title = new JLabel("Output:");
                        pnlTab.add(title, gbc);
                        gbc.gridx++;
                        gbc.weightx = 0;
                        pnlTab.add(close, gbc);
                        tabb.add(OutputPanel);
                        int index = tabb.indexOfComponent(OutputPanel);
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

                        tabb.setTabComponentAt(index, pnlTab);
                        close.setName("" + index);
                        tabb.setSelectedIndex(index);
                    }
                    changeStatus("Status: Compiling done!", false);
                }
            });
            t.start();
        }
    }

    private void changeStatus(String text, boolean flag) {
        if (text != null) {
            statusMsg.setText(text);
        }
        if (flag) {
            ImageIcon img = new ImageIcon("img/loading.gif");
            System.out.println(img.getIconHeight());
            statusMsg.setText(text);
            statusMsg.setIcon(img);
        } else {
            statusMsg.setText(statusMsg.getText());
            statusMsg.setIcon(null);
        }
    }

    public void save() {
        int i = tabb.getSelectedIndex();
        if (!(tabb.getTabCount() == 0) && !(tabb.getTitleAt(i).equals("Output"))) {
            try {

                JPanel p = (JPanel) tabb.getTabComponentAt(i);
                JLabel saveStatus = (JLabel) p.getComponent(2);
                if (saveStatus.getText().compareToIgnoreCase("false") == 0) {
                    saveStatus.setText("true");
                    JLabel title = (JLabel) p.getComponent(0);
                    String f = title.getText();
                    f = f.replace("*", "");
                    JLabel path = (JLabel) p.getComponent(1);
                    JScrollPane s = (JScrollPane) tabb.getComponentAt(i);
                    JViewport viewport = s.getViewport();
                    JEditorPane a = (JEditorPane) viewport.getView();
                    File file = new File(path.getText());
                    System.out.println("Saving file [" + path.getText() + "]");
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(a.getText());
                        writer.flush();
                    }
                    title.setText(f);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File saved");

        }
    }

    public JEditorPane openFile(File f) {
        String PathTemp = f.getAbsolutePath();
        System.out.println("Checking if file [" + PathTemp + "] is open");
        boolean fileOpen = false;
        int loc = 0;

        for (int i = 0; i < tabb.getComponentCount() - 1; i++) {
            
                JPanel panel = (JPanel) tabb.getTabComponentAt(i);
                if (panel.getComponentCount() > 2) {
                JLabel p = (JLabel) panel.getComponent(1);
                String pathText = p.getText();
                if (pathText.charAt(0) == '*') {
                    pathText = pathText.substring(1);
                }
                if (PathTemp.compareTo(pathText) == 0) {
                    fileOpen = true;
                    loc = i;
                    JScrollPane s = (JScrollPane) tabb.getComponentAt(i);
                    JViewport viewport = s.getViewport();
                    editor = (JEditorPane) viewport.getView();
                    System.out.println("Editor: " + editor.toString());
                }
            }
        }
        if (fileOpen) {
            System.out.println("file is open at tab index " + loc);
            tabb.setSelectedIndex(loc);
        } else {
            System.out.println("Opening file in a new tab");
            System.out.println("Opening a new file");
            File fileToOpen = null;
            JEditorPane codeEditor = new JEditorPane();
            editor = codeEditor;
            JScrollPane scroll = new JScrollPane(codeEditor);
            codeEditor.setContentType("text/java");
            String code = null;
            BufferedReader br = null;
            try {
                fileToOpen = f;
                br = new BufferedReader(new FileReader(fileToOpen.getAbsolutePath()));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    code = sb.toString();

                } catch (IOException ex) {
                    //Logger.getLogger(TextEditor.class
                    // .getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();

                    } catch (IOException ex) {
                        //Logger.getLogger(TextEditor.class
                        // .getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(TextEditor.class
                // .getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (br != null) {
                        br.close();

                    }
                } catch (IOException ex) {
                    //Logger.getLogger(TextEditor.class
                    //  .getName()).log(Level.SEVERE, null, ex);
                }
            }
            scroll.setPreferredSize(new Dimension(260, 85));

            scroll.setMaximumSize(new Dimension(2147483647, 2147483647));
            scroll.setMinimumSize(new Dimension(0, 17));

            codeEditor.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(DocumentEvent de) {
                    JPanel p = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
                    JLabel saveStatus = (JLabel) p.getComponent(2);
                    saveStatus.setText("false");
                    changeTitle();

                }

                @Override
                public void insertUpdate(DocumentEvent de) {
                    JPanel p = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
                    JLabel saveStatus = (JLabel) p.getComponent(2);
                    saveStatus.setText("false");
                    changeTitle();
                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    JPanel p = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
                    JLabel saveStatus = (JLabel) p.getComponent(2);
                    saveStatus.setText("false");
                    changeTitle();
                }

                public void changeTitle() {
                    System.out.println("File changed");
                    int i = tabb.getSelectedIndex();
                    JPanel panel = (JPanel) tabb.getTabComponentAt(i);
                    JLabel title1 = (JLabel) panel.getComponent(0);
                    JLabel saveStatus = (JLabel) panel.getComponent(2);
                    if (saveStatus.getText().compareToIgnoreCase("false") == 0) {
                        System.out.println("No star");
                        String title = title1.getText();
                        if (title.charAt(0) != '*') {
                            System.out.println("adding star..");
                            title = "*" + title;
                            title1.setText(title);
                        }
                    }
                }
            });

            tabb.add(scroll);
            int i = tabb.getTabCount();
            tabb.setSelectedIndex(i - 1);
            JPanel pnlTab = new JPanel(new GridBagLayout());
            pnlTab.setOpaque(false);
            JLabel title = new JLabel(fileToOpen.getName());
            JButton close = new JButton("x");
            close.setBorderPainted(false);
            close.setForeground(Color.red);
            close.setFocusPainted(false);
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
            System.out.println("Opening file [" + fileToOpen + "]");
            System.out.println("File name: " + fileToOpen.getName());
            System.out.println("Tab count = " + tabb.getTabCount());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            //adding the title
            pnlTab.add(title, gbc);
            JLabel path = new JLabel(fileToOpen.getAbsolutePath());
            path.setVisible(false);
            //adding path of the file
            gbc.gridx++;
            pnlTab.add(path, gbc);
            JLabel saveStatus = new JLabel("false");
            saveStatus.setVisible(false);
            //adding flag to check if file is saved
            gbc.gridx++;
            pnlTab.add(saveStatus, gbc);
            JLabel isCompiled = new JLabel("false");
            isCompiled.setVisible(false);
            //adding flag to check if file is compiled
            gbc.gridx++;
            pnlTab.add(isCompiled, gbc);
            JLabel outputFile = new JLabel("null");
            outputFile.setVisible(false);
            //adding location og the output file
            gbc.gridx++;
            pnlTab.add(outputFile, gbc);
            JLabel exeLink = new JLabel("null");
            exeLink.setVisible(false);
            //adding location of the executable link
            gbc.gridx++;
            pnlTab.add(exeLink, gbc);
            gbc.gridx++;
            gbc.weightx = 0;
            pnlTab.add(close, gbc);
            tabb.setTabComponentAt(i - 1, pnlTab);
            codeEditor.setText(code);
            //revalidate();
        }
        return editor;
    }
    
        public void run() {
        System.out.println("Running file...");
        save();
        int selIndex = tabb.getSelectedIndex();
        JPanel p = (JPanel) tabb.getTabComponentAt(selIndex);
        final JLabel title1 = (JLabel) p.getComponent(0);
        System.out.println(title1.getText());
        if (title1.getText().equalsIgnoreCase("makefile")) {
            System.out.println("Running a make file");
            if (!(tabb.getComponentCount() == 0 || tabb.getTitleAt(selIndex).equals("Output"))) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JLabel path = (JLabel) p.getComponent(1);
                        String filePath = path.getText();
                        System.out.println("Makefile [" + filePath + "]");
                        String theCommand = "";
                        String location = filePath.substring(0, filePath.toLowerCase().indexOf("makefile") - 1);
                        theCommand += "cd " + location + "\n";
                        theCommand += "make uninstall" + "\n";
                        theCommand += "make clean" + "\n";
                        theCommand += "make" + "\n";
                        theCommand += "make install" + "\n";
                        theCommand += "echo 'Done building...'" + "\n";
                        System.out.println("Saving shellscript for make at /home/castor/Desktop/scriptMake.sh");
                        File scriptFile = new File("/home/castor/Desktop/scriptMake.sh");

                        try {
                            scriptFile.createNewFile();
                            BufferedWriter bf = new BufferedWriter(new FileWriter(scriptFile));
                            bf.write(theCommand);
                            bf.close();
                        } catch (IOException ex) {
                            // Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Ready to run make");
                                String arr[] = {"xterm", "-hold", "-e", "sh", "/home/castor/Desktop/scriptMake.sh"};
                                System.out.println("Make executed successfully");
                                comand.Execute(arr);
                            }
                        });
                        t.start();
                    }
                });
                t.start();
            }
        } else {
            //compiling file
            JPanel pnl = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
            JLabel lbl = (JLabel) pnl.getComponent(3);
            if (lbl.getText().compareTo("true") != 0) {
                compile(null, null);
            }
            lbl = (JLabel) pnl.getComponent(5);
            final String exe = lbl.getText();
            System.out.println("File compiled successfully");
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Ready to run exe");
                    String arr[] = {"xterm", "-hold", "-e", exe};
                    comand.Execute(arr);
                }
            });
            t1.start();
        }
    }
}
