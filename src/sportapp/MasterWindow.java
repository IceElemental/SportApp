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

/**
 *
 * @author VMS
 */
public class MasterWindow extends JFrame {
        private JPanel masterPanel, profilePanel, trainingPanel, trainignAvailablePanel, exercisePanel;
        private JButton showPanel1, showPanel2, clearPanel, manageTraining, manageProfile, createProfile, selectProfile, createTraining, selectTraining, startTraining;
        private JComboBox selectProfileBox;
        private JScrollPane exerciseWindow;
        private JLabel greetings, currentProfileLabel, currentProfileValue, currentTrainingLabel, currentTrainingValue;
        private JTextArea profileArea;
        private int mainWindowWidth = 620, mainWindowHeight = 600;
        private int margin = 15;
        private int leftPanelWidth = 210, profilePanelHeight = 110, trainingAvailablePanelHeight = 160;
        private int profilePanelYPos = 50, trainingAvailablePanelYPos = 189;
        private int createProfileYPos = 140, selectProfileYPos = 110;
        private int createTrainigYPos = 260, selectTrainigYPos = 210;
        private int exerciseWidth = 350, exerciseHeight = 300, exerciseXPos = 250, exerciseYPos = 50;
       
        private Dimension mainWindow = new Dimension(mainWindowWidth, mainWindowHeight);
        private static boolean seePanel1 = true, seePanel2 = true;
        private String profileString, profileName, trainingString, trainingName;
        private Insets noMargin = new Insets(0,0,0,0);
        private LineBorder lineBorder = new LineBorder(Color.GRAY);
       
        public MasterWindow()
        {
            super("SportApp");
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
//            currentProfileLabel.setBorder(lineBorder);
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
            manageProfile = new JButton("Управление профилянми");
            profilePanel.add(manageProfile);
            manageProfile.setSize(leftPanelWidth-10, 45);
            manageProfile.setLocation(5, 60);

//            selectProfile = new JButton("Выбрать профиль");
//            createProfile = new JButton("Создать профиль");
            
//            profilePanel.add(selectProfile);
//            selectProfile.setSize(leftPanelWidth-10, 25);
////            selectProfile.setLocation(margin, selectProfileYPos);
//            selectProfile.setLocation(5, 60);
            
//            profilePanel.add(selectProfile);
//            selectProfile.setSize(leftPanelWidth-10, 25);
////            selectProfile.setLocation(margin, selectProfileYPos);
//            selectProfile.setLocation(5, 60);
//            
//            profilePanel.add(createProfile);
//            createProfile.setSize(leftPanelWidth-10, 25);
////            createProfile.setLocation(margin, createProfileYPos);
//            createProfile.setLocation(5, 90);

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
//           currentProfileLabel.setBorder(lineBorder);
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
//            selectTraining = new JButton("Выбрать тренировку");
//            createTraining = new JButton("Создать тренировку");
            startTraining = new JButton("Начать тренировку");
            
            
            trainignAvailablePanel.add(manageTraining);
            manageTraining.setSize(leftPanelWidth-10, 40);
            manageTraining.setLocation(5, 60);
//            trainignAvailablePanel.add(selectTraining);
//            selectTraining.setSize(leftPanelWidth-10, 25);
//            selectTraining.setLocation(5, 60);
//            
//            trainignAvailablePanel.add(createTraining);
//            createTraining.setSize(leftPanelWidth-10, 25);
//            createTraining.setLocation(5, 90);
            
            trainignAvailablePanel.add(startTraining);
            startTraining.setSize(leftPanelWidth-10, 50);
            startTraining.setLocation(5, 105);

// ===================================================================
//                 панель текущего упражнения
// ===================================================================
//            exercisePanel = new Exercise(5, "Присед",30,8);
            exerciseWindow = new JScrollPane();
            add(exerciseWindow);
            exerciseWindow.setSize(exerciseWidth, exerciseHeight);
            exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
            
//            String test = String.valueOf(exercisePanel.getHeight()) + "  " + String.valueOf(exerciseWindow.getHeight());
//            currentProfileValue.setText(System.getProperty("user.home"));
//            JPanel test1 = new TestPanel("Панель 1");
//            this.add(test1);
//            test1.setLocation(50,120);
//            test1.setVisible(seePanel1);
//                      
//            JPanel test2 = new TestPanel("Панель 2");
//            this.add(test2);
//            test2.setLocation(420,120);
//            test2.setVisible(seePanel2);
//           
            showPanel1 = new JButton("Show panel1");
            add(showPanel1);
            showPanel1.setSize(130, 30);
            showPanel1.setLocation(130, 400);
            showPanel1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (exerciseWindow != null) 
                    { 
                        remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    if (exercisePanel != null) 
                    { 
                        remove(exercisePanel);
                        exercisePanel = null;
                    }
                    exercisePanel = new Exercise(5, "Присед");
                    exerciseWindow = new JScrollPane(exercisePanel);
                    
                    add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    validate();
                    repaint();
                }
           });
//           
           showPanel2 = new JButton("Show panel2");
           add(showPanel2);
           showPanel2.setSize(130, 30);
           showPanel2.setLocation(400, 400);
           showPanel2.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (exerciseWindow != null) 
                    { 
                        remove(exerciseWindow);
                        exerciseWindow = null;
                    }
                    if (exercisePanel != null) 
                    { 
                        remove(exercisePanel);
                        exercisePanel = null;
                    }

                    exercisePanel = new Exercise(5, "Присед",10,8);
                    exerciseWindow = new JScrollPane(exercisePanel);
                    add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    validate();
                    repaint();
                }
           });
           
           clearPanel = new JButton("CLEAR");
           add(clearPanel);
           clearPanel.setSize(130, 30);
           clearPanel.setLocation(265, 400);
           clearPanel.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (exerciseWindow != null) 
                    { 
                        remove(exerciseWindow);
                        exerciseWindow = null;
                    }

                    if (exercisePanel != null) 
                    { 
                        remove(exercisePanel);
                        exercisePanel = null;
                    }
//
//                    exercisePanel = new Exercise(5, "Присед",10,8);
                    exerciseWindow = new JScrollPane();
                    add(exerciseWindow);
                    exerciseWindow.setSize(exerciseWidth, exerciseHeight);
                    exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
                    
                    validate();
                    repaint();
                }
           });
       }
}
