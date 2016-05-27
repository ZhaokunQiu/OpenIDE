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

    public void ExecuteCommand(String cmd) {
        ExecuteShellComand obj = new ExecuteShellComand();

        System.out.println(cmd);
        obj.executeCommand(cmd);

    }

    public String ExecuteCC(String str1, String str2, String str3, String str4, String str5) {
        String s = "";
        try {

            ProcessBuilder builder = new ProcessBuilder(str1, str2, str3, str4,str5);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                s = s + line;
                s = s + "\n";
                displayOutput(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public String ExecuteEdit(String str1, String str2, String str3, String str4) {
        String s = "";
        try {

            ProcessBuilder builder = new ProcessBuilder(str1, str2, str3, str4);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                //System.out.println(line);
                s = s + line;
                s = s + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public String ExecuteMake(String str1, String str2, String str3, String str4, String str5) {
        String s = "";
        try {

            ProcessBuilder builder = new ProcessBuilder(str1, str2, str3, str4, str5);
            builder.redirectErrorStream(true);
            Process p = builder.start();
           
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                //System.out.println(line);
                s = s + line;
                s = s + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShellComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
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
