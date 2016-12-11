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
import java.io.File;

/**
 *
 * @author VMS
 */
public class MasterWindow extends JFrame {
        private static JFrame mainFrame;
        private static ProfileManager profileManagerPanel;
        private static TrainingManager trainingManagerPanel;
        private static JPanel masterPanel, profilePanel, trainingPanel, trainignAvailablePanel, exercisePanel;
        private static JButton showPanel1, showPanel2, clearPanel, manageTraining, manageProfile, createProfile, selectProfile, createTraining, selectTraining, startTraining;
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
        private static int exerciseWidth = 350, exerciseHeight = 300, exerciseXPos = 250, exerciseYPos = 50;
       
        private ButtonListener buttonchik = new ButtonListener();
        private Dimension mainWindow = new Dimension(mainWindowWidth, mainWindowHeight);
        private static boolean trainingAvailable = false, seePanel2 = true, profileManagerWorking = false, trainingManagerWorking = false, trainingStarted = false;
        private String profileString, profileName, trainingString, trainingName;
        private Insets noMargin = new Insets(0,0,0,0);
        private LineBorder lineBorder = new LineBorder(Color.GRAY);
        private File bufferProfileDir, bufferTrainingDir;
        
        public MasterWindow()
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
            if (profileName == null) {profileName = "не выбрано"; }
            if (trainingName == null) { trainingName = "не выбрано"; }
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
            manageTraining.setEnabled(trainingAvailable);

            trainignAvailablePanel.add(startTraining);
            startTraining.setSize(leftPanelWidth-10, 50);
            startTraining.setLocation(5, 105);
            startTraining.addActionListener(buttonchik);
            startTraining.setEnabled(trainingAvailable);

// ===================================================================
//                 панель текущего упражнения
// ===================================================================

            exerciseWindow = new JScrollPane();
            add(exerciseWindow);
            exerciseWindow.setSize(exerciseWidth, exerciseHeight);
            exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
            
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
                exercisePanel = new Exercise(5, "Присед");
                exerciseWindow = new JScrollPane(exercisePanel);
                mainFrame.add(exerciseWindow);
                exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
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
                exercisePanel = new Exercise(1, "Присед",10,8);
                exerciseWindow = new JScrollPane(exercisePanel);
                mainFrame.add(exerciseWindow);
                exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
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
                    manageTraining.setEnabled(false);
                    startTraining.setEnabled(false);
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
                    mainFrame.validate();
                    mainFrame.repaint();
//                    currentProfileValue.setText("НАЖАЛ");
                }
                if (profileManagerWorking == false)
                {
                    manageTraining.setEnabled(true);
                    startTraining.setEnabled(true);
                    
                    currentProfileValue.setText(profileManagerPanel.getCurrentProfile());
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    profileManagerPanel = null;
                    mainFrame.validate();
                    mainFrame.repaint();
                    if ( !"не выбрано".equals(currentProfileValue.getText()) ) 
                    {
                        trainingAvailable = true;
                        bufferProfileDir = SportApp.getProfileDir();
                        bufferTrainingDir = new File((bufferProfileDir.toString()).concat(System.getProperty("file.separator")).concat(currentProfileValue.getText()).concat(System.getProperty("file.separator").concat("Trainings")));
                        if (!bufferTrainingDir.exists())
                        {
                            bufferTrainingDir.mkdirs();
                        }
                    }
                    manageTraining.setEnabled(trainingAvailable);
                    startTraining.setEnabled(trainingAvailable);
                    
//                    currentProfileValue.setText("ОТЖАЛ");
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
                    manageProfile.setEnabled(false);
                    startTraining.setEnabled(false);
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
                    mainFrame.validate();
                    mainFrame.repaint();
                }
                if (trainingManagerWorking == false)
                {
                    manageProfile.setEnabled(true);
                    startTraining.setEnabled(true);
                    currentTrainingValue.setText(trainingManagerPanel.getCurrentTraining());
                    if (exerciseWindow != null)
                    {
                        mainFrame.remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    trainingManagerPanel = null;
                    mainFrame.validate();
                    mainFrame.repaint();
                }
            }
            // Сама тренировка
            if (e.getSource() == startTraining)
            {
                trainingStarted = !trainingStarted;
                if (trainingStarted == true)
            {
                manageProfile.setEnabled(false);
                manageTraining.setEnabled(false);
                currentProfileValue.setText("НАЖАЛ");
                currentTrainingValue.setText("НАЖАЛ");
            }
            if (trainingStarted == false)
            {
                manageProfile.setEnabled(true);
                manageTraining.setEnabled(true);
                currentProfileValue.setText("ОТЖАЛ");
                currentTrainingValue.setText("ОТЖАЛ");
            }
        }
    }
}

}
