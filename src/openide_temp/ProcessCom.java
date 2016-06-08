/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openide_temp;

import testclasses.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author castor
 */
public class ProcessCom {

    private static String outputText;
    private Scanner reader;
    private BufferedWriter writer;
    boolean flag = true;
    boolean inputFlag = false;

    ProcessCom() {
        outputText = new String();
        try {
            ProcessBuilder builder = new ProcessBuilder("/bin/bash");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();

            //reader = new BufferedReader(new InputStreamReader(stdout));
            reader = new Scanner(process.getInputStream());

            writer = new BufferedWriter(new OutputStreamWriter(stdin));

        } catch (IOException ex) {
            Logger.getLogger(ProcessCom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        //Thread t = new Thread(new ThreadReader(reader,outputText));
        //t.start();
    }

    public String readOutput() {
        String output = "";
        String line = "";
        while (reader.hasNext()) {
            line = reader.nextLine();
            if (line.contains("castor123") || (line.contains("scanf(") && !line.contains("printf"))) {
                if ((line.contains("scanf(") && !line.contains("printf"))) {
                    System.out.println(line);
                    System.out.println("Need to ask user input..");
                    inputFlag = true;
                } else {
                    break;
                }
            }
            output += line + "\n";
            //System.out.println(line);
        }
        return output;
    }

    public void executeCommand(String command) {
        System.out.println("Executed " + command);
        runCommand(command);
        runCommand("castor123#god");
    }

    public void runCommand(String command) {
        //atomicReference.set("");
        try {
            outputText = "";
            command += "\n";
            writer.write(command);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProcessCom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
