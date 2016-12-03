/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import static java.lang.Thread.sleep;
import java.net.URL;
import javax.swing.JFileChooser;

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

//        String s = System.getProperty("file.separator");
//        File roots = new File("D:\\Photo");
//        File[] mam = File.listRoots();
//        
//        for (File qqq : mam) {
//            System.out.println(qqq.toString());
//        }
        FileSystemSaver dniwe = new FileSystemSaver();
        dniwe.setVisible(true);
    }
}
