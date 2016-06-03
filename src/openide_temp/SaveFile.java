/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

/**
 *
 * @author castor
 */
public class SaveFile {
    private JTabbedPane tabb;
    SaveFile(JTabbedPane tab){
        tabb = tab;
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
