/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.io.File;
//import java.io.IOException;
//import javax.swing.filechooser.FileSystemView;
//import static java.lang.Thread.sleep;
//import java.net.URL;
//import javax.swing.JFileChooser;

/**
 *
 * @author VMS
 */
public class SportApp {
    
   
        //File workingDir = new File(System.getProperty("user.dir"));
//        private static String trainingDirString =  System.getProperty("user.dir").concat(System.getProperty("file.separator").concat("Trainings"));
        private static String profileDirString =  System.getProperty("user.dir").concat(System.getProperty("file.separator").concat("Profiles"));
//        private static File trainingDir = new File(trainingDirString);
        private static File profileDir = new File(profileDirString);
        static 
        {
//            if (!trainingDir.exists()) { trainingDir.mkdir(); }
            if (!profileDir.exists()) { profileDir.mkdir(); }
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        MasterWindow startProgramm = new MasterWindow();
        startProgramm.setVisible(true);

    }
    protected static File getProfileDir()
    {
        return profileDir;
    }
//    protected static File getTrainingDir()
//    {
//        return trainingDir;
//    }
}