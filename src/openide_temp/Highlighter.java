/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.awt.Color;
import static java.awt.image.ImageObserver.PROPERTIES;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;

/**
 *
 * @author castor
 */
public class Highlighter {

    public DefaultHighlighter.DefaultHighlightPainter getHighlightPainter() {
        return highlightPainter;
    }
        Color color;
        DefaultHighlighter.DefaultHighlightPainter highlightPainter;
        Highlighter(){
            color = Color.yellow;
        }
       protected void hightLightLine(JEditorPane edit, int lineNo) {
        edit.setCaretPosition(PROPERTIES);
        edit.getHighlighter().removeAllHighlights();
         highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(color);
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
       
       protected void changeColor(Color color){
           this.color = color;
       }

}
