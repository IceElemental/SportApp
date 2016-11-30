/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import static java.lang.Thread.sleep;
import java.net.URL;

/**
 *
 * @author VMS
 */
public class SportApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        //Training ololo = new Training(5, "Мамкин драник", 20, 30);
        //ololo.setVisible(true);
        //String q = System.getProperty("file.separator");
        
        URL location = SportApp.class.getProtectionDomain().getCodeSource().getLocation();
        
        System.out.println("file.separator    " + System.getProperty("file.separator"));
        System.out.println("java.class.path   " + System.getProperty("java.class.path"));
        System.out.println("java.home         " + System.getProperty("java.home"));
        System.out.println("java.vendor       " + System.getProperty("java.vendor"));
        System.out.println("java.vendor.url   " + System.getProperty("java.vendor.url"));
        System.out.println("java.version      " + System.getProperty("java.version"));
        System.out.println("os.arch           " + System.getProperty("os.arch"));
        System.out.println("os.name           " + System.getProperty("os.name"));
        System.out.println("os.version        " + System.getProperty("os.version"));
        System.out.println("path.separator    " + System.getProperty("path.separator"));
        System.out.println("user.dir          " + System.getProperty("user.dir"));
        System.out.println("user.home         " + System.getProperty("user.home"));
        System.out.println("user.name         " + System.getProperty("os.name"));
        System.out.println("Class location    " + location.getFile());
    }
}
