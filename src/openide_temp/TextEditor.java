package openide_temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import jsyntaxpane.syntaxkits.CSyntaxKit;

public class TextEditor extends javax.swing.JFrame {

    public boolean projectTreeVisable = true, resize = false;
    public String  output, selectedPath;
    public static JPanel OutputPanel;
    public ExecuteShellComand comand;
    public static int compiledIndex, lineNumber, selectedExeIndex;
    public static JLabel link;
    public ArrayList<String> allExes, allCFiles;
    public ArrayList<ArrayList> projectExes, projectSources;
    public ProjectTree projectTreeObj;
    public SaveFile savefile;

    public TextEditor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        OutputPanel = new JPanel();
        OutputPanel.setLayout(new BoxLayout(OutputPanel, BoxLayout.Y_AXIS));
        OutputPanel.setName("Output");
        comand = new ExecuteShellComand();
        allExes = new ArrayList();
        projectExes = new ArrayList();
        allCFiles = new ArrayList();
        IDELogger = LoggerFactory.getLogger(TextEditor.class);
        projectSources = new ArrayList();

        CSyntaxKit.initKit();
        initComponents();
        savefile = new SaveFile(tabb,allCFiles, comand, allExes, statusMsg);

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        warningDialog = new javax.swing.JDialog();
        warningPanel = new javax.swing.JPanel();
        warningText = new javax.swing.JLabel();
        warningCancelBtn = new javax.swing.JButton();
        runIconButton = new javax.swing.JButton();
        covePanel = new javax.swing.JPanel();
        buttonRibbon = new javax.swing.JPanel();
        newIcon = new javax.swing.JButton();
        openIcon = new javax.swing.JButton();
        saveIcon = new javax.swing.JButton();
        compileIcon = new javax.swing.JButton();
        runIcon = new javax.swing.JButton();
        tabb = new javax.swing.JTabbedPane();
        menuOptions = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newFile = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        open = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        copy = new javax.swing.JMenuItem();
        cut = new javax.swing.JMenuItem();
        paste = new javax.swing.JMenuItem();
        undo = new javax.swing.JMenuItem();
        redo = new javax.swing.JMenuItem();
        find = new javax.swing.JMenuItem();
        projectMenu = new javax.swing.JMenu();
        compile = new javax.swing.JMenuItem();
        run = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        documentation = new javax.swing.JMenuItem();
        window = new JMenu();
        projectWindow = new JMenuItem();
        projectTree = new JScrollPane();
        openProject = new JMenuItem();
        projectDisplay = new JPanel();
        runConfig = new JMenuItem();
        debugConfig = new JMenuItem();
        stepUp = new JButton();
        stepDown = new JButton();
        stop = new JButton();
        printValue = new JTextField();
        printBtn = new JButton("Value");


        /* Opening a waring dialog when user tries to create a new file which is already present */
        warningDialog.setMinimumSize(new java.awt.Dimension(177, 97));
        warningPanel.setMaximumSize(new java.awt.Dimension(165, 97));
        warningPanel.setMinimumSize(new java.awt.Dimension(165, 97));
        warningText.setText("File already Exists. ");
        warningCancelBtn.setText("Cancel");
        warningCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warningDialog.setVisible(false);
            }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(warningPanel);
        warningPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(warningCancelBtn))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(warningText)))
                        .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(warningText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(warningCancelBtn)
                        .addContainerGap())
        );

        javax.swing.GroupLayout paraLayout = new javax.swing.GroupLayout(warningDialog.getContentPane());
        warningDialog.getContentPane().setLayout(paraLayout);
        paraLayout.setHorizontalGroup(
                paraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(warningPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        paraLayout.setVerticalGroup(
                paraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(warningPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        /* End of warning dialog */
        runIconButton.setText("Run");
        runIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenIDE");
        setMinimumSize(new java.awt.Dimension(744, 495));

        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        covePanel.setName(""); // NOI18N

        newIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/new1.png"))); // NOI18N
        newIcon.setBorderPainted(false);
        newIcon.setFocusPainted(false);
        newIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFile();
            }
        });

        openIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/open1.png"))); // NOI18N
        openIcon.setBorderPainted(false);
        openIcon.setFocusPainted(false);
        openIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open();
            }
        });

        saveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/save1.png"))); // NOI18N
        saveIcon.setBorderPainted(false);
        saveIcon.setFocusPainted(false);
        saveIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savefile.save();
            }
        });

        compileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/compile1.png"))); // NOI18N
        compileIcon.setBorderPainted(false);
        compileIcon.setFocusPainted(false);
        compileIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savefile.compile(null, null);
            }
        });

        runIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/run1.png"))); // NOI18N
        runIcon.setBorderPainted(false);
        runIcon.setFocusPainted(false);
        runIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run();
            }
        });

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
        stepDown.setIcon(new ImageIcon(getClass().getResource("/resources/prev.png")));
        stepDown.setBorderPainted(false);
        stepDown.setFocusPainted(false);
        stepDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        stop.setIcon(new ImageIcon(getClass().getResource("/resources/stop.png")));
        stop.setBorderPainted(false);
        stop.setFocusPainted(false);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        buttonRibbonLayout = new javax.swing.GroupLayout(buttonRibbon);
        buttonRibbon.setLayout(buttonRibbonLayout);
        buttonRibbonLayout.setHorizontalGroup(
                buttonRibbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(buttonRibbonLayout.createSequentialGroup()
                        .addComponent(newIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compileIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepDown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printBtn)
                        .addGap(0, 415, Short.MAX_VALUE))
        );
        buttonRibbonLayout.setVerticalGroup(
                buttonRibbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(newIcon)
                .addComponent(openIcon)
                .addComponent(saveIcon)
                .addComponent(runIcon)
                .addComponent(compileIcon)
                .addComponent(stepDown)
                .addComponent(stepUp)
                .addComponent(stop)
                .addComponent(printValue)
                .addComponent(printBtn)
        );
        BorderLayout coveLay = new BorderLayout();
        covePanel.setLayout(coveLay);
        covePanel.add(buttonRibbon, coveLay.NORTH);
        covePanel.add(tabb, coveLay.CENTER);
        status = new JPanel();
        BorderLayout statusLay = new BorderLayout();
        status.setLayout(statusLay);
        statusMsg = new JLabel("Status: Ready");
        statusMsg.setBorder(new EmptyBorder(10, 10, 10, 10));
        status.add(statusMsg, statusLay.WEST);
        covePanel.add(status, coveLay.SOUTH);
        //projectTree = new JPanel();
        //adding projects
        projectTree.getViewport().add(projectDisplay);
        projectDisplay.setLayout(new GridBagLayout());
        projectDisplay.setBackground(Color.WHITE);
        gridBag = new GridBagConstraints();
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.weighty = 1;
        gridBag.weighty = 1.0;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        projectTreeObj = new ProjectTree(projectSources, projectExes, gridBag, projectDisplay);
        projectDisplay.add(new JTree(projectTreeObj.addNodes(null, new File("."), new ArrayList(), new ArrayList())), gridBag);

        //projectDisplay.add(new JLabel("Open Projects"));
        //projectTree.setBackground(Color.LIGHT_GRAY);
        covePanel.add(projectTree, coveLay.WEST);
        getContentPane().add(covePanel);
        //resizing project tree

        //adding project tree
        menuOptions.setMargin(new java.awt.Insets(0, 5, 0, 0));

        fileMenu.setText("File");

        newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFile.setText("New");
        newFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newFile();
            }
        });

        fileMenu.add(newFile);
        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savefile.save();
            }
        });
        fileMenu.add(save);

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open();
            }
        });
        fileMenu.add(open);

        openProject.setText("Open Project");
        openProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectTreeObj.addProjectTree();
            }

        });
        fileMenu.add(openProject);
        jMenu1.setText("Recent");
        fileMenu.add(jMenu1);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);

        menuOptions.add(fileMenu);

        //edit menu
        EditMenu.setText("Edit");

        copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copy.setText("Copy");

        EditMenu.add(copy);

        cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cut.setText("Cut");
        EditMenu.add(cut);

        paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        paste.setText("Paste");
        EditMenu.add(paste);

        undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undo.setText("Undo");
        EditMenu.add(undo);

        redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redo.setText("Redo");
        EditMenu.add(redo);

        find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        find.setText("Find");

        EditMenu.add(find);

        menuOptions.add(EditMenu);

        projectMenu.setText("Project");

        compile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        compile.setText("Compile");
        compile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savefile.compile(null, null);
            }
        });
        projectMenu.add(compile);

        run.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        run.setText("Run");
        projectMenu.add(run);

        //Adding run config
        runConfig.setText("Run Configuration");
        runConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runConfiguration();
            }

        });
        projectMenu.add(runConfig);

        //adding debug config menu
        debugConfig.setText("Debug Configuration");
        debugConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Debug debug = new Debug(projectSources, allCFiles, buttonRibbonLayout, stepUp, stepDown, stop);
                Debug();
            }

        });
        projectMenu.add(debugConfig);

        menuOptions.add(projectMenu);

        // Window menu
        window.setText("Window");
        projectWindow.setText("Project Window");
        projectWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectTreeVisable) {
                    System.out.println("Removing project tree..");
                    covePanel.remove(projectTree);
                    covePanel.revalidate();
                    projectTreeVisable = false;
                } else {
                    System.out.println("Adding Project Tree..");
                    projectTree.setBackground(Color.LIGHT_GRAY);
                    covePanel.add(projectTree, coveLay.WEST);
                    covePanel.revalidate();
                    projectTreeVisable = true;
                }
            }

        });
        window.add(projectWindow);

        menuOptions.add(window);

        //Help menu
        helpMenu.setText("Help");

        documentation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        documentation.setText("Documentation");
        helpMenu.add(documentation);

        menuOptions.add(helpMenu);

        setJMenuBar(menuOptions);
        pack();
    }// </editor-fold>                        

    public void newFile() {
        JEditorPane codeEditor = new JEditorPane();
        codeEditor.setContentType("text/java");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Location to save: ");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.exists()) {
                try {
                    fileToSave.createNewFile();
                    openFile(fileToSave);
                } catch (IOException ex) {
                    //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                warningDialog.setVisible(true);
            }
        }
    }

    public static void displayOutput(String line) {
        JLabel lab = new JLabel();
        if (line.contains("error") || line.contains("warning")) {

            link = new JLabel();
            link.addMouseListener(new ErrorLoctionListener(tabb));
            link.setForeground(Color.blue);
            int index = line.indexOf("error");
            String btn = line.substring(0, index);
            String txt = line.substring(index);
            link.setText(btn);
            lab.setText(txt);
            OutputPanel.add(link);
            OutputPanel.add(lab);
        } else {
            lab.setText(line);
            OutputPanel.add(lab);
        }
        System.out.println(line);
    }

    public void run() {
        System.out.println("Running file...");
        savefile.save();
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
                savefile.compile(null, null);
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

    public void openFile(File f) {
        String PathTemp = f.getAbsolutePath();
        System.out.println("Checking if file is open...");
        boolean fileOpen = false;
        int loc = 0;
        for (int i = 0; i < tabb.getComponentCount() - 1; i++) {
            JPanel panel = (JPanel) tabb.getTabComponentAt(i);
            JLabel path = (JLabel) panel.getComponent(1);
            if (PathTemp.compareTo(path.getText()) == 0) {
                fileOpen = true;
                loc = i;
                JScrollPane s = (JScrollPane) tabb.getComponentAt(i);
                JViewport viewport = s.getViewport();
                editor = (JEditorPane) viewport.getView();
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
            pnlTab.add(title, gbc);
            gbc.gridx++;
            JLabel path = new JLabel(fileToOpen.getAbsolutePath());
            path.setVisible(false);
            pnlTab.add(path, gbc);
            JLabel saveStatus = new JLabel("false");
            saveStatus.setVisible(false);
            pnlTab.add(saveStatus, gbc);
            JLabel isCompiled = new JLabel("false");
            isCompiled.setVisible(false);
            pnlTab.add(isCompiled, gbc);
            JLabel outputFile = new JLabel("null");
            outputFile.setVisible(false);
            pnlTab.add(outputFile, gbc);
            JLabel exeLink = new JLabel("null");
            exeLink.setVisible(false);
            pnlTab.add(exeLink, gbc);
            gbc.gridx++;
            gbc.weightx = 0;
            pnlTab.add(close, gbc);
            tabb.setTabComponentAt(i - 1, pnlTab);
            codeEditor.setText(code);
            revalidate();
        }
    }

    public void open() {
        File fileToOpen = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the file: ");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToOpen = fileChooser.getSelectedFile();
            openFile(fileToOpen);
            //isOpen(fileToOpen,fileToOpen.getName());
        }
    }

    private void hightLightLine(JEditorPane edit, int lineNo) {
        edit.setCaretPosition(PROPERTIES);
        edit.getHighlighter().removeAllHighlights();
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        Element root = edit.getDocument().getDefaultRootElement();

        int startOfLineOffset = root.getElement(lineNo - 1).getStartOffset();
        int stopOfLineOffset = root.getElement(lineNo).getStartOffset();
        edit.setCaretPosition(startOfLineOffset);
        try {
            edit.getHighlighter().addHighlight(startOfLineOffset, stopOfLineOffset, highlightPainter);
        } catch (BadLocationException ex) {
            // Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void runConfiguration() {
        System.out.println("Run configuration started");
        runConfigDialog = new JDialog();
        Dimension dim = new Dimension(350, 380);
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
                    //System.out.println("state changed: " + path);
                    //get the arrayList
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

    private void Debug() {
        System.out.println("Starting Debug configuration..");
        debugConfigDialog = new JDialog();
        Dimension dim = new Dimension(400, 380);
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
                    //System.out.println("state changed: " + path);
                    //get the arrayList
                    if (path.compareTo("Local source files") == 0) {
                        allLocs.removeAllElements();
                        for (int i = 0; i < allCFiles.size(); i++) {
                            allLocs.addElement(allCFiles.get(i));
                        }
                    } else {
                        for (int i = 0; i < projectSources.size(); i++) {
                            if (path.equals(projectSources.get(i).get(0))) {
                                //System.out.println("Found equal: " + projectSources.get(i).get(0));
                                allLocs.removeAllElements();
                                /*for (int j = 0; j < allExes.size(); j++) {
                                    allLocs.addElement(allExes.get(j));
                                }*/
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
        //gbc.weightx = 1;
        //gbc.weighty = 1;
        runConfigPanel.add(lineNo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        runConfigPanel.add(submit, gbc);
        debugConfigDialog.setVisible(true);

    }

    private void debugNext() {
        gdb.executeCommand("next");
        System.out.println(gdb.readOutput());
        gdb.executeCommand("where");
        String output = gdb.readOutput();
        gdbOutput.setText(gdbOutput.getText() + "\n" + output);

        output = output.substring(output.lastIndexOf(":") + 1, output.length() - 1);
        System.out.println("line next: " + output);
        hightLightLine(editor, Integer.parseInt(output));

    }

    private void debug(ArrayList<ArrayList> projectSources, JTextField lineNo) {
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

        int lineNumber = 0;
        String pathOfSource = "";
        for (int i = 0; i < projectSources.size(); i++) {
            if (projectSources.get(i).get(0).equals(selectedPath)) {
                pathOfSource = (String) projectSources.get(i).get(selectedExeIndex + 1);
            }
        }
        if (selectedPath == null) {
            pathOfSource = allCFiles.get(selectedExeIndex);
        }
        //System.out.println("Debugging file : [" + pathOfSource+"]");

        String exceFileTemp = pathOfSource;
        gdb = new ProcessCom();
        File f1 = new File(pathOfSource + ".c");
        System.out.println("Debugging file : [" + f1.getAbsolutePath() + "]");
        System.out.println("Name: " + f1.getName());
        openFile(f1);
        gdb.executeCommand("gdb " + exceFileTemp);
        gdbOutput.setText(gdbOutput.getText() + "\n" + gdb.readOutput());
        debugConfigDialog.setVisible(false);
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
        hightLightLine(editor, lineNumber);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TextEditor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextEditor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextEditor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextEditor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextEditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JMenu EditMenu;
    private javax.swing.JPanel buttonRibbon;
    private javax.swing.JMenuItem compile;
    private javax.swing.JButton compileIcon;
    private javax.swing.JMenuItem copy;
    private javax.swing.JPanel covePanel;
    private javax.swing.GroupLayout buttonRibbonLayout;
    private javax.swing.JMenuItem cut;
    private javax.swing.JMenuItem documentation;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem find;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton warningCancelBtn;
    private javax.swing.JButton runIconButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuOptions;
    private javax.swing.JPanel warningPanel;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JButton newIcon;
    private javax.swing.JMenuItem open;
    private javax.swing.JButton openIcon;
    private javax.swing.JDialog warningDialog;
    private javax.swing.JLabel warningText;
    private javax.swing.JMenuItem paste;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JMenuItem redo;
    private javax.swing.JMenuItem run;
    private javax.swing.JButton runIcon;
    private javax.swing.JMenuItem save;
    private javax.swing.JButton saveIcon;
    public static javax.swing.JTabbedPane tabb;
    private javax.swing.JMenuItem undo;
    private JPanel status;
    private JLabel statusMsg;
    private JScrollPane projectTree;
    private JMenu window;
    private JMenuItem projectWindow;
    private JMenuItem openProject;
    private JMenuItem runConfig;
    private JMenuItem debugConfig;
    public JPanel projectDisplay;
    public GridBagConstraints gridBag;
    private JDialog debugConfigDialog;
    private JButton stepUp;
    private JButton stepDown;
    private JButton stop;
    private JEditorPane editor;
    private JTextField printValue;
    private JButton printBtn;
    private ProcessCom gdb;
    private JEditorPane gdbOutput;
    private JScrollPane debuggerComponent;
    private JDialog runConfigDialog;
    private Logger IDELogger;
}
