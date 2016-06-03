/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author castor
 */
import java.awt.Window;
import javax.swing.JPanel;
public class ProjectTree extends JFrame{
    private final ArrayList<ArrayList> projectSources,projectExes;
    private GridBagConstraints gridBag;
    private JPanel projectDisplay;
           
    
    ProjectTree(ArrayList<ArrayList> ps,ArrayList<ArrayList> pe, GridBagConstraints gl,JPanel panel){
        projectSources = ps;
        projectExes = pe;
        System.out.println(projectExes.toString());
        gridBag = gl;
        projectDisplay = panel;
    }
    
    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir, ArrayList<String> tempExes, ArrayList<String> tempSource) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++) {
            //System.out.println(tmp[i]);

            File f = new File(dir.getAbsolutePath() + "/" + tmp[i]);
            if (f.canExecute() && !tmp[i].endsWith(".o") && !f.isDirectory() && !tmp[i].contains(".")) {
                tempExes.add(dir.getAbsolutePath() + "/./" + tmp[i]);
            }

            if (f.canExecute() && !tmp[i].endsWith(".o") && !f.isDirectory() && !tmp[i].contains(".")) {
                tempSource.add(dir.getAbsolutePath() + "/" + tmp[i]);
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
                addNodes(curDir, f, tempExes, tempSource);
            } else {
                files.addElement(thisObject);
            }
        }
        for (int fnum = 0; fnum < files.size(); fnum++) {
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        }
        return curDir;
    }

    public void addProjectTree() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the project folder: ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File projectFolder = fileChooser.getSelectedFile();
            ArrayList<String> tempExes = new ArrayList<>();
            ArrayList<String> tempSource = new ArrayList<>();
            tempExes.add(projectFolder.getAbsolutePath());
            tempSource.add(projectFolder.getAbsolutePath());
            JTree tree = new JTree(addNodes(null, projectFolder, tempExes, tempSource));
            projectExes.add(tempExes);
            projectSources.add(tempSource);
            MouseListener ml = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    //System.out.println("Mouse pressed!");
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    if (selRow != -1) {
                        if (e.getClickCount() == 1) {
                            //System.out.println("Single Click");
                            System.out.println(selPath);

                        } else if (e.getClickCount() == 2) {
                            //System.out.println("Double Click!");

                            Object[] path = selPath.getPath();
                            String fPath = path[path.length - 2] + "/" + path[path.length - 1];
                            System.out.println(fPath);
                            File f = new File(fPath);
                            if (f.isFile()) {
                                //openFile(new File(fPath));
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
}
