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
       protected void hightLightLine(JEditorPane edit, int lineNo) {
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

}
