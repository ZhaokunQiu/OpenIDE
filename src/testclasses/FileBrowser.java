package testclasses;

import java.awt.GridBagConstraints;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import openide_temp.IDEOperation;

public class FileBrowser extends JFrame {

    public FileBrowser(ArrayList<ArrayList> projectExes, GridBagConstraints gridBag, JPanel projectDisplay) {
        this.projectExes = projectExes;
        this.gridBag = gridBag;
        this.projectDisplay = projectDisplay;
    }

    public void setOperations(IDEOperation operations) {
        this.operations = operations;
    }

    private DefaultMutableTreeNode root;

    private DefaultTreeModel treeModel;

    private JTree tree;
    
    private  ArrayList<ArrayList> projectSources,projectExes;
    private GridBagConstraints gridBag;
    private JPanel projectDisplay;
    private IDEOperation operations;   

    public void addProjectTree(File file1) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the project folder: ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File projectFolder = fileChooser.getSelectedFile();
            ArrayList<String> tempExes = new ArrayList<>();
            ArrayList<String> tempSource = new ArrayList<>();
            //Adding project loaction at 1st location
            tempExes.add(projectFolder.getAbsolutePath());
            tempSource.add(projectFolder.getAbsolutePath());
            JFrame frame = new JFrame("File Browser");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            //File fileRoot = new File("/home/castor/");
            root = new DefaultMutableTreeNode(toString(projectFolder));
            treeModel = new DefaultTreeModel(root);

            tree = new JTree(treeModel);
            tree.setShowsRootHandles(true);
            JScrollPane scrollPane = new JScrollPane(tree);

            frame.add(scrollPane);
            frame.setLocationByPlatform(true);
            frame.setSize(640, 480);
            frame.setVisible(true);
            createChildren(projectFolder, root,tempExes,tempSource);
            projectExes.add(tempExes);
            projectSources.add(tempSource);
            gridBag.gridy++;
            projectDisplay.add(tree, gridBag);
            projectDisplay.revalidate();
        }
    }

    private void createChildren(File fileRoot, DefaultMutableTreeNode node, ArrayList<String> tempExes, ArrayList<String> tempSource) {
        File[] files = fileRoot.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            DefaultMutableTreeNode childNode
                    = new DefaultMutableTreeNode(toString(file));
            node.add(childNode);
            if (file.canExecute() && !file.getName().endsWith(".o") && !file.isDirectory() && !file.getName().contains(".")) {
                System.out.println(""+file.getParent()+"/./"+file.getName());
                //tempExes.add();
            }

            if (file.canExecute() && !file.getName().endsWith(".o") && !file.isDirectory() && !file.getName().contains(".")) {
               // tempSource.add(dir.getAbsolutePath() + "/" + tmp[i]);
            }
            if (file.isDirectory()) {
                createChildren(file, childNode, tempExes, tempSource);
            }
        }
    }

    public String toString(File file) {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }

}
