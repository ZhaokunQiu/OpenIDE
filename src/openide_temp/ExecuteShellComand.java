/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static openide_temp.TextEditor.displayOutput;

/**
 *
 * @author deadshot
 */
public class ExecuteShellComand {
    
      public String Execute(String[] cmd) {
        String s = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                displayOutput(line);
                //System.out.println(line);
                s = s + line;
                s = s + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    private void executeCommand(String command) {

        try {

            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {

                line = reader.readLine();
                System.out.println(line);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
