/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.io.File;
import java.io.IOException;
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
        File workingDir = new File(System.getProperty("user.dir"));
        private static String trainingDirString =  System.getProperty("user.dir").concat(System.getProperty("file.separator").concat("Trainings"));
        private static String profileDirString =  System.getProperty("user.dir").concat(System.getProperty("file.separator").concat("Profiles"));
        private static File trainingDir = new File(trainingDirString);
//        private static File trainingUserDir = new File(trainingDirString.concat(System.getProperty("file.separator")).concat("Users"));
        private static File trainingBasicDir = new File(trainingDirString.concat(System.getProperty("file.separator")).concat("Basic"));
        private static File profileDir = new File(profileDirString);
        
        protected static final String FREE_WEIGHT = "Тренировка с отягощением";
        protected static final String NO_WEIGHT = "Тренировка без отягощения";
        protected static final String TIME_TRAINING = "Тренировка на время";
        protected static final String BLANK_NAME = "не выбрано";
        protected static final String EX_SEPARATOR = " _!_!_ ";
        private static Object[] listExercises = new Object[3];
    
        static {
            listExercises[0] = FREE_WEIGHT;
            listExercises[1] = NO_WEIGHT;
            listExercises[2] = TIME_TRAINING;
        }
        
        protected static Object[] getExerciseList()
        {
            return listExercises;
        }
        
        static 
        {
            if (!trainingDir.exists()) 
            { 
                trainingDir.mkdir(); 
            }
            if (!trainingBasicDir.exists())
                { trainingBasicDir.mkdir(); }
            if (!profileDir.exists()) 
            { 
                profileDir.mkdir(); 
            }
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        
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