/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import static openide_temp.TextEditor.OutputPanel;
import static openide_temp.TextEditor.compiledIndex;

/**
 *
 * @author castor
 */
public class SaveFile {

    private JTabbedPane tabb;
    private String exceFile;
    private ArrayList<String> allCFiles, allExes;
    private ExecuteShellComand comand;
    private JLabel statusMsg;

    SaveFile(JTabbedPane tab, String exe, ArrayList allC, ExecuteShellComand cmd, ArrayList allE, JLabel msg) {
        tabb = tab;
        exceFile  = exe;
        allCFiles = allC;
        comand = cmd;
        allExes = allE;
        statusMsg = msg;
    }

    public void compile(String source, String outfile) {
        boolean compileFile = true;
        System.out.println("Compiling...");
        String out = "", filePath = "";
        OutputPanel.removeAll();
        String f;
        JPanel pnl = (JPanel) tabb.getTabComponentAt(tabb.getSelectedIndex());
        JLabel lbl = (JLabel) pnl.getComponent(2);
        if (lbl.getText().compareTo("false") == 0) {
            save();
        } 
        int i = tabb.getSelectedIndex();
        if (source != null) {
            System.out.println("Compiling source file " + source);
            out = outfile;
            filePath = source;
            System.out.println("Excutable file stored at loc: " + out);
        } else if (!(tabb.getComponentCount() == 0 || tabb.getTitleAt(i).equals("Output"))) {
            System.out.println("Compiling tab file");
            i = tabb.getSelectedIndex();
            compiledIndex = i;
            JPanel p = (JPanel) tabb.getTabComponentAt(i);
            JLabel title1 = (JLabel) p.getComponent(0);
            f = title1.getText();
            JLabel path = (JLabel) p.getComponent(1);
            filePath = path.getText();
            out = filePath.substring(0, filePath.length() - 2);
            String exceFileTemp = filePath.substring(0, filePath.lastIndexOf('/') + 1) + "./" + filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length() - 2);
            exceFile = exceFileTemp.replace(" ", "\\ ");
            System.out.println("Compiling source file " + filePath);
            System.out.println("Excutable file stored at loc: " + exceFile);
        } else {
            compileFile = false;
        }
        if (compileFile) {
            final String outtemp = out;
            final String filePathTemp = filePath;
            System.out.println("Ready for compiling...");
            System.out.println("Compilation output: ");
            //changeStatus("Compiling", true);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String arr[] = {"cc", filePathTemp, "-o", outtemp, "-g", "-g3"};
                    String output = comand.Execute(arr);
                    if (output.equals("")) {
                        System.out.println("File compiled without errors.");
                        statusMsg.setText("Status: Code compile without any errors.");
                        allCFiles.add(outtemp.replace(" ", "\\ "));
                        allExes.add(outtemp.replace(" ", "\\ "));
                    } else {
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
                    //isCompiled = true;
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
}
