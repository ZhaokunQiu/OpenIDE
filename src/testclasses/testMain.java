/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclasses;

/**
 *
 * @author castor
 */
public class testMain {
    public static void main(String[] args) {
       
        processCom process = new processCom();
        process.init();
        process.executeCommand("gdb");
        System.out.println(process.readOutput());
        process.executeCommand("file /home/castor/rev");
        System.out.println(process.readOutput());
        process.executeCommand("b 5");
        System.out.println(process.readOutput());
        process.executeCommand("run");
        System.out.println(process.readOutput());
        process.executeCommand("next");
        System.out.println(process.readOutput());
    }
}
