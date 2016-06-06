package openide_temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import jsyntaxpane.syntaxkits.CSyntaxKit;

public class TextEditor extends javax.swing.JFrame {

    public boolean projectTreeVisable = true, resize = false;
    public String output, selectedPath;
    public static JPanel OutputPanel;
    public ExecuteShellComand comand;
    public static int compiledIndex, lineNumber, selectedExeIndex;
    public static JLabel link;
    public ArrayList<String> allExes, allCFiles;
    public ArrayList<ArrayList> projectExes, projectSources;
    public ProjectTree projectTreeObj;
    public IDEOperation ideOperation;

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
        ideOperation = new IDEOperation(tabb, allCFiles, comand, allExes, statusMsg);
        projectTreeObj.setOperations(ideOperation);

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

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


        /* End of warning dialog */
        runIconButton.setText("Run");
        runIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ideOperation.run();
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
                ideOperation.save();
            }
        });

        compileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/compile1.png"))); // NOI18N
        compileIcon.setBorderPainted(false);
        compileIcon.setFocusPainted(false);
        compileIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ideOperation.compile(null, null);
            }
        });

        runIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/run1.png"))); // NOI18N
        runIcon.setBorderPainted(false);
        runIcon.setFocusPainted(false);
        runIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ideOperation.run();
            }
        });

        buttonRibbon.setLayout(new BoxLayout(buttonRibbon, BoxLayout.X_AXIS));
        newIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(newIcon);
        openIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(openIcon);
        saveIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(saveIcon);
        runIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(runIcon);
        compileIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRibbon.add(compileIcon);

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
                ideOperation.save();
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
                ideOperation.compile(null, null);
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
                //runConfiguration();
                new RunConfiguration(projectExes, allExes, comand);
            }

        });
        projectMenu.add(runConfig);

        //adding debug config menu
        debugConfig.setText("Debug Configuration");
        debugConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Debug debug = new Debug(projectSources, allCFiles, buttonRibbonLayout, stepUp, stepDown, stop);
                debug = new Debugger(projectSources, allCFiles, buttonRibbon, tabb, ideOperation, debugConfig);
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
                    ideOperation.openFile(fileToSave);
                } catch (IOException ex) {
                    //Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ideOperation.changeStatus("Error: File already exists", false);
            }
        }
    }

    public static void displayOutput(String line) {
        JLabel lab = new JLabel();
        if (line.contains("error") || line.contains("warning")) {
            System.out.println("error: " + line);
            link = new JLabel();
            link.addMouseListener(new ErrorLoctionListener(tabb));
            link.setForeground(Color.blue);
            link.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int index = line.indexOf("error");
            if (index == -1) {
                index = line.indexOf("warning");
            }
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

    public void open() {
        File fileToOpen = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the file: ");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToOpen = fileChooser.getSelectedFile();
            ideOperation.openFile(fileToOpen);
        }
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
                texteditor = new TextEditor();
                texteditor.setVisible(true);
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
    private javax.swing.JMenuItem cut;
    private javax.swing.JMenuItem documentation;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem find;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton runIconButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuOptions;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JButton newIcon;
    private javax.swing.JMenuItem open;
    private javax.swing.JButton openIcon;
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
    private Logger IDELogger;
    private Debugger debug;
    protected static TextEditor texteditor;
}
