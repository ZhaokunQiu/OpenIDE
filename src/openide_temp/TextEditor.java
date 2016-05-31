package openide_temp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import java.util.logging.Logger;
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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import jsyntaxpane.syntaxkits.CSyntaxKit;

public class TextEditor extends javax.swing.JFrame {

    private static boolean projectTreeVisable = true, isSaved = true, resize = false, isCompiled = false;
    private static String exceFile = "", output, selectedPath;
    private static JPanel OutputPanel;
    private static ExecuteShellComand comand;
    public static int compiledIndex, mouseX, mouseY, lineNumber, selectedExeIndex;
    public static JLabel link;
    public static ArrayList allExes;
    public static ArrayList<ArrayList> projectExes;

    public TextEditor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        CSyntaxKit.initKit();
        initComponents();
        OutputPanel = new JPanel();
        OutputPanel.setLayout(new BoxLayout(OutputPanel, BoxLayout.Y_AXIS));
        OutputPanel.setName("Output");
        comand = new ExecuteShellComand();
        allExes = new ArrayList();
        projectExes = new ArrayList();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        area1 = new javax.swing.JTextArea();
        warningDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        paraText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        parameters = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        parameter = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        debugger = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bLineNo = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        covePanel = new javax.swing.JPanel();
        buttonRibbon = new javax.swing.JPanel();
        newIcon = new javax.swing.JButton();
        openIcon = new javax.swing.JButton();
        saveIcon = new javax.swing.JButton();
        debug = new javax.swing.JButton();
        compileIcon = new javax.swing.JButton();
        runIcon = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        panel1.setName("Output"); // NOI18N
        area1.setEditable(false);
        area1.setBackground(new java.awt.Color(57, 57, 57));
        area1.setColumns(20);
        area1.setForeground(new java.awt.Color(254, 254, 254));
        area1.setRows(5);
        area1.setText("run:");
        scroll1.setViewportView(area1);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );

        warningDialog.setMinimumSize(new java.awt.Dimension(177, 97));

        jPanel1.setMaximumSize(new java.awt.Dimension(165, 97));
        jPanel1.setMinimumSize(new java.awt.Dimension(165, 97));

        paraText.setText("File already Exists. ");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jButton1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(paraText)))
                        .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(paraText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addContainerGap())
        );

        javax.swing.GroupLayout paraLayout = new javax.swing.GroupLayout(warningDialog.getContentPane());
        warningDialog.getContentPane().setLayout(paraLayout);
        paraLayout.setHorizontalGroup(
                paraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        paraLayout.setVerticalGroup(
                paraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.setMaximumSize(new java.awt.Dimension(447, 90));
        jPanel2.setMinimumSize(new java.awt.Dimension(447, 90));

        jButton3.setText("Run");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(parameter)
                        .addContainerGap())
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jButton3)
                        .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(parameter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout parametersLayout = new javax.swing.GroupLayout(parameters.getContentPane());
        parameters.getContentPane().setLayout(parametersLayout);
        parametersLayout.setHorizontalGroup(
                parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        parametersLayout.setVerticalGroup(
                parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(parametersLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        debugger.setMinimumSize(new java.awt.Dimension(484, 262));

        jPanel3.setMaximumSize(new java.awt.Dimension(484, 262));
        jPanel3.setMinimumSize(new java.awt.Dimension(484, 262));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jTable1.setMaximumSize(new java.awt.Dimension(300, 72));
        jTable1.setMinimumSize(new java.awt.Dimension(300, 72));
        jScrollPane2.setViewportView(jTable1);
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(bLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton5)
                                        .addComponent(jButton6))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout debuggerLayout = new javax.swing.GroupLayout(debugger.getContentPane());
        debugger.getContentPane().setLayout(debuggerLayout);
        debuggerLayout.setHorizontalGroup(
                debuggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        debuggerLayout.setVerticalGroup(
                debuggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextField1.setText("jTextField1");

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
                save();
            }
        });

        debug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/debug_1.png"))); // NOI18N
        debug.setBorderPainted(false);
        debug.setFocusPainted(false);
        debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoIconActionPerformed(evt);
            }
        });

        compileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/compile1.png"))); // NOI18N
        compileIcon.setBorderPainted(false);
        compileIcon.setFocusPainted(false);
        compileIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compile();
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/share-icon1.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parameters.setVisible(true);
            }
        });

        javax.swing.GroupLayout buttonRibbonLayout = new javax.swing.GroupLayout(buttonRibbon);
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
                        .addComponent(debug)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compileIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 415, Short.MAX_VALUE))
        );
        buttonRibbonLayout.setVerticalGroup(
                buttonRibbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(newIcon)
                .addComponent(openIcon)
                .addComponent(saveIcon)
                .addGroup(buttonRibbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(debug)
                        .addComponent(runIcon)
                        .addComponent(compileIcon)
                        .addComponent(jButton2))
        );

        tabb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabbMouseDragged(evt);
            }
        });
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
        projectDisplay.add(new JTree(addNodes(null, new File("."), new ArrayList())), gridBag);

        //projectDisplay.add(new JLabel("Open Projects"));
        //projectTree.setBackground(Color.LIGHT_GRAY);
        covePanel.add(projectTree, coveLay.WEST);
        getContentPane().add(covePanel);
        //resizing project tree
        projectTree.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                if (mouseX >= (projectTree.getWidth() - 10) && mouseX <= projectTree.getWidth()) {
                    resize = true;
                }
                if (resize) {
                    System.out.println(mouseX);
                    projectTree.setSize(projectTree.getWidth() + 1, projectTree.getHeight());
                    //tabb.setSize(tabb.getWidth() -1, tabb.getHeight());
                    //pack();
                }
            }
        });

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
                saveActionPerformed(evt);
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
                addProjectTree();
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
                compile();
            }
        });
        projectMenu.add(compile);

        run.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        run.setText("Run");
        projectMenu.add(run);
        runConfig.setText("Run Configuration");
        runConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runConfiguration();
            }

        });
        projectMenu.add(runConfig);

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
        JScrollPane scroll = new JScrollPane(codeEditor);
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
                    Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                warningDialog.setVisible(true);
            }
        }
    }

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {
        save();
    }

    public void save() {
        int i = tabb.getSelectedIndex();
        if (!(tabb.getTabCount() == 0) && !(tabb.getTitleAt(i).equals("Output"))) {

            try {
                JPanel p = (JPanel) tabb.getTabComponentAt(i);
                JLabel title = (JLabel) p.getComponent(0);
                String f = title.getText();
                f = f.replace("*", "");
                JLabel path = (JLabel) p.getComponent(1);
                JScrollPane s = (JScrollPane) tabb.getComponentAt(i);
                JViewport viewport = s.getViewport();
                JEditorPane a = (JEditorPane) viewport.getView();
                File file = new File(path.getText());
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(a.getText());
                    writer.flush();
                }
                title.setText(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            isSaved = false;
        }
    }

    public void compile() {
        OutputPanel.removeAll();
        String f;
        if (!isSaved) {
            save();
        }
        int i = tabb.getSelectedIndex();
        if (!(tabb.getComponentCount() == 0 || tabb.getTitleAt(i).equals("Output"))) {
            //String filePath = "";
            i = tabb.getSelectedIndex();
            compiledIndex = i;
            JPanel p = (JPanel) tabb.getTabComponentAt(i);
            JLabel title1 = (JLabel) p.getComponent(0);
            f = title1.getText();
            JLabel path = (JLabel) p.getComponent(1);
            String filePath = path.getText();
            String out = filePath.substring(0, filePath.length() - 2) + ".o";
            String exceFileTemp = filePath.substring(0, filePath.lastIndexOf('/') + 1) + "./" + filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length() - 2) + ".o";
            exceFile = exceFileTemp.replace(" ", "\\ ");
            System.out.println("Executable loc: " + exceFile);
            System.out.println("Compiling loc: " + out);
            changeStatus("Compiling", true);
            final String outtemp = out;
            final String filePathTemp = filePath;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String arr[] = {"cc",filePathTemp,"-o",outtemp,"-g"};
                    //output = comand.ExecuteCC("cc", filePathTemp, "-o", outtemp, "-g");
                    output = comand.Execute(arr);
                    if (output.equals("")) {
                        statusMsg.setText("Status: Code compile without any errors.");
                    } else {
                        JButton close = new JButton("x");
                        close.setForeground(Color.RED);
                        close.setBorderPainted(false);
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
                    isCompiled = true;
                }
            });
            t.start();
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
    }

    public void run() {
        save();
        int selIndex = tabb.getSelectedIndex();
        JPanel p = (JPanel) tabb.getTabComponentAt(selIndex);
        final JLabel title1 = (JLabel) p.getComponent(0);
        System.out.println(title1.getText());
        if (title1.getText().equalsIgnoreCase("makefile")) {
            System.out.println("Its a make file");
            if (!(tabb.getComponentCount() == 0 || tabb.getTitleAt(selIndex).equals("Output"))) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JLabel path = (JLabel) p.getComponent(1);
                        String filePath = path.getText();
                        System.out.println("here is the file path = " + filePath);
                        String theCommand = "";
                        String location = filePath.substring(0, filePath.toLowerCase().indexOf("makefile") - 1);
                        theCommand += "cd " + location + "\n";
                        theCommand += "make uninstall" + "\n";
                        theCommand += "make clean" + "\n";
                        theCommand += "make" + "\n";
                        theCommand += "make install" + "\n";
                        theCommand += "echo 'Done building...'" + "\n";
                        //theCommand += "mosquitto" + "\n";
                        File scriptFile = new File("/home/castor/Desktop/scriptMake.sh");

                        try {
                            scriptFile.createNewFile();
                            BufferedWriter bf = new BufferedWriter(new FileWriter(scriptFile));
                            bf.write(theCommand);
                            bf.close();
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String arr[] = {"xterm","-hold","-e","sh","/home/castor/Desktop/scriptMake.sh"};
                                comand.Execute(arr);
                            }

                        });
                        t.start();

                    }

                });
                t.start();

            }

        } else {
            compile();
            if (isCompiled) {
                System.out.println("is compiled");
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String parameters = "";
                        parameters = parameter.getText();
                        StringTokenizer st1 = new StringTokenizer(parameters);
                        int count = st1.countTokens();
                        count += 4;
                        String[] ar = new String[count];
                        ar[0] = "xterm";
                        ar[1] = "-hold";
                        ar[2] = "-e";
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
            }
        }
    }

    public void openFile(File f) {
        File fileToOpen = null;
        JEditorPane codeEditor = new JEditorPane();
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
                Logger.getLogger(TextEditor.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();

                } catch (IOException ex) {
                    Logger.getLogger(TextEditor.class
                            .getName()).log(Level.SEVERE, null, ex);

                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();

                }
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        scroll.setPreferredSize(new Dimension(260, 85));

        scroll.setMaximumSize(new Dimension(2147483647, 2147483647));
        scroll.setMinimumSize(new Dimension(0, 17));

        codeEditor.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (isSaved == false) {
                    changeTitle();
                }
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                if (isSaved == false) {
                    changeTitle();
                }

            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (isSaved == false) {
                    changeTitle();
                }

            }

            public void changeTitle() {
                if (isSaved == false) {
                    //Font font = new Font("Ubuntu", Font.BOLD, 15);
                    int i = tabb.getSelectedIndex();
                    JPanel panel = (JPanel) tabb.getTabComponentAt(i);
                    JLabel title1 = (JLabel) panel.getComponent(0);
                    String title = title1.getText();
                    if (!(title.charAt(0) == '*')) {
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(title, gbc);
        gbc.gridx++;
        JLabel path = new JLabel(fileToOpen.getAbsolutePath());
        path.setVisible(false);
        pnlTab.add(path, gbc);
        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(close, gbc);
        tabb.setTabComponentAt(i - 1, pnlTab);
        codeEditor.setText(code);
    }

    public void open() {
        File fileToOpen = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the file: ");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToOpen = fileChooser.getSelectedFile();
            openFile(fileToOpen);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        warningDialog.setVisible(false);
    }

    private void undoIconActionPerformed(java.awt.event.ActionEvent evt) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String substring = exceFile.substring(exceFile.indexOf(".") + 1, exceFile.length());
                String s1 = exceFile.substring(0, (exceFile.indexOf(".") - 1));
                substring = s1 + substring;
                String cmd[] = {"ddd", substring};
                comand.Execute(cmd);
            }

        });
        t.start();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        run();
        parameters.setVisible(false);
    }

    private void tabbMouseDragged(java.awt.event.MouseEvent evt) {
        mouseX = evt.getX();
        mouseY = evt.getY();
        if (mouseX >= 0 && mouseX <= 10) {
            resize = true;
        }
        if (resize) {
            System.out.println(mouseX);
            tabb.setSize(tabb.getWidth() - (mouseX / 100), tabb.getHeight());
        }
    }

    private void changeStatus(String text, boolean flag) {
        if (text != null) {
            statusMsg.setText(text);
        }
        if (flag) {
            System.out.println("its true..");
            ImageIcon img = new ImageIcon("img/loading.gif");
            System.out.println(img.getIconHeight());
            statusMsg.setText(text);
            statusMsg.setIcon(img);
        } else {
            statusMsg.setText(statusMsg.getText());
            statusMsg.setIcon(null);
        }
    }

    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir, ArrayList<String> tempExes) {
        System.out.println("Once");
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);

            File f = new File(dir.getAbsolutePath() + "/" + tmp[i]);
            if (f.canExecute() && !tmp[i].endsWith(".o") && !f.isDirectory() && !tmp[i].contains(".")) {
                tempExes.add(dir.getAbsolutePath() + "/./" + tmp[i]);
            }

            ol.addElement(tmp[i]);
        }
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector files = new Vector();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals(".")) {
                newPath = thisObject;
            } else {
                newPath = curPath + File.separator + thisObject;
            }
            if ((f = new File(newPath)).isDirectory()) {
                addNodes(curDir, f, tempExes);
            } else {
                files.addElement(thisObject);
            }
        }
        for (int fnum = 0; fnum < files.size(); fnum++) {
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        }
        return curDir;
    }

   
    public void gitCommit(){
    
    }
    
    public void addProjectTree() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the project folder: ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File projectFolder = fileChooser.getSelectedFile();
            ArrayList<String> tempExes = new ArrayList<>();
            tempExes.add(projectFolder.getAbsolutePath());
            JTree tree = new JTree(addNodes(null, projectFolder, tempExes));
            projectExes.add(tempExes);
            MouseListener ml = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    System.out.println("Mouse pressed!");
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    if (selRow != -1) {
                        if (e.getClickCount() == 1) {
                            System.out.println("Single Click");
                            System.out.println(selPath);

                        } else if (e.getClickCount() == 2) {
                            System.out.println("Double Click!");

                            Object[] path = selPath.getPath();
                            String fPath = path[path.length - 2] + "/" + path[path.length - 1];
                            System.out.println(fPath);
                            File f = new File(fPath);
                            if (f.isFile()) {
                                openFile(new File(fPath));
                            }

                        }
                    }
                }
            };
            tree.addMouseListener(ml);
            gridBag.gridy++;
            projectDisplay.add(tree, gridBag);
            projectDisplay.revalidate();

        }
    }

    private void runConfiguration() {
        System.out.println("Runing run config..");
        JDialog runConfigDialog = new JDialog();
        Dimension dim = new Dimension(400, 190);
        runConfigDialog.setMaximumSize(dim);
        runConfigDialog.setMinimumSize(dim);
        runConfigDialog.setPreferredSize(dim);

        JPanel runConfigPanel = new JPanel();
        JTextField args = new JTextField();
        JScrollPane scrolBar = new JScrollPane();
        BoxLayout bx = new BoxLayout(runConfigPanel,BoxLayout.Y_AXIS);
        runConfigPanel.setLayout(new BoxLayout(runConfigPanel, BoxLayout.Y_AXIS));
        runConfigDialog.add(runConfigPanel);
        JLabel project = new JLabel("Project Location: ");
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        JButton submit = new JButton("Run");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add here code...");
                //call run function

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {

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
                        System.out.println("run FIle: " + exceFile);
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
            }

        });

        runConfigPanel.add(project);
        runConfigPanel.add(projectLoc);
        runConfigPanel.add(scrolBar);
        runConfigPanel.add(args);
        runConfigPanel.add(submit);
        runConfigDialog.setVisible(true);

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextEditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JMenu EditMenu;
    private static javax.swing.JTextArea area1;
    private javax.swing.JTextField bLineNo;
    private javax.swing.JPanel buttonRibbon;
    private javax.swing.JMenuItem compile;
    private javax.swing.JButton compileIcon;
    private javax.swing.JMenuItem copy;
    private javax.swing.JPanel covePanel;
    private javax.swing.JMenuItem cut;
    private javax.swing.JFrame debugger;
    private javax.swing.JMenuItem documentation;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem find;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JButton newIcon;
    private javax.swing.JMenuItem open;
    private javax.swing.JButton openIcon;
    private javax.swing.JPanel panel1;
    private javax.swing.JDialog warningDialog;
    private javax.swing.JLabel paraText;
    private javax.swing.JTextField parameter;
    private javax.swing.JDialog parameters;
    private javax.swing.JMenuItem paste;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JMenuItem redo;
    private javax.swing.JMenuItem run;
    private javax.swing.JButton runIcon;
    private javax.swing.JMenuItem save;
    private javax.swing.JButton saveIcon;
    private javax.swing.JScrollPane scroll1;
    public static javax.swing.JTabbedPane tabb;
    private javax.swing.JMenuItem undo;
    private javax.swing.JButton debug;
    private JPanel status;
    private JLabel statusMsg;
    private JScrollPane projectTree;
    private JMenu window;
    private JMenuItem projectWindow;
    private JMenuItem openProject;
    private JMenuItem runConfig;
    private JPanel projectDisplay;
    private GridBagConstraints gridBag;
}
