/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VMS
 */
public class MasterWindow extends JFrame {
        private static JFrame mainFrame;
        private static ProfileManager profileManagerPanel;
        private static TrainingManager trainingManagerPanel;
        private static JPanel masterPanel, profilePanel, trainingPanel, trainignAvailablePanel, exercisePanel, resulterPanel;
        private static JButton saveResult, nextExercise, showPanel1, showPanel2, clearPanel, manageTraining, manageProfile, createProfile, selectProfile, createTraining, selectTraining, startTraining;
        private static JComboBox selectProfileBox;
        private static JScrollPane exerciseWindow;
        private static JLabel greetings, currentProfileLabel, currentProfileValue, currentTrainingLabel, currentTrainingValue;
        private static JTextArea profileArea;
        
        private static int mainWindowWidth = 620, mainWindowHeight = 500;
        private static int margin = 15;
        private static int leftPanelWidth = 210, profilePanelHeight = 110, trainingAvailablePanelHeight = 160;
        private static int profilePanelYPos = 50, trainingAvailablePanelYPos = 189;
        private static int createProfileYPos = 140, selectProfileYPos = 110;
        private static int createTrainigYPos = 260, selectTrainigYPos = 210;
        private static int exerciseWidth = 350, exerciseHeight = 260, exerciseXPos = 250, exerciseYPos = 50, resulterPanelHeight = 40;
        private static int runningExerciseCount = 0;
        private static final int exNamePos = 0, exCountPos = 2, exTypePos = 1;
        private static String profileString, profileName, trainingString, trainingName, bufTrainingLine;
        private static boolean exerciseRunnung = true, seePanel2 = true, lastExercise = false, nextExerciseAvailable = false;
        private static boolean trainingManageAvailable = false, trainingStartAvailable = false, profileManageAvailable = false;
        private static boolean profileManagerWorking = false, trainingManagerWorking = false, trainingStarted = false;
                
        private ButtonListener buttonchik = new ButtonListener();
        private Dimension mainWindow = new Dimension(mainWindowWidth, mainWindowHeight);
        private Insets noMargin = new Insets(0,0,0,0);
        private LineBorder lineBorder = new LineBorder(Color.GRAY);
        
        private static File bufferProfileDir, bufferTrainingDir, currentStartedTraining;
        private static String[] trainreader, currentWorkingExerciseMass;
        private ArrayList<String> currentTrainingList;
        
        public MasterWindow() throws FileNotFoundException, IOException
        {
            super("SportApp");
            mainFrame = this;
            setSize(mainWindow);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
            setLayout(null);
            greetings = new JLabel("Сегодня отличный день для тренировки!");
            add(greetings);
            greetings.setLocation(15, 10);
            greetings.setSize(mainWindowWidth-30, 30);
            greetings.setHorizontalAlignment(JLabel.CENTER);
            if (profileName == null) {profileName = SportApp.BLANK_NAME; }
            if (trainingName == null) { trainingName = SportApp.BLANK_NAME; }
            profileString = "Выбранный профиль:";
            trainingString = "Выбранная тренировка:";
                   
// ===================================================================
//                        панель Профиля
// ===================================================================
           
            profilePanel = new JPanel();
            add(profilePanel);
            profilePanel.setLayout(null);
            profilePanel.setSize(leftPanelWidth, profilePanelHeight);
            profilePanel.setLocation(margin, profilePanelYPos);
            profilePanel.setBorder(lineBorder);
           
            currentProfileLabel = new JLabel(profileString);
            profilePanel.add(currentProfileLabel);
            currentProfileLabel.setSize((leftPanelWidth - 10), 20);
            currentProfileLabel.setLocation(5, 5);
            currentProfileLabel.setHorizontalAlignment(JLabel.CENTER);
           
            currentProfileValue = new JLabel(profileName);
            profilePanel.add(currentProfileValue);
            currentProfileValue.setSize((leftPanelWidth - 10), 30);
            currentProfileValue.setLocation(5, 25);
            currentProfileValue.setBorder(lineBorder);
            currentProfileValue.setHorizontalAlignment(JLabel.CENTER);

// ===================================================================
//                  Кнопки работы с профилями
// ===================================================================

            manageProfile = new JButton("Управление профилями");
            profilePanel.add(manageProfile);
            manageProfile.setSize(leftPanelWidth-10, 45);
            manageProfile.setLocation(5, 60);
            manageProfile.addActionListener(buttonchik);

// ===================================================================
//                 панель Выбора Тренировки
// ===================================================================
           
            trainignAvailablePanel = new JPanel();
            add(trainignAvailablePanel);
            trainignAvailablePanel.setLayout(null);
            trainignAvailablePanel.setSize(leftPanelWidth, trainingAvailablePanelHeight);
            trainignAvailablePanel.setLocation(margin, trainingAvailablePanelYPos);
            trainignAvailablePanel.setBorder(lineBorder);
           
            currentTrainingLabel = new JLabel(trainingString);
            trainignAvailablePanel.add(currentTrainingLabel);
            currentTrainingLabel.setSize((leftPanelWidth - 10), 20);
            currentTrainingLabel.setLocation(5, 5);
            currentTrainingLabel.setHorizontalAlignment(JLabel.CENTER);
           
            currentTrainingValue = new JLabel(trainingName);
            trainignAvailablePanel.add(currentTrainingValue);
            currentTrainingValue.setSize((leftPanelWidth - 10), 30);
            currentTrainingValue.setLocation(5, 25);
            currentTrainingValue.setBorder(lineBorder);
            currentTrainingValue.setHorizontalAlignment(JLabel.CENTER);
            
// ===================================================================
//                  Кнопки работы с тренировками
// ===================================================================
            
            manageTraining = new JButton("Управление тренировками");
            startTraining = new JButton("Начать тренировку");
            
            
            trainignAvailablePanel.add(manageTraining);
            manageTraining.setSize(leftPanelWidth-10, 40);
            manageTraining.setLocation(5, 60);
            manageTraining.addActionListener(buttonchik);
            manageTraining.setEnabled(trainingManageAvailable);

            trainignAvailablePanel.add(startTraining);
            startTraining.setSize(leftPanelWidth-10, 50);
            startTraining.setLocation(5, 105);
            startTraining.addActionListener(buttonchik);
            startTraining.setEnabled(trainingStartAvailable);

// ===================================================================
//                 панель текущего упражнения
// ===================================================================

            exerciseWindow = new JScrollPane();
            add(exerciseWindow);
            exerciseWindow.setSize(exerciseWidth, exerciseHeight);
            exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
            exerciseWindow.setBorder(lineBorder);
            
            
            resulterPanel = new JPanel();
            add(resulterPanel);
            resulterPanel.setSize(exerciseWidth, resulterPanelHeight);
            resulterPanel.setLocation(exerciseXPos, (exerciseYPos + exerciseHeight - 1));
            resulterPanel.setLayout(null);
            resulterPanel.setBorder(lineBorder);
            
            nextExercise = new JButton("Следующее упражнение");
            resulterPanel.add(nextExercise);
            nextExercise.setSize(166, 30);
            nextExercise.setLocation(5,5);
            nextExercise.setMargin(noMargin);
            nextExercise.setEnabled(nextExerciseAvailable);
            
            saveResult = new JButton("Сохранить результаты");
            resulterPanel.add(saveResult);
            saveResult.setSize(166, 30);
            saveResult.setLocation(177,5);
            saveResult.setMargin(noMargin);
            saveResult.setEnabled(lastExercise);
            
            showPanel1 = new JButton("Show panel1");
            add(showPanel1);
            showPanel1.setSize(130, 30);
            showPanel1.setLocation(130, 400);
            showPanel1.addActionListener(buttonchik);
            
            showPanel2 = new JButton("Show panel2");
            add(showPanel2);
            showPanel2.setSize(130, 30);
            showPanel2.setLocation(400, 400);
            showPanel2.addActionListener(buttonchik);
           
            clearPanel = new JButton("CLEAR");
            add(clearPanel);
            clearPanel.setSize(130, 30);
            clearPanel.setLocation(265, 400);
            clearPanel.addActionListener(buttonchik);
            
            
            File newTraining = new File("D:\\Prog\\NetBeans\\SportApp\\Profiles\\Мурзяндр\\Trainings\\001");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(newTraining))); 
//            FileReader reader = new FileReader(newTraining);
            String test = reader.readLine();
            trainreader = test.split(SportApp.EX_SEPARATOR);
            
        }
        
        protected static void countExercise()
        {
            runningExerciseCount++;
        }
        
        protected static File getTrainingDir()
        {
            return bufferTrainingDir;
        }
        
//========================================================================            
//                      Обработка событий
//========================================================================  
        
        public class ButtonListener implements ActionListener
        {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showPanel1)
            {
                if (exerciseWindow != null) 
                { 
                    mainFrame.remove(exerciseWindow);
                    exerciseWindow = null;
                }
                if (exercisePanel != null) 
                { 
                    mainFrame.remove(exercisePanel);
                    exercisePanel = null;
                }
                exercisePanel = new Exercise(false, trainreader[1], Integer.parseInt(trainreader[2]), 1);
//                exercisePanel = new Exercise("www", 3, true);
                exerciseWindow = new JScrollPane(exercisePanel);
                mainFrame.add(exerciseWindow);
                exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                exerciseWindow.setBorder(lineBorder);
                mainFrame.validate();
                mainFrame.repaint();
            }
            if (e.getSource() == showPanel2)
            {
                if (exerciseWindow != null) 
                { 
                    mainFrame.remove(exerciseWindow);
                    exerciseWindow = null;
                }
                if (exercisePanel != null)
                {
                    mainFrame.remove(exercisePanel);
                    exercisePanel = null;
                }
                exercisePanel = new Exercise(false, "Отжимания", 5, 2);
                exerciseWindow = new JScrollPane(exercisePanel);
                mainFrame.add(exerciseWindow);
                exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                exerciseWindow.setBorder(lineBorder);
                mainFrame.validate();
                mainFrame.repaint();
            }
            if (e.getSource() == clearPanel)
            {
                if (exerciseWindow != null)
                {
                    mainFrame.remove(exerciseWindow);
                    exerciseWindow = null;
                }
//                if (exercisePanel != null)
//                {
//                    mainFrame.remove(exercisePanel);
//                    exercisePanel = null;
//                }
                exerciseWindow = new JScrollPane();
                mainFrame.add(exerciseWindow);
                exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                exerciseWindow.setBorder(lineBorder);
                mainFrame.validate();
                mainFrame.repaint();
            }
//========================================================================            
//                      Управление профилями
//========================================================================

            if (e.getSource() == manageProfile)
            {
                profileManagerWorking = !profileManagerWorking;
//                currentProfileLabel.setText("НАЖАЛ");
                if (profileManagerWorking == true)
                {
//                    System.out.println("profManagerWorking: " + profileManagerWorking + "\n prof manager: " + profileManageAvailable + "\n train manager: " + trainingManageAvailable + "\n train starter: " + trainingStartAvailable);
                    profileManageAvailable = true;
                    trainingManageAvailable = false;
                    trainingStartAvailable = false;
                    manageProfile.setEnabled(profileManageAvailable);
                    manageTraining.setEnabled(trainingManageAvailable);
                    startTraining.setEnabled(trainingStartAvailable);
                    
                    manageProfile.setText("Выбрать текущий профиль");
                    
//                    System.out.println("profManagerWorking: " + profileManagerWorking + "\n prof manager: " + profileManageAvailable + "\n train manager: " + trainingManageAvailable + "\n train starter: " + trainingStartAvailable);
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    profileManagerPanel = new ProfileManager();
                    exerciseWindow = new JScrollPane(profileManagerPanel);
                    mainFrame.add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    exerciseWindow.setBorder(lineBorder);
                    mainFrame.validate();
                    mainFrame.repaint();
//                    System.out.println("profManagerWorking: " + profileManagerWorking + "\n prof manager: " + profileManageAvailable + "\n train manager: " + trainingManageAvailable + "\n train starter: " + trainingStartAvailable);
//                    currentProfileValue.setText("НАЖАЛ");
                }
                if (profileManagerWorking == false)
                {
//                    System.out.println("profManagerWorking: " + profileManagerWorking + "\n prof manager: " + profileManageAvailable + "\n train manager: " + trainingManageAvailable + "\n train starter: " + trainingStartAvailable);
                    profileManageAvailable = true;
                    trainingManageAvailable = false;
                    trainingStartAvailable = false;
                    manageProfile.setEnabled(profileManageAvailable);
                    manageTraining.setEnabled(trainingManageAvailable);
                    startTraining.setEnabled(trainingStartAvailable);
                    
                    manageProfile.setText("Управление профилями");
                    
                    currentProfileValue.setText(profileManagerPanel.getCurrentProfile());
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    profileManagerPanel = null;
                    mainFrame.validate();
                    mainFrame.repaint();
                    if ( !SportApp.BLANK_NAME.equals(currentProfileValue.getText()) ) 
                    {
                        trainingManageAvailable = true;
                        trainingStartAvailable = false;
//                        manageTraining.setEnabled(trainingManageAvailable);
//                        startTraining.setEnabled(trainingStartAvailable);
                        
                        bufferProfileDir = SportApp.getProfileDir();
                        bufferTrainingDir = new File((bufferProfileDir.toString()).concat(System.getProperty("file.separator")).concat(currentProfileValue.getText()).concat(System.getProperty("file.separator").concat("Trainings")));
                        if (!bufferTrainingDir.exists())
                        {
                            bufferTrainingDir.mkdirs();
                        }
                    }
                    else 
                    { 
                        trainingManageAvailable = false; 
                    } 
                    manageTraining.setEnabled(trainingManageAvailable); 
                    startTraining.setEnabled(trainingStartAvailable);
                    
                    exerciseWindow = new JScrollPane();
                    mainFrame.add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    exerciseWindow.setBorder(lineBorder);

                }
            }
//========================================================================            
//                      Управление тренировками
//========================================================================

            if (e.getSource() == manageTraining)
            {
                trainingManagerWorking = !trainingManagerWorking;
                if (trainingManagerWorking == true)
                {
                    profileManageAvailable = false;
                    trainingManageAvailable = true;
                    trainingStartAvailable = false;
                    manageProfile.setEnabled(profileManageAvailable);
                    manageTraining.setEnabled(trainingManageAvailable);
                    startTraining.setEnabled(trainingStartAvailable);

                    manageTraining.setText("Выбрать тренировку");
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    trainingManagerPanel = new TrainingManager();
                    exerciseWindow = new JScrollPane(trainingManagerPanel);
                    mainFrame.add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    exerciseWindow.setBorder(lineBorder);
                    mainFrame.validate();
                    mainFrame.repaint();
                }
                if (trainingManagerWorking == false)
                {
                    manageTraining.setText("Управление тренировками");
                    currentTrainingValue.setText(trainingManagerPanel.getCurrentTraining());
                    if (currentTrainingValue.getText().equals(SportApp.BLANK_NAME))
                    {
                        trainingStartAvailable = false;
                    }
                    else 
                    {
                        trainingStartAvailable = true;
                        runningExerciseCount = 0;
                        lastExercise = false;
                    }
                    
                    profileManageAvailable = false;
                    trainingManageAvailable = true;
                    manageProfile.setEnabled(trainingManageAvailable);
                    manageTraining.setEnabled(trainingManageAvailable);
                    startTraining.setEnabled(trainingStartAvailable);
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    trainingManagerPanel = null;
                    exerciseWindow = new JScrollPane();
                    mainFrame.add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    exerciseWindow.setBorder(lineBorder);
                    mainFrame.validate();
                    mainFrame.repaint();
                }
            }
//========================================================================            
//                          Сама тренировка
//========================================================================

            if (e.getSource() == startTraining)
            {
                trainingStarted = !trainingStarted;
                if (trainingStarted == true)
                {
                    runningExerciseCount = 0;
                    trainingManageAvailable = false;
                    trainingStartAvailable = false;
                    manageProfile.setEnabled(trainingManageAvailable);
                    manageTraining.setEnabled(trainingStartAvailable);
                    startTraining.setText("Прервать тренировку");
                    //Читаем файл
                    currentStartedTraining = new File(bufferTrainingDir.toString().concat(System.getProperty("file.separator")).concat(currentTrainingValue.getText()));
                    System.out.println(currentStartedTraining.toString());
                    //пишем содержимле файла в массив
                    try {
                        currentTrainingList = new ArrayList<String>();
                        BufferedReader trainReader = new BufferedReader(new InputStreamReader(new FileInputStream(currentStartedTraining)));
                        while ((bufTrainingLine = trainReader.readLine()) != null )
                        {
                            currentTrainingList.add(bufTrainingLine);
                        }
                        trainReader.close();
                    } 
                    catch (FileNotFoundException ex) {} 
                    catch (IOException ex) {}
                
                    if (currentTrainingList.size() == 1)
                    {
                        lastExercise = true;
                        saveResult.setEnabled(lastExercise);
                    }
                    else
                    {
                        nextExerciseAvailable = true;
                        nextExercise.setEnabled(nextExerciseAvailable);
                    }
//                lastExercise
                    
                    // пишем первую строку в массив упражнения
                    currentWorkingExerciseMass = (currentTrainingList.get(runningExerciseCount)).split(SportApp.EX_SEPARATOR);
                    if (exerciseWindow != null) 
                    { 
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    if (exercisePanel != null)
                    {
                        mainFrame.remove(exercisePanel);
                        exercisePanel = null;
                    }
                    if ( runningExerciseCount == currentTrainingList.size() )
                    {
                        lastExercise = true;
                    }
                    exercisePanel = new Exercise(lastExercise, currentWorkingExerciseMass[exNamePos], Integer.parseInt(currentWorkingExerciseMass[exCountPos]), Integer.parseInt(currentWorkingExerciseMass[exTypePos]));
                    exerciseWindow = new JScrollPane(exercisePanel);
                    mainFrame.add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    mainFrame.validate();
                    mainFrame.repaint();
                    }
                
                if (trainingStarted == false)
                {
                    trainingManageAvailable = true;
                    trainingStartAvailable = true;
                    manageProfile.setEnabled(trainingManageAvailable);
                    manageTraining.setEnabled(trainingStartAvailable);
                    startTraining.setText("Начать тренировку");
                }
            }
        }
    }
}