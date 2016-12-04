/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class Exercise extends JPanel {
    private static int height, width, marginTop = 15, marginBottom = 20, marginLR = 20, sizex = 70, sizey = 30, spacex = 15, spacey = 5, textSizeX = 110;
    private static ArrayList<JLabel> trainFields = new ArrayList<>();
    private static HashMap<String, JTextField> weightMap = new HashMap<>();
    private static HashMap<String, JTextField> countMap = new HashMap<>();
    private static JPanel panel;
    private static JButton save;
    private static JLabel weightLabel, countLabel, trainLabel;
    private static String approachResult = "";
    private static Date today = new Date();
    private static SimpleDateFormat makeDate = new SimpleDateFormat("yyyy-MM-dd");
    private static String currDate = makeDate.format(today);
    private static boolean saved;
    
    public Exercise(int number, String name)
    {
//        super(name);
        panel = this;
        saved = false;
        height = marginTop + (sizey + spacey) * ( number + 2 ) + marginBottom;
        width = marginLR + textSizeX + spacex + sizex * 2 + spacex + marginLR + 5;
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        
        createFields(number, name);
    }
    
    public Exercise(int number, String name, int defaultWeight, int defaultCount)
    {
        this(number, name);
        for ( int i = 0; i < number; i++)
        {
            weightMap.get("weightField".concat(String.valueOf(i))).setText(String.valueOf(defaultWeight));
            countMap.get("countField".concat(String.valueOf(i))).setText(String.valueOf(defaultCount));
        }
    }
    
    private static void createFields(int number, String name)
    {
        Border lightBorder = new LineBorder(Color.GRAY);
        Border darkBorder = new LineBorder(Color.DARK_GRAY);
        Insets inMargin = new Insets(3,3,3,3);
        
        int weightXPos, countXPos, YPos, textXPos;
        textXPos = marginLR;
        weightXPos = marginLR + textSizeX + spacex;
        countXPos = marginLR + textSizeX + spacex + sizex + spacex;
        YPos = marginTop;
        
        trainLabel = new JLabel(name);
        weightLabel = new JLabel("Вес");
        countLabel = new JLabel("Повторов");
        
        panel.add(trainLabel);
        trainLabel.setSize(textSizeX, sizey);
        trainLabel.setLocation(textXPos, YPos-5);
        trainLabel.setHorizontalAlignment(JTextField.CENTER);
        
        
        panel.add(weightLabel);
        weightLabel.setSize(sizex, sizey);
        weightLabel.setLocation(weightXPos, YPos-5);
        weightLabel.setHorizontalAlignment(JTextField.CENTER);
        weightLabel.setBorder(lightBorder);
        
        panel.add(countLabel);
        countLabel.setSize(sizex, sizey);
        countLabel.setLocation(countXPos, YPos-5);
        countLabel.setHorizontalAlignment(JTextField.CENTER);
        countLabel.setBorder(lightBorder);
        
        YPos += spacey + sizey;
        
        for (int i = 0; i < number; i++)
        {
            weightMap.put("weightField".concat(String.valueOf(i)), new JTextField());
            countMap.put("countField".concat(String.valueOf(i)), new JTextField());
            
            trainFields.add(i, new JLabel("Подход №".concat(String.valueOf(i + 1)), JLabel.CENTER));
            
            panel.add(trainFields.get(i));
            trainFields.get(i).setSize(textSizeX, sizey);
            trainFields.get(i).setLocation(textXPos, YPos);
            
            panel.add(weightMap.get("weightField".concat(String.valueOf(i))));
            weightMap.get("weightField".concat(String.valueOf(i))).setSize(sizex, sizey);
            weightMap.get("weightField".concat(String.valueOf(i))).setLocation(weightXPos, YPos);
            weightMap.get("weightField".concat(String.valueOf(i))).setHorizontalAlignment(JTextField.CENTER);
            weightMap.get("weightField".concat(String.valueOf(i))).setBorder(darkBorder);
            weightMap.get("weightField".concat(String.valueOf(i))).setMargin(inMargin);
            
            panel.add(countMap.get("countField".concat(String.valueOf(i))));
            countMap.get("countField".concat(String.valueOf(i))).setSize(sizex, sizey);
            countMap.get("countField".concat(String.valueOf(i))).setLocation(countXPos, YPos);
            countMap.get("countField".concat(String.valueOf(i))).setHorizontalAlignment(JTextField.CENTER);
            countMap.get("countField".concat(String.valueOf(i))).setBorder(darkBorder);
            countMap.get("countField".concat(String.valueOf(i))).setMargin(inMargin);
            
            YPos += spacey + sizey;
        }
        
        save = new JButton("Сохранить результаты");
        panel.add(save);
        save.setSize((2 * sizex + spacex), sizey);
        save.setLocation(weightXPos, YPos+5);
        save.setMargin(inMargin);   
        save.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) 
            {
                int countGoodFields = 0;
                for (int i = 0; i < number; i++)
                {
                    if (!"".equals(weightMap.get("weightField".concat(String.valueOf(i))).getText())) countGoodFields++;
                    if (!"".equals(countMap.get("countField".concat(String.valueOf(i))).getText())) countGoodFields++;
                }
                if (countGoodFields == (2*number))
                {
                    getResult(number, name);
                    if (!saved) {
                        JOptionPane.showMessageDialog(null, approachResult);
                        saved = true;
                    } else { JOptionPane.showMessageDialog(null, "Данные уже сохранены"); }
                }
                else { JOptionPane.showMessageDialog(null, "Заполните все поля"); }
            }
        });
    }

    private static void getResult(int number, String name) {
        StringBuilder appRes = new StringBuilder(currDate);
        appRes.append(" ");
        appRes.append(name);
        appRes.append(" ");
        appRes.append(number);
        appRes.append(" ");
        for (int i = 0; i < number; i++)
        {
            appRes.append(weightMap.get("weightField".concat(String.valueOf(i))).getText());
            appRes.append(" ");
        }
        for (int i = 0; i < number; i++)
        {
            appRes.append(countMap.get("countField".concat(String.valueOf(i))).getText());
            appRes.append(" ");
        }
        approachResult = String.valueOf(appRes);
    }
}
