/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class TrainingManager extends JPanel{
    private static JButton createTraining, selectTraining, deleteTraining, backToMainMenu, cancelAndBack;
    private static JLabel selectedTraining;
    private static JPanel profileTraining;
    private static int margin = 15, componentHeight = 30, componentWidth = 200, width = 200, height = 250, paddingLeft = 60, paddingTop = 40;
    private static LineBorder lineBorder = new LineBorder(Color.DARK_GRAY);
    
    protected TrainingManager()
    {
        super();
        profileTraining = this;
        profileTraining.setLayout(null);
        profileTraining.setPreferredSize(new Dimension(width,height));
        
        selectedTraining = new JLabel("Kurva training");
        profileTraining.add(selectedTraining);
        selectedTraining.setSize(componentWidth, componentHeight);
        selectedTraining.setLocation(paddingLeft + margin, paddingTop + margin);
        selectedTraining.setBorder(lineBorder);
        selectedTraining.setHorizontalAlignment(JLabel.CENTER);
        
        selectTraining = new JButton("Выбрать тренировку");
        profileTraining.add(selectTraining);
        selectTraining.setSize(componentWidth, componentHeight);
        selectTraining.setLocation(paddingLeft + margin, paddingTop + margin*2 + componentHeight);
        selectTraining.setBorder(lineBorder);
        selectTraining.setHorizontalAlignment(JButton.CENTER);
        
        createTraining = new JButton("Создать тренировку");
        profileTraining.add(createTraining);
        createTraining.setSize(componentWidth, componentHeight);
        createTraining.setLocation(paddingLeft + margin, paddingTop + margin*3 + componentHeight*2);
        createTraining.setBorder(lineBorder);
        createTraining.setHorizontalAlignment(JButton.CENTER);
        
        deleteTraining = new JButton("Удалить тренировку");
        profileTraining.add(deleteTraining);
        deleteTraining.setSize(componentWidth, componentHeight);
        deleteTraining.setLocation(paddingLeft + margin, paddingTop + margin*4 + componentHeight*3);
        deleteTraining.setBorder(lineBorder);
        deleteTraining.setHorizontalAlignment(JButton.CENTER);
    }
    
    protected static String getCurrentTraining()
    {
        return selectedTraining.getText();
    }
    
}
