/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author castor
 */
public class processCom {

    private String outputText="";
    private BufferedReader reader;
    private BufferedWriter writer;

    processCom() {
        try {
            ProcessBuilder builder = new ProcessBuilder("/bin/bash");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stdout));
            writer = new BufferedWriter(new OutputStreamWriter(stdin));

        } catch (IOException ex) {
            Logger.getLogger(processCom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String line;

                    //outputText = "";
                    while ((line = reader.readLine()) != null) {
                        //System.out.println("Reading...");
                        outputText += line + "\n";
                        System.out.println(line);
                    }
                    System.out.println(outputText);
                } catch (IOException ex) {
                    System.out.println("");
                }

            }

        });
        t.start();
    }

    public String readOutput() {
        return outputText;
    }

    public void runCommand(String command) {
        try {
            command += "\n";
            writer.write(command);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(processCom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void runProcess() {

    }
}
