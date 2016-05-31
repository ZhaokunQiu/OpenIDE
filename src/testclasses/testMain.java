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
        process.runCommand("gdb");
        System.out.println(process.readOutput());
        process.runCommand("file /home/castor/rev");
        System.out.println(process.readOutput());
        process.runCommand("b 5");
        System.out.println(process.readOutput());
        process.runCommand("run");
        System.out.println(process.readOutput());
        process.runCommand("nexts");
        System.out.println(process.readOutput());
    }
}
