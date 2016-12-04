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
       private JButton showPanel1, showPanel2, newProfile, selectProfile, createTraining, selectTraining;
       private JComboBox selectProfileBox;
       private JScrollPane exerciseWindow;
       private JLabel greetings, currentProfileLabel, currentProfileValue;
       private JTextArea profileArea;
       private int mainWindowWidth = 650, mainWindowHeight = 600;
       private int margin = 15;
       private int leftPanelWidth = 200, trainingAvailablePanelHeight = 80, profilePanelHeight = 55;
       private int trainingAvailablePanelYPos = 200, profilePanelYPos = 50;
       private int newProfileYPos = 140, selectProfileYPos = 110;
       private int exerciseWidth = 350, exerciseHeight = 300, exerciseXPos = 250, exerciseYPos = 50;
       
       private Dimension mainWindow = new Dimension(mainWindowWidth, mainWindowHeight);
       private static boolean seePanel1 = true, seePanel2 = true;
       private String profileString, profileName;
       private Insets noMargin = new Insets(0,0,0,0);
       private LineBorder lineBorder = new LineBorder(Color.GRAY);
       
       public MasterWindow()
       {
           super("SportApp");
           this.setSize(mainWindow);
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.setResizable(false);
           this.setLocationRelativeTo(null);
           this.setLayout(null);
           greetings = new JLabel("Сегодня отличный день для тренировки!");
           this.add(greetings);
           greetings.setLocation(15, 0);
           greetings.setSize(mainWindowWidth-30, 30);
           greetings.setHorizontalAlignment(JLabel.CENTER);
           if (profileName == null) {profileName = "не выбрано"; }
          
           profileString = "Выбранный профиль: ";
           
// ===================================================================
//                        панель Профиля
// ===================================================================
           
           profilePanel = new JPanel();
           this.add(profilePanel);
           profilePanel.setLayout(null);
           profilePanel.setSize(leftPanelWidth, profilePanelHeight);
           profilePanel.setLocation(margin, profilePanelYPos);
           profilePanel.setBorder(lineBorder);
           
           currentProfileLabel = new JLabel(profileString);
           profilePanel.add(currentProfileLabel);
           currentProfileLabel.setSize((leftPanelWidth - 10), 20);
           currentProfileLabel.setLocation(5, 5);
//           currentProfileLabel.setBorder(lineBorder);
           currentProfileLabel.setHorizontalAlignment(JLabel.CENTER);
           
           currentProfileValue = new JLabel(profileName);
           profilePanel.add(currentProfileValue);
           currentProfileValue.setSize((leftPanelWidth - 10), 25);
           currentProfileValue.setLocation(5, 25);
           currentProfileValue.setBorder(lineBorder);
           currentProfileValue.setHorizontalAlignment(JLabel.CENTER);

// ===================================================================
//                  Кнопки работы с профилями
// ===================================================================
            selectProfile = new JButton("Выбрать профиль");
            newProfile = new JButton("Создать профиль");
            
            this.add(selectProfile);
            selectProfile.setSize(leftPanelWidth, 25);
            selectProfile.setLocation(margin, selectProfileYPos);
            
            this.add(newProfile);
            newProfile.setSize(leftPanelWidth, 25);
            newProfile.setLocation(margin, newProfileYPos);

// ===================================================================
//                 панель Выбора Тренировки
// ===================================================================
           
            trainignAvailablePanel = new JPanel();
            this.add(trainignAvailablePanel);
            trainignAvailablePanel.setLayout(null);
            trainignAvailablePanel.setSize(leftPanelWidth, trainingAvailablePanelHeight);
            trainignAvailablePanel.setLocation(margin, trainingAvailablePanelYPos);
            trainignAvailablePanel.setBorder(lineBorder);
           
           
// ===================================================================
//                 панель Выбора Тренировки
// ===================================================================
            exercisePanel = new Exercise(5, "Присед");
            exerciseWindow = new JScrollPane(exercisePanel);
           
            this.add(exerciseWindow);
            exerciseWindow.setSize(exerciseWidth, exerciseHeight);
            exerciseWindow.setLocation(exerciseXPos, exerciseYPos);
            exerciseWindow.createVerticalScrollBar();
            
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
//            showPanel1 = new JButton("Show panel1");
//            this.add(showPanel1);
//            showPanel1.setSize(130, 30);
//            showPanel1.setLocation(135, 60);
//            showPanel1.addActionListener(new ActionListener()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                   seePanel1 = !seePanel1;
//                   test1.setVisible(seePanel1);
//               }
//           });
//           
//           showPanel2 = new JButton("Show panel2");
//           this.add(showPanel2);
//           showPanel2.setSize(130, 30);
//           showPanel2.setLocation(505, 60);
//           showPanel2.addActionListener(new ActionListener()
//           {
//               @Override
//               public void actionPerformed(ActionEvent e) {
//                   seePanel2 = !seePanel2;
//                   test2.setVisible(seePanel2);
//               }
//           });
       }
}
