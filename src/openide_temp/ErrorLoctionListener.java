/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Element;
import javax.swing.text.Highlighter.Highlight;
import static openide_temp.TextEditor.compiledIndex;
import static openide_temp.TextEditor.lineNumber;

/**
 *
 * @author ubuntu
 */
public class ErrorLoctionListener implements MouseListener {

    public JTabbedPane tabb1;
    private Highlighter highlighter;

    ErrorLoctionListener(JTabbedPane tabb) {
        this.tabb1 = tabb;
        highlighter = new Highlighter();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JScrollPane scrol = (JScrollPane) tabb1.getComponentAt(compiledIndex);
        JViewport viewport = scrol.getViewport();
        JEditorPane editor = (JEditorPane) viewport.getView();
        
        int offset = 0;
        JLabel link = (JLabel) e.getComponent();
        if (link.getText().contains(".c")) {
                System.out.println("Yes this is the dstring");
                int index = link.getText().indexOf(":");
                System.out.println("This is it" + index);
                String new1 = link.getText().substring(++index);
                int i = 0;
                String new2 = "";
                while (new1.charAt(i) != ':') {
                    new2 += new1.charAt(i);
                    i++;
                }
                int myOffset = 0;
                lineNumber = Integer.parseInt(new2);
                System.out.println("Line number is: " + lineNumber + " The comp id isL : " + compiledIndex);
                
                
                lineNumber = lineNumber-1;
                if(lineNumber<0){
                    lineNumber = 0;
                }
                Element map = editor.getDocument().getDefaultRootElement();
                if (lineNumber < 0) {
                    try {
                        throw new BadLocationException("Negative line", -1);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(ErrorLoctionListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (lineNumber >= map.getElementCount()) {
                    try {
                        throw new BadLocationException("No such line", editor.getDocument().getLength() + 1);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(ErrorLoctionListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Element lineElem = map.getElement(lineNumber);
                    offset =  lineElem.getStartOffset();
                }
                System.out.println("Offset: "+offset);
                tabb1.setSelectedComponent(scrol);
                editor.getCaret().setVisible(true);
                editor.setCaretPosition(offset);
                highlighter.changeColor(Color.pink);
                highlighter.hightLightLine(editor, lineNumber);
                editor.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        javax.swing.text.Highlighter highlighter1 = editor.getHighlighter();
                        highlighter1.removeAllHighlights();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                
                });
                
             
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
